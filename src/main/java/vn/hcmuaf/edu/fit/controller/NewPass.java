package vn.hcmuaf.edu.fit.controller;

import vn.hcmuaf.edu.fit.services.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;


@WebServlet(name = "NewPass", value = "/newPass")
public class NewPass extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = (String) request.getSession().getAttribute("emailPass");
        String pass = request.getParameter("pass");
        String confirmPass = request.getParameter("confirmPass");
        if (UserService.getInstance().changePass(email, pass, confirmPass)) {
            request.getSession().removeAttribute("emailPass");
            request.setAttribute("type", "success");
            request.setAttribute("information", "Thay đổi mật khẩu thành công");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }else {
            request.setAttribute("type", "error");
            request.setAttribute("pass", pass);
            request.setAttribute("confirmPass", confirmPass);
            request.setAttribute("information", "Mật khẩu nhập không đúng");
            request.getRequestDispatcher("newPass.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}