/**
 * @Description : 使用 MimeMessageHelper 發送
 * @ClassName : SenderExampleMain2.java
 * @Copyright : Copyright (c) 2025 
 * @ModifyHistory : 
 *  v1.00, 2025/05/10, frankchang
 *   1) First Release.
 */

package com.example.smtp.mail.SmtpMailApplication.main;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.ResourceUtils;

import com.example.smtp.mail.SmtpMailApplication.config.SenderConfig;
import com.example.smtp.mail.SmtpMailApplication.config.SmtpConfig;

import jakarta.mail.Authenticator;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeUtility;

public class SenderExampleMain2 {
	
	private static String SMTP_HOST_NAME = "smtp.mail.yahoo.com";

	private static SmtpConfig emailConfig = new SmtpConfig();
	
	private static SenderConfig senderConfig = new SenderConfig();
	
	private static String filePath = "MailContent.txt";
	
	private static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	public static void main(String[] args) throws MessagingException, IOException {
		
		// properties setting
	    Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.smtp.port", "465");        
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.ssl","true");
        props.put("mail.smtp.auth", "true"); 

        // session setting
        Session session = Session.getInstance(props,
              new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(emailConfig.getUserName(), emailConfig.getUserPxxd());
                }
              });
        
        session.setDebug(true);
		
        // message setting
		MimeMessage message = new MimeMessage(session);
		
      	MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
      	
      	helper.setTo(senderConfig.getEmail());             
      	helper.setSubject(senderConfig.getSubject() + ", 時間 " + LocalTime.now().format(DTF));          
      	String encodedSendNickName = MimeUtility.encodeText("張阿堯", "UTF-8", "B");             
      	helper.setFrom(new InternetAddress(senderConfig.getSnederEmail(), encodedSendNickName));             
      	helper.setText(getContent(), true);                     

  		// 傳送郵件             
        System.out.println("sending...");
        
      	Transport transport = session.getTransport();
      	transport.send(message);
      	
        System.out.println(LocalTime.now().format(DTF) + " Sent message successfully....");

	}

	/**
	 * 讀取 resources 內容
	 * 
	 * @return
	 * @throws IOException 
	 */
	private static String getContent() throws IOException {
		
		File file = ResourceUtils.getFile("classpath:" + filePath);
		String fileContent = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);

		System.out.println(fileContent);
		
		return fileContent;
	}
}
