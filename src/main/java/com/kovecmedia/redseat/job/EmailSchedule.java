package com.kovecmedia.redseat.job;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kovecmedia.redseat.doa.JobHistoryRepository;
import com.kovecmedia.redseat.doa.MessageQueueRepository;
import com.kovecmedia.redseat.doa.UserRepository;
import com.kovecmedia.redseat.entity.JobHistory;
import com.kovecmedia.redseat.entity.MessageQueue;
import com.kovecmedia.redseat.entity.User;
import com.kovecmedia.redseat.model.JobStatus;
import com.kovecmedia.redseat.model.MessageStatus;
import com.kovecmedia.redseat.service.EmailService;

@Component
public class EmailSchedule {
	private Logger logger = Logger.getLogger(this.getClass());

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	private EmailService emailService;

	@Autowired
	private MessageQueueRepository messageQueueRepository;

	@Autowired
	private JobHistoryRepository jobHistoryRepository;

	@Autowired
	private UserRepository userRepository;

	@Scheduled(initialDelay = 30000, fixedDelay = 300000) // 5 minutes
	public void runSchedule() throws MessagingException, IOException {

		long millis = System.currentTimeMillis();
		JobHistory history = new JobHistory();
		java.sql.Date date = new java.sql.Date(millis);
		java.sql.Time time = new java.sql.Time(millis);
		history.setRundate(date);
		history.setRuntime(time);

		history.setStatus(JobStatus.Rnuning);

		try {

			List<MessageQueue> messages = messageQueueRepository.findByStatus(MessageStatus.NOTSENT);

			if (messages != null) {
				jobHistoryRepository.save(history);

				logger.info("Welcome Email Job Stated");

				for (MessageQueue message : messages) {
					User user = userRepository.findByEmail(message.getEmail()).get();
					try {
						Thread.sleep(10000);
					} catch (InterruptedException ex) {
						logger.log(Level.FATAL, ex);
						Thread.currentThread().interrupt();
						return;
					}

					emailService.sendTemplate(user, message.getScheduledId().getTemplateName());
					message.setStatus(MessageStatus.SENT);
					message.setRundate(date);
					messageQueueRepository.save(message);

				}
				millis = System.currentTimeMillis();
				date = new java.sql.Date(millis);
				time = new java.sql.Time(millis);
				history.setRundate(date);
				history.setRuntime(time);

				history.setStatus(JobStatus.Passed);

				jobHistoryRepository.save(history);

			}

		} catch (Exception e) {

			millis = System.currentTimeMillis();
			date = new java.sql.Date(millis);
			time = new java.sql.Time(millis);
			history.setRundate(date);
			history.setRuntime(time);
			e.printStackTrace();
			history.setStatus(JobStatus.Failed);
			e.printStackTrace();
		}

		logger.info("Welcome Email Job End");
	}

}
