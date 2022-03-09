package br.com.util;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	
	public static void enviarMail(String destinatario, String assunto, String corpo) throws IOException{
	
		String mailPortaSMTP = "587";
		String mailHost = "smtp.gmail.com";
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", mailHost);
		props.put("mail.smtp.port", mailPortaSMTP);
		props.put("mail.smtp.socketFactory.port", mailPortaSMTP);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.ssl.trust", mailHost);
		props.put("mail.smtp.auth", true);
		props.put("mail.debug", true);
		
		SimpleAuth auth = new SimpleAuth();
		auth.getPasswordAuthentication();
		
		Session session = Session.getDefaultInstance(props, auth);
		session.setDebug(true);
		
		MimeMessage message = new MimeMessage(session);
		try {
			
			Address from = new InternetAddress("seuacervo.contato@gmail.com");
			Address to = new InternetAddress(destinatario);
			
			message.setFrom(from); 							// Endereço que envia 
			message.addRecipient(RecipientType.TO, to);		// Endereço que recebe
			message.setSentDate(new Date());
			message.setSubject(assunto); 					// Assunto 
			message.setText(corpo);							// Corpo do Texto 	
			Transport.send(message);		
			
		}catch (Exception e) {
			System.out.print(e.getMessage());
		}	
	}
	
}
