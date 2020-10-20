package com.kovecmedia.redseat.doa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kovecmedia.redseat.entity.Billing;
import com.kovecmedia.redseat.model.BillingStatus;
import com.kovecmedia.redseat.entity.Package;

public interface BillingRepository extends JpaRepository<Billing, Long> {
	@Query("SELECT b from billing b where b.status =:status and b.packageId =:package1")
	List<Billing> findbyStatusandpackageId(BillingStatus status, Package package1);
	Billing findBypackageId(Package package1);
}
