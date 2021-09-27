package com.gopro.service;

import java.util.Date;

public interface CommanService {

	Date maximiumTimeOfDate(Date date);
	
	Date minimumTimeOfDate(Date date);
		
	Date incrementDate(Date toDate, int i);

	Date oneWeekBeforeDate(Date date);

	Date oneMounthBeforeDate(Date date);	

}
