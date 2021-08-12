package com.kovecmedia.redseat.service;

import java.io.IOException;

import javax.mail.MessagingException;

import com.kovecmedia.redseat.entity.User;
import com.kovecmedia.redseat.payload.respond.PackageLabel;
import com.lowagie.text.DocumentException;

public interface EmailService {

	void sendWelcomeMessage(User user) throws MessagingException, IOException;

	void sendCustomsMessage(User user,long id) throws MessagingException, IOException;

	void sendNewPackage(User user,long id) throws MessagingException, IOException;

	void sendDelivery(User user) throws MessagingException, IOException;
	
	void sendPerAerlt(User user,long id) throws MessagingException, IOException;


	void sendArrivedPackage(User user) throws MessagingException, IOException;

	void sendDeliveryOnIsWay(User user) throws MessagingException, IOException;

	void sendBillingInvoice(User user) throws MessagingException, IOException, DocumentException;

	void sendResetPassword(User user) throws MessagingException, IOException, DocumentException;
	
	void sendAdminEmail(User user) throws MessagingException, IOException;

	void sendTemplate(User user, String templateName,long id) throws MessagingException, IOException, DocumentException;

	void sendAdminPerEmailAerlt(User user,long id) throws MessagingException, IOException;

	void sendDelayed(User user,long id) throws MessagingException, IOException;
	
	void sendLabel(PackageLabel barcode) throws MessagingException, IOException, DocumentException;
	
}
