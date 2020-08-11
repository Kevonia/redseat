package com.kovecmedia.redseat.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.kovecmedia.redseat.doa.PackageRepository;
import com.kovecmedia.redseat.entity.User;
import com.kovecmedia.redseat.model.Mail;
import com.kovecmedia.redseat.model.PackageLocation;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender emailSender;
	
	@Autowired
	private PackageRepository packageRepository;
	@Autowired
	private org.thymeleaf.spring5.SpringTemplateEngine templateEngine;

	@Override
	public void sendWelcomeMessage(User user) throws MessagingException, IOException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = getEmailHelper(message);
//		helper.addAttachment("logo.png", new ClassPathResource("logo.png"));

		Mail mail = new Mail();

		mail.setFrom("no-reply@redseat.com");
		mail.setMailTo(user.getEmail());

		mail.setSubject("Sending Email with Thymeleaf HTML Template Example");

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("name", user.getName());
		model.put("logo", "https://seeklogo.com/images/T/Test-logo-37F8EF5B80-seeklogo.com.png");
		model.put("signature", "http://memorynotfound.com");
		mail.setProps(model);

		mail.setFrom("no-reply@redseat.com");
		mail.setMailTo(user.getEmail());

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
	public void sendTemplate(User user, String templateName) throws MessagingException {

		if (templateName.equals("welcome")) {
			try {
				sendWelcomeMessage(user);
			} catch (MessagingException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}

	@Override
	public void sendBillingInvoice(User user) throws MessagingException, IOException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = getEmailHelper(message);
		
		List<com.kovecmedia.redseat.entity.Package> packagelist =packageRepository.findByUserIdandLocation(user, PackageLocation.WAREHOUSE);
		

		Mail mail = new Mail();

		mail.setFrom("no-reply@redseat.com");
		mail.setMailTo(user.getEmail());

		mail.setSubject("Sending Email with Thymeleaf HTML Template Example");

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("name", user.getName());
		model.put("logo", "https://seeklogo.com/images/T/Test-logo-37F8EF5B80-seeklogo.com.png");
		model.put("signature", "http://memorynotfound.com");
		mail.setProps(model);

		mail.setFrom("no-reply@redseat.com");
		mail.setMailTo(user.getEmail());

		Context context = new Context();
		context.setVariables(mail.getProps());

		String html = templateEngine.process("welcome", context);

		helper.setTo(mail.getMailTo());
		helper.setText(html, true);
		helper.setSubject(mail.getSubject());
		helper.setFrom(mail.getFrom());

		emailSender.send(message);

	}

	private MimeMessageHelper getEmailHelper(MimeMessage message) throws MessagingException {

		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());

		return helper;

	}

}