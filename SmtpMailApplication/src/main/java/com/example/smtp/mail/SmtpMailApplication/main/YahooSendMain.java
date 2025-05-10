/**
 * @Description : TODO
 * @ClassName : YahooSendMain.java
 * @Copyright : Copyright (c) 2025 
 * @ModifyHistory : 
 *  v1.00, 2025/05/10, frankchang
 *   1) First Release.
 */

package com.example.smtp.mail.SmtpMailApplication.main;

import java.util.Properties;

import com.example.smtp.mail.SmtpMailApplication.config.EmailConfig;
import com.example.smtp.mail.SmtpMailApplication.config.SenderConfig;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class YahooSendMain {
	
	private static String SMTP_HOST_NAME = "smtp.mail.yahoo.com";

	private static EmailConfig emailConfig = new EmailConfig();
	
	private static SenderConfig senderConfig = new SenderConfig();
	
	public static void main(String[] args) throws MessagingException {
		
	    Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.smtp.port", "465");        
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.ssl","true");
        props.put("mail.smtp.auth", "true"); 

        Session session = Session.getInstance(props,
              new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(emailConfig.getUserName(), emailConfig.getUserPxxd());
                }
              });
        
        session.setDebug(true);
		
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(emailConfig.getUserName()));
		message.setContent(senderConfig.getContent(), "text/html;charset=UTF-8");
		message.setSubject(senderConfig.getSubject());
		message.setRecipients(
		        Message.RecipientType.TO,
		        InternetAddress.parse(senderConfig.getEmail())
		);
		
		
        System.out.println("sending...");
		
        Transport.send(message);
		
        System.out.println("Sent message successfully....");

			
	}
}
