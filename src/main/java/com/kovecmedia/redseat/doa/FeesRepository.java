package com.kovecmedia.redseat.doa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kovecmedia.redseat.entity.Fee;


public interface FeesRepository   extends JpaRepository<Fee, Long> {

}


