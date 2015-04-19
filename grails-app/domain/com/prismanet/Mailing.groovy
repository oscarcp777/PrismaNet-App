package com.prismanet

class Mailing {
	
	String type
	String  fromDate
	String  toDate
	String  tentFrom
	String  tentTo
	
	String  tweets1
	String  author1
	String  day1
	String  wordsDay1
	String  hour1
	String  wordsHour1
	String  tent1
	String  postLikes1
	String  postComments1
	String  postLink1
	String  postPhoto1
	
	String  tweets2
	String  author2
	String  day2
	String  wordsDay2
	String  hour2
	String  wordsHour2
	String  tent2
	String  postLikes2
	String  postComments2
	String  postLink2
	String  postPhoto2

	String  tweets3
	String  author3
	String  day3
	String  wordsDay3
	String  hour3
	String  wordsHour3
	String  tent3
	String  postLikes3
	String  postComments3
	String  postLink3
	String  postPhoto3
	
	String  tweets4
	String  author4
	String  day4
	String  wordsDay4
	String  hour4
	String  wordsHour4
	String  tent4
	String  postLikes4
	String  postComments4
	String  postLink4
	String  postPhoto4

    static constraints = {
		postLink1(maxSize: 1000)
		postPhoto1(maxSize: 1000)
		postLink2(maxSize: 1000)
		postPhoto2(maxSize: 1000)
		postLink3(maxSize: 1000)
		postPhoto3(maxSize: 1000)		
		postLink4(maxSize: 1000)
		postPhoto4(maxSize: 1000)
    }
}
