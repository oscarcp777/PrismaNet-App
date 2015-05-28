package com.prismanet

import grails.gorm.CriteriaBuilder
import grails.orm.HibernateCriteriaBuilder

import com.prismanet.context.AttributeContext



class GenericService {
	
	def sessionFactory
	def propertyInstanceMap = org.codehaus.groovy.grails.plugins.DomainClassGrailsPlugin.PROPERTY_INSTANCE_MAP

	AttributeContext context
	def domainClass
	
	GenericService(){
	}
	
	GenericService(def domainClass,AttributeContext context){
		this.domainClass = domainClass
		this.context = context
	}
	
	def cleanUpGorm() {
		try {
			sessionFactory.currentSession.flush()
			sessionFactory.currentSession.clear()
			propertyInstanceMap.get().clear()
			log.debug "Gorm clean"
		} catch (Exception e) {
			log.error e.getCause()
		}
	}
	
	
	def list(filters, parameters, orders){
		list(domainClass, context, filters, parameters, orders)
	}
	
	def list(domainClass, context, filters, parameters, orders) {
		def criteria = domainClass.createCriteria()
		def results = criteria.list(parameters) {
			context.clearDefinedAlias()
			and {
				filters.each{
					def property = getPropertyName(context, criteria, it.attribute)
					def value = it.value
					if (it.type)
						switch (it.type) {
							case FilterType.EQ:
								eq(property, value)
								break
							case FilterType.NE:
								ne(property, value)
								break
							case FilterType.GT:
								gt(property, value)
								break
							case FilterType.GE:
								ge(property, value)
								break
							case FilterType.LT:
								lt(property, value)
								break
							case FilterType.LE:
								le(property, value)
								break
							case FilterType.IN:
								'in'(property, value)
								break
							case FilterType.NOTIN:
								not{'in'(property, value)}
								break
							default:
								eq(property, value)
						}
					else
						eq(getPropertyName(context, criteria, it.attribute), it.value)
				}
			}
			orders.each{
				switch (it.value) {
					case OrderType.ASC:
						order(getPropertyName(context, criteria, it.attribute), "asc")
						break
					case OrderType.DESC:
						order(getPropertyName(context, criteria, it.attribute), "desc")
						break
				}
			}
			
		}
		[results: results, totalCount: results.totalCount]
		
	}
	
	def groupBy(groups, filters, projection, orders){
		groupBy(domainClass, context, groups, filters, projection, orders)
	}
	
	def groupBy(domainClass, context, groups, filters, projection, orders){
		groupBy(domainClass, context, groups, filters, projection, orders, [:])
	}
	
	def groupBy(domainClass, context, groups, filters, projection, orders, parameters){
		
		def criteria = domainClass.createCriteria()
		def resultList = criteria.list(parameters) {
			context.clearDefinedAlias()
			and{
				filters.each {
					def property = getPropertyName(context, criteria, it.attribute)
					def value = it.value
					if (it.type)
						switch (it.type) {
							case FilterType.EQ:
								eq(property, value)
								break
							case FilterType.NE:
								ne(property, value)
								break
							case FilterType.GT:
								gt(property, value)
								break
							case FilterType.GE:
								ge(property, value)
								break
							case FilterType.LT:
								lt(property, value)
								break
							case FilterType.LE:
								le(property, value)
								break
							case FilterType.IN:
								'in'(property, value)
								break
							case FilterType.NOTIN:
								not{'in'(property, value)}
								break
							default:
								eq(property, value)
						}
					else
						eq(property, value)
				}
			}
			projections {
				for (item in groups){
					groupProperty(getPropertyName(context, criteria, item))
				}
				projection.each{
					String property = getPropertyName(context, criteria, it.key)

					switch(it.value){
						case ProjectionType.SUM:
							sum(property,property)
							break
						case ProjectionType.MIN:
							min(property,property)
							break
						case ProjectionType.MAX:
							max(property,property)
							break
						case ProjectionType.AVG:
							avg(property,property)
							break
						case ProjectionType.COUNT:
							count(property,property)
							break
						case ProjectionType.COUNT_DISTINCT:
							countDistinct(property,property)
							break

					}
				}
			}
			orders.each{
				switch (it.value) {
					case OrderType.ASC:
						order(getPropertyName(context, criteria, it.attribute), "asc")
						break
					case OrderType.DESC:
						order(getPropertyName(context, criteria, it.attribute), "desc")
						break
				}
			}
		}
		return resultList
	}
	
	private String getPropertyName(AttributeContext context, HibernateCriteriaBuilder criteria, String attr){
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
	public enum FilterType{
		EQ,			// Igual a
		NE,			// No Igual a
		GT, 		// Mayor que
		GE, 		// Mayor o Igual que
		LT,			// Menor que
		LE,			// Menor o Igual que
		IN,			// Lista de valores discretos incluidos
		NOTIN;		// Lista de valores discretos excluidos
	}
	
	
	/**
	 * Enumeracion que contiene los distintos tipos de orden que se puede aplicar
	 *
	 */
	protected enum OrderType{
		ASC,	// Ascendente
		DESC;	// Descendente
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
		COUNT("_count"),
		COUNT_DISTINCT("_countDistinct");
	
		
		private String suffix;
		
		private ProjectionType(String suffix){
			this.suffix = suffix;
		}
		
		public String getSuffix(){
			return this.suffix;
		}
	}
}
