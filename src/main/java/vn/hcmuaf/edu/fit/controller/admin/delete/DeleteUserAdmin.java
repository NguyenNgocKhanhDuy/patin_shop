package vn.hcmuaf.edu.fit.controller.admin.delete;

import vn.hcmuaf.edu.fit.bean.User;
import vn.hcmuaf.edu.fit.services.PermissionsService;
import vn.hcmuaf.edu.fit.services.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;


@WebServlet(name = "DeleteUserAdmin", value = "/deleteUserAdmin")
public class DeleteUserAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id;
        try {
            id = Integer.parseInt(request.getParameter("id"));
            if (UserService.getInstance().deleteUser(id)){
                PermissionsService.getPermissionsService().deletePer(id);
                request.setAttribute("type", "success");
                request.setAttribute("information", "Xoá thành công");
                request.getRequestDispatcher("showUserAdmin").forward(request, response);
            }else {
                request.setAttribute("type", "error");
                request.setAttribute("information", "Lỗi sql");
                request.getRequestDispatcher("showUserAdmin").forward(request, response);
            }
        }catch (NumberFormatException e){
            request.setAttribute("type", "error");
            request.setAttribute("information", "Lỗi null");
            request.getRequestDispatcher("showUserAdmin").forward(request, response);
        }
    }
}