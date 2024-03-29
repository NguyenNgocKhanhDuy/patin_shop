package vn.hcmuaf.edu.fit.controller.admin.add;

import vn.hcmuaf.edu.fit.bean.Category;
import vn.hcmuaf.edu.fit.bean.User;
import vn.hcmuaf.edu.fit.dao.CategoryDao;
import vn.hcmuaf.edu.fit.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;


@WebServlet(name = "AddCategoryAdmin", value = "/addCategoryAdmin")
public class AddCategoryAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");

        if (name == null){
            request.setAttribute("type", "error");
            request.setAttribute("information", "Lỗi null");
            request.getRequestDispatcher("showCategoryAdmin").forward(request, response);
        }else {

            Category category = new Category(0, name);
            if (!CategoryDao.getInstance().insertCategory(category)){
                request.setAttribute("type", "error");
                request.setAttribute("information", "Lỗi sql");
                request.getRequestDispatcher("showCategoryAdmin").forward(request, response);
            }else {
                request.setAttribute("type", "success");
                request.setAttribute("information", "Thêm thành công");
                request.getRequestDispatcher("showCategoryAdmin").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}