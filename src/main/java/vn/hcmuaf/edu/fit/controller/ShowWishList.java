package vn.hcmuaf.edu.fit.controller;

import vn.hcmuaf.edu.fit.bean.ProductMain;
import vn.hcmuaf.edu.fit.bean.User;
import vn.hcmuaf.edu.fit.services.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.List;


@WebServlet(name = "ShowWishList", value = "/showWishList")
public class ShowWishList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("auth");
        if (user == null){
            request.setAttribute("type", "error");
            request.setAttribute("information", "Đăng nhập để tiếp tục");
            request.getRequestDispatcher("listProduct").forward(request, response);
        }else {
            int currentPage;
            try {
                currentPage = Integer.parseInt(request.getParameter("currentPage"));
            }catch (NumberFormatException e) {
                currentPage = 1;
            }

            String href = "showWishList?";
            request.setAttribute("href", href);

            List<ProductMain> all = ProductService.getInstance().getWishList(user.getId());

            int totalPage = (int) Math.ceil(all.size() / 15.0);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("productPerPage", 15);

            List<ProductMain> productList = ProductService.getInstance().getWishListPerPage(currentPage, user.getId());
            request.setAttribute("list", productList);
            request.getRequestDispatcher("wishlist.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}