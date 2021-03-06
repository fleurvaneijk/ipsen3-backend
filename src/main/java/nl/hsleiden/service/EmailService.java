package nl.hsleiden.service;

import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Service for sending e-mails
 * @author Fleur van Eijk
 */
public class EmailService {

    /**
     Outgoing Mail (SMTP) Server
     requires TLS or SSL: smtp.gmail.com (use authentication)
     Use Authentication: Yes
     Port for TLS/STARTTLS: 587
     */
    public static void readyMail(String email) {
        final String fromEmail = "dubio.klantenservice@gmail.com"; //requires valid gmail id
        final String password = "Dubio*00"; // correct password for gmail id
        final String toEmail = email; // can be any email id


        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        String subject = "Welkom bij Dubio!";
        String body = "<div style=\"color:blue;\">Beste gebruiker,\nWelkom bij Dubio!\nElke maandag staat er een nieuw deksels dilemma voor u klaar." +
                "\nGa naar de Dubio website om uw dilemma te bekijken.\n - Dubio klantenservice</div>";

        sendEmail(session, toEmail,subject, body);
    }

    /**
     * Utility method to send simple HTML email
     * @param session
     * @param toEmail
     * @param subject
     * @param body
     */
    private static void sendEmail(Session session, String toEmail, String subject, String body){
        try
        {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("no_reply@example.com", "Dubio klantenservice"));

            msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

            msg.setSubject(subject, "UTF-8");

//            msg.setText(body, "UTF-8");
            msg.setContent(body, "text/html; charset=utf-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

            Transport.send(msg);


        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}