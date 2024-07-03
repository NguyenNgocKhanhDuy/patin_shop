package vn.hcmuaf.edu.fit.controller.admin.delete;

import vn.hcmuaf.edu.fit.dao.CategoryDao;
import vn.hcmuaf.edu.fit.dao.ColorDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "DeleteColorAdmin", value = "/deleteColorAdmin")
public class DeleteColorAdmin extends HttpServlet {
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

            if (ColorDao.getInstance().deleteColor(id)){
                response.getWriter().println("Xoá thành công");
//                request.setAttribute("type", "success");
//                request.setAttribute("information", "Xoá thành công");
//                request.getRequestDispatcher("showColorAdmin").forward(request, response);
            }else {
                response.getWriter().println("Lỗi SQL");
//                request.setAttribute("type", "error");
//                request.setAttribute("information", "Lỗi sql");
//                request.getRequestDispatcher("showColorAdmin").forward(request, response);
            }

        }catch (NumberFormatException e){
            response.getWriter().println("Lỗi Null");
//            request.setAttribute("type", "error");
//            request.setAttribute("information", "Lỗi null");
//            request.getRequestDispatcher("showColorAdmin").forward(request, response);
        }
    }
}