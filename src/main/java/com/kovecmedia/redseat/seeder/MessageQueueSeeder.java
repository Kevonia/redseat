package com.kovecmedia.redseat.seeder;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.kovecmedia.redseat.doa.MessageQueueRepository;
import com.kovecmedia.redseat.doa.ScheduledJobRepository;
import com.kovecmedia.redseat.doa.UserRepository;
import com.kovecmedia.redseat.entity.MessageQueue;
import com.kovecmedia.redseat.model.MessageStatus;

@Component
public class MessageQueueSeeder {

	private Logger logger = Logger.getLogger(MessageQueueSeeder.class);
	@Autowired
	private MessageQueueRepository messageQueueRepository;

	@Autowired
	private ScheduledJobRepository scheduledJobRepository;
	
	@Autowired
	private UserRepository userRepository;

	public void run() {
		try {
			List<MessageQueue> list = new ArrayList<MessageQueue>();
			NumberFormat formatter = new DecimalFormat("#0.00000");
			Faker faker = new Faker();
			long start = System.currentTimeMillis();

			for (int i = 1; i <= 5; i++) {
				MessageQueue messageQueue = new MessageQueue();

				messageQueue.setEmail(userRepository.findById((long) 100).get().getEmail());
				messageQueue.setPhonenumber("");
				messageQueue.setRundate(null);
				messageQueue.setStatus(MessageStatus.NOTSENT);
				messageQueue.setScheduledId(scheduledJobRepository.getOne((long) 2));
				list.add(messageQueue);
			}

			long end = System.currentTimeMillis();
			messageQueueRepository.saveAll(list);
			logger.info("MessageQueue Seeder ran in " + formatter.format((end - start) / 1000d) + " seconds");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
