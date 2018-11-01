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

public class JavaMailApp {
	public static void enviar(String email, String mensagem) {
		Properties props = new Properties();
		/** Parâmetros de conexão com servidor Gmail */
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

		/** Ativa Debug para sessão */
		session.setDebug(true);

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("nao.responder.pet.bull@gmail.com")); // Remetente

			Address[] toUser = InternetAddress // Destinatário(s)
					.parse(email);

			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject("Redefinição de senha - PET BULL");// Assunto
			message.setText("Olá caro usuário!" + (char) 13 + (char) 13
					+ "Recebemos sua solicitação de alteração de senha, geramos a "
					+ "seguinte senha temporária para você:  " + mensagem + (char) 13
					+ "Caso, mesmo com a nova senha, não conseguir acesso, entre em contato conosco!" + (char) 13
					+ (char) 13 + (char) 13 + "Atenciosamente, PET BULL." + (char) 13
					+ "Este E-mail é gerado automáticamente, não sendo preciso responder!" + (char) 13
					+ "Fone para contato: (49) 99912 6830.");
			/** Método para enviar a mensagem criada */
			Transport.send(message);

			System.out.println("Feito!!!");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
