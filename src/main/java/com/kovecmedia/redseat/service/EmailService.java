package com.kovecmedia.redseat.service;

import java.io.IOException;

import javax.mail.MessagingException;

import com.kovecmedia.redseat.entity.User;
import com.lowagie.text.DocumentException;

public interface EmailService {

	void sendWelcomeMessage(User user) throws MessagingException, IOException;

	void sendCustomsMessage(User user) throws MessagingException, IOException;

	void sendNewPackage(User user) throws MessagingException, IOException;

	void sendDelivery(User user) throws MessagingException, IOException;

	void sendArrivedPackage(User user) throws MessagingException, IOException;

	void sendDeliveryOnIsWay(User user) throws MessagingException, IOException;

	void sendBillingInvoice(User user) throws MessagingException, IOException, DocumentException;

	void sendResetPassword(User user) throws MessagingException, IOException, DocumentException;

	void sendTemplate(User user, String templateName) throws MessagingException, IOException, DocumentException;

}
