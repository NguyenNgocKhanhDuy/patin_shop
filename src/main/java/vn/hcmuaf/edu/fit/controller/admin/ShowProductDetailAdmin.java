package vn.hcmuaf.edu.fit.controller.admin;

import vn.hcmuaf.edu.fit.bean.*;
import vn.hcmuaf.edu.fit.dao.ImageProductDao;
import vn.hcmuaf.edu.fit.services.PermissionsService;
import vn.hcmuaf.edu.fit.services.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.List;


@WebServlet(name = "ShowProductDetailAdmin", value = "/showProductDetailAdmin")
public class ShowProductDetailAdmin extends HttpServlet {
    String rsName = "product";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("auth");
        if (user == null) request.getRequestDispatcher("login.jsp").forward(request, response);
        else {
            int per = PermissionsService.getPermissionsService().checkAccess(rsName, user.getId());
            request.setAttribute("per", per);

            int id;
            try {
                id = Integer.parseInt(request.getParameter("id"));
                ProductMain product = ProductService.getInstance().getProduct(id);
                request.setAttribute("product", product);

                String text = product.getProductDetail().getProduct().getInformation();
                text = text.replace("%", "\r\n").trim();
                request.setAttribute("text", text);

                String info = product.getProductDetail().getProduct().getInformation().replace("%", "<br>- ");
                product.getProductDetail().getProduct().setInformation(info);

                int currentPage;
                try {
                    currentPage = Integer.parseInt(request.getParameter("currentPage"));
                }catch (NumberFormatException e) {
                    currentPage = 1;
                }

                String href = "showProductDetailAdmin?id="+id;
                request.setAttribute("href", href);

                double productPerPage = 5.0;

                int totalPage = (int) Math.ceil(ProductService.getInstance().getAllProductDetail(id).size()/ productPerPage);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("currentPage", currentPage);
                request.setAttribute("productPerPage", (int) productPerPage);

                List<ProductMain> productDetail = ProductService.getInstance().getAllProductDetailPerPage(currentPage, (int) productPerPage, id);
                request.setAttribute("productDetail", productDetail);

                List<ImageProduct> imageProducts = ImageProductDao.getInstance().getAllImage(id);
                request.setAttribute("images", imageProducts);

                request.getRequestDispatcher("admin.jsp").forward(request, response);

            }catch (NumberFormatException e){
                request.setAttribute("type", "error");
                request.setAttribute("information", "Lỗi null");
                request.getRequestDispatcher("showProductAdmin").forward(request, response);
            }
        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}