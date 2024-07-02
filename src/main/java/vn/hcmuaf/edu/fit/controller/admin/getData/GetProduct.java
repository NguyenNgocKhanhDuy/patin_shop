package vn.hcmuaf.edu.fit.controller.admin.getData;

import com.google.gson.Gson;
import vn.hcmuaf.edu.fit.bean.ProductMain;
import vn.hcmuaf.edu.fit.services.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.List;


@WebServlet(name = "GetProduct", value = "/getProduct")
public class GetProduct extends HttpServlet {
    private Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ProductMain> products = ProductService.getInstance().getAllProduct();
        response.setContentType("application/json");
        response.getWriter().println(gson.toJson(products));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}