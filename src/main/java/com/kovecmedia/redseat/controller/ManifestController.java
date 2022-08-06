package com.kovecmedia.redseat.controller;

import javax.xml.transform.dom.DOMSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kovecmedia.redseat.service.ManifestService;

@RestController
@RequestMapping("api/manifest")
public class ManifestController {

	Logger logger = LoggerFactory.getLogger(CountryController.class);
	
	@Autowired
	ManifestService ManifestService;
	
	
	@GetMapping(value = "/create/{id}", produces = "application/json")
	public String createMinfestXML(@PathVariable long id) {	
		 return ManifestService.createMinfestXML(id);
		
	}
	
	
	@GetMapping(value = "/test", produces = "application/json")
	public String test() {	
		ManifestService.createMinfestTest();		  
		 return "Done creating XML File";
		
	}
}
