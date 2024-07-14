package vn.hcmuaf.edu.fit.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Form;
import vn.hcmuaf.edu.fit.bean.User;
import vn.hcmuaf.edu.fit.dao.UserDao;
import vn.hcmuaf.edu.fit.google_login.Constants;
import vn.hcmuaf.edu.fit.google_login.UserGoogle;
import vn.hcmuaf.edu.fit.services.UserService;


@WebServlet(name = "GoogleLogin", value = "/googleLogin")
public class GoogleLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        String accessToken = getToken(code);
        UserGoogle userGoogle = getUserInfo(accessToken);

        User user = new User();
        user.setEmail(userGoogle.getEmail());
        user.setFullName(userGoogle.getName());
        user.setAvatar(userGoogle.getPicture());

        if (!UserService.getInstance().isUserExists(user.getEmail())){
            UserDao.getInstance().addLoginGoogle(user, "", "info", "login google");
        }
        HttpSession session = request.getSession(true);
        session.setAttribute("auth", UserService.getInstance().getUserByEmail(user.getEmail()));
        String location = (String) session.getAttribute("location");
        if (location == null) location = "home";
        request.setAttribute("type", "success");
        request.setAttribute("information", "Đăng nhập thành công");
        UserDao.getInstance().addLoginGoogle(user, "", "alert", "login google");
        request.getRequestDispatcher(location).forward(request, response);
    }
    public static String getToken(String code) throws ClientProtocolException, IOException {
        String response = Request.Post(Constants.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
                        .add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", Constants.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", Constants.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static UserGoogle getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();

        UserGoogle googlePojo = new Gson().fromJson(response, UserGoogle.class);

        return googlePojo;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}