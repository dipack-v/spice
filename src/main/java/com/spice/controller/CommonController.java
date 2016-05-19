package com.spice.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spice.dao.repository.entity.Item;

@RestController
public class CommonController {
	
	@RequestMapping(value="alive", method = RequestMethod.GET)
	public ResponseEntity<String> alive() {		
		return new ResponseEntity<String>(HttpStatus.OK);	
	}
	
	@RequestMapping(value="country", method = RequestMethod.GET)
	public List<Item> getCountryList() {		
		return  Arrays.asList(new Item(1L, "IN", "India"), new Item(2L,"UK", "United Kingdom"));		
	}
	

}
