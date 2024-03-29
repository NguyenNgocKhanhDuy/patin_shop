package vn.hcmuaf.edu.fit.controller;

import vn.hcmuaf.edu.fit.bean.User;
import vn.hcmuaf.edu.fit.cart.Cart;
import vn.hcmuaf.edu.fit.cart.CartKey;
import vn.hcmuaf.edu.fit.services.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "Payment", value = "/payment")
public class Payment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        User user = (User) request.getSession().getAttribute("auth");

        String type = (String) request.getAttribute("type");
        String information = (String) request.getAttribute("information");

        request.setAttribute("type", type);
        request.setAttribute("information", information);

        if (user == null || cart == null){
            response.getWriter().println("null");
        }else {

            request.setAttribute("user", user);

            List<CartKey> keys = new ArrayList<>();
            boolean flag = true;
            int quantity;

            for (CartKey ck: cart.getData().keySet()) {
                keys.add(ck);
                quantity = ProductService.getInstance().getQuantity(ck.getId(), ck.getSize(), ck.getColor());
                if (cart.getData().get(ck).getQuantity() > quantity){
                    flag = false;
                    break;
                }
            }
            if (flag == false){
                request.setAttribute("type", "error");
                request.setAttribute("information", "Có sản phẩm đã hết hàng");
                request.getRequestDispatcher("showCart").forward(request, response);
            }

            request.setAttribute("data", cart.getData());
            request.setAttribute("keys", keys);

            request.getRequestDispatcher("payment.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doGet(request, response);
    }
}