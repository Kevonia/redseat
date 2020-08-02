package com.kovecmedia.redseat.doa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kovecmedia.redseat.entity.Country;


@Repository
public interface CountryRepository extends JpaRepository<Country, Long>  {

}
