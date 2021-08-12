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
import com.kovecmedia.redseat.model.FeeType;

@Component
public class FeeSeeder {
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private FeesRepository feesRepository;

	public void run() {
		try {
			List<Fee> list = new ArrayList<>();
			NumberFormat formatter = new DecimalFormat("#0.00000");

			long start = System.currentTimeMillis();

			double startValue = 650;
			double startValueUSD = 4.3;

			for (int i = 1; i <= 50; i++) {
				Fee fee = new Fee();
				fee.setLowerLimit(i);
				fee.setUpperLimit(i + 0.99);
				fee.setName("Freight Forwarding - Wgt " + Integer.toString(i) + " lb");
				fee.setValueJmd(startValue);
				fee.setValueUsd(startValueUSD);
				fee.setType(FeeType.Shipping);

		 		list.add(fee);

				if (i == 1) {
					startValue = startValue + 450;
					startValueUSD = startValueUSD + 3;
				} else if (i < 10) {
					startValueUSD = startValueUSD + 3;
					startValue = startValue + 350;
				} else if (i < 20 && i >= 10) {
					startValue = startValue + 300;
					startValueUSD = startValueUSD + 2.5;
				} else {
					startValue = startValue + 250;
					startValueUSD = startValueUSD + 1.5;
				}
			}
			
			Fee fee = new Fee();
			fee.setLowerLimit(Integer.MIN_VALUE);
			fee.setUpperLimit(49.99);
			fee.setName("Processing Fee");
			fee.setValueJmd(250);
			fee.setValueUsd(1.50);
			fee.setType(FeeType.Processing);
			list.add(fee);
			
			Fee fee2 = new Fee();
			fee2.setLowerLimit(50.00);
			fee2.setUpperLimit(Integer.MAX_VALUE);
			fee2.setName("Processing Fee");
			fee2.setValueJmd(800);
			fee2.setValueUsd(5.50);
			fee2.setType(FeeType.Processing);
			list.add(fee2);


			long end = System.currentTimeMillis();
			feesRepository.saveAll(list);
			logger.info("Fee Seeder ran in " + formatter.format((end - start) / 1000d) + " seconds");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
