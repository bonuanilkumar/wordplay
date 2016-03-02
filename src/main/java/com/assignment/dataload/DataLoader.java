package com.assignment.dataload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.TreeMap;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.assignment.constants.AppConstants;
import com.assignment.util.WordplayUtil;

@WebListener
public class DataLoader implements ServletContextListener {

	public static TreeMap<String, Integer> data = new TreeMap<String, Integer>();;

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Loading Data Init");
		Properties properties = WordplayUtil.loadProperties();
		if (properties.get(AppConstants.INPUT_DATA) != null
				&& properties.get(AppConstants.INPUT_DATA).toString().trim().length() > 0)
			readDataFromFiles(properties.get(AppConstants.INPUT_DATA).toString());
	}

	/**
	 * 
	 * This method iterates over the files in the directory
	 * 
	 * @param inputFilesDir
	 */
	public void readDataFromFiles(String inputFilesDir) {
		try {
			File input_dir = new File(inputFilesDir);
			if (input_dir != null && input_dir.isDirectory() && input_dir.listFiles() != null) {
				for (File file2 : input_dir.listFiles()) {
					if (file2 != null && file2.isFile()) {
						scanFile(file2);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * This methods scans through the entire file and generates only English
	 * words
	 * 
	 * @param file
	 * 
	 */
	private void scanFile(File file) {

		Scanner wordFile = null;
		String word;

		try {
			// Scanner Delimiter will split for all characters/digits except for
			// Alphabet
			wordFile = new Scanner(new FileReader(file)).useDelimiter(AppConstants.SCANNER_DELIMITER_REGEX);
			while (wordFile.hasNext()) {
				word = wordFile.next();
				// Get the current count of this word, add one, and then store
				// the new count:
				// count = getCount(word.toLowerCase(), data) + 1;
				if (word != null && word.trim().length() > 0)
					data.put(word.toLowerCase(), WordplayUtil.getCount(word.toLowerCase(), data) + 1);
			}
		} catch (FileNotFoundException e) {
			System.err.println(e);
			return;
		} finally {
			if (wordFile != null)
				wordFile.close();
		}

	}

	public static void main(String[] args) throws IOException {
		DataLoader dataLoader = new DataLoader();
		String current = new java.io.File(".").getCanonicalPath();
		System.out.println("Current dir:" + current);
		dataLoader.readDataFromFiles("./src/main/resources/input_data");

		System.out.println(data.get("ThIs".toLowerCase()));
	}

}
