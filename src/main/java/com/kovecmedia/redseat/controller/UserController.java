package com.kovecmedia.redseat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kovecmedia.redseat.entity.User;
import com.kovecmedia.redseat.model.UserRegistration;
import com.kovecmedia.redseat.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	UserService userservice;

	@GetMapping(value = "/{id}", produces = "application/json")
	public User getbyId(@PathVariable long id) {

		User user = new User();

		user = userservice.getUser(id);

		return user;
	}

	@PostMapping(value = "add", produces = "application/json")
	public UserRegistration addUser(@RequestBody UserRegistration request) {

		// User user = new User("foo", "foo", "Kevonia123@gmail.com", null, null, null);

		userservice.save(request);

		return request;
	}

}