package com.assignment.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class WordplayUtil {

	public static int getCount(String word, Map<String, Integer> data) {
		if (data.containsKey(word)) {
			// Finds the word in the map, returns the total count
			return data.get(word);
		} else {
			// No occurrences of the search word
			return 0;
		}
	}

	public static Properties loadProperties() {
		InputStream input = null;
		Properties prop = new Properties();

		try {

			String filename = "config.properties";
			input = WordplayUtil.class.getClassLoader().getResourceAsStream(filename);
			if (input == null) {
				System.out.println("Sorry, unable to find " + filename);
				return prop;
			}

			// load a properties file from class path, inside static method
			prop.load(input);

			// get the property value and print it out
			System.out.println(prop.getProperty("inputData"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}
	
	public static void main(String[] args) {
		loadProperties();
	}
}