package com.kovecmedia.redseat.job;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kovecmedia.redseat.doa.JobHistoryRepository;
import com.kovecmedia.redseat.doa.MessageQueueRepository;
import com.kovecmedia.redseat.doa.UserRepository;
import com.kovecmedia.redseat.entity.JobHistory;
import com.kovecmedia.redseat.entity.MessageQueue;
import com.kovecmedia.redseat.model.JobStatus;
import com.kovecmedia.redseat.model.Mail;
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

	@Scheduled(fixedRate = 300000)
	public void runSchedule() throws MessagingException, IOException {

		long millis = System.currentTimeMillis();
		JobHistory history = new JobHistory();
		java.sql.Date date = new java.sql.Date(millis);
		java.sql.Time time = new java.sql.Time(millis);
		history.setRundate(date);
		history.setRuntime(time);

		history.setStatus(JobStatus.Rnuning);

		try {
			jobHistoryRepository.save(history);
			List<MessageQueue> messages = messageQueueRepository.findByStatus(MessageStatus.NOTSENT);
			logger.info("Welcome Email Job Stated");
			for (MessageQueue message : messages) {

				Mail mail = new Mail();

				mail.setFrom("no-reply@redseat.com");
				mail.setMailTo(message.getEmail());
				
			

				mail.setSubject("Sending Email with Thymeleaf HTML Template Example");

				Map<String, Object> model = new HashMap<String, Object>();
				model.put("name", 	userRepository.findByEmail(message.getEmail()).get().getName());
				model.put("logo", "https://seeklogo.com/images/T/Test-logo-37F8EF5B80-seeklogo.com.png");
				model.put("signature", "http://memorynotfound.com");
				mail.setProps(model);

				Thread.sleep(3000);

				emailService.sendTemplate(mail, message.getScheduledId().getTemplateName());
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
		} catch (Exception e) {

			millis = System.currentTimeMillis();
			date = new java.sql.Date(millis);
			time = new java.sql.Time(millis);
			history.setRundate(date);
			history.setRuntime(time);

			history.setStatus(JobStatus.Failed);
		}

		logger.info("Welcome Email Job End");
	}

}
