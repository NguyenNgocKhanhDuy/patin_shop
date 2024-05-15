package vn.hcmuaf.edu.fit.controller;


import vn.hcmuaf.edu.fit.bean.User;
import vn.hcmuaf.edu.fit.services.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;


@WebServlet(name = "Login", value = "/doLogin")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");

        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        User u = new User();
        u.setEmail(email);
        u.setPassword(password);
        User user = UserService.getInstance().checkLogin(u, ipAddress, "login");
        String infomation = UserService.getInstance().checkEmail(email);


        if (!"valid".equals(infomation)){
            request.setAttribute("type", "alert");
            request.setAttribute("infomation", infomation);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }else {
            if (user == null) {
                request.setAttribute("email", email);
                request.setAttribute("type", "error");
                request.setAttribute("information", "Email hoặc mật khẩu không chính xác");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }else {
                if (user.getVerify() == 1) {
                    if (!gRecaptchaResponse.equals("")) {
                        HttpSession session = request.getSession(true);
                        session.setAttribute("auth", user);

                        String location = (String) session.getAttribute("location");
                        if (location == null) location = "home";

                        request.setAttribute("type", "success");
                        request.setAttribute("information", "Đăng nhập thành công");
                        request.getRequestDispatcher(location).forward(request, response);
                    } else {
                        request.setAttribute("type", "error");
                        request.setAttribute("information", "Xác minh rằng bạn không phải là robot");
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                    }

                }else {
                    UserService.getInstance().reSend(user.getEmail());
                    request.getRequestDispatcher("verifyEmail.jsp").forward(request, response);
                }
            }
        }
    }

}