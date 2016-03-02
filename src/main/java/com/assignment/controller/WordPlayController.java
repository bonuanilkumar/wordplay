package com.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.dataload.DataLoader;
import com.assignment.init.WordReader;

@RestController
@RequestMapping("/search_word")
public class WordPlayController {
	
	@Autowired
	public WordReader wordReader;
	
	@RequestMapping(value = "/{key}", method = RequestMethod.GET)  
	public WordPlayResponse searchKey(@PathVariable("key") String key) {
		System.out.println(key);
		return wordReader.getWordPlayResponse(key);
	}

}
