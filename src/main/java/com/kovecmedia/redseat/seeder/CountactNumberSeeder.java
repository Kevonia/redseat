package com.kovecmedia.redseat.seeder;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.kovecmedia.redseat.doa.ContactNumberRepository;
import com.kovecmedia.redseat.entity.ContactNumber;
import com.kovecmedia.redseat.model.PhoneType;

@Component
public class CountactNumberSeeder {

	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	ContactNumberRepository contactNumberRepository;
	

	
	public void run() {
		try {			
			List<ContactNumber> list = new ArrayList<ContactNumber>();
			Faker faker = new Faker();
			NumberFormat formatter = new DecimalFormat("#0.00000");
			 Random rd = new Random();
			 
			long start = System.currentTimeMillis();
			for (int i = 1; i <= 10; i++) {
				ContactNumber contactNumber = new ContactNumber();
				contactNumber.setNumber(faker.phoneNumber().phoneNumber());
				contactNumber.setIsprimary(rd.nextBoolean());
				contactNumber.setType(PhoneType.getRandomPhoneType());
				list.add(contactNumber);
			}
			long end = System.currentTimeMillis();
			contactNumberRepository.saveAll(list);
			logger.info("CountactNumber  Seeder ran in " + formatter.format((end - start) / 1000d) + " seconds");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
