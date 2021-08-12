package com.kovecmedia.redseat.doa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kovecmedia.redseat.entity.EXRate;

public interface ExrateRepository extends JpaRepository<EXRate, Long>  {
	 
	EXRate findTopByOrderByIdDesc();
}
