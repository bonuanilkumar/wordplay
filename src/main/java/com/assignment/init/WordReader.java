package com.assignment.init;

import java.util.TreeMap;

import com.assignment.controller.WordPlayResponse;
import com.assignment.dataload.DataLoader;
import com.assignment.util.WordplayUtil;

public class WordReader {

	static TreeMap<String, Integer> frequencyMap = new TreeMap<String, Integer>();
	
	public WordPlayResponse getWordPlayResponse(String key){
		WordPlayResponse playResponse = new WordPlayResponse();
		playResponse.setNoOfOccurrences(getNoOfOccurences(key)) ;
		playResponse.setFrequency(getFrequency(key));
		return playResponse;
	}

	/**
	 * 
	 * This method gives the total number of occurences of the search word in the
	 * list of documents.
	 *  
	 * @param key
	 * @return
	 */
	private Integer getNoOfOccurences(String key) {
		try {
			if (key != null && key.trim().length() > 0){
				Integer noOfOccurences =  DataLoader.data.get(key.toString().trim().toLowerCase());
				if(noOfOccurences != null)
					return noOfOccurences;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 
	 * This method returns the total number of times the service got requrest with a 
	 * particular key/search word.
	 * 
	 * @param key
	 * @return
	 */
	private Integer getFrequency(String key) {
		
		try{
			if(key != null && key.trim().length() > 0)
				frequencyMap.put(key.toString().toLowerCase(), WordplayUtil.getCount(key.toString().toLowerCase(), frequencyMap) + 1);
			
			return WordplayUtil.getCount(key.toString().toLowerCase(), frequencyMap);
		}catch(Exception e){
			
		}
		return 1;
	}

}
