package com.kovecmedia.redseat.seeder;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MainSeeder implements CommandLineRunner {

	@Autowired
	private CountrySeeder countrySeeder;

	@Autowired
	private RoleSeeder roleSeeder;

	@Autowired
	AddressSeeder addressSeeder;

	@Autowired
	CountactNumberSeeder countactNumberSeeder;

	@Autowired
	UserSeeder userSeeder;

	@Autowired
	PackageSeeder packageSeeder;

	@Autowired
	InvoiceSeeder invoiceSeeder;

	@Autowired
	ScheduledJobSeeder scheduledJobSeeder;

	@Autowired
	MessageQueueSeeder messageQueueSeeder;

	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void run(String... args) throws Exception {
		// execute the code you want to or just call an other function that will handle
		// that like the following

		NumberFormat formatter = new DecimalFormat("#0.00000");

		long start = System.currentTimeMillis();
		countrySeeder.run();
		roleSeeder.run();
		addressSeeder.run();
		countactNumberSeeder.run();
		userSeeder.run();
		packageSeeder.run();
		invoiceSeeder.run();
		scheduledJobSeeder.run();
		//messageQueueSeeder.run();
		long end = System.currentTimeMillis();
		logger.info("All  Seeder ran in " + formatter.format((end - start) / 1000d) + " seconds");

	}
}