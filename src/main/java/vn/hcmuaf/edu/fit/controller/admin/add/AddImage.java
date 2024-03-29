package vn.hcmuaf.edu.fit.controller.admin.add;

import vn.hcmuaf.edu.fit.dao.ImageProductDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;


@WebServlet(name = "AddImage", value = "/addImage")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class AddImage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id;
        try {
            id = Integer.parseInt(request.getParameter("id"));
            Part filePart = request.getPart("file");

            if (filePart.getSize() != 0) {
                String fileName = filePart.getSubmittedFileName();
                ServletContext servletContext = getServletContext();
                File root = new File(servletContext.getRealPath("/") + "data/product/id"+id);
                if (!root.exists()) root.mkdirs();

                System.out.println(root.getAbsolutePath());
                String url = "data/product/id"+id+"/"+fileName;

                for (Part part : request.getParts()) {
                    part.write(root.getAbsolutePath() + "/" + fileName);
                }

                if (ImageProductDao.getInstance().addImage(url, id)){
                    request.setAttribute("type", "success");
                    request.setAttribute("information", "Thêm thành công");
                    request.getRequestDispatcher("showProductDetailAdmin?id="+id).forward(request, response);
                }else {
                    request.setAttribute("type", "error");
                    request.setAttribute("information", "Lỗi sql");
                    request.getRequestDispatcher("showProductDetailAdmin?id="+id).forward(request, response);
                }

            }else {
                request.setAttribute("type", "error");
                request.setAttribute("information", "Thêm ảnh sản phẩm");
                request.getRequestDispatcher("showProductAdmin").forward(request, response);
            }
        }catch (NumberFormatException e){
            request.setAttribute("type", "error");
            request.setAttribute("information", "Lỗi");
            request.getRequestDispatcher("showProductAdmin").forward(request, response);
        }

    }
}