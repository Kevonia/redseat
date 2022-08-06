package com.kovecmedia.redseat.service;

import java.util.List;
import java.util.Optional;

import com.kovecmedia.redseat.entity.Country;


public interface CountryService {

	public List<Country> findAll();
	public Optional<Country>  findbyid(long id);

}
