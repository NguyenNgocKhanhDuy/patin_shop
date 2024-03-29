package vn.hcmuaf.edu.fit.mail;

import java.io.IOException;
import java.util.Properties;

public class MailProperties {
    private static Properties prop = new Properties();

    static {
        try {
            prop.load(MailProperties.class.getClassLoader().getResourceAsStream("mail.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getHost() {
        return prop.get("mail.smtp.host").toString();
    }

    public static String getPort() {
        return prop.get("mail.smtp.port").toString();
    }

    public static String getUsername() {
        return prop.get("mail.user").toString();
    }

    public static String getPassword() {
        return prop.get("mail.pass").toString();
    }

    public static boolean getAuth() {
        return "true".equals(prop.get("mail.smtp.auth").toString());
    }

    public static boolean getTls() {
        return "true".equals(prop.get("mail.smtp.starttls.enable").toString());
    }


}
