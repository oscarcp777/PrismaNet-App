package com.prismanet.utils

import static org.junit.Assert.*;

import org.junit.Test;

import grails.test.*
import grails.test.mixin.*
import groovy.time.TimeCategory


class DateUtilsTests {

    
    @Test
    void testLoadZerosForMinute() {
		
		use ( TimeCategory ) {
			Date from=new Date();
			Date to=from + 20.minutes
			def minOne=from+ 1.minutes
			def mintree=from+ 3.minutes
			def minten=from+ 10.minutes 
			def dateValueList=[:]
			dateValueList.put(minOne.time, 12)
			dateValueList.put(mintree.time, 14)
			dateValueList.put(minten.time, 45)
			def data=DateUtils.loadZerosForMinute(dateValueList,from,to)
			assertEquals data.size(),20
			assertEquals data.get(1).get(0),minOne.time
			println data
			println dateValueList
		}
        
    }
}
