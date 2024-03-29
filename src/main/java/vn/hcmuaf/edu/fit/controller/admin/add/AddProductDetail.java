package vn.hcmuaf.edu.fit.controller.admin.add;

import vn.hcmuaf.edu.fit.bean.Color;
import vn.hcmuaf.edu.fit.bean.Product;
import vn.hcmuaf.edu.fit.bean.ProductDetail;
import vn.hcmuaf.edu.fit.bean.Size;
import vn.hcmuaf.edu.fit.services.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;


@WebServlet(name = "AddProductDetail", value = "/addProductDetail")
public class AddProductDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id;
        try {
            id = Integer.parseInt(request.getParameter("id"));
            int price;
            int quantity;
            int color;
            int size;
            try {
                price = Integer.parseInt(request.getParameter("price"));
                quantity = Integer.parseInt(request.getParameter("quantity"));
                color = Integer.parseInt(request.getParameter("color"));
                size = Integer.parseInt(request.getParameter("size"));

                Product product = new Product();
                product.setId(id);
                Size sizeOb = new Size();
                sizeOb.setId(size);
                Color colorOb = new Color();
                colorOb.setId(color);
                ProductDetail productDetail = new ProductDetail(product, sizeOb, colorOb, quantity, price);
                if (ProductService.getInstance().isExistProductDetail(id, size, color)){
                    request.setAttribute("type", "error");
                    request.setAttribute("information", "Đã tồn tại sản phẩm");
                    request.getRequestDispatcher("showProductDetailAdmin?id="+id).forward(request, response);
                }else {
                    if (ProductService.getInstance().addProductDetail(productDetail)){
                        request.setAttribute("type", "success");
                        request.setAttribute("information", "Thêm thành công");
                        request.getRequestDispatcher("showProductDetailAdmin?id"+id).forward(request, response);
                    }else {
                        request.setAttribute("type", "error");
                        request.setAttribute("information", "Lỗi sql");
                        request.getRequestDispatcher("showProductDetailAdmin?id="+id).forward(request, response);
                    }

                }

            }catch (NumberFormatException e){
                request.setAttribute("type", "error");
                request.setAttribute("information", "Không được để trống");
                request.getRequestDispatcher("showProductAdmin").forward(request, response);
            }
        }catch (NumberFormatException e){
            request.setAttribute("type", "error");
            request.setAttribute("information", "Lỗi");
            request.getRequestDispatcher("showProductAdmin").forward(request, response);
        }

    }
}