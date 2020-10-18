package com.kovecmedia.redseat.doa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kovecmedia.redseat.entity.AuthorizedPickup;
import com.kovecmedia.redseat.entity.Invoice;
import com.kovecmedia.redseat.entity.Package;
import com.kovecmedia.redseat.entity.User;


public interface AuthorizedPickupRepository extends JpaRepository<AuthorizedPickup, Long> {
	
	
	List<AuthorizedPickup> findByUser(User Users);
}

