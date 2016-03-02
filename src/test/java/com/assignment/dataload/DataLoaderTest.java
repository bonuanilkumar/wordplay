/**
 * 
 */
package com.assignment.dataload;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author abonu
 *
 */
public class DataLoaderTest {
	
	@BeforeClass
	public static void setUpBeforeClass(){
		DataLoader dataLoader = new DataLoader();
		dataLoader.readDataFromFiles("./src/test/resources/input_data");
		
	}

	/**
	 * Test method for {@link com.assignment.dataload.DataLoader#readDataFromFiles(java.lang.String)}.
	 */
	@Test
	public void testReadDataFromFiles() {
		// Test to check whether the data is loaded or not
		assertNotEquals(0, DataLoader.data.size());
	}

	@Test
	public void testReadDataFromFiles1(){
		assertEquals( (Integer)16, DataLoader.data.get("this"));
	}
	
	@Test
	public void testReadDataFromFiles2(){
		// This test checks that the data loader parse URL's as well
		//http://www.google.com/search
		assertEquals((Integer)1 , DataLoader.data.get("http"));
		assertEquals((Integer)1, DataLoader.data.get("search"));
	}
	
}
