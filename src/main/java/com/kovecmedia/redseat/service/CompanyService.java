package com.kovecmedia.redseat.service;

import java.util.List;
import java.util.Optional;

import com.kovecmedia.redseat.entity.Company;

public interface CompanyService {

	public List<Company> findAll();
	public Company  findbyid(long id);
}
