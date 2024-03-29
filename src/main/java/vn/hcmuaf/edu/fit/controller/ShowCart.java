package vn.hcmuaf.edu.fit.controller;

import vn.hcmuaf.edu.fit.bean.User;
import vn.hcmuaf.edu.fit.cart.Cart;
import vn.hcmuaf.edu.fit.cart.CartKey;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "ShowCart", value = "/showCart")
public class ShowCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("auth");
        if (user == null){
            request.setAttribute("type", "error");
            request.setAttribute("information", "Đăng nhập để tiếp tục");
            request.getRequestDispatcher("listProduct").forward(request, response);
        }else {
            Cart cart = (Cart) request.getSession().getAttribute("cart");
            if (cart == null) cart = new Cart();

            List<CartKey> keys = new ArrayList<>();

            for (CartKey ck: cart.getData().keySet()) {
                keys.add(ck);
            }

            request.setAttribute("data", cart.getData());
            request.setAttribute("keys", keys);

            request.setAttribute("type", request.getAttribute("type"));
            request.setAttribute("information", request.getAttribute("information"));

            request.getRequestDispatcher("cart.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}