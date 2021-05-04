package com.example;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {
		public void sendMail(String to, String username, String password) {
//			String to = "saman.820@gmail.com";
			String from = "saman_820@yahoo.com";
			String host = "smtp.mail.yahoo.com";
			Properties properties = System.getProperties();
			properties.put("mail.smtp.host", host);
			properties.put("mail.smtp.port", "587");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.auth", "true");

			Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("saman_820@yahoo.com", "claksandoqpdeblf");
				}
			});
			
			session.setDebug(true);
			try {
				MimeMessage message = new MimeMessage(session);

				message.setFrom(new InternetAddress(from));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				message.setSubject("Welcome to ERS");
				message.setText("Welcome to Employee Reimbursement system, "+ username);

				System.out.println("sending...");
				Transport.send(message);
				System.out.println("Sent message successfully....");
			} catch (MessagingException mex) {
				mex.printStackTrace();
			}
		}
		
		
		
//	public static void main(String[] args) {
//		final String to = "saman.820@gmail.com";
//		final String from = "saman_820@yahoo.com";
//
//		String host = "smtp.mail.yahoo.com";
//		Properties properties = System.getProperties();
//
//		properties.put("mail.smtp.host", host);
//		properties.put("mail.smtp.port", "587");
//		properties.put("mail.smtp.starttls.enable", "true");
//		properties.put("mail.smtp.auth", "true");
//
//		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication("saman_820@yahoo.com", "claksandoqpdeblf");
//			}
//		});
//
//		session.setDebug(true);
//		try {
//			MimeMessage message = new MimeMessage(session);
//
//			message.setFrom(new InternetAddress(from));
//			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//			message.setSubject("This is for testing");
//			message.setText("This is testttt");
//
//			System.out.println("sending...");
//			Transport.send(message);
//			System.out.println("Sent message successfully....");
//		} catch (MessagingException mex) {
//			mex.printStackTrace();
//		}
//	}
}
