package com.prismanet

import com.prismanet.context.AttributeContext
import grails.orm.HibernateCriteriaBuilder



class GenericService {
	
	protected AttributeContext context
	
	GenericService(){
	}
	
	def categoriesService(HibernateCriteriaBuilder criteria, def groups, def filters, def projection){
		def resultList = criteria {
			
			context.clearDefinedAlias()
			and{	
				filters.each {
					if (it.type)
						switch (it.type) {
							case FilterType.EQ:
								eq(getPropertyName(criteria, it.attribute), it.value)
							break
							case FilterType.GT:
								gt(getPropertyName(criteria, it.attribute), it.value)
							break
							case FilterType.GE:
								ge(getPropertyName(criteria, it.attribute), it.value)
							break
							case FilterType.LT:
								lt(getPropertyName(criteria, it.attribute), it.value)
							break	
							case FilterType.LE:
								le(getPropertyName(criteria, it.attribute), it.value)
							break	
							default:
								eq(getPropertyName(criteria, it.attribute), it.value)
						}
					else
						eq(getPropertyName(criteria, it.attribute), it.value)
				}
		}	
		projections {
				for (item in groups){
					groupProperty(getPropertyName(criteria, item))
				}
				projection.each{
					String property = getPropertyName(criteria, it.key)

					switch(it.value){
						case ProjectionType.SUM:
							sum(property)
						case ProjectionType.MIN:
							min(property)
						case ProjectionType.MAX:
							max(property)
						case ProjectionType.AVG:
							avg(property)
						case ProjectionType.COUNT:
							count(property)

					}
				}
			}
		}
		return resultList
	}
	
	private String getPropertyName(HibernateCriteriaBuilder criteria, String attr){
		String propertyName = context.getAttributePropertyName(attr)
		String propertyAliasName = null;
		if(context.getEntityNameFor(attr)!=null){
			context.createAliasFor(criteria, attr)
			propertyAliasName = context.getAliasNameForProperty(attr)
		}
		if (propertyAliasName)
			propertyName = propertyAliasName + "." + propertyName
			
		return propertyName 
	}
	
	/**
	 * Metodo que devuelve el alias para una columna que esta pasada por una funcion agregada (projection)
	 * @param fieldName Nombre de campo
	 * @param type tipo de projection
	 * @return
	 */
	protected String getProjectionAliasForProperty(String fieldName, ProjectionType type){
		return fieldName + type.getSuffix();
	}
	
	
	
	
	/**
	 * Enumeracion que contiene los distintos tipos de Operadores de Filtros. 
	 *
	 */
	protected enum FilterType{
		EQ,			// Igual a
		GT, 		// Mayor que
		GE, 		// Mayor o Igual que
		LT,			// Menor que
		LE;			// Menor o Igual que
		
	}
    
	
	/**
	 * Enumeracion que contiene los distintos tipos de Projections. Cada una contiene un sufijo utilizado
	 * para los alias.
	 *
	 */
	protected enum ProjectionType{
		SUM("_sum"),
		MIN("_min"),
		MAX("_max"),
		AVG("_avg"),
		COUNT("_count");
	
		
		private String suffix;
		
		private ProjectionType(String suffix){
			this.suffix = suffix;
		}
		
		public String getSuffix(){
			return this.suffix;
		}
	}
}
