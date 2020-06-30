package com.yuri.luis.api.boaviagem.util;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Util {

	public static SimpleDateFormat sdf;
	
	public static void  formatarData() {
		
		sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
	}
}
