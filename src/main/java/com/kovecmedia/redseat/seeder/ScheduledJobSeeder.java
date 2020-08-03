package com.kovecmedia.redseat.seeder;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kovecmedia.redseat.doa.ScheduledJobRepository;
import com.kovecmedia.redseat.entity.ScheduledJob;
import com.kovecmedia.redseat.model.JobType;
@Component
public class ScheduledJobSeeder {
	private Logger logger = Logger.getLogger(ScheduledJobSeeder.class);
	@Autowired
	private ScheduledJobRepository scheduledJobRepository;

	public void run() {
		try {
			List<ScheduledJob> list = new ArrayList<ScheduledJob>();
			NumberFormat formatter = new DecimalFormat("#0.00000");
			long start = System.currentTimeMillis();

			long millis = System.currentTimeMillis();
			java.sql.Date date = new java.sql.Date(millis);

			ScheduledJob scheduledJob = new ScheduledJob();
			scheduledJob.setRundate(date);
			scheduledJob.setStatus("successful");
			scheduledJob.setTemplateName("welcome");
			scheduledJob.setType(JobType.EMAIL);

			list.add(scheduledJob);

			long end = System.currentTimeMillis();
			scheduledJobRepository.saveAll(list);
			logger.info("ScheduledJob Seeder ran in " + formatter.format((end - start) / 1000d) + " seconds");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
