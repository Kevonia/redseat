package com.kovecmedia.redseat.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kovecmedia.redseat.doa.UserRepository;
import com.kovecmedia.redseat.entity.AuthorizedPickup;
import com.kovecmedia.redseat.entity.Country;
import com.kovecmedia.redseat.entity.MenuItems;
import com.kovecmedia.redseat.payload.request.authorizedUsersRequest;
import com.kovecmedia.redseat.service.AuthorizedPickupService;

@RestController
@RequestMapping("authorizedpickup")
public class AuthorizedPickupController {
	Logger logger = LoggerFactory.getLogger(AuthorizedPickupController.class);
	@Autowired
	private AuthorizedPickupService authorizedPickupService;
	
	@Autowired
	private UserRepository userRepository;

	@PostMapping(value = "/add", produces = "application/json")
	public  @Valid authorizedUsersRequest save(@Valid @RequestBody authorizedUsersRequest authorizedPickup) {

		try {
			authorizedPickupService.addUPickupUser(authorizedPickup);
		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return authorizedPickup;
	}
	
	@GetMapping(value = "/user/{id}", produces = "application/json")
	public List<AuthorizedPickup> getByIdUser(@PathVariable long id) {

		List<AuthorizedPickup> list = new ArrayList<>();

		try {
			list = authorizedPickupService.getAllByUser(userRepository.getOne(id));
		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return list;
	}
	
	@DeleteMapping(value = "/remove/{id}/{userId}", produces = "application/json")
	public List<AuthorizedPickup> removeById(@PathVariable long id,@PathVariable long userId) {

		List<AuthorizedPickup> list = new ArrayList<>();

		try {
			list = authorizedPickupService.removdById(id, userRepository.getOne(userId));
		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return list;
	}

}
