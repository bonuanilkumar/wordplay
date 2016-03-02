package com.assignment.init;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import com.assignment.controller.WordPlayResponse;
import com.assignment.dataload.DataLoader;

public class WordReaderTest {

	public static WordReader reader ;
	@BeforeClass
	public static void setUpBeforeClass() {
		DataLoader dataLoader = new DataLoader();
		dataLoader.data = new HashMap<String, Integer>();
		dataLoader.readDataFromFiles("./src/test/resources/large_chunks");
		reader = new WordReader();

	}

	@Test
	public void testGetWordPlayResponse() {
		WordReader reader = new WordReader();

		// Attempt 1 : Check No. of Occurences & frequency
		// This should give frequency as 1 and No of occurences as 16
		WordPlayResponse playResponse = reader.getWordPlayResponse("This");
		assertEquals(1, playResponse.getFrequency());
		assertEquals(2223, playResponse.getNoOfOccurrences());

		// Attempt 2 : Check No. of Occurences & frequency
		// This should give frequency as 2 as this is the second hit with this
		// keyword
		// and No of occurences as 16 (case insensitive)
		playResponse = reader.getWordPlayResponse("THiS");
		assertEquals(2, playResponse.getFrequency());
		assertEquals(2223, playResponse.getNoOfOccurrences());
		
		// Attempt 3 : Check No. of Occurences & frequency
		// This should give frequency as 3 as this is the Third hit with this
		// keyword
		// and No of occurences as 16 (case insensitive)
		playResponse = reader.getWordPlayResponse("THIS");
		assertEquals(3, playResponse.getFrequency());
		assertEquals(2223, playResponse.getNoOfOccurrences());
	}

	@Test
	public void testGetWordPlayResponse2() {
		// Attempt 4 : With No matches
		// frequency should be 1
		// no of occurences 0
		WordPlayResponse playResponse = reader.getWordPlayResponse("Anil");
		assertEquals(1, playResponse.getFrequency());
		assertEquals(0, playResponse.getNoOfOccurrences());
	}

	@Test
	public void testGetWordPlayResponse3() {
		// Attempt 5 : Number as a key
		// frequency : 1 - As this is the 1st hit
		// no of occurences : 0
		WordPlayResponse playResponse = reader.getWordPlayResponse("123");
		assertEquals(1, playResponse.getFrequency());
		assertEquals(0, playResponse.getNoOfOccurrences());
	}
}
