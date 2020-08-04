package com.kovecmedia.redseat.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.kovecmedia.redseat.model.Mail;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private org.thymeleaf.spring5.SpringTemplateEngine templateEngine;

	@Override
	public void sendWelcomeMessage(Mail mail) throws MessagingException, IOException {

		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());

//		helper.addAttachment("logo.png", new ClassPathResource("logo.png"));

		Context context = new Context();
		context.setVariables(mail.getProps());

		String html = templateEngine.process("welcome", context);

		helper.setTo(mail.getMailTo());
		helper.setText(html, true);
		helper.setSubject(mail.getSubject());
		helper.setFrom(mail.getFrom());
		
		emailSender.send(message);

	}

	@Override
	public void sendTemplate(Mail mail, String templateName) throws MessagingException {
    
		if (templateName.equals("welcome")) {
			try {
				sendWelcomeMessage(mail);
			} catch (MessagingException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}

}