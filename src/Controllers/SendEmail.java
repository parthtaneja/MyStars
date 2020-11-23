package Controllers;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

    public static void Send( String recieverEmail , String MessageToSend ){

        final String username = "prthtnja@gmail.com";
        final String password = "parth130899";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("prthtnja@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recieverEmail)
            );
            message.setSubject("MyStars: Waitlist Approval");
            message.setText(MessageToSend);

            Transport.send(message);
            System.out.println("Email notification sent to " + recieverEmail + "!");

        } catch (MessagingException e) {
            //e.printStackTrace();

            System.out.println("Unable to send email notification!");
        }


    }


}