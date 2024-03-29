package vn.hcmuaf.edu.fit.controller.cart;

import vn.hcmuaf.edu.fit.bean.User;
import vn.hcmuaf.edu.fit.cart.Cart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;


@WebServlet(name = "Cart", value = "/cartController")
public class CartController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("auth");
        if (user == null) {
            request.setAttribute("type", "error");
            request.setAttribute("information", "Đăng nhập để tiếp tục");
            request.getRequestDispatcher("productDetail").forward(request, response);
        }else {
            int id = 0;
            int size = 0;
            int color = 0;
            int quantity = 0;

            try {
                id = Integer.parseInt(request.getParameter("productID"));
                size = Integer.parseInt(request.getParameter("size"));
                color = Integer.parseInt(request.getParameter("colors"));
                quantity = Integer.parseInt(request.getParameter("quantity"));

                Cart cart = (Cart) request.getSession().getAttribute("cart");
                if (cart == null) {
                    cart = new Cart();
                    request.getSession().setAttribute("cart", cart);
                }

                if (quantity <= 0) quantity = 1;
                if(!cart.add(id, size, color, quantity)) {
                    request.setAttribute("type", "error");
                    request.setAttribute("information", "Lỗi");
                    request.getRequestDispatcher("productDetail").forward(request, response);
                }

                request.getSession().setAttribute("id", id);

                request.getSession().setAttribute("cart", cart);
                response.sendRedirect("/patin_shop/showCart");

            }catch (NumberFormatException e) {
                request.setAttribute("type", "error");
                request.setAttribute("information", "Lỗi");
                request.getRequestDispatcher("productDetail").forward(request, response);
            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}