package com.kovecmedia.redseat.service;

import java.io.IOException;

import javax.mail.MessagingException;

import com.kovecmedia.redseat.model.Mail;

public interface EmailService {

	void sendWelcomeMessage(Mail mail) throws MessagingException, IOException;

	void sendTemplate(Mail mail, String templateName) throws MessagingException;



}
