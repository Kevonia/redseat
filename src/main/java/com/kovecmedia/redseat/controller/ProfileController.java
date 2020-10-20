package com.kovecmedia.redseat.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kovecmedia.redseat.entity.User;
import com.kovecmedia.redseat.payload.respond.MessageResponse;
import com.kovecmedia.redseat.service.UserService;

@RestController
@RequestMapping("profile")
public class ProfileController {

	@Autowired
	UserService userservice;
	
	@PostMapping(value = "/update/", produces = "application/json")
	public User  update(@Valid @RequestBody  User user) {
			return userservice.UpdateProfile(user);	  
	}
	
}
