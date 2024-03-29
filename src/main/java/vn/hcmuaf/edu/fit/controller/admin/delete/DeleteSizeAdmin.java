package vn.hcmuaf.edu.fit.controller.admin.delete;

import vn.hcmuaf.edu.fit.dao.ColorDao;
import vn.hcmuaf.edu.fit.dao.SizeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "DeleteSizeAdmin", value = "/deleteSizeAdmin")
public class DeleteSizeAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id;
        try {
            id = Integer.parseInt(request.getParameter("id"));

            if (SizeDao.getInstance().deleteSize(id)){
                request.setAttribute("type", "success");
                request.setAttribute("information", "Xoá thành công");
                request.getRequestDispatcher("showSizeAdmin").forward(request, response);
            }else {
                request.setAttribute("type", "error");
                request.setAttribute("information", "Lỗi sql");
                request.getRequestDispatcher("showSizeAdmin").forward(request, response);
            }

        }catch (NumberFormatException e){
            request.setAttribute("type", "error");
            request.setAttribute("information", "Lỗi null");
            request.getRequestDispatcher("showSizeAdmin").forward(request, response);
        }
    }
}