package vn.hcmuaf.edu.fit.controller.admin.getData;

import com.google.gson.Gson;
import vn.hcmuaf.edu.fit.bean.ProductMain;
import vn.hcmuaf.edu.fit.services.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.List;


@WebServlet(name = "GetProductDetail", value = "/getProductDetail")
public class GetProductDetail extends HttpServlet {
    private Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id;
        try {
            id = Integer.parseInt(request.getParameter("id"));
            List<ProductMain> products = ProductService.getInstance().getAllProductDetail(id);
            response.setContentType("application/json");
            response.getWriter().println(gson.toJson(products));
        }catch (NumberFormatException e) {

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}