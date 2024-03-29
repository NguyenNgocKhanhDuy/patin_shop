package vn.hcmuaf.edu.fit.controller.admin.add;

import vn.hcmuaf.edu.fit.bean.*;
import vn.hcmuaf.edu.fit.dao.ImageProductDao;
import vn.hcmuaf.edu.fit.services.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;


@WebServlet(name = "AddProductAdmin", value = "/addProductAdmin")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class AddProductAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int price;
        int color;
        int size;
        int quantity;
        int sale_percent;
        String hot = request.getParameter("hot");
        String information = request.getParameter("information");
        if (name == null || information == null) {
            request.setAttribute("type", "error");
            request.setAttribute("information", "Lỗi null");
            request.getRequestDispatcher("showUserAdmin").forward(request, response);
        } else if (name.trim().equals("")) {
            request.setAttribute("type", "error");
            request.setAttribute("information", "Tên sản phẩm không được để trống");
            request.getRequestDispatcher("showProductAdmin").forward(request, response);
        }else {
            if (hot == null) hot = "0";
            int hotNum = Integer.valueOf(hot);
            try {
                price = Integer.parseInt(request.getParameter("price"));
                quantity = Integer.parseInt(request.getParameter("quantity"));
                color = Integer.parseInt(request.getParameter("color"));
                size = Integer.parseInt(request.getParameter("size"));
                sale_percent = Integer.parseInt(request.getParameter("sale"));
                double sale = sale_percent/100.0;
                information = information.replace("\r\n", "%");
                if (quantity <= 0){
                    request.setAttribute("type", "error");
                    request.setAttribute("information", "Số lượng phải lớn hơn 0");
                    request.getRequestDispatcher("showProductAdmin").forward(request, response);
                }else {
                    Size sizeOb = new Size(size, "");
                    Color colorOb = new Color(color, "");

                    Product product = new Product(0, name, hotNum, sale, information);
                    boolean flag = ProductService.getInstance().addProduct(product);
                    product.setId(ProductService.getInstance().getIdNewProduct());

                    ProductDetail productDetail = new ProductDetail(product, sizeOb, colorOb, quantity, price);

                    flag = ProductService.getInstance().addProductDetail(productDetail) && flag;

                    int id = ProductService.getInstance().getIdNewProduct();
                    String imgUrl = "";

                    Part filePart = request.getPart("file");


                    if (filePart.getSize() != 0) {
                        String fileName = filePart.getSubmittedFileName();
                        ServletContext servletContext = getServletContext();
                        File root = new File(servletContext.getRealPath("/") + "data/product/id"+id);
                        if (!root.exists()) root.mkdirs();

                        System.out.println(root.getAbsolutePath());
                        imgUrl = "data/product/id"+id+"/" + fileName;


                        for (Part part : request.getParts()) {
                            part.write(root.getAbsolutePath() + "/" + fileName);
                        }

                        if (ImageProductDao.getInstance().addFirstImage(imgUrl, id) && flag){
                            request.setAttribute("type", "success");
                            request.setAttribute("information", "Thêm thành công");
                            request.getRequestDispatcher("showProductAdmin").forward(request, response);
                        }else {
                            request.setAttribute("type", "error");
                            request.setAttribute("information", "Lỗi sql");
                            request.getRequestDispatcher("showProductAdmin").forward(request, response);
                        }
                    } else {
                        request.setAttribute("type", "error");
                        request.setAttribute("information", "Thêm ảnh sản phẩm");
                        request.getRequestDispatcher("showProductAdmin").forward(request, response);
                    }

                }

            }catch (NumberFormatException e){
                request.setAttribute("type", "error");
                request.setAttribute("information", "Lỗi giảm giá phải là số");
                request.getRequestDispatcher("showProductAdmin").forward(request, response);
            }
        }
    }
}