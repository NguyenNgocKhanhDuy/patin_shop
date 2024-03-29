package vn.hcmuaf.edu.fit.controller.admin.update;

import vn.hcmuaf.edu.fit.bean.Product;
import vn.hcmuaf.edu.fit.services.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;


@WebServlet(name = "UpdateProductAdmin", value = "/updateProductAdmin")
public class UpdateProductAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        }catch (NumberFormatException e){
            request.setAttribute("type", "error");
            request.setAttribute("information", "Lỗi");
            request.getRequestDispatcher("showProductAdmin").forward(request, response);
        }
        String name = request.getParameter("name");
        String hot = request.getParameter("hot");
        String information = request.getParameter("information");
        int salePercent;
        if (name == null || information == null) {
            request.setAttribute("type", "error");
            request.setAttribute("information", "Lỗi");
            request.getRequestDispatcher("showProductDetailAdmin?id="+id).forward(request, response);
        } else if (name.trim().equals("")) {
            request.setAttribute("type", "error");
            request.setAttribute("information", "Tên không được để trống");
            request.getRequestDispatcher("showProductDetailAdmin?id="+id).forward(request, response);
        }else {
            try {

                if (hot == null) hot = "0";
                int hotNum = Integer.valueOf(hot);
                salePercent = Integer.parseInt(request.getParameter("sale"));
                double sale = salePercent/100.0;
                information = information.trim().replace("\r\n", "%");

                if (information.length() > 1000){
                    request.setAttribute("type", "error");
                    request.setAttribute("information", "Thông tin quá dài (Không được vượt quá 1000 kí tự)");
                    request.getRequestDispatcher("showProductDetailAdmin?id="+id).forward(request, response);
                }else {
                    Product product = new Product(id, name, hotNum, sale, information);
                    if (ProductService.getInstance().updateProduct(product)){
                        request.setAttribute("type", "success");
                        request.setAttribute("information", "Cập nhật thành công");
                        request.getRequestDispatcher("showProductDetailAdmin?id="+id).forward(request, response);
                    }else {
                        request.setAttribute("type", "error");
                        request.setAttribute("information", "Lỗi sql");
                        request.getRequestDispatcher("showProductDetailAdmin?id="+id).forward(request, response);
                    }
                }


            }catch (NumberFormatException e){
                request.setAttribute("type", "error");
                request.setAttribute("information", "Lỗi giảm giá phải là số");
                request.getRequestDispatcher("showProductDetailAdmin?id="+id).forward(request, response);
            }
        }
    }
}