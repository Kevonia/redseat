package com.kovecmedia.redseat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kovecmedia.redseat.doa.TestimonialRepository;
import com.kovecmedia.redseat.entity.Testimonial;

@Service
public class TestimonialImpl implements TestimonialService {
	
	@Autowired
	TestimonialRepository testimonialRepository;

	@Override
	public List<Testimonial> getAll() throws Exception {
		// TODO Auto-generated method stub
		return testimonialRepository.findAll();
	}
	

}
