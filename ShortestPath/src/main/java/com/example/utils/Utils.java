package com.example.utils;


public class Utils {
	

	public static Double formatFloatValue(Number value) {
		
		return Double.parseDouble(String.format("%.2f", value));

	}

}
