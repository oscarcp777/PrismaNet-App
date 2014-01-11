package com.prismanet

import com.prismanet.context.AttributeContext
import grails.orm.HibernateCriteriaBuilder



class GenericService {
	
	AttributeContext context
	def domainClass 
	
	GenericService(def domainClass,AttributeContext context){
		this.domainClass = domainClass
		this.context = context
	}
	
	
	
	def list(filters, parameters){
		list(domainClass, context, filters, parameters)
	}
	
	def list(domainClass, context, filters, parameters) {
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
		}
		[results: results, totalCount: results.totalCount]
		
	}
	
	def groupBy(groups, filters, projection){
		groupBy(domainClass, context, groups, filters, projection)
	}
	
	def groupBy(domainClass, context, groups, filters, projection){
		def criteria = domainClass.createCriteria()
		
		def resultList = criteria {
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
	protected enum FilterType{
		EQ,			// Igual a
		GT, 		// Mayor que
		GE, 		// Mayor o Igual que
		LT,			// Menor que
		LE,			// Menor o Igual que
		IN,			// Lista de valores discretos incluidos
		NOTIN;		// Lista de valores discretos excluidos
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
