package vn.hcmuaf.edu.fit.controller.admin.update;

import vn.hcmuaf.edu.fit.bean.Size;
import vn.hcmuaf.edu.fit.dao.ColorDao;
import vn.hcmuaf.edu.fit.dao.SizeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "UpdateSizeAdmin", value = "/updateSizeAdmin")
public class UpdateSizeAdmin extends HttpServlet {
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
                try {
                    int sizeName = Integer.valueOf(name);
                    Size size = SizeDao.getInstance().getSizeById(id);
                    size.setName(name);

                    if (SizeDao.getInstance().updateSize(size)){
                        request.setAttribute("type", "success");
                        request.setAttribute("information", "Cập nhật thành công");
                        request.getRequestDispatcher("showSizeAdmin").forward(request, response);
                    }else {
                        request.setAttribute("type", "error");
                        request.setAttribute("information", "Lỗi sql");
                        request.getRequestDispatcher("showSizeAdmin").forward(request, response);
                    }


                }catch (NumberFormatException e){
                    request.setAttribute("type", "error");
                    request.setAttribute("information", "Size nhập vào là số");
                    request.getRequestDispatcher("showSizeAdmin").forward(request, response);
                }

            }catch (NumberFormatException e ){
                request.setAttribute("type", "error");
                request.setAttribute("information", "Lỗi");
                request.getRequestDispatcher("showSizeAdmin").forward(request, response);
            }
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}