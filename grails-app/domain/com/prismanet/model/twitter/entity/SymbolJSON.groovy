package com.prismanet.model.twitter.entity

import java.util.List;

class SymbolJSON {
	static mapWith = "mongo"
	String text;
	List<Integer> indices;
	static constraints = {
	}
	static mapping ={
		version false
  }
}
