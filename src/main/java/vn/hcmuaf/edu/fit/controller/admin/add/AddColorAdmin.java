package vn.hcmuaf.edu.fit.controller.admin.add;

import vn.hcmuaf.edu.fit.bean.Category;
import vn.hcmuaf.edu.fit.bean.Color;
import vn.hcmuaf.edu.fit.dao.CategoryDao;
import vn.hcmuaf.edu.fit.dao.ColorDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "AddColorAdmin", value = "/addColorAdmin")
public class AddColorAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");

        if (name == null){
            request.setAttribute("type", "error");
            request.setAttribute("information", "Lỗi null");
            request.getRequestDispatcher("showColorAdmin").forward(request, response);
        }else {

            Color color = new Color(0, name);

            if (!ColorDao.getInstance().insertColor(color)){
                request.setAttribute("type", "error");
                request.setAttribute("information", "Lỗi sql");
                request.getRequestDispatcher("showColorAdmin").forward(request, response);
            }else {
                request.setAttribute("type", "success");
                request.setAttribute("information", "Thêm thành công");
                request.getRequestDispatcher("showColorAdmin").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}