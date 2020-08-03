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

import com.kovecmedia.redseat.doa.MessageQueueRepository;
import com.kovecmedia.redseat.entity.MessageQueue;
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

	@Scheduled(fixedRate = 300000)
	public void RunSchedule() throws MessagingException, IOException {

		List<MessageQueue> Messages = messageQueueRepository.findByStatus(MessageStatus.NOTSENT);
		logger.info("Welcome Email Job Stated");
		for (MessageQueue Message : Messages) {

			Mail mail = new Mail();
			mail.setFrom("no-reply@memorynotfound.com");
			mail.setMailTo(Message.getEmail());

			mail.setSubject("Sending Email with Thymeleaf HTML Template Example");

			Map<String, Object> model = new HashMap<String, Object>();
			model.put("name", "Memorynotfound.com");
			model.put("location", "Belgium");
			model.put("signature", "http://memorynotfound.com");
			mail.setProps(model);
			try {
				Thread.sleep(3000);
				long millis = System.currentTimeMillis();
				java.sql.Date date = new java.sql.Date(millis);
				emailService.sendWelcomeMessage(mail, "welcome");
				Message.setStatus(MessageStatus.SENT);
				Message.setRundate(date);
				messageQueueRepository.save(Message);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		logger.info("Welcome Email Job End");
	}

}
