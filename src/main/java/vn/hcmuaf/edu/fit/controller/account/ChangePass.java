package vn.hcmuaf.edu.fit.controller.account;

import vn.hcmuaf.edu.fit.bean.User;
import vn.hcmuaf.edu.fit.services.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;


@WebServlet(name = "ChangePass", value = "/changePass")
public class ChangePass extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldPass = request.getParameter("oldPass");
        String newPass = request.getParameter("newPass");
        String confirmPass = request.getParameter("confirmPass");

        User user = (User) request.getSession().getAttribute("auth");

        if (oldPass == null || newPass == null || confirmPass == null || user == null){
            request.setAttribute("type", "error");
            request.setAttribute("information", "Lỗi");
            request.getRequestDispatcher("account.jsp").forward(request, response);
        }

       else if ("".equals(newPass) || "".equals(confirmPass)){
            request.setAttribute("type", "error");
            request.setAttribute("information", "Không được để trống");
            request.getRequestDispatcher("account.jsp").forward(request, response);
        }else {
            if (UserService.getInstance().checkPass(user.getId(), oldPass)){
                if (UserService.getInstance().changePass(user.getEmail(), newPass, confirmPass)){
                    request.setAttribute("type", "success");
                    request.setAttribute("information", "Thay đổi thành oông");
                    request.getRequestDispatcher("account.jsp").forward(request, response);
                }else {
                    request.setAttribute("type", "error");
                    request.setAttribute("information", "Mật khẩu nhập lại không đúng");
                    request.getRequestDispatcher("account.jsp").forward(request, response);
                }
            }else {
                request.setAttribute("type", "error");
                request.setAttribute("information", "Mật khẩu cũ không chính xác");
                request.getRequestDispatcher("account.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}