package com.kovecmedia.redseat.doa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kovecmedia.redseat.entity.Billing;
import com.kovecmedia.redseat.model.BillingStatus;


public interface BillingRepository extends JpaRepository<Billing, Long> {
	List<Billing> findbyStatus(BillingStatus stauts);
}
