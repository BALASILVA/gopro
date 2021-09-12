package com.gopro.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class CommanServiceImpl implements CommanService {

	@Override
	public Date incrementDate(Date date, int noOfDays) {
		System.out.println(date+ " " +noOfDays);
		Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, noOfDays); //minus number would decrement the days
        date  = cal.getTime();	
        System.out.println(date);
        return date;
	}
	
	@Override
	public Date maximiumTimeOfDate(Date date) {
		Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY,23);
        cal.set(Calendar.MINUTE,59);
        cal.set(Calendar.SECOND,59);
        cal.set(Calendar.MILLISECOND,999);
        date = cal.getTime();        
        return date;
	}
	
}
