package com.kovecmedia.redseat.doa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kovecmedia.redseat.entity.Address;
import com.kovecmedia.redseat.entity.DutyList;

public interface DutylistRepository extends JpaRepository<DutyList, Long>  {

	
}
