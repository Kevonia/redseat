package com.kovecmedia.redseat.seeder;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.kovecmedia.redseat.doa.PackageRepository;
import com.kovecmedia.redseat.doa.UserRepository;
import com.kovecmedia.redseat.entity.Package;
import com.kovecmedia.redseat.model.PackageLocation;
import com.kovecmedia.redseat.model.PackagesStatus;

@Component
public class PackageSeeder {
	 private Logger logger = Logger.getLogger(PackageSeeder.class);
		@Autowired
		private UserRepository userRepository;

		@Autowired
		private PackageRepository packageRepository;



		public void run() {
			try {			
				List<Package> list = new ArrayList<Package>();
				Faker faker = new Faker();
				NumberFormat formatter = new DecimalFormat("#0.00000");
				
				long start = System.currentTimeMillis();
				for (int i = 1; i <= 10; i++) {
					Package  package1 = new Package();
					package1.setTrackingNumber(faker.numerify("ABC-##-EFG-##-HIJ"));
					package1.setLocation(PackageLocation.getRandomPackageLocation());
					package1.setSeller(faker.company().name());
					package1.setStatus(PackagesStatus.getRandomPackagesStatus());
					package1.setDescription(faker.company().catchPhrase());
					package1.setValue(2.00);
					package1.setWeight(4);
					package1.setUserId(userRepository.getOne((long) 100));
					package1.setUpdate_by("system");
					list.add(package1);
				}
				long end = System.currentTimeMillis();
				packageRepository.saveAll(list);
				logger.info("Package Seeder ran in " + formatter.format((end - start) / 1000d) + " seconds");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
}
