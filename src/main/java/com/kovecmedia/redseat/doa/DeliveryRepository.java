package com.kovecmedia.redseat.doa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kovecmedia.redseat.entity.Address;
import com.kovecmedia.redseat.entity.AuthorizedPickup;
import com.kovecmedia.redseat.entity.Delivery;
import com.kovecmedia.redseat.entity.User;



public interface DeliveryRepository extends JpaRepository<Delivery, Long>  {

	List<Delivery> findByUser(User Users);
	
}
