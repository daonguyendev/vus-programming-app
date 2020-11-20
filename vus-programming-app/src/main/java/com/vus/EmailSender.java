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
 * Tính năng gửi email của gmail
 **/
public class EmailSender {

	public static void main(String[] args) {
		// Bước 4: Tạo đối tượng senderProps để thiết lập các thuộc tính của người gửi email
		Properties senderProps = new Properties();
		senderProps.put("mail.smtp.auth", true);
		senderProps.put("mail.smtp.host", "smtp.gmail.com");
		senderProps.put("mail.smtp.socketFactory.port", 465);
		senderProps.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		senderProps.put("mail.smtp.port", 465);
		
		// Bước 5: Tạo đối tượng senderSession (phiên làm việc của người gửi) 
		// và nhận các thuộc tính của người gửi email từ đối tượng senderProps
		Session senderSession = Session.getDefaultInstance(senderProps, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("vus.programming@gmail.com", "Vus@PgMJS2020");
			}
		});

		// Bước 6: Soạn và gửi email
		try {
			// Bước 6.1: Tạo danh sách đối tượng người nhận email (ở đây có 2 người nhận thôi)
			String to = "daonguyen.dev@gmail.com , anh.le2708@hcmut.edu.vn, dao.nbm1502@gmail.com";
			InternetAddress[] receivers = InternetAddress.parse(to, true);
			
			
			// Bước 6.2: Soạn email
			MimeMessage myEmail = new MimeMessage(senderSession);
			myEmail.setRecipients(Message.RecipientType.TO, receivers);
			myEmail.setSubject("Hội thảo lập trình");
			myEmail.setText("Chào mừng mọi người đến với buổi hội thảo lập trình hôm nay nhé!");
			
			// Bước 6.3: Gửi email và xuất thông báo gửi email thành công
			Transport.send(myEmail);
			System.out.println("Thư của bạn đã được gửi thành công!!!");
		} catch (MessagingException ex) {
			// Bước 6.4: Thông báo cho người gửi biết đã gửi email thất bại
			System.out.println("Thư của bạn đã được gửi thất bại!!!");
			throw new RuntimeException(ex);
		}
	}

}
