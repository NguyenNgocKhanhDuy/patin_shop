package vn.hcmuaf.edu.fit.controller.product_detail;

import vn.hcmuaf.edu.fit.bean.ImageRating;
import vn.hcmuaf.edu.fit.bean.Product;
import vn.hcmuaf.edu.fit.bean.Rating;
import vn.hcmuaf.edu.fit.bean.User;
import vn.hcmuaf.edu.fit.services.RatingService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.time.LocalDateTime;


@WebServlet(name = "PostRating", value = "/postRating")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class PostRating extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("auth");
        if (user == null) {
            request.setAttribute("type", "error");
            request.setAttribute("information", "Đăng nhập để tiếp tục");
            request.getRequestDispatcher("showRatingProduct").forward(request, response);
        } else {
            int point;
            String content = request.getParameter("content");
            content = content.trim();

            try {
                int productID = Integer.parseInt(request.getParameter("productID"));
                point = Integer.parseInt(request.getParameter("rate"));

                LocalDateTime date = LocalDateTime.now();
                Product product = new Product();
                product.setId(productID);

                Rating rating = new Rating(0, point, content, date, 0, user, product);

                if (content.length() == 0){
                    if (!RatingService.getInstance().addRatingNoContent(rating)) {
                        request.setAttribute("content", content);
                        request.setAttribute("type", "error");
                        request.setAttribute("information", "Có lỗi xảy ra");
                        request.getRequestDispatcher("showRatingProduct").forward(request, response);
                    }
                }else {
                    if (!RatingService.getInstance().addRatingHasContent(rating)) {
                        request.setAttribute("content", content);
                        request.setAttribute("type", "error");
                        request.setAttribute("information", "Có lỗi xảy ra");
                        request.getRequestDispatcher("showRatingProduct").forward(request, response);
                    }
                }



                Part filePart = request.getPart("file");

                if (filePart.getSize() != 0) {
                    String fileName = filePart.getSubmittedFileName();
                    ServletContext servletContext = getServletContext();
                    File root = new File(servletContext.getRealPath("/") + "data/rating");
                    if (!root.exists()) root.mkdirs();

                    System.out.println(root.getAbsolutePath());

                    ImageRating imageRating = new ImageRating();
                    imageRating.setRating(RatingService.getInstance().getNewRating());
                    imageRating.setUrl("data/rating/" + fileName);

                    RatingService.getInstance().addRatingImg(imageRating);

                    for (Part part : request.getParts()) {
                        part.write(root.getAbsolutePath() + "/" + fileName);
                    }

                }
                request.setAttribute("type", "success");
                request.setAttribute("information", "Đánh giá thành công");
                request.getRequestDispatcher("showRatingProduct").forward(request, response);


            } catch (NumberFormatException e) {
                request.setAttribute("content", content);
                request.setAttribute("type", "error");
                request.setAttribute("information", "Nhập đánh giá của bạn");
                request.getRequestDispatcher("showRatingProduct").forward(request, response);
            }


        }

    }
}