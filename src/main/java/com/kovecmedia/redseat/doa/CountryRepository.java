package com.kovecmedia.redseat.doa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kovecmedia.redseat.entity.Country;



public interface CountryRepository extends JpaRepository<Country, Long>  {

}
