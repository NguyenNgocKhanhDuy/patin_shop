package vn.hcmuaf.edu.fit.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.management.RuntimeErrorException;
import java.util.Properties;

public class Mail {
    private static Properties prop = new Properties();
    static {
        prop.put("mail.smtp.host", MailProperties.getHost());
        prop.put("mail.smtp.port", MailProperties.getPort());
        prop.put("mail.smtp.auth", MailProperties.getAuth());
        prop.put("mail.smtp.socketFactory.port", MailProperties.getPort());
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    }

    public Mail() {
    }

    public static Properties getProp() {
        return prop;
    }

    public static void main(String[] args) {
        Session session = Session.getInstance(Mail.getProp(),
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(MailProperties.getUsername(), MailProperties.getPassword());
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(MailProperties.getUsername()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("21130035@st.hcmuaf.edu.vn"));
            message.setSubject("Thư xác thực Email");
//            message.setText("Mã xác thực: ");
            message.setContent("<a href = \"http://localhost:8080/patin_shop/patin/index.jsp\">Click me</a>", "text/html");
            Transport.send(message);
            System.out.println("Done");
        } catch (MessagingException e) {
            System.out.println("Fail");
        }
    }
}
