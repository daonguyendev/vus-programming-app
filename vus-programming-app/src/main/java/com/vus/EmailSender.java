package com.vus;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Send email to your friends
 **/
public class EmailSender {

	public static void main(String[] args) {
		// Create an props object
		Properties props = new Properties();
		props.put(CommonConstant.SMTP_AUTH, CommonConstant.IS_AUTH_ENABLE);
		props.put(CommonConstant.SMTP_HOST, CommonConstant.HOST_NAME);
		props.put(CommonConstant.SMTP_SOCKET_FACTORY_PORT, CommonConstant.SSL_PORT);
		props.put(CommonConstant.SMTP_SOCKET_FACTORY_CLASS, CommonConstant.SSL_CLASS);
		props.put(CommonConstant.SMTP_PORT, CommonConstant.SSL_PORT);
		
		// Create session for object created
		Session session = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(CommonConstant.APP_EMAIL, CommonConstant.APP_PASSWORD);
			}
		});
		
		// Compose message to send email
		try {
			// Create recipients
			String recipients = CommonConstant.FIRST_RECEIVE_EMAIL 
									+ "," + CommonConstant.SECOND_RECEIVE_EMAIL;
			InternetAddress[] toRecipients = InternetAddress.parse(recipients, true);
			
			// Compose email
			MimeMessage message = new MimeMessage(session);
			message.setRecipients(Message.RecipientType.TO, toRecipients);
			message.setSubject(CommonConstant.EMAIL_SUBJECT);
			message.setText(CommonConstant.EMAIL_CONTENT);
			
			// Sending email
			Transport.send(message);
			
			// Email sending confirmation
			System.out.println(CommonConstant.EMAIL_SENDING_CONFIRMATION);
		} catch (MessagingException ex) {
			throw new RuntimeException(ex);
		}
	}

}
