package com.kovecmedia.redseat.seeder;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.kovecmedia.redseat.doa.FeesRepository;
import com.kovecmedia.redseat.entity.Fee;

@Component
public class FeeSeeder {
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private FeesRepository feesRepository;



	public void run() {
		try {
			List<Fee> list = new ArrayList<>();
			NumberFormat formatter = new DecimalFormat("#0.00000");
			Faker faker = new Faker();
			long start = System.currentTimeMillis();

			for (int i = 1; i <= 20; i++) {
				Fee fee = new Fee();
				fee.setName(faker.name().name());
				fee.setValue(faker.number().randomNumber());
		
				list.add(fee);
			}

			long end = System.currentTimeMillis();
			feesRepository.saveAll(list);
			logger.info("Fee Seeder ran in " + formatter.format((end - start) / 1000d) + " seconds");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
