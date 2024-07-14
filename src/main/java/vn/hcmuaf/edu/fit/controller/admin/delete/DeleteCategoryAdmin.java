package vn.hcmuaf.edu.fit.controller.admin.delete;

import vn.hcmuaf.edu.fit.bean.Category;
import vn.hcmuaf.edu.fit.dao.CategoryDao;
import vn.hcmuaf.edu.fit.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "DeleteCategoryAdmin", value = "/deleteCategoryAdmin")
public class DeleteCategoryAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id;
        response.setContentType("text/plain");
        try {
            id = Integer.parseInt(request.getParameter("id"));

            Category category = new Category();
            category.setId(id);
            String ip = request.getHeader("X-FORWARDED_FOR");
            if(ip == null){
                ip = request.getRemoteAddr();
            }
            if (CategoryDao.getInstance().deleteCategory(category, ip)){
                response.getWriter().println("Xoá thành công");
//                request.setAttribute("type", "success");
//                request.setAttribute("information", "Xoá thành công");
//                request.getRequestDispatcher("showCategoryAdmin").forward(request, response);
            }else {
                response.getWriter().println("Lỗi SQL");
//                request.setAttribute("type", "error");
//                request.setAttribute("information", "Lỗi sql");
//                request.getRequestDispatcher("showCategoryAdmin").forward(request, response);
            }


        }catch (NumberFormatException e){
            response.getWriter().println("Lỗi Null");
//            request.setAttribute("type", "error");
//            request.setAttribute("information", "Lỗi");
//            request.getRequestDispatcher("showCategoryAdmin").forward(request, response);
        }
    }
}