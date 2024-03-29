package vn.hcmuaf.edu.fit.controller;

import vn.hcmuaf.edu.fit.services.MailService;
import vn.hcmuaf.edu.fit.services.RegisterService;
import vn.hcmuaf.edu.fit.services.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;


@WebServlet(name = "ResendCode", value = "/doResend")
public class ResendCode extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = (String) request.getSession().getAttribute("email");
        if (UserService.getInstance().reSend(email)){
            long timeStart = RegisterService.getInstance().getCurrentTime();
            request.getSession().setAttribute("timeStart", timeStart);
            request.setAttribute("type", "success");
            request.setAttribute("information", "Gửi lại mã thành công");
            request.getRequestDispatcher("verifyEmail.jsp").forward(request, response);
        }else {
            request.setAttribute("type", "error");
            request.setAttribute("information", "Có lỗi xảy ra");
            request.getRequestDispatcher("verifyEmail.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}