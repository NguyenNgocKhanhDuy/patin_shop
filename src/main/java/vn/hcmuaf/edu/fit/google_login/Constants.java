package vn.hcmuaf.edu.fit.google_login;

public class Constants {
    public static String GOOGLE_CLIENT_ID = "364252646575-s7ogdt6pl2949v6pv3hlt8rgcb27u26u.apps.googleusercontent.com";

//    public static String GOOGLE_CLIENT_SECRET = "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";
    public static String GOOGLE_CLIENT_SECRET = "code";

    public static String GOOGLE_REDIRECT_URI = "http://localhost:8080/patin_shop/googleLogin";

    public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";

    public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";

    public static String GOOGLE_GRANT_TYPE = "authorization_code";
}
