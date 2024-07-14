package vn.hcmuaf.edu.fit.controller.admin.delete;

import vn.hcmuaf.edu.fit.bean.User;
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
        response.setContentType("text/plain");
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        try {
            String idParam = request.getParameter("id");
            System.out.println("okL:  "+idParam);
            id = Integer.parseInt(idParam);
            User user = new User();
            user.setId(id);
            user.setBeforeData("email:"+user.getEmail());
            if (UserService.getInstance().deleteUser(user, ipAddress)){
//                PermissionsService.getPermissionsService().deletePer(id);
                System.out.println("OK XONG");
                response.getWriter().println("Xoá thành công");
//                request.setAttribute("type", "success");
//                request.setAttribute("information", "Xoá thành công");
//                request.getRequestDispatcher("showUserAdmin").forward(request, response);
            }else {
                System.out.println("SQL error");
                response.getWriter().println("Lỗi SQL");
//                request.setAttribute("type", "error");
//                request.setAttribute("information", "Lỗi sql");
//                request.getRequestDispatcher("showUserAdmin").forward(request, response);
            }
        }catch (NumberFormatException e){
            System.out.println("Null error");
            response.getWriter().println("Lỗi Null");
//            request.setAttribute("type", "error");
//            request.setAttribute("information", "Lỗi null");
//            request.getRequestDispatcher("showUserAdmin").forward(request, response);
        }
    }
}