package com.niit.config;


	
	import java.util.Properties;
	import org.springframework.beans.factory.annotation.Configurable;
	import org.springframework.context.annotation.Bean;
	import org.springframework.mail.javamail.JavaMailSenderImpl;
	@Configurable
	public class MailConfig {
		@Bean
		public JavaMailSenderImpl javaMailSenderImpl(){
			JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
			mailSender.setHost("smtp.gmail.com");
			mailSender.setPort(587);
			mailSender.setUsername("soundariya0104@gmail.com");
			mailSender.setPassword("balaji1234");
			Properties prop = mailSender.getJavaMailProperties();
			prop.put("mail.transport.protocol", "smtp");
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.starttls.enable", "true");
			prop.put("mail.debug", "true");
			return mailSender;
		}
	} 