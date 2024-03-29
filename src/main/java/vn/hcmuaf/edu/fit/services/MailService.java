package vn.hcmuaf.edu.fit.services;

import vn.hcmuaf.edu.fit.mail.Mail;
import vn.hcmuaf.edu.fit.mail.MailProperties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MailService {
    private static MailService instance;

    public MailService() {
    }

    public static MailService getInstance() {
        if (instance == null) instance = new MailService();
        return instance;
    }

    public boolean sendMailVerify(String to, String mes) {
        Session session = Session.getInstance(Mail.getProp(),
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(MailProperties.getUsername(), MailProperties.getPassword());
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(MailProperties.getUsername()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Thư xác thực Email", "utf-8");
            message.setText("Mã xác thực: "+mes, "utf-8");
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }

    public boolean sendMail(String to, String mes) {
        Session session = Session.getInstance(Mail.getProp(),
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(MailProperties.getUsername(), MailProperties.getPassword());
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(MailProperties.getUsername()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Tin nhắn từ khách hàng patin", "utf-8");
            message.setText(mes, "utf-8");
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }


    public boolean isValidCode(long timeStart, long timeEnd) {
        long time = timeEnd - timeStart;
        int t = (int) time / 1000;
        int h = t/3600;
        int m = t % 3600 / 60;
        int s = t % 3600 % 60;
        if (h > 0) return false;
        if (m > 15) return false;
        return true;
    }

}
