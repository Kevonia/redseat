package com.kovecmedia.redseat.service;

import java.io.IOException;

import javax.mail.MessagingException;

import com.kovecmedia.redseat.entity.User;

public interface EmailService {

	void sendWelcomeMessage(User user) throws MessagingException, IOException;

	void sendBillingInvoice(User user) throws MessagingException, IOException;
	
	void sendTemplate(User user, String templateName) throws MessagingException;



}
