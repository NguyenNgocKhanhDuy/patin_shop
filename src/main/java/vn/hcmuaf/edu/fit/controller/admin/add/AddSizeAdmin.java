package vn.hcmuaf.edu.fit.controller.admin.add;

import vn.hcmuaf.edu.fit.bean.Color;
import vn.hcmuaf.edu.fit.bean.Size;
import vn.hcmuaf.edu.fit.dao.ColorDao;
import vn.hcmuaf.edu.fit.dao.SizeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "AddSizeAdmin", value = "/addSizeAdmin")
public class AddSizeAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");

        if (name == null){
            request.setAttribute("type", "error");
            request.setAttribute("information", "Lỗi null");
            request.getRequestDispatcher("showSizeAdmin").forward(request, response);
        }else {
            try {
                int sizeName = Integer.valueOf(name);
                Size size = new Size(0, name);

                if (!SizeDao.getInstance().insertSize(size)){
                    request.setAttribute("type", "error");
                    request.setAttribute("information", "Lỗi sql");
                    request.getRequestDispatcher("showSizeAdmin").forward(request, response);
                }else {
                    request.setAttribute("type", "success");
                    request.setAttribute("information", "Thêm thành công");
                    request.getRequestDispatcher("showSizeAdmin").forward(request, response);
                }

            }catch (NumberFormatException e){
                request.setAttribute("type", "error");
                request.setAttribute("information", "Size nhập vào số");
                request.getRequestDispatcher("showSizeAdmin").forward(request, response);
            }


        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}