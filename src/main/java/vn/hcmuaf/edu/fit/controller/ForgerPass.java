package vn.hcmuaf.edu.fit.controller;

import vn.hcmuaf.edu.fit.services.MailService;
import vn.hcmuaf.edu.fit.services.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;


@WebServlet(name = "ForgerPass", value = "/forgerPass")
public class ForgerPass extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("emailPass");
        if (!UserService.getInstance().isUserExists(email)) {
            request.setAttribute("type", "error");
            request.setAttribute("information", "Không tồn tại tài khoản");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }else {
            if (UserService.getInstance().sendforgetPass(email)){
                request.getSession().setAttribute("timeStart", System.currentTimeMillis());
                request.getSession().setAttribute("emailPass", email);
                request.getRequestDispatcher("/verifyForgetPass.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}