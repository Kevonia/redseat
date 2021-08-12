package com.kovecmedia.redseat.service;

import java.util.List;

import com.kovecmedia.redseat.entity.Testimonial;


public interface TestimonialService {

	
	List<Testimonial> getAll() throws Exception;
}
