package vn.hcmuaf.edu.fit.controller.admin;

import vn.hcmuaf.edu.fit.bean.*;
import vn.hcmuaf.edu.fit.services.PermissionsService;
import vn.hcmuaf.edu.fit.services.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.List;


@WebServlet(name = "ShowUserAdmin", value = "/showUserAdmin")
public class ShowUserAdmin extends HttpServlet {
    String rsName = "user";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("auth");
        if (user == null) request.getRequestDispatcher("login.jsp").forward(request, response);
        else {
             int per = PermissionsService.getPermissionsService().checkAccess(rsName, user.getId());
             request.setAttribute("per", per);

            List<User> allUser = UserService.getInstance().getAllUser();
            request.setAttribute("allUser", allUser);

            int currentPage;
            try {
                currentPage = Integer.parseInt(request.getParameter("currentPage"));
            }catch (NumberFormatException e) {
                currentPage = 1;
            }

            String href = "showUserAdmin?";
            request.setAttribute("href", href);

            double productPerPage = 5.0;

            int totalPage = (int) Math.ceil(allUser.size() / productPerPage);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("productPerPage", (int) productPerPage);

            List<User> users = UserService.getInstance().getUserPerPage(currentPage, (int) productPerPage);
            request.setAttribute("users", users);



            request.setAttribute("type", request.getAttribute("type"));
            request.setAttribute("information", request.getAttribute("information"));

            request.getRequestDispatcher("admin.jsp").forward(request, response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}