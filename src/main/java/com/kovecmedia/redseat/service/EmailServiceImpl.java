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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.kovecmedia.redseat.doa.BillingRepository;
import com.kovecmedia.redseat.doa.DeliveryRepository;
import com.kovecmedia.redseat.doa.FeesRepository;
import com.kovecmedia.redseat.doa.ForgetPasswordRepository;
import com.kovecmedia.redseat.doa.PackageRepository;
import com.kovecmedia.redseat.entity.Billing;
import com.kovecmedia.redseat.entity.Delivery;
import com.kovecmedia.redseat.entity.Fee;
import com.kovecmedia.redseat.entity.ForgetPassword;
import com.kovecmedia.redseat.entity.Package;
import com.kovecmedia.redseat.entity.User;
import com.kovecmedia.redseat.model.BillingStatus;
import com.kovecmedia.redseat.model.Mail;
import com.kovecmedia.redseat.model.PackageLocation;
import com.kovecmedia.redseat.model.SendingEmailAddress;
import com.kovecmedia.redseat.payload.respond.PackageLabel;
import com.lowagie.text.DocumentException;

@Service
public class EmailServiceImpl implements EmailService {

	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private PackageRepository packageRepository;

	@Autowired
	private BillingRepository billingRepository;

	@Autowired
	private ForgetPasswordRepository forgetPasswordRepository;

	@Autowired
	private FeesRepository feeRepository;
	
	@Autowired
	private  DeliveryRepository deliveryRepository;

	@Autowired
	private org.thymeleaf.spring5.SpringTemplateEngine templateEngine;

	@Override
	public void sendWelcomeMessage(User user) throws MessagingException, IOException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = getEmailHelper(message);

		Mail mail = new Mail();

		mail.setFrom(SendingEmailAddress.NO_REPY.toString());
		mail.setMailTo(user.getEmail());

		mail.setSubject("Welcome to RedSeat");

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("name", user.getName());
		model.put("logo", "https://dashboard.rscja.com/assets/img/logo.png");
		model.put("id", user.getId());
		mail.setProps(model);

		mail.setMailTo(user.getEmail());

		Context context = new Context();
		context.setVariables(mail.getProps());

		String html = templateEngine.process("welcome", context);

		helper.setTo(mail.getMailTo());
		helper.setText(html, true);
		helper.setSubject(mail.getSubject());
		helper.setFrom(mail.getFrom());

		emailSender.send(message);
	    
