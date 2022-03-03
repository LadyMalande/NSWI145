package tut01;
import javax.jws.WebService;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@WebService(endpointInterface = "tut01.Mail")
public class MailImpl implements Mail {
    @Override
    public boolean sendOrderNumber(String to, int orderNumber) {
        String subject = "ClothesShop - Objednávka číslo " + orderNumber;
        String text = "Děkujeme za Vaši objednávku! \n Číslo Vaší objednávky je " + orderNumber + ".";

        return sendMail(to, subject, text, null);
    }

    @Override
    public boolean sendPDFReceipt(String to, int orderNumber) {
        String subject = "ClothesShop - Faktura objednávky číslo " + orderNumber;
        String text = "V příloze naleznete fakturu vaší objednávky číslo " + orderNumber + ".";
        File file = new File("objednavka" + orderNumber + ".pdf");
        return sendMail(to, subject, text, file);
    }

    private boolean sendMail(String to, String subject, String text, File file){

        // Sender's email ID needs to be mentioned
        String from = "lenka.hirstovska@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("lenka.hirstovska@gmail.com", "xzjtembvpkqalzhl");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(subject);

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(text);

            if(file != null){
                MimeBodyPart attachmentPart = new MimeBodyPart();
                attachmentPart.attachFile(file);
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);
                multipart.addBodyPart(attachmentPart);
                message.setContent(multipart);
            } else{
                // Now set the actual message
                message.setText(text);
            }

            // Send message
            Transport.send(message);
            return true;
        } catch (MessagingException | IOException mex) {
            mex.printStackTrace();
            return false;
        }
    }

}
