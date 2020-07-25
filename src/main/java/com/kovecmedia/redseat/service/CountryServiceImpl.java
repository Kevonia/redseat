package com.kovecmedia.redseat.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kovecmedia.redseat.doa.CountryRepository;
import com.kovecmedia.redseat.entity.Country;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryRepository repository;

	@Override
	public List<Country> findAll() {
		// TODO Auto-generated method stub
		List<Country> countrylist = new ArrayList<Country>();
		countrylist = this.repository.findAll();
		return countrylist;

	}

	@Override
	public Optional<Country> findbyid(long id) {
		// TODO Auto-generated method stub
		return this.repository.findById(id);
	}
}
