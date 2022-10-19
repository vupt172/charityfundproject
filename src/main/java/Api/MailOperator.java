package Api;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class MailOperator {
	public static final String SMTP_HOST = "smtp.gmail.com";
	public static final int SMTP_PORT = 587;
	public static final String GMAIL_USERNAME = "duantuthien123@gmail.com";
	public static final String GMAIL_APP_PASSWORD = "lcixdlefyqilyodu";
	private String username;
	private String password;
	private Properties prop;
	private Session session;

	public MailOperator(String username, String pwd) {
		this.username = username;
		this.password = pwd;
		prop = new Properties();
		prop.put("mail.smtp.host",SMTP_HOST);
		prop.put("mail.smtp.port", SMTP_PORT);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true"); // TLS
		session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(MailOperator.this.username, MailOperator.this.password);
			}
		});

	}

	public void sendMessage(String from, String to, String subject, String msg) {
		try {
			Message message = new MimeMessage(this.session);
			message.setFrom(new InternetAddress(from));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setText(msg);
			message.setContent(msg,"text/html; charset=utf-8");
			Transport.send(message);
			System.out.println("Done");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
