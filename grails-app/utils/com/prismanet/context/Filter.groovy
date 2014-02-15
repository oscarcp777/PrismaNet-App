package com.prismanet.context

import com.prismanet.GenericService.FilterType;

class Filter {
	
	/**
	 * Tipo de operador del filtro 
	 */
	FilterType type
	
	/**
	 * Valor del filtro
	 */
	def value
	
	/**
	 * Atributo a filtrar, ver XXAtributeContext de la consulta relacionada
	 */
	def attribute

	@Override
	public String toString() {
		return attribute + ": " + value + "(" + type + ")"
	}	
	

}
