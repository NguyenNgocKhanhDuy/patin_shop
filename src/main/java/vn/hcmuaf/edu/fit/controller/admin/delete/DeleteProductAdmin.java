package vn.hcmuaf.edu.fit.controller.admin.delete;

import vn.hcmuaf.edu.fit.bean.Product;
import vn.hcmuaf.edu.fit.dao.ProductDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;


@WebServlet(name = "DeleteProductAdmin", value = "/deleteProductAdmin")
public class DeleteProductAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       int id = 0;
        response.setContentType("text/plain");
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        try {
            id = Integer.parseInt(request.getParameter("id"));
            Product product = new Product();
            product.setId(id);
            if (ProductDao.getInstance().delete(product,ipAddress,"danger","admin delete prod")){
//                request.setAttribute("type", "success");
//                request.setAttribute("information", "Xoá thành công");
                response.getWriter().println("Xoá thành công");
//                request.getRequestDispatcher("showProductAdmin").forward(request, response);
            }else {
//                request.setAttribute("type", "error");
                response.getWriter().println("Lỗi SQL");
//                request.setAttribute("information", "Lỗi sql");
//                request.getRequestDispatcher("showProductAdmin").forward(request, response);
            }
        }catch (NumberFormatException e){
            response.getWriter().println("Lỗi Null");
//            request.setAttribute("type", "error");
//            request.setAttribute("information", "Lỗi");
//            request.getRequestDispatcher("showProductAdmin").forward(request, response);
        }
    }
}