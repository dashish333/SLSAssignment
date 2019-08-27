package com.software.eCommerce.services;

import com.software.eCommerce.util.TimeTrackingUtil;

public class TimeTrackingUtilImpl implements TimeTrackingUtil {

	public long startTime() {
		return System.nanoTime();
	
	}

	public long endTime() {
		return System.nanoTime();
		

	}

}
