package com.kovecmedia.redseat.seeder;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kovecmedia.redseat.doa.CountryRepository;
import com.kovecmedia.redseat.doa.IDTYPERepository;
import com.kovecmedia.redseat.entity.Country;
import com.kovecmedia.redseat.entity.IDTYPE;
import com.kovecmedia.redseat.model.UpdateBy;

@Component
public class IDTYPESeeder {

	private Logger logger = Logger.getLogger(IDTYPESeeder.class);
	@Autowired
	private IDTYPERepository idtyperepository;
	
	@Autowired
	private CountryRepository countryRepository;

	public void run() {
		try {
			List<IDTYPE> list = new ArrayList<IDTYPE>();
		
			NumberFormat formatter = new DecimalFormat("#0.00000");
			
			String update_by =UpdateBy.System.name();

			long start = System.currentTimeMillis();

			IDTYPE role1 = new IDTYPE();
			IDTYPE role2 = new IDTYPE();
			IDTYPE role3 = new IDTYPE();
			IDTYPE role4 = new IDTYPE();
			IDTYPE role5 = new IDTYPE();
			
			Country country = countryRepository.getOne((long) 1);

			role1.setName("Passport");

			role1.setCountry(country);
			
			role2.setName("Drivers license");
			role1.setCountry(country);
			role3.setName("Elector Registration Identification");
			role1.setCountry(country);
			
			role4.setName("Tax ID");
			role1.setCountry(country);
			role5.setName("Others");
			
			role1.setCountry(country);
			
			list.add(role1);
			list.add(role2);
			list.add(role3);

			long end = System.currentTimeMillis();
			idtyperepository.saveAll(list);
			logger.info("IDTYPES Seeder ran in " + formatter.format((end - start) / 1000d) + " seconds");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
