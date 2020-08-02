package com.kovecmedia.redseat.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kovecmedia.redseat.entity.Country;
import com.kovecmedia.redseat.service.CountryService;

@RestController
@RequestMapping("country")
public class CountryController {

	Logger logger = LoggerFactory.getLogger(CountryController.class);
	@Autowired
	private CountryService country;

	@GetMapping(value = "/", produces = "application/json")
	public List<Country> getAll() {

		List<Country> countrylist = new ArrayList<Country>();

		try {
			countrylist = country.findAll();
		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return countrylist;
	}

}
