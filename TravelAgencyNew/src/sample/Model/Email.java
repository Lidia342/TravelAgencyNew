package sample.Model;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class Email {

    public static void sendEmail(String recipient, String type) {

        Data myData = Data.getInstance();

        final String username = "TravelAgencyNew@gmail.com";
        final String password = "Agency100";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("TravelAgencyNew@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));

        message.setSubject("Booking Confirmation");


            message.setText("Dear " + myData.getUser().getFirstName() + ",\n\n" +
                        "Your booking for package " + myData.getCurrentPackage().getPackageName()
                    + " has been confirmed. Please, keep this email as proof of your booking.\n\nYour departing flight is at " +
                     myData.getCurrentPackage().getDeparture_time()+". Thanks for booking!\n!,\n");

        Transport.send(message);

    } catch (AddressException e) {
                    throw new RuntimeException(e);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}