package com.kovecmedia.redseat.seeder;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.kovecmedia.redseat.doa.AddressRepository;
import com.kovecmedia.redseat.doa.CountryRepository;
import com.kovecmedia.redseat.entity.Address;
import com.kovecmedia.redseat.model.AddressStatus;
import com.kovecmedia.redseat.model.UpdateBy;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class AddressSeeder {
    
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	CountryRepository countryRepository;
	
	public void run() {
		try {			
			List<Address> list = new ArrayList<Address>();
			Faker faker = new Faker();
			NumberFormat formatter = new DecimalFormat("#0.00000");
			
			long start = System.currentTimeMillis();
			for (int i = 1; i <= 10; i++) {
				Address address = new Address();
			     address.setAddressline1(faker.address().streetAddress());
			     address.setAddressline2(faker.address().secondaryAddress());
			     address.setCountry(countryRepository.getOne((long) 1));
			     address.setType(AddressStatus.getRandomAddressType());
			     address.setZipcode(faker.address().zipCode());
			     address.setUpdate_by(UpdateBy.System.name());
				list.add(address);
			}
			long end = System.currentTimeMillis();
			addressRepository.saveAll(list);
			logger.info("Address  Seeder ran in " + formatter.format((end - start) / 1000d) + " seconds");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
