package vn.hcmuaf.edu.fit.controller.admin;

import vn.hcmuaf.edu.fit.bean.*;
import vn.hcmuaf.edu.fit.dao.CategoryDao;
import vn.hcmuaf.edu.fit.services.PermissionsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "ShowCategoryAdmin", value = "/showCategoryAdmin")
public class ShowCategoryAdmin extends HttpServlet {
    String rsName = "category";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("auth");
        if (user == null) request.getRequestDispatcher("login.jsp").forward(request, response);
        else {
            int per = PermissionsService.getPermissionsService().checkAccess(rsName, user.getId());
            request.setAttribute("per", per);

            List<Category> allCategory = CategoryDao.getInstance().getAllCategory();
            request.setAttribute("allCategory", allCategory);


            int currentPage;
            try {
                currentPage = Integer.parseInt(request.getParameter("currentPage"));
            }catch (NumberFormatException e) {
                currentPage = 1;
            }

            String href = "showCategoryAdmin?";
            request.setAttribute("href", href);

            double productPerPage = 5.0;

            int totalPage = (int) Math.ceil(allCategory.size() / productPerPage);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("productPerPage", (int) productPerPage);

            List<Category> categories = CategoryDao.getInstance().getCategoryPerPage(currentPage, (int) productPerPage);
            request.setAttribute("categories", categories);

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