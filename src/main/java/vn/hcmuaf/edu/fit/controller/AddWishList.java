package vn.hcmuaf.edu.fit.controller;

import vn.hcmuaf.edu.fit.bean.User;
import vn.hcmuaf.edu.fit.services.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;


@WebServlet(name = "AddWishList", value = "/addWishList")
public class AddWishList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id;

        try {
            id = Integer.parseInt(request.getParameter("productID"));

            User user = (User) request.getSession().getAttribute("auth");

            if (user == null) {
                request.setAttribute("type", "error");
                request.setAttribute("information", "Đăng nhập để tiếp tục");
                request.getRequestDispatcher("productDetail?productID="+id).forward(request, response);
            }else {
                if (ProductService.getInstance().isExistInWishList(user.getId(), id)){
                    request.setAttribute("type", "error");
                    request.setAttribute("information", "Đã có trong danh sách");
                    request.getRequestDispatcher("productDetail?productID="+id).forward(request, response);
                }else {
                    if (ProductService.getInstance().addWishList(user.getId(), id)){
                        response.sendRedirect("showWishList?id="+id);
                    }else {
                        request.setAttribute("type", "error");
                        request.setAttribute("information", "Lỗi");
                        request.getRequestDispatcher("productDetail?productID="+id).forward(request, response);
                    }
                }
            }

        }catch (NumberFormatException e ){
            request.setAttribute("type", "error");
            request.setAttribute("information", "Lỗi");
            request.getRequestDispatcher("listProduct").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}