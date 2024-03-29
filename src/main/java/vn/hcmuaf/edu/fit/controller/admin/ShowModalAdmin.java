package vn.hcmuaf.edu.fit.controller.admin;

import com.google.gson.Gson;
import vn.hcmuaf.edu.fit.bean.*;
import vn.hcmuaf.edu.fit.dao.CategoryDao;
import vn.hcmuaf.edu.fit.dao.ColorDao;
import vn.hcmuaf.edu.fit.dao.SizeDao;
import vn.hcmuaf.edu.fit.services.ProductService;
import vn.hcmuaf.edu.fit.services.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;


@WebServlet(name = "ShowModalAdmin", value = "/showModalAdmin")
public class ShowModalAdmin extends HttpServlet {
    private Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String select = request.getParameter("select");
        int id;
        if (select == null){
            request.setAttribute("type", "error");
            request.setAttribute("information", "Lỗi");
            request.getRequestDispatcher("showAdmin").forward(request, response);
        }else {
            try {
                id = Integer.parseInt(request.getParameter("id"));
                response.setContentType("application/json");

                if (select.equals("user")){
                    User user = UserService.getInstance().getUserByID(id);
                    response.getWriter().println(gson.toJson(user));

                } else if (select.equals("product")) {
                    String size = request.getParameter("size");
                    String color = request.getParameter("color");
                    if (size == null || color == null){
                        request.setAttribute("type", "error");
                        request.setAttribute("information", "Lỗi");
                        request.getRequestDispatcher("showAdmin").forward(request, response);
                    }else {
                        int idSize = SizeDao.getInstance().getIdByName(size);
                        int idColor = ColorDao.getInstance().getIdByName(color);
//                        Product product = ProductService.getInstance().getProductDetail(id, idSize, idColor);
                        ProductMain product = ProductService.getInstance().getProductDetail(id, idSize, idColor);
                        response.getWriter().println(gson.toJson(product));
                    }
                } else if (select.equals("brand")) {
                    Category category = CategoryDao.getInstance().getCategory(id);
                    response.getWriter().println(gson.toJson(category));
                } else if (select.equals("color")) {
                    Color color = ColorDao.getInstance().getColorById(id);
                    response.getWriter().println(gson.toJson(color));
                } else if (select.equals("size")) {
                    Size size = SizeDao.getInstance().getSizeById(id);
                    response.getWriter().println(gson.toJson(size));
                }


            }catch (NumberFormatException e) {
                request.setAttribute("type", "error");
                request.setAttribute("information", "Lỗi");
                request.getRequestDispatcher("showUserAdmin").forward(request, response);
            }

        }


        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}