		sendAdminEmail(user);
	}

	@Override
	public void sendTemplate(User user, String templateName,long id) throws MessagingException, IOException, DocumentException {
		
		if (templateName.equals("welcome")) {

			sendWelcomeMessage(user);

		} else if (templateName.equals("billing")) {
			sendBillingInvoice(user);
		} else if (templateName.equals("customs")) {
			sendCustomsMessage(user,id);
		} else if (templateName.equals("delivery")) {
			sendDelivery(user);
		} else if (templateName.equals("deliveryonisway")) {
			sendDeliveryOnIsWay(user);
		} else if (templateName.equals("newpackage")) {
			sendNewPackage(user,id);
		} else if (templateName.equals("arrivedpackage")) {
			sendArrivedPackage(user);
		} else if (templateName.equals("resetpassword")) {
			sendResetPassword(user);
		}
		else if (templateName.equals("prealert")) {
			sendPerAerlt(user,id);
		}
		else if (templateName.equals("delayed")) {
			sendDelayed(user,id);
		}
	}

	@Override
	public void sendBillingInvoice(User user) throws MessagingException, IOException, DocumentException {
		 try {
		List<com.kovecmedia.redseat.entity.Package> packagelist = packageRepository.findByUserIdAndEmailSent(user,
				false);

		for (Package packageitem : packagelist) {

			if (!packageitem.isPreAlert()) {

				Billing billing = new Billing();
				Set<Fee> billingfees = new HashSet();

				billing.setDescription(packageitem.getDescription() + '-' + user.getName());
				billing.setPackageId(packageitem);
				billing.setStatus(BillingStatus.UNPAID);

				for (Fee item : feeRepository.findAll()) {
					if (packageitem.getWeight() < item.getUpperLimit()
							&& packageitem.getWeight() >= item.getLowerLimit()) {
						billingfees.add(item);
					}

				}

				billing.setFee(billingfees);

				billingRepository.save(billing);

				processInvoice(user, billing);

				packageRepository.save(packageitem);

			}
		}
		
		  }
		  catch(Exception e) {
			  logger.info(e.getMessage());
			}

	}

	private void processInvoice(User user, Billing invoice) throws IOException, DocumentException, MessagingException {
		java.util.Date today = Calendar.getInstance().getTime();

		byte[] fileContent = FileUtils
				.readFileToByteArray(new File(System.getProperty("user.home") + File.separator + "logo.png"));
		String encodedString = Base64.getEncoder().encodeToString(fileContent);

		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = getEmailHelper(message);

		String image = "data:image/png;base64," + encodedString;
		if (invoice.getFee().size() != 0) {
			Mail mail = new Mail();

			String itemname = user.getName() + "-" + invoice.getId();

			System.out.println(itemname);

			mail.setFrom(SendingEmailAddress.BILLING.toString());
			mail.setMailTo(user.getEmail());

			mail.setSubject("Sending Email with Thymeleaf HTML Template Example");

			Map<String, Object> model = new HashMap<String, Object>();

			model.put("invoice", invoice.getId());
			model.put("today", today);
			model.put("name", user.getName());
			model.put("email", user.getEmail());
			model.put("billing", invoice.getFee());
			model.put("total", invoice.getFee().stream().mapToDouble(o -> o.getValueJmd()).sum());
			mail.setProps(model);

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

	private String generatePdfFromHtml(String html, String name) throws IOException, DocumentException {
		String outputFolder = "D:\\"+name + ".pdf";
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

	@Override
	public void sendResetPassword(User user) throws MessagingException, IOException, DocumentException {
		// TODO Auto-generated method stub
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = getEmailHelper(message);

		ForgetPassword forgetPassword = forgetPasswordRepository.findByUsedAndUser(false, user);

		if (forgetPassword != null) {

			Mail mail = new Mail();

			mail.setFrom(SendingEmailAddress.NO_REPY.toString());
			mail.setMailTo(forgetPassword.getUser().getEmail());

			mail.setSubject("FORGOT YOUR PASSWORD?");

			Map<String, Object> model = new HashMap<String, Object>();
			model.put("name", forgetPassword.getUser().getName());
			model.put("token", "https://dashboard.rscja.com/reset/" + forgetPassword.getToken());

			mail.setProps(model);

			mail.setMailTo(user.getEmail());

			Context context = new Context();
			context.setVariables(mail.getProps());

			String html = templateEngine.process("resetpassword", context);

			helper.setTo(mail.getMailTo());
			helper.setText(html, true);
			helper.setSubject(mail.getSubject());
			helper.setFrom(mail.getFrom());

			emailSender.send(message);
		}

	}

	@Override
	public void sendCustomsMessage(User user,long id) throws MessagingException, IOException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = getEmailHelper(message);

		Mail mail = new Mail();

		mail.setFrom(SendingEmailAddress.SHIP.toString());
		mail.setMailTo(user.getEmail());
		 
		  try {
			  com.kovecmedia.redseat.entity.Package packagelist = packageRepository.findById(id).get();
				mail.setSubject("Barrel ah Pack");

				Map<String, Object> model = new HashMap<String, Object>();
				model.put("name", user.getName());
				model.put("courier", packagelist.getSeller());
				model.put("trackingnumber", packagelist.getTrackingNumber());
				model.put("weight", Math.ceil(packagelist.getWeight()));
				model.put("description", packagelist.getDescription());
				model.put("value", packagelist.getValue());
				mail.setProps(model);

				mail.setMailTo(user.getEmail());

				Context context = new Context();
				context.setVariables(mail.getProps());

				String html = templateEngine.process("customs", context);

				helper.setTo(mail.getMailTo());
				helper.setText(html, true);
				helper.setSubject(mail.getSubject());
				helper.setFrom(mail.getFrom());

				emailSender.send(message);
		  }
		  catch(Exception e) {
			  logger.info(e.getMessage());
			}
		
			 
	

	}

	@Override
	public void sendNewPackage(User user,long id) throws MessagingException, IOException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = getEmailHelper(message);

		
		 
		 try {  
	  com.kovecmedia.redseat.entity.Package packagelist = packageRepository.findById(id).get();
         
	
	
		Mail mail = new Mail();

		mail.setFrom(SendingEmailAddress.SHIP.toString());
		mail.setMailTo(user.getEmail());

		mail.setSubject("Safe and Sound!");

		Map<String, Object> model = new HashMap<String, Object>();
		
		
		model.put("name", user.getName());
		model.put("courier", packagelist.getSeller());
		model.put("trackingnumber", packagelist.getTrackingNumber());
		model.put("weight", Math.ceil(packagelist.getWeight()));
		model.put("description", packagelist.getDescription());
		model.put("value", packagelist.getValue());
	
		mail.setProps(model);

		mail.setMailTo(user.getEmail());

		Context context = new Context();
		context.setVariables(mail.getProps());

		String html = templateEngine.process("newpackage", context);

		helper.setTo(mail.getMailTo());
		helper.setText(html, true);
		helper.setSubject(mail.getSubject());
		helper.setFrom(mail.getFrom());

		emailSender.send(message);
		 }
		catch(Exception e) {
			  logger.info(e.getMessage());
			}
	}

	@Override
	public void sendDelivery(User user) throws MessagingException, IOException {
		// TODO Auto-generated method stub
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = getEmailHelper(message);

		Mail mail = new Mail();
		
		Delivery delivery = deliveryRepository.findByUser(user).get(0);
		

		mail.setFrom(SendingEmailAddress.SHIP.toString());
		mail.setMailTo(user.getEmail());

		mail.setSubject("Barrel ah Pack");

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("name", user.getName());
		model.put("day", delivery.getUpdate_at());
		model.put("signed", delivery.getSignedBy());
		mail.setProps(model);

		mail.setMailTo(user.getEmail());

		Context context = new Context();
		context.setVariables(mail.getProps());

		String html = templateEngine.process("delivery", context);

		helper.setTo(mail.getMailTo());
		helper.setText(html, true);
		helper.setSubject(mail.getSubject());
		helper.setFrom(mail.getFrom());

		emailSender.send(message);

	}

	@Override
	public void sendArrivedPackage(User user) throws MessagingException, IOException {
		// TODO Auto-generated method stub
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = getEmailHelper(message);

		Mail mail = new Mail();
		

		mail.setFrom(SendingEmailAddress.SHIP.toString());
		mail.setMailTo(user.getEmail());

		mail.setSubject("Package Available");

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("name", user.getName());
		mail.setProps(model);

		mail.setMailTo(user.getEmail());

		Context context = new Context();
		context.setVariables(mail.getProps());

		String html = templateEngine.process("arrivedpackage", context);

		helper.setTo(mail.getMailTo());
		helper.setText(html, true);
		helper.setSubject(mail.getSubject());
		helper.setFrom(mail.getFrom());

		emailSender.send(message);
	}

	@Override
	public void sendDeliveryOnIsWay(User user) throws MessagingException, IOException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = getEmailHelper(message);

		Mail mail = new Mail();		
		Delivery delivery = deliveryRepository.findByUser(user).get(0);
		
		
		
		mail.setFrom(SendingEmailAddress.SHIP.toString());
		mail.setMailTo(user.getEmail());

		mail.setSubject("Deh Pon di Move");

		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("name", user.getName());
		model.put("address", delivery.getDeliveryAddress());
		model.put("day", delivery.getSchedule_date());
		model.put("time", delivery.getSchedule_Peroid());
		model.put("img", "https://redseat-pdf.s3.amazonaws.com/"+delivery.getPackages().get(0).getPackageImg());
		
		
		mail.setProps(model);

		mail.setMailTo(user.getEmail());

		Context context = new Context();
		context.setVariables(mail.getProps());

		String html = templateEngine.process("deliveryonisway", context);

		helper.setTo(mail.getMailTo());
		helper.setText(html, true);
		helper.setSubject(mail.getSubject());
		helper.setFrom(mail.getFrom());

		emailSender.send(message);

	}

	@Override
	public void sendPerAerlt(User user,long id) throws MessagingException, IOException {
		// TODO Auto-generated method stub
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = getEmailHelper(message);

		Mail mail = new Mail(); 
		
		
		
		com.kovecmedia.redseat.entity.Package packagelist = packageRepository.findById(id).get();
		
		mail.setFrom(SendingEmailAddress.SHIP.toString());
		mail.setMailTo(user.getEmail());

		mail.setSubject("Deh Pon di Move");

		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("name", user.getName());
		model.put("description", packagelist.getDescription());
		model.put("value", packagelist.getValue());
		model.put("trackingnumber", packagelist.getTrackingNumber());
		
		
		
		mail.setProps(model);

		mail.setMailTo(user.getEmail());

		Context context = new Context();
		context.setVariables(mail.getProps());

		String html = templateEngine.process("prealert", context);

		helper.setTo(mail.getMailTo());
		helper.setText(html, true);
		helper.setSubject(mail.getSubject());
		helper.setFrom(mail.getFrom());

		emailSender.send(message);
		
		sendAdminPerEmailAerlt(user,id);
	}

	@Override
	public void sendAdminEmail(User user) throws MessagingException, IOException {
		// TODO Auto-generated method stub
		
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = getEmailHelper(message);

		Mail mail = new Mail();

		mail.setFrom(SendingEmailAddress.NO_REPY.toString());
		mail.setMailTo(user.getEmail());

		mail.setSubject("New User Created");

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("name", user.getName());
		model.put("logo", "https://dashboard.rscja.com/assets/img/logo.png");
		model.put("id", user.getId());
		mail.setProps(model);

		mail.setMailTo("info@rscja.com");

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
	public void sendAdminPerEmailAerlt(User user,long id) throws MessagingException, IOException {
		// TODO Auto-generated method stub
		
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = getEmailHelper(message);

		Mail mail = new Mail();
		
		
		
		com.kovecmedia.redseat.entity.Package packagelist = packageRepository.findById(id).get();
		
		mail.setFrom(SendingEmailAddress.SHIP.toString());
		mail.setMailTo(user.getEmail());

		mail.setSubject("Deh Pon di Move");

		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("name", user.getName());
		model.put("description", packagelist.getDescription());
		model.put("value", packagelist.getValue());
		model.put("trackingnumber", packagelist.getTrackingNumber());
		
		
		
		mail.setProps(model);

		mail.setMailTo("info@rscja.com");

		Context context = new Context();
		context.setVariables(mail.getProps());

		String html = templateEngine.process("adminperalert", context);

		helper.setTo(mail.getMailTo());
		helper.setText(html, true);
		helper.setSubject(mail.getSubject());
		helper.setFrom(mail.getFrom());

		emailSender.send(message);
		
	}

	@Override
	public void sendDelayed(User user, long id) throws MessagingException, IOException {
		// TODO Auto-generated method stub
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = getEmailHelper(message);

		Mail mail = new Mail();
		
		
		
		com.kovecmedia.redseat.entity.Package packagelist = packageRepository.findById(id).get();
		
		mail.setFrom(SendingEmailAddress.SHIP.toString());
		mail.setMailTo(user.getEmail());

		mail.setSubject("Oh no, Oh No, Oh No nO NO No!");

		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("name", user.getName());
		
		model.put("reason", packagelist.getReason());
		
		mail.setProps(model);

		mail.setMailTo(user.getEmail());

		Context context = new Context();
		context.setVariables(mail.getProps());

		String html = templateEngine.process("delayed", context);

		helper.setTo(mail.getMailTo());
		helper.setText(html, true);
		helper.setSubject(mail.getSubject());
		helper.setFrom(mail.getFrom());

		emailSender.send(message);
	}

	@Override
	public void sendLabel(PackageLabel barcode) throws MessagingException, IOException, DocumentException {
		// TODO Auto-generated method stub
			
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = getEmailHelper(message);
		
			Mail mail = new Mail();
	

			mail.setFrom(SendingEmailAddress.BILLING.toString());
			mail.setMailTo("Kevonia123@gmail.com");

			mail.setSubject("Sending Email with Thymeleaf HTML Template Example");

			Map<String, Object> model = new HashMap<String, Object>();

             
			String img1= "data:image/png;base64,"+ Base64.getEncoder().encodeToString(barcode.getQrTop()); 
			String img2= "data:image/png;base64,"+ Base64.getEncoder().encodeToString(barcode.getQrPackage()); 
			model.put("package", barcode);
//			model.put("qrtop",type+Base64.getEncoder().encodeToString(barcode.getQrTop()) );
//			model.put("qrtop", type+ Base64.getEncoder().encodeToString(barcode.getQrPackage()));

			
		
			String image = "data:image/png;base64,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";
			
			mail.setProps(model);
  
			mail.setMailTo("Kevonia123@gmail.com");

			Context context = new Context();
			context.setVariables(mail.getProps());
			context.setVariable("qrtop", img1);
			context.setVariable("qrpackage", img2);
			context.setVariable("logo", image);
			
			 String html = templateEngine.process("lables/package", context);
			

		     String output = generatePdfFromHtml(html, "label");
			
			helper.setTo(mail.getMailTo());
			helper.setText(html, true);
			helper.setSubject(mail.getSubject());
			helper.setFrom(mail.getFrom());

			//emailSender.send(message);
	}
}