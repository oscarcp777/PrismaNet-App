package com.prismanet

import java.text.SimpleDateFormat;

class Tweet {
	//TODO que esta clase herede de Mentions, que agrupa menciones ya sea de twitter como de
	// cualquier otro lugar
	String content
	Boolean retweet
	Long tweetId
	Date created
	String date
	String period
	String time
	
	Integer day
	Integer month
	Integer year
	Integer hour
	String minute 
	
	static belongsTo = [author:Author]

    static constraints = {
		content(maxLength:140)
		tweetId unique:true  
    }
	
	void setCreated(Date inputDate){
		def resultMap = parseDate(inputDate)
		
		year = resultMap['year']
		month = resultMap['month']
		day =resultMap['day']
		period = resultMap['period']
		time = resultMap['time']
		hour = resultMap['hour']
		minute = resultMap['minute']
		date = resultMap['date']

		created = inputDate
	}
	
	@Override
	public String toString() {time
		return author?.accountName + "-" + content;
	}
	
	
	def parseDate(Date date){
		SimpleDateFormat dF = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat dayF = new SimpleDateFormat("dd");
		SimpleDateFormat mF = new SimpleDateFormat("MM");
		SimpleDateFormat yF = new SimpleDateFormat("yyyy");
		SimpleDateFormat pF = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat tF = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat hF = new SimpleDateFormat("HH");
		SimpleDateFormat minF = new SimpleDateFormat("HH:mm");
		
		[date:dF.format(date), day:dayF.format(date) as Integer, month:mF.format(date) as Integer, year:yF.format(date) as Integer, 
			period: pF.format(date), time:tF.format(date), hour:hF.format(date) as Integer, minute:minF.format(date)]
	}
}
