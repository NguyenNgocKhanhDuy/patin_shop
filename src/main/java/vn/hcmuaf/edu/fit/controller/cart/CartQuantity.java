package vn.hcmuaf.edu.fit.controller.cart;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import vn.hcmuaf.edu.fit.cart.Cart;
import vn.hcmuaf.edu.fit.cart.CartKey;
import vn.hcmuaf.edu.fit.cart.CartProduct;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "CartQuantity", value = "/cartQuantity")
public class CartQuantity extends HttpServlet {
    private Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            if (type.equals("minus")) {
                if (cart.decrease(id, size, color)){
                }else {
                    response.getWriter().println(1);
                }
            } else if (type.equals("plus")) {
                cart.increase(id, size, color);
            }

            request.getSession().setAttribute("cart", cart);

            ArrayList<CartKey> keys = new ArrayList<>();
            ArrayList<String> s =new ArrayList<>();

            for (CartKey key: cart.getData().keySet()) {
                String txt = "";
                txt += "{\"id\":\""+key.getId()+"\",\"size\":\""+key.getSize()+"\",\"color\":\""+key.getColor()+"\"}";
                s.add(txt);
                keys.add(key);
            }

            Map<String, CartProduct> c = new HashMap<>();

            for (int i = 0; i < s.size(); i++) {
                c.put(s.get(i), cart.getData().get(keys.get(i)));
            }


            response.setContentType("application/json");
            response.getWriter().print(gson.toJson(c));


        } catch (NumberFormatException e) {
            request.setAttribute("type", "error");
            request.setAttribute("information", "Lỗi");
            request.getRequestDispatcher("productDetail").forward(request, response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}