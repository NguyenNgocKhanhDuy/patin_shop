package vn.hcmuaf.edu.fit.controller.product_detail;

import com.google.gson.Gson;
import vn.hcmuaf.edu.fit.services.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.ArrayList;


@WebServlet(name = "ProductPriceQuantity", value = "/productPriceQuantity")
public class ProductPriceQuantity extends HttpServlet {
    private Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int size = Integer.parseInt(request.getParameter("size"));
        int color = Integer.parseInt(request.getParameter("color"));

        ArrayList<Integer> priceQuantity = new ArrayList<>();
        int price = ProductService.getInstance().getPrice(id, size, color);
        int quantity = ProductService.getInstance().getQuantity(id, size, color);
        priceQuantity.add(price);
        priceQuantity.add(quantity);

        response.setContentType("application/json");
        response.getWriter().print(gson.toJson(priceQuantity));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}