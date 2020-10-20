package com.kovecmedia.redseat.service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kovecmedia.redseat.doa.AddressRepository;
import com.kovecmedia.redseat.doa.CountryRepository;
import com.kovecmedia.redseat.doa.DeliveryRepository;
import com.kovecmedia.redseat.doa.UserRepository;
import com.kovecmedia.redseat.entity.Address;
import com.kovecmedia.redseat.entity.Delivery;
import com.kovecmedia.redseat.entity.User;
import com.kovecmedia.redseat.payload.request.DeliveryRequest;
@Service
public class DeliveryServiceImpl implements DeliveryService {

	@Autowired
	private DeliveryRepository deliveryrepository;
	
	@Autowired
	private UserRepository userRepository;

	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private CountryRepository countryrepository;
	
	@Override
	public void addDelivery(DeliveryRequest deliveryRequest) {
		// TODO Auto-generated method stub
		Delivery delivery = new Delivery();
		
		Address address = new Address();
		
		Set<Address> addresslist = new HashSet<>();
		
		User user = userRepository.getOne(deliveryRequest.getUserid());
		
		delivery.setInstructions(deliveryRequest.getInstructions());
		delivery.setSchedule_Peroid(deliveryRequest.getPeroid());
		delivery.setCharge(deliveryRequest.getCharge());
		delivery.setPackages(deliveryRequest.getPackages());
		delivery.setSchedule_date(new Timestamp(deliveryRequest.getDate().getTime()));
		delivery.setDeliveryAddress(deliveryRequest.getAddressLine1()+" "+ deliveryRequest.getAddressLine2());
		delivery.setUser(user);
		
		
		
		address.setAddressline1(deliveryRequest.getAddressLine1());
		address.setAddressline2(deliveryRequest.getAddressLine2());
		address.setCountry(countryrepository.getOne((long) 1));
		address.setZipcode(deliveryRequest.getZipCode());
		
		addresslist.add(address);
		
		user.setAddress(addresslist);
		
		addressRepository.save(address);
		
		userRepository.save(user);
		
		deliveryrepository.save(delivery);
		
	}

	@Override
	public List<Delivery> getAllByUser(User user) throws Exception {
		// TODO Auto-generated method stub
		return 	 deliveryrepository.findByUser(user);
	}

	@Override
	public Delivery getById(long id) throws Exception {
		// TODO Auto-generated method stub
		return deliveryrepository.findById(id).get();
	}

}
