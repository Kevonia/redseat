package com.kovecmedia.redseat.service;

import java.util.List;

import com.kovecmedia.redseat.entity.Delivery;
import com.kovecmedia.redseat.entity.User;
import com.kovecmedia.redseat.payload.request.DeliveryRequest;

public interface DeliveryService {

	
	void addDelivery(DeliveryRequest deliveryRequest );
	
	List<Delivery> getAllByUser(User user) throws Exception;
}
