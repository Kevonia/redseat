package com.kovecmedia.redseat.doa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kovecmedia.redseat.entity.Company;



public interface CompanyRepository extends JpaRepository<Company, Long>  {

}
