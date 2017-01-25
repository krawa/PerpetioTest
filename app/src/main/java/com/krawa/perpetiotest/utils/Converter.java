package com.krawa.perpetiotest.utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Converter {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy | HH:mm", Locale.getDefault());

	public static String timeToDateString (long time){
		return dateFormat.format(time * 1000);
	}

}
