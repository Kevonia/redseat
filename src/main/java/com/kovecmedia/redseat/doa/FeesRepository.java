package com.kovecmedia.redseat.doa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kovecmedia.redseat.entity.Fee;


public interface FeesRepository   extends JpaRepository<Fee, Long> {
	
	@Query("SELECT fe FROM fees fe where :Wgt Between lower_limit and upper_limit and name <>'Processing Fee'")
	Fee getFreightFee(long Wgt);	
	
	@Query("SELECT fe FROM fees fe where :Wgt Between lower_limit and upper_limit and name  = 'Processing Fee'")
	Fee getProcessingFee(long Wgt);	
}


