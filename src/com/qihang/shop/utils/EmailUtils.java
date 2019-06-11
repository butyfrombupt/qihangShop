package com.qihang.shop.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class EmailUtils {

	public static void sendMail(String to,String code){
		Properties props=new Properties();
		props.setProperty("mail.host", "localhost");
		Session session=Session.getInstance(props, new Authenticator(){

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("service@shop.com", "123");
			}
		}); 
		Message message=new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("service@shop.com"));
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			message.setSubject("来自启航商城官方激活邮件");
			message.setContent("<h1>启航商城官方激活邮件!点下面链接完成激活操作!</h1><h3><a href='http://127.0.0.1:8080/Shop/user_active.action?code="+code+"'> http://127.0.0.1:8080/Shop/user_active.action?code="+code+"</a></h3>", "text/html;charset=UTF-8");
		    Transport.send(message);
		} catch (AddressException e) {
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		sendMail("279833753@qq.com", "123456789");
		System.out.println("send");
	}
	

}
