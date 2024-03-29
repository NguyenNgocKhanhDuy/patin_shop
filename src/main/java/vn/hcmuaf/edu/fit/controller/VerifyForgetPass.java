package vn.hcmuaf.edu.fit.controller;

import vn.hcmuaf.edu.fit.services.MailService;
import vn.hcmuaf.edu.fit.services.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;


@WebServlet(name = "VerifyForgetPass", value = "/verifyForgetPass")
public class VerifyForgetPass extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long timeEnd = System.currentTimeMillis();
        long timeStart = (long) request.getSession().getAttribute("timeStart");
        String email = (String) request.getSession().getAttribute("emailPass");
        String[] codes = request.getParameterValues("code");
        String s = "";
        for (int i = 0; i < codes.length; i++) {
            s += codes[i];
        }
        int code = Integer.parseInt(s);

        if (MailService.getInstance().isValidCode(timeStart, timeEnd)){
            if (UserService.getInstance().checkKey(code, email)) {
                request.getSession().removeAttribute("timeStart");
                request.setAttribute("type", "success");
                request.setAttribute("information", "Xác thực thành công");
                request.getRequestDispatcher("newPass.jsp").forward(request, response);
            }else {
                request.setAttribute("type", "error");
                request.setAttribute("information", "Mã xác minh không đúng");
                request.getRequestDispatcher("verifyForgetPass.jsp").forward(request, response);
            }
        }else {
            request.setAttribute("type", "error");
            request.setAttribute("information", "Mã xác minh không đúng");
            request.getRequestDispatcher("verifyForgetPass.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}