package com.kovecmedia.redseat.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kovecmedia.redseat.entity.Company;
import com.kovecmedia.redseat.entity.Country;
import com.kovecmedia.redseat.service.CompanyService;

@RestController
@RequestMapping("api/company")
public class CompanyController {

	
	Logger logger = LoggerFactory.getLogger(CountryController.class);
	
	@Autowired
	CompanyService companyService;
	
	@GetMapping(value = "/", produces = "application/json")
	public List<Company> index() {

		List<Company> companyList = new ArrayList<Company>();

		try {
			companyList = companyService.findAll();
		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return companyList;
	}

	
	@GetMapping(value = "/get/{id}", produces = "application/json")
	public Company getByID(@PathVariable long id) {

		
		return companyService.findbyid(id);
	}
	
	
}
