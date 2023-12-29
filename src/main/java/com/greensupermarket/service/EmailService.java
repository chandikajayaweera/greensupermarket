package com.greensupermarket.service;

import com.greensupermarket.util.EmailConfiguration;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.IOException;
import java.util.Properties;

public class EmailService {

    private final EmailConfiguration emailConfiguration;

    public EmailService() throws IOException {
        this.emailConfiguration = new EmailConfiguration();
    }

    public void sendEmail(String to, String subject, String body) throws MessagingException {
        Properties properties = emailConfiguration.getEmailProperties();

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                return new jakarta.mail.PasswordAuthentication(
                        properties.getProperty("mail.smtp.user"),
                        properties.getProperty("mail.smtp.password")
                );
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("\"GreenSupermarket\"<GreenSupermarket@gmail.com>")); // Set the "from" address
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(body);

        Transport.send(message);
    }

}
