package com.kovecmedia.redseat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kovecmedia.redseat.entity.Testimonial;
import com.kovecmedia.redseat.entity.User;
import com.kovecmedia.redseat.service.TestimonialService;


@RestController
@RequestMapping("api/testimonial")
public class TestimonialController {

	@Autowired
	TestimonialService testimoniasService ;
	
	@GetMapping(value = "/", produces = "application/json")
	public List<Testimonial> getAll() throws Exception {

		return testimoniasService.getAll();
	}
	


}