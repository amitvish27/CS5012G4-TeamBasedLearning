package edu.umsl.java.util;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class MailApi {
	public static boolean send(String from, String password, String to, String sub, String msg) {
		// Get properties object
		boolean flag=false;
		ReadProperties.loadPropertiesFile();
		Properties props = ReadProperties.getProp();

		// get Session
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});
		// compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(sub);
			message.setText(msg);
			// send message
			Transport.send(message);
			flag=true;
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return flag;
	}
	public static void main(String[] args) {
		ReadProperties.loadPropertiesFile();
		// from,password,to,subject,message
		if(MailApi.send(ReadProperties.getMailUser(), ReadProperties.getMailPswd(), "amitvish27@gmail.com", "Test Mail", "Hi, this is a test mail."))
			System.out.println("Mail sent");
	}
}
