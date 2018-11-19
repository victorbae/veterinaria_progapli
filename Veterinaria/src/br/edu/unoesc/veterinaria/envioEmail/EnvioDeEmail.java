package br.edu.unoesc.veterinaria.envioEmail;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnvioDeEmail {
	public static void enviar(String email, String mensagem) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.port", "587");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("nao.responder.pet.bull@gmail.com", "ROOTPETBULL");
			}
		});
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("nao.responder.pet.bull@gmail.com"));

			Address[] toUser = InternetAddress.parse(email);

			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject("Redefini��o de senha - PET BULL");
			message.setText("Ol� caro usu�rio!" + (char) 13 + (char) 13
					+ "Recebemos sua solicita��o de altera��o de senha, geramos a "
					+ "seguinte senha tempor�ria para voc�:  " + mensagem + (char) 13
					+ "Caso, mesmo com a nova senha, n�o conseguir acesso, entre em contato conosco!" + (char) 13
					+ (char) 13 + (char) 13 + "Atenciosamente, PET BULL." + (char) 13
					+ "Este E-mail � gerado autom�ticamente, n�o sendo preciso responder!" + (char) 13
					+ "Fone para contato: (49) 99912 6830.");
			Transport.send(message);
			System.out.println("Enviado");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
