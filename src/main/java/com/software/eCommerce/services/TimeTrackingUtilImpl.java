package com.software.eCommerce.services;

import com.software.eCommerce.util.TimeTrackingUtil;

public class TimeTrackingUtilImpl implements TimeTrackingUtil {

	public long currentTime() {
		return System.currentTimeMillis();
	
	}

}
