package br.com.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SimpleAuth extends Authenticator{
	
	String username = "***";//digitar e-mail
	String password = "***"; //digitar senha
	
	
	public SimpleAuth(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public SimpleAuth() {
		
	}
	
	protected PasswordAuthentication getPasswordAuthentication() {
		try {
			return new PasswordAuthentication(username, password);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

}
