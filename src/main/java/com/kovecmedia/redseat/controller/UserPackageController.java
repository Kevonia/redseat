package com.kovecmedia.redseat.controller;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kovecmedia.redseat.payload.respond.UserPackages;
import com.kovecmedia.redseat.service.UserService;



@RestController
@RequestMapping("userpackage")
public class UserPackageController {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	UserService userservice;


	@GetMapping(value = "/{id}", produces = "application/json")
	@CrossOrigin(origins = "*",allowedHeaders="*")
	public UserPackages getbyId(@PathVariable long id) {
		UserPackages userPackages = new UserPackages();
		userPackages = userservice.getUserPaakages(id);
		logger.info(userPackages.toString());
		return userPackages;
	}

}
