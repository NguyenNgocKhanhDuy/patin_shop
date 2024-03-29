package vn.hcmuaf.edu.fit.controller.admin.update;

import vn.hcmuaf.edu.fit.bean.Color;
import vn.hcmuaf.edu.fit.dao.CategoryDao;
import vn.hcmuaf.edu.fit.dao.ColorDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "UpdateColorAdmin", value = "/updateColorAdmin")
public class UpdateColorAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id;
        String name = request.getParameter("name");


        if (name == null){
            request.setAttribute("type", "error");
            request.setAttribute("information", "Lỗi");
            request.getRequestDispatcher("admin.jsp").forward(request, response);
        }else {
            try {
                id = Integer.parseInt(request.getParameter("id"));

                Color color = ColorDao.getInstance().getColorById(id);
                color.setName(name);

                if (ColorDao.getInstance().updateColor(color)){
                    request.setAttribute("type", "success");
                    request.setAttribute("information", "Cập nhật thành công");
                    request.getRequestDispatcher("showColorAdmin").forward(request, response);
                }else {
                    request.setAttribute("type", "error");
                    request.setAttribute("information", "Lỗi sql");
                    request.getRequestDispatcher("showColorAdmin").forward(request, response);
                }


            }catch (NumberFormatException e ){
                request.setAttribute("type", "error");
                request.setAttribute("information", "Lỗi");
                request.getRequestDispatcher("showColorAdmin").forward(request, response);
            }
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}