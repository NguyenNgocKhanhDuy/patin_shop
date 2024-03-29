package vn.hcmuaf.edu.fit.controller.product_detail;

import vn.hcmuaf.edu.fit.bean.*;
import vn.hcmuaf.edu.fit.dao.*;
import vn.hcmuaf.edu.fit.services.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.List;


@WebServlet(name = "ProductDetail", value = "/productDetail")
public class ProductDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productID;

        try {
            productID = Integer.parseInt(request.getParameter("productID"));

            request.setAttribute("productID", productID);

            List<ProductMain> products = ProductService.getInstance().getAllProductDetail(productID);

            String info = products.get(0).getProductDetail().getProduct().getInformation().replace("%", "<br>- ");
            products.get(0).getProductDetail().getProduct().setInformation(info);

            request.setAttribute("products", products);

            String href = "productDetail?productID="+productID;
            request.setAttribute("href", href);


            List<Rating> ratings = RatingDao.getInstance().getAllRatingOfProduct(productID);
            request.setAttribute("ratings", ratings);
            double totalRate = 0;
            for (int i = 0; i < ratings.size(); i++) {
                totalRate += ratings.get(i).getScore()+0.0;
            }
            totalRate = totalRate / ratings.size();

            request.setAttribute("totalRate", (double) Math.round(totalRate * 10) / 10);



            List<ImageProduct> listImg = ImageProductDao.getInstance().getAllImage(productID);
            request.setAttribute("listImg", listImg);

            List<Color> colors = ColorDao.getInstance().getProductColor(productID);
            request.setAttribute("colors", colors);

            List<Size> sizes = SizeDao.getInstance().getProductSize(productID);
            request.setAttribute("sizes", sizes);

            List<ProductMain> orderProduct = ProductService.getInstance().getRandomProduct(4);
            request.setAttribute("orderProducts", orderProduct);

            int totalQuantity = 0;
            for (int i = 0; i < products.size(); i++) {
                totalQuantity += products.get(i).getProductDetail().getQuantity();
            }

            request.setAttribute("totalQuantity", totalQuantity);

            request.setAttribute("type", request.getAttribute("type"));
            request.setAttribute("information", request.getAttribute("information"));

            request.getRequestDispatcher("product_detail.jsp").forward(request, response);


        }catch (NumberFormatException e) {
            request.setAttribute("type", "error");
            request.setAttribute("infomation", "Lỗi");
            request.getRequestDispatcher("listProduct").forward(request, response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {;
        doGet(request, response);
    }
}