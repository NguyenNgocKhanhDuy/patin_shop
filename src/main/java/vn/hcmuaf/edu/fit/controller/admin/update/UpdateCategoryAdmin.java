package vn.hcmuaf.edu.fit.controller.admin.update;

import vn.hcmuaf.edu.fit.bean.Category;
import vn.hcmuaf.edu.fit.dao.CategoryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "UpdateCategoryAdmin", value = "/updateCategoryAdmin")
public class UpdateCategoryAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id;
        String name = request.getParameter("name");


        if (name == null){
            request.setAttribute("type", "error");
            request.setAttribute("information", "Lỗi");
            request.getRequestDispatcher("admin.jsp").forward(request, response);
        }else {
            try {
                id = Integer.parseInt(request.getParameter("id"));

                Category category = CategoryDao.getInstance().getCategory(id);
                category.setName(name);

                if (CategoryDao.getInstance().updateCategory(category)){
                    request.setAttribute("type", "success");
                    request.setAttribute("information", "Cập nhật thành công");
                    request.getRequestDispatcher("showCategoryAdmin").forward(request, response);
                }else {
                    request.setAttribute("type", "error");
                    request.setAttribute("information", "Lỗi sql");
                    request.getRequestDispatcher("showCategoryAdmin").forward(request, response);
                }


            }catch (NumberFormatException e ){
                request.setAttribute("type", "error");
                request.setAttribute("information", "Lỗi");
                request.getRequestDispatcher("showCategoryAdmin").forward(request, response);
            }
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}