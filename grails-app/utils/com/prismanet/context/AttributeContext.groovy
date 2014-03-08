package com.prismanet.context

import grails.orm.HibernateCriteriaBuilder;

import org.hibernate.Criteria
import org.hibernate.sql.JoinFragment

import com.prismanet.Concept;

class MalformedParameterException extends RuntimeException {
	String message
	Concept concept
}

class AttributeContext {
	
	/**
	 * Relaciones entre Atributos del Cliente y Atributos de las Entidades
	 * Persistentes
	 */
	private HashMap<String, AttributeRelationData> propertyRelations
	
	/**
	 * Relaciones de EntityPath con Alias Definidos
	 */
	private HashMap<String,String> aliasEntityPathDefined
	
	private HashMap<String, Integer> aliasCreated
	
	/**
	 * Lista de Clases Componentes que pertenecen al contexto.
	 */
	private ArrayList<String> componentClassDefined;
	
	AttributeContext() {
		propertyRelations = new HashMap<String, AttributeRelationData>()
		aliasCreated = new HashMap<String, Integer>()
		aliasEntityPathDefined = new HashMap<String,String>()
		componentClassDefined = new ArrayList<String>()
		this.initializeRelations()
	}
	
	
	/**
	 * Este metodo puede ser sobrecargado para proveer agregado de relaciones
	 * inline.<br>
	 */
	protected void initializeRelations() {

	}
	
	
	/**
	 * Devuelve el nombre de la propiedad del Atributo en el contexto de la
	 * entidad persistente
	 */
	String getAttributePropertyName(String attribute) {
		AttributeRelationData data = this.propertyRelations.get(attribute);
		if (data == null)
			throw new MalformedParameterException(message:"El Atributo: " + attribute
					+ " no esta definido en el contexto"
					+ System.getProperty("line.separator")
					+ "Contexto Origen: " + this.getClass());
		return data.propertyName;
	}
	
	
	/**
	 * <p>
	 * Agrega una relacion entre un Atributo y el nombre de la
	 * propiedad que le corresponde en el contexto de la entidad persistente
	 * Este metodo es el que se debe utilizar para agregar las relaciones
	 * correspondientes a las subclases de AttributeContext.
	 *
	 * @param attr
	 *            Atributo
	 * @param property
	 *            Nombre de la Propiedad en el Contexto. Si la propiedad esta en
	 *            una clase componente se debe incluir el nombre de clase y el
	 *            nombre de la propiedad (Ej: partID.Type)
	 * @param descProperty
	 *            Nombre de la Propiedad que describe property
	 * @param entityName
	 *            Nombre de la Entidad persistible (tabla) a la que pertenece la
	 *            propiedad. Si es null se asume que la propiedad pertenece a la
	 *            entidad raiz
	 */
	void addPropertyRelation(String attr, String property, String descProperty, String entityName) {
		this.propertyRelations.put(attr, new AttributeRelationData(property, descProperty, entityName))
	}
	
	/**
	 * Devuelve true si el atributo esta definido en el contexto
	 * @param attr
	 * @return
	 */
	boolean hasPropertyRelationForAttribute(String attr){
		return this.propertyRelations.containsKey(attr)
	}
	
	
	String getDescriptionPropertyNameFor(String descriptionName) {
		String result;
		AttributeRelationData data = this.propertyRelations.get(descriptionName)
		if (data == null)
			throw new MalformedParameterException(message:"El Atributo:" + descriptionName + " no esta definido en el contexto: "+ this.getClass())
		result = data.descPropertyName
		if (result == null)
			throw new MalformedParameterException(message:"El Atributo:" + descriptionName + " no tiene descripcion definida en el contexto: "+ this.getClass())
		return result
	}
	
	String getEntityNameFor(String attributeName)  {
		String result
		AttributeRelationData data = this.propertyRelations.get(attributeName)
		if (data == null)
			throw new MalformedParameterException(message:"El Atributo:" + attributeName + " no esta definido en el contexto: "+ this.getClass())
		result = data.entityName
		return result
	}
	
	/**
	 * Agrega el nombre de una Clase Componente a la lista en el contexto.
	 * <p>
	 * Nota: Las clases componentes se diferencian de las persistentes en que
	 * dichas clases no persisten por si mismas sino dentro de otra clase
	 * persistente. La discriminacion se realiza para que el Context no genere
	 * alias para el acceso a este tipo de clases
	 *
	 * @param name
	 *            Nombre de la Clase Componente a agregar
	 */
	protected void addContextComponentClassName(String name) {
		if (!this.componentClassDefined.contains(name))
			this.componentClassDefined.add(name)
	}
	
	/**
	 * Devuelve verdadero si en name corresponde a una Clase Componente en el
	 * Contexto
	 *
	 * @param name
	 *            Nombre a verificar
	 * @return
	 */
	protected boolean isComponentClass(String name) {
		return this.componentClassDefined.contains(name)
	}
	
	
	protected boolean isAliasEntityPathDefined(String alias) {
		return this.aliasEntityPathDefined.containsKey(alias)
	}
	
	protected String getAliasNameForEntityPath(String entityPath){
		String alias = aliasEntityPathDefined.get(entityPath)
		return alias
	}
	
	/**
	 * Limpia la lista de alias Definidos
	 */
	public void clearDefinedAlias() {
		this.aliasEntityPathDefined.clear();
	}

	
	/**
	 * Crea un alias para un nombre de Atributo determinado
	 */
	public void createAliasFor(HibernateCriteriaBuilder criteria, String attr) throws MalformedParameterException {
		String entityName;
		boolean optional;
		AttributeRelationData data = this.propertyRelations.get(attr);
		if (data == null)
			throw new MalformedParameterException(message:"El Atributo:" + attr + " no esta definido en el contexto: "+ this.getClass());
		entityName = data.entityName;
		optional = data.optional;
		if (entityName == null)
			throw new MalformedParameterException(message:"El Atributo:" + attr + " no tiene Entidad Definida: "+ this.getClass());
		int dotPos = entityName.indexOf(".");
		// Si viene separado por puntos tengo que generar varios alias
		if (dotPos != -1) {
			String[] entities = entityName.split("\\.");
			String aliasName;
			String lastAlias = "";
			String entityPath;
			Integer level = new Integer(0);
			if (!(entities.length > 0))
				throw new MalformedParameterException(message:"El Atributo:" + attr + " tiene mal definido el nombre de la entidad:" + entityName + ":" + this.getClass());
			// Para cada entidad encontrada
			for (String e : entities) {
				if (!(level == 0)) {
					if (!this.isComponentClass(e)) {
						// Si no es Component Class
						aliasName = e + "_" + level.toString();
						entityPath = lastAlias + "." + e;
						if (!this.isAliasEntityPathDefined(entityPath))
							aliasName = this.defineAlias(criteria, entityPath, aliasName, optional);
						else
							aliasName = getAliasNameForEntityPath(entityPath);
						lastAlias = aliasName;
					} else {
						lastAlias = lastAlias + "." + e;
					}
				} else {
					if (!this.isComponentClass(e)) {
						// Si no es Component Class
						aliasName = e + "_0";
						if (!this.isAliasEntityPathDefined(e))
							aliasName = this.defineAlias(criteria, e, e + "_0", optional);
						else
							aliasName = getAliasNameForEntityPath(e);
						lastAlias =aliasName;
					} else {
						lastAlias = e;
					}
				}
				level++;
			}
			// En lastAlias quedo el alias que hay que usar en los fitros del
			// atributo
			data.setAliasCreated(true);
			data.setAliasName(lastAlias);
		} else {
			// Hay una sola Entidad, genero un unico alias
			String aliasName = entityName + "_0";
			if (!this.isAliasEntityPathDefined(entityName)) {
				aliasName = this.defineAlias(criteria, entityName, aliasName, optional);
			}else
				aliasName = getAliasNameForEntityPath(entityName);
			data.setAliasCreated(true);
			data.setAliasName(aliasName);
		}
	}
	
	/**
	 * Crea un alias y devuelve el nombre del alias creado tomando como base en alias pasado como parametro y
	 * determinando si debe cambiar su nombre para evitar colisiones de alias.
	 * @param criteria Criteria al cual se le agregara un alias
	 * @param entityPath EntityPath que referencia a dicho alias
	 * @param alias Alias a crear
	 * @param optional Indica si es inner o outer join
	 */
	protected String defineAlias(HibernateCriteriaBuilder criteria, String entityPath, String alias, boolean optional) {
		String newAlias;
		Integer nextAliasOrder;
		if(!aliasCreated.containsKey(alias)){
			newAlias = alias + "_1";
			aliasCreated.put(alias, 1);
		}else{
			nextAliasOrder = aliasCreated.get(alias) + 1;
			aliasCreated.put(alias, nextAliasOrder);
			newAlias = alias + "_" + nextAliasOrder;
		}
		
		if (optional) {
			criteria.createAlias(entityPath, newAlias, JoinFragment.LEFT_OUTER_JOIN);
		} else {
			criteria.createAlias(entityPath, newAlias);
		}
		this.aliasEntityPathDefined.put(entityPath,newAlias);
		return newAlias;
	}
	
	/**
	 * Devuelve el Alias para un nombre de Atributo Determinado
	 */
	public String getAliasNameForProperty(String attr) throws MalformedParameterException {
		AttributeRelationData data = this.propertyRelations.get(attr);
		if (data == null)
			throw new MalformedParameterException(message:"El Atributo:" + attr + " no esta definido en el contexto: "+ this.getClass());
		String aliasName = data.aliasName;
		if (!data.isAliasCreated())
			throw new MalformedParameterException(message:"Al Atributo:" + attr + " no se le creo el alias aun");
		if (aliasName == null)
			throw new MalformedParameterException(message:"El Atributo:" + attr + " no tiene alias definido en el contexto: "+ this.getClass());
		return aliasName;
	}


	
	
	
	class AttributeRelationData {
		String propertyName
		String descPropertyName
		String entityName
		String aliasName
		boolean isAliasCreated
		boolean optional

		AttributeRelationData(String propertyName, String descPropertyName, String entityName){
			this.propertyName = propertyName
			this.descPropertyName = descPropertyName
			this.entityName = entityName
			isAliasCreated = false
			optional = false
		}

		AttributeRelationData(String propertyName, String descPropertyName, String entityName, boolean optional){
			this(propertyName, descPropertyName, entityName)
			this.optional = optional
		}

		protected void setAliasName(String aliasName) {
			this.aliasName = aliasName;
		}

		protected boolean isAliasCreated() {
			return isAliasCreated;
		}

		protected void setAliasCreated(boolean isAliasCreated) {
			this.isAliasCreated = isAliasCreated;
		}
	}


}
