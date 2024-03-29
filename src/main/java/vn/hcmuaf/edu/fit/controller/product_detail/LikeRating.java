package vn.hcmuaf.edu.fit.controller.product_detail;

import vn.hcmuaf.edu.fit.bean.Rating;
import vn.hcmuaf.edu.fit.bean.RatingLike;
import vn.hcmuaf.edu.fit.bean.User;
import vn.hcmuaf.edu.fit.services.RatingService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;


@WebServlet(name = "LikeRating", value = "/likeRating")
public class LikeRating extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("auth");
        if (user == null){
            request.setAttribute("type", "error");
            request.setAttribute("information", "Đăng nhập để tiếp tục");
            request.getRequestDispatcher("listProduct").forward(request, response);
        }else {
            int id;
            int productID;
            try {
                id = Integer.parseInt(request.getParameter("id"));
                productID = Integer.parseInt(request.getParameter("productID"));
                Rating rating = new Rating();
                rating.setId(id);
                RatingLike ratingLike = new RatingLike();
                ratingLike.setRating(rating);
                ratingLike.setUser(user);
                if(RatingService.getInstance().addRatingLike(ratingLike)){
                    request.getRequestDispatcher("productDetail?id="+id+"&productID="+productID).forward(request, response);
                }else {
                    request.setAttribute("type", "error");
                    request.setAttribute("information", "Lỗi sql");
                    request.getRequestDispatcher("listProduct").forward(request, response);
                }
            }catch (NumberFormatException e){
                request.setAttribute("type", "error");
                request.setAttribute("information", "Lỗi");
                request.getRequestDispatcher("listProduct").forward(request, response);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}