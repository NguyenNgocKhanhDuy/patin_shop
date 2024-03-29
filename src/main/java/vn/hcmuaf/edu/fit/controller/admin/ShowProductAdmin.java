package vn.hcmuaf.edu.fit.controller.admin;

import vn.hcmuaf.edu.fit.bean.ProductMain;
import vn.hcmuaf.edu.fit.bean.User;
import vn.hcmuaf.edu.fit.services.PermissionsService;
import vn.hcmuaf.edu.fit.services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "ShowProductAdmin", value = "/showProductAdmin")
public class ShowProductAdmin extends HttpServlet {
    String rsName = "product";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("auth");
        if (user == null) request.getRequestDispatcher("login.jsp").forward(request, response);
        else {
            int per = PermissionsService.getPermissionsService().checkAccess(rsName, user.getId());
            request.setAttribute("per", per);

            int all = ProductService.getInstance().countAll();
            request.setAttribute("allProduct", all);


            int currentPage;
            try {
                currentPage = Integer.parseInt(request.getParameter("currentPage"));
            }catch (NumberFormatException e) {
                currentPage = 1;
            }

            String href = "showProductAdmin?";
            request.setAttribute("href", href);

            double productPerPage = 5.0;

            int totalPage = (int) Math.ceil(all/ productPerPage);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("productPerPage", (int) productPerPage);

            List<ProductMain> products = ProductService.getInstance().getProductPerPage(currentPage, "", (int) productPerPage);
            request.setAttribute("products", products);

            request.setAttribute("type", request.getAttribute("type"));
            request.setAttribute("information", request.getAttribute("information"));

            request.getRequestDispatcher("admin.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}