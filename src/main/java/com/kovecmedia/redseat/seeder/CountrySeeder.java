package com.kovecmedia.redseat.seeder;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.kovecmedia.redseat.doa.CountryRepository;
import com.kovecmedia.redseat.entity.Country;
import com.kovecmedia.redseat.model.UpdateBy;

@Component
public class CountrySeeder {
	private Logger logger = Logger.getLogger(CountrySeeder.class);
	@Autowired
	private CountryRepository countryRepository;

	public void run() {
		try {			
			List<Country> list = new ArrayList<Country>();
			Faker faker = new Faker();
			NumberFormat formatter = new DecimalFormat("#0.00000");
			
			long start = System.currentTimeMillis();
			for (int i = 1; i <= 200; i++) {
				Country country = new Country();
				country.setCode(faker.country().countryCode2());
				country.setCountrycode(faker.country().countryCode3());
				country.setCountryname(faker.country().name());
				country.setUpdate_by(UpdateBy.System.name());
				list.add(country);
			}
			long end = System.currentTimeMillis();
			countryRepository.saveAll(list);
			logger.info("Country Seeder ran in " + formatter.format((end - start) / 1000d) + " seconds");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
