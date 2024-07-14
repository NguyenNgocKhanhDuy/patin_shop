package vn.hcmuaf.edu.fit.controller.admin;
import vn.hcmuaf.edu.fit.bean.Log;
import vn.hcmuaf.edu.fit.bean.User;
import vn.hcmuaf.edu.fit.dao.LogDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.List;


@WebServlet(name = "ShowLogAdmin", value = "/showLogAdmin")
public class ShowLogAdmin extends HttpServlet {
    String rsName = "log";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("auth");
        if (user == null) request.getRequestDispatcher("login.jsp").forward(request, response);
        else {
//            int per = PermissionsService.getPermissionsService().checkAccess(rsName, user.getId());
//            request.setAttribute("per", per);

            List<Log> allLog = LogDao.getInstance().getAllLog();
            request.setAttribute("allLog", allLog);


            int currentPage;
            try {
                currentPage = Integer.parseInt(request.getParameter("currentPage"));
            }catch (NumberFormatException e) {
                currentPage = 1;
            }

            String href = "showLogAdmin?";
            request.setAttribute("href", href);

            double productPerPage = 5.0;

            int totalPage = (int) Math.ceil(allLog.size() / productPerPage);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("productPerPage", (int) productPerPage);

            List<Log> logs = LogDao.getInstance().getLogPerPage(currentPage, (int) productPerPage);
            request.setAttribute("logs", logs);
            request.setAttribute("typeToShow", "log");

            request.setAttribute("type", request.getAttribute("type"));
            request.setAttribute("information", request.getAttribute("information"));

            request.getRequestDispatcher("admin.jsp").forward(request, response);
        }


    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}