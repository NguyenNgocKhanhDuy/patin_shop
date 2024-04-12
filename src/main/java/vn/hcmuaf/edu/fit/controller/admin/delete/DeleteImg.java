package vn.hcmuaf.edu.fit.controller.admin.delete;

import vn.hcmuaf.edu.fit.bean.ImageProduct;
import vn.hcmuaf.edu.fit.bean.Product;
import vn.hcmuaf.edu.fit.dao.ImageProductDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;


@WebServlet(name = "DeleteImg", value = "/deleteImg")
public class DeleteImg extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id;
        int idImg;
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        try {
            id = Integer.parseInt(request.getParameter("id"));
            idImg = Integer.parseInt(request.getParameter("idImg"));
            ImageProduct imgProduct = new ImageProduct();
            Product product = new Product();
            product.setId(id);
            imgProduct.setProduct(product);
            if (ImageProductDao.getInstance().delete(imgProduct,ipAddress,"danger","admin delete img")){
                request.setAttribute("type", "success");
                request.setAttribute("information", "Xoá thành công");
                request.getRequestDispatcher("showProductDetailAdmin?id="+id).forward(request, response);
            }else {
                request.setAttribute("type", "error");
                request.setAttribute("information", "Lỗi sql");
                request.getRequestDispatcher("showProductDetailAdmin?id="+id).forward(request, response);
            }
        }catch (NumberFormatException e){
            request.setAttribute("type", "error");
            request.setAttribute("information", "Lỗi");
            request.getRequestDispatcher("showProductAdmin").forward(request, response);
        }
    }
}