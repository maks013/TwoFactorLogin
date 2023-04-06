package com.example.login2faapp.email;
import java.io.IOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static com.example.login2faapp.email.EmailConfig.*;

public class EmailSender {

    String recipient;
    String title;
    String content;

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void messageSend() throws IOException {

        Properties props = new Properties();
        props.put("mail.smtp.user", EMAIL_FOR_SENDING_LOGIN);
        props.put("mail.smtp.host", MAIL_SMTP_HOST);
        props.put("mail.smtp.port", MAIL_SMTP_PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", MAIL_SMTP_PORT);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "true");

        try {
            Authenticator auth = new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(EMAIL_FOR_SENDING_LOGIN, EMAIL_FOR_SENDING_PASSWORD);
                }
            };
            Session session = Session.getInstance(props, auth);
            MimeMessage msg = new MimeMessage(session);
            msg.setText(content);
            msg.setSubject(title);
            msg.setFrom(new InternetAddress(EMAIL_FOR_SENDING_LOGIN));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            Transport.send(msg);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }
}