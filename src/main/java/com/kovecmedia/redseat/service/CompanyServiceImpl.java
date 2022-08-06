package com.kovecmedia.redseat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kovecmedia.redseat.doa.CompanyRepository;
import com.kovecmedia.redseat.entity.Company;

@Service
public class CompanyServiceImpl implements  CompanyService {

	@Autowired
	CompanyRepository companyRepository;
	
	@Override
	public List<Company> findAll() {
	
		return companyRepository.findAll();
	}

	@Override
	public Company findbyid(long id) {
		
		return companyRepository.findById(id).get();
	}

}
