package vn.hcmuaf.edu.fit.controller.product_detail;

import vn.hcmuaf.edu.fit.bean.*;
import vn.hcmuaf.edu.fit.dao.*;
import vn.hcmuaf.edu.fit.services.ProductService;
import vn.hcmuaf.edu.fit.services.RatingService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "ShowRatingProduct", value = "/showRatingProduct")
public class ShowRatingProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productID;
        int currentPage;

        String contentRating = request.getParameter("content");
        request.setAttribute("content", contentRating);

        try {

            productID = Integer.parseInt(request.getParameter("productID"));
            request.setAttribute("productID", productID);

            try {
                currentPage = Integer.parseInt(request.getParameter("currentPage"));
            }catch (NumberFormatException e){
                currentPage = 1;
            }
            request.setAttribute("currentPage", currentPage);
            int totalPage = (int) Math.ceil((RatingDao.getInstance().getSizeAllRating(productID) / (3+0.0)));
            request.setAttribute("totalPage", totalPage);

            List<ProductMain> products = ProductService.getInstance().getAllProductDetail(productID);
            request.setAttribute("products", products);


            String sort = request.getParameter("sort");

            List<Rating> ratings = new ArrayList<>();
            if (sort == null){
                sort = "date";
            }

            request.setAttribute("sort", sort);
            ratings = RatingService.getInstance().getAllRatingPerPage(productID, currentPage, sort);
            request.setAttribute("ratingPerPage", ratings);

            String href = "showRatingProduct?productID="+productID;
            href += "&sort="+sort;
            request.setAttribute("href", href);

            List<Rating> all = RatingDao.getInstance().getAllRatingOfProduct(productID);
            request.setAttribute("ratings", all);
            double totalRate = 0;
            for (int i = 0; i < all.size(); i++) {
                totalRate += all.get(i).getScore()+0.0;
            }
            totalRate = totalRate / all.size();

            request.setAttribute("totalRate", (double) Math.round(totalRate * 10) / 10);

            List<RatingLike> likes = RatingDao.getInstance().getLike(productID);
            request.setAttribute("likes", likes);

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
                totalQuantity += products.get(0).getProductDetail().getQuantity();
            }

            request.setAttribute("totalQuantity", totalQuantity);

            List<ImageRating> listImgRating = RatingDao.getInstance().getImageRatingOfProduct(productID);
            request.setAttribute("listImgRating", listImgRating);
            request.setAttribute("hasImg", 0);

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}