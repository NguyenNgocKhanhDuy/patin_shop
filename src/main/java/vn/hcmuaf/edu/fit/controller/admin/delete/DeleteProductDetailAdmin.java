package vn.hcmuaf.edu.fit.controller.admin.delete;

import vn.hcmuaf.edu.fit.bean.Color;
import vn.hcmuaf.edu.fit.bean.Product;
import vn.hcmuaf.edu.fit.bean.ProductDetail;
import vn.hcmuaf.edu.fit.bean.Size;
import vn.hcmuaf.edu.fit.dao.ProductDetailDao;
import vn.hcmuaf.edu.fit.services.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;


@WebServlet(name = "DeleteProductDetailAdmin", value = "/deleteProductDetailAdmin")
public class DeleteProductDetailAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id;
        int size;
        int color;
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        try {
            id = Integer.parseInt(request.getParameter("id"));
            size = Integer.parseInt(request.getParameter("size"));
            color = Integer.parseInt(request.getParameter("color"));
            ProductDetail productDetail = new ProductDetail();
            Product product = new Product();
            product.setId(id);
            Size sz = new Size();
            sz.setId(size);
            Color cl = new Color();
            cl.setId(color);
            productDetail.setProduct(product);
            productDetail.setSize(sz);
            productDetail.setColor(cl);

            if (ProductDetailDao.getInstance().delete(productDetail,ipAddress, "danger", "admin delete detail product")){
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