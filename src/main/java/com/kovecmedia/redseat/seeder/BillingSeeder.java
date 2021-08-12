package com.kovecmedia.redseat.seeder;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.kovecmedia.redseat.doa.BillingRepository;
import com.kovecmedia.redseat.doa.FeesRepository;
import com.kovecmedia.redseat.doa.PackageRepository;
import com.kovecmedia.redseat.entity.Billing;
import com.kovecmedia.redseat.entity.Fee;
import com.kovecmedia.redseat.model.BillingStatus;
@Component
public class BillingSeeder {

	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private BillingRepository billingRepository;
     
	@Autowired
	private PackageRepository packageRepository;

	@Autowired
	private FeesRepository feesRepository;

	public void run() {
		try {
			List<Billing> list = new ArrayList<>();
			NumberFormat formatter = new DecimalFormat("#0.00000");
			Faker faker = new Faker();
			long start = System.currentTimeMillis();

			for (int i = 1; i <= 10; i++) {
				Billing billing = new Billing();
				long millis = System.currentTimeMillis();
				java.sql.Date date = new java.sql.Date(millis);
				Optional<com.kovecmedia.redseat.entity.Package> value = packageRepository.findById((long) i);
			    HashSet<Fee> billingfees=new HashSet();   
			    
			    billingfees.addAll(feesRepository.findAll());
				if(value.isPresent()) {
					billing.setDescription(faker.esports().game());
					billing.setStatus(BillingStatus.getRandomBillingStatus());
					billing.setPackageId(value.get());
					billing.setValue(faker.number().randomDouble(2, 100, 10000));
					billing.setFee(billingfees);
					list.add(billing);
				}
		
			}

			long end = System.currentTimeMillis();
			billingRepository.saveAll(list);
			logger.info("Billing Seeder ran in " + formatter.format((end - start) / 1000d) + " seconds");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
