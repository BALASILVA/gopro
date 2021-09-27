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
	
	@Override
	public Date minimumTimeOfDate(Date date) {
		Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY,00);
        cal.set(Calendar.MINUTE,00);
        cal.set(Calendar.SECOND,00);
        cal.set(Calendar.MILLISECOND,000);
        date = cal.getTime();        
        return date;
	}

	@Override
	public Date oneWeekBeforeDate(Date date) {
		Calendar cal = Calendar.getInstance();
        cal.setTime(date);                
        cal.add(Calendar.DATE,-7);
        cal.set(Calendar.HOUR_OF_DAY,00);
        cal.set(Calendar.MINUTE,00);
        cal.set(Calendar.SECOND,00);
        cal.set(Calendar.MILLISECOND,000);
        date = cal.getTime();        
        return date;
	}

	@Override
	public Date oneMounthBeforeDate(Date date) {
		Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH,-12);
        cal.set(Calendar.DATE,00);
        cal.set(Calendar.HOUR_OF_DAY,00);
        cal.set(Calendar.MINUTE,00);
        cal.set(Calendar.SECOND,00);
        cal.set(Calendar.MILLISECOND,000);
        date = cal.getTime();        
        return date;
	}
	
}
