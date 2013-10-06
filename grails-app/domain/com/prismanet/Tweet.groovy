package com.prismanet

class Tweet {
	//TODO que esta clase herede de Mentions, que agrupa menciones ya sea de twitter como de
	// cualquier otro lugar
	String content
	Boolean retweet
	Long tweetId
	Date created
	Integer month
	Integer year
	String period
	//TODO con el atributo created tengo solo la fecha falta la hora ver de agregar como
	// timestamp y antes de guardar dividir dia de hora
	static belongsTo = [author:Author]

    static constraints = {
		content(maxLength:140)
		tweetId unique:true  
    }
	
	void setCreated(Date date){
		if (date != null){
			month = date.getMonth() + 1
			year = date.getYear()
			period = date.getYear().toString() + month.toString()
		}
		date.clearTime()
		created = date
	}
	
	@Override
	public String toString() {
		return author?.accountName + "-" + content;
	}
}
