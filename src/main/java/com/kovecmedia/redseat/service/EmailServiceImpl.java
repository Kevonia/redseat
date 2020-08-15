package com.kovecmedia.redseat.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.kovecmedia.redseat.doa.BillingRepository;
import com.kovecmedia.redseat.doa.PackageRepository;
import com.kovecmedia.redseat.entity.Billing;
import com.kovecmedia.redseat.entity.Package;
import com.kovecmedia.redseat.entity.User;
import com.kovecmedia.redseat.model.BillingStatus;
import com.kovecmedia.redseat.model.Mail;
import com.kovecmedia.redseat.model.PackageLocation;
import com.lowagie.text.DocumentException;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private PackageRepository packageRepository;

	@Autowired
	private BillingRepository billingRepository;

	@Autowired
	private org.thymeleaf.spring5.SpringTemplateEngine templateEngine;

	@Override
	public void sendWelcomeMessage(User user) throws MessagingException, IOException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = getEmailHelper(message);

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
	public void sendTemplate(User user, String templateName) throws MessagingException, IOException, DocumentException {

		if (templateName.equals("welcome")) {

			sendWelcomeMessage(user);

		} else if (templateName.equals("billing")) {
			sendBillingInvoice(user);
		}

	}

	@Override
	public void sendBillingInvoice(User user) throws MessagingException, IOException, DocumentException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = getEmailHelper(message);

		List<com.kovecmedia.redseat.entity.Package> packagelist = packageRepository.findByUserIdandLocation(user,
				PackageLocation.WAREHOUSE);

		byte[] fileContent = FileUtils
				.readFileToByteArray(new File(System.getProperty("user.home") + File.separator + "logo.png"));
		String encodedString = Base64.getEncoder().encodeToString(fileContent);

		String image = "data:image/png;base64," + encodedString;

		java.util.Date today = Calendar.getInstance().getTime();
		for (Package packageitem : packagelist) {

			List<Billing> invoices = billingRepository.findbyStatusandpackageId(BillingStatus.UNPAID, packageitem);

			for (Billing invoice : invoices) {

				if (invoice.getFee().size() != 0) {
					Mail mail = new Mail();

					String itemname = user.getName() + "-" + invoice.getId();

					System.out.println(itemname);

					mail.setFrom("no-reply@redseat.com");
					mail.setMailTo(user.getEmail());

					mail.setSubject("Sending Email with Thymeleaf HTML Template Example");

					Map<String, Object> model = new HashMap<String, Object>();

					model.put("invoice", invoice.getId());
					model.put("today", today);
					model.put("name", user.getName());
					model.put("email", user.getEmail());
					model.put("billing", invoice.getFee());
					model.put("total", invoice.getFee().stream().mapToDouble(o -> o.getValue()).sum());
					mail.setProps(model);

					mail.setFrom("no-reply@redseat.com");
					mail.setMailTo(user.getEmail());

					Context context = new Context();
					context.setVariables(mail.getProps());
					context.setVariable("image", image);

					String html = templateEngine.process("billing", context);

					String output = generatePdfFromHtml(html, itemname);

					helper.addAttachment("invoice.pdf", new ClassPathResource(itemname + ".pdf"));

					helper.setTo(mail.getMailTo());
					helper.setText(html, true);
					helper.setSubject(mail.getSubject());
					helper.setFrom(mail.getFrom());

					emailSender.send(message);
					achivefile(itemname);
					
				}
			}

		}

	}

	private String generatePdfFromHtml(String html, String name) throws IOException, DocumentException {
		String outputFolder = this.getClass().getResource("/").getFile() + name + ".pdf";
		OutputStream outputStream = new FileOutputStream(outputFolder);

		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(html);
		renderer.layout();
		renderer.createPDF(outputStream);

		outputStream.close();

		return outputFolder;
	}

	private void achivefile(String name) throws IOException {
		DateTime date = DateTime.now();
		String month = date.toString("MMM");
		String srcDirectoryName = this.getClass().getResource("/").getFile();
		String achieveDirectoryName = this.getClass().getResource("/").getFile() + File.separator + "achieve"
				+ File.separator + month + File.separator;

		Path sourceFilePath = Paths.get(srcDirectoryName + name + ".pdf");
		Path targetFilePath = Paths.get(achieveDirectoryName + name + ".pdf");

		if (!Files.exists(Paths.get(achieveDirectoryName))) {
			Files.createDirectories(Paths.get(achieveDirectoryName));

		}
		Files.deleteIfExists(targetFilePath);
		Files.move(sourceFilePath, targetFilePath);

	}

	private MimeMessageHelper getEmailHelper(MimeMessage message) throws MessagingException {

		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());

		return helper;

	}

}