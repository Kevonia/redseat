package com.kovecmedia.redseat.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kovecmedia.redseat.doa.UserRepository;
import com.kovecmedia.redseat.entity.AuthorizedPickup;
import com.kovecmedia.redseat.entity.Delivery;
import com.kovecmedia.redseat.payload.request.DeliveryRequest;
import com.kovecmedia.redseat.service.DeliveryService;

@RestController
@RequestMapping("delivery")
public class DeliveryController {

	Logger logger = LoggerFactory.getLogger(DeliveryController.class);
	@Autowired
	private DeliveryService deliveryService;
	
	@Autowired
	private UserRepository userRepository;

	@PostMapping(value = "/add", produces = "application/json")
	public @Valid DeliveryRequest save(@Valid @RequestBody DeliveryRequest deliveryRequest) {

		try {
			deliveryService.addDelivery(deliveryRequest);
		
		} catch (Exception ex) {
			ex.printStackTrace();
      
		}

		return deliveryRequest;
	}
	
	@GetMapping(value = "/user/{id}", produces = "application/json")
	public List<Delivery> getByIdUser(@PathVariable long id) {

		List<Delivery> list = new ArrayList<>();

		try {
			list = deliveryService.getAllByUser(userRepository.getOne(id));
		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return list;
	}

}
