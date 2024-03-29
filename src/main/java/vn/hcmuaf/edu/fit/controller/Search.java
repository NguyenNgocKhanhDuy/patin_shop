package vn.hcmuaf.edu.fit.controller;

import com.google.gson.Gson;
import vn.hcmuaf.edu.fit.bean.ProductMain;
import vn.hcmuaf.edu.fit.services.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.List;


@WebServlet(name = "Search", value = "/search")
public class Search extends HttpServlet {
    private Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String find = (String) request.getParameter("search");
        List<ProductMain> listSearch = ProductService.getInstance().search(find);
        response.setContentType("application/json");
        response.getWriter().print(gson.toJson(listSearch));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}