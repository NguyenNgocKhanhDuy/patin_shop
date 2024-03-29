package vn.hcmuaf.edu.fit.controller;

import vn.hcmuaf.edu.fit.bean.ProductMain;
import vn.hcmuaf.edu.fit.services.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.List;


@WebServlet(name = "Home", value = "/home")
public class Home extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ProductMain> listHotProduct = ProductService.getInstance().getHotProduct();
        List<ProductMain> listProduct = ProductService.getInstance().getProductHome();

        request.setAttribute("hotProducts", listHotProduct);
        request.setAttribute("products", listProduct);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}