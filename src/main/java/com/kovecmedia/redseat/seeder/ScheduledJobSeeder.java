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
			List<ScheduledJob> list = new ArrayList<>();
			NumberFormat formatter = new DecimalFormat("#0.00000");
			long start = System.currentTimeMillis();

			ScheduledJob scheduledJob = new ScheduledJob();
			scheduledJob.setIsActive(true);
			scheduledJob.setTemplateName("welcome");
			scheduledJob.setType(JobType.EMAIL);

			ScheduledJob scheduledJob2 = new ScheduledJob();
			scheduledJob2.setIsActive(true);
			scheduledJob2.setTemplateName("billing");
			scheduledJob2.setType(JobType.EMAIL);
			
			ScheduledJob scheduledJob3 = new ScheduledJob();
			scheduledJob3.setIsActive(true);
			scheduledJob3.setTemplateName("resetpassword");
			scheduledJob3.setType(JobType.EMAIL);
			
			ScheduledJob scheduledJob4 = new ScheduledJob();
			scheduledJob4.setIsActive(true);
			scheduledJob4.setTemplateName("customs");
			scheduledJob4.setType(JobType.EMAIL);
			
			
			ScheduledJob scheduledJob5 = new ScheduledJob();
			scheduledJob5.setIsActive(true);
			scheduledJob5.setTemplateName("delivery");
			scheduledJob5.setType(JobType.EMAIL);
			
			
			ScheduledJob scheduledJob6 = new ScheduledJob();
			scheduledJob6.setIsActive(true);
			scheduledJob6.setTemplateName("newpackage");
			scheduledJob6.setType(JobType.EMAIL);
			
			
			ScheduledJob scheduledJob7 = new ScheduledJob();
			scheduledJob7.setIsActive(true);
			scheduledJob7.setTemplateName("arrivedpackage");
			scheduledJob7.setType(JobType.EMAIL);
			
			ScheduledJob scheduledJob8 = new ScheduledJob();
			scheduledJob8.setIsActive(true);
			scheduledJob8.setTemplateName("deliveryonisway");
			scheduledJob8.setType(JobType.EMAIL);

			list.add(scheduledJob);
			list.add(scheduledJob2);
			list.add(scheduledJob3);
			list.add(scheduledJob4);
			list.add(scheduledJob5);
			list.add(scheduledJob6);
			list.add(scheduledJob7);
			list.add(scheduledJob8);

			long end = System.currentTimeMillis();
			scheduledJobRepository.saveAll(list);
			logger.info("ScheduledJob Seeder ran in " + formatter.format((end - start) / 1000d) + " seconds");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
