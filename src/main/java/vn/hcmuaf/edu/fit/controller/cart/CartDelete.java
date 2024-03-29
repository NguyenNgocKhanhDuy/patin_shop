package vn.hcmuaf.edu.fit.controller.cart;

import vn.hcmuaf.edu.fit.cart.Cart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;


@WebServlet(name = "CartDelete", value = "/cartDelete")
public class CartDelete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;
        int size = 0;
        int color = 0;
        String type = request.getParameter("type");
        if (type == null) type = "";

        try {
            id = Integer.parseInt(request.getParameter("id"));
            size = Integer.parseInt(request.getParameter("size"));
            color = Integer.parseInt(request.getParameter("color"));

            Cart cart = (Cart) request.getSession().getAttribute("cart");

            cart.remove(id, size, color);
            request.getSession().setAttribute("cart", cart);

            request.setAttribute("type", "success");
            request.setAttribute("information", "Xoá thành công");
            request.getRequestDispatcher("showCart").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("type", "error");
            request.setAttribute("information", "Lỗi");
            request.getRequestDispatcher("productDetail").forward(request, response);
        }
    }
}