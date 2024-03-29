package vn.hcmuaf.edu.fit.controller;

import vn.hcmuaf.edu.fit.bean.ProductMain;
import vn.hcmuaf.edu.fit.services.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@WebServlet(name = "ListProduct", value = "/listProduct")
public class ListProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ProductMain> listHotProduct = ProductService.getInstance().getHotProduct();
        request.setAttribute("hotProducts", listHotProduct);

        int currentPage = 0;
        int min;
        int max;

        int minValue = 10000;
        int maxValue = 10000000;

        double productPerPage = 15;

        try {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }catch (NumberFormatException e) {
            currentPage = 1;
        }

        try {
            min = Integer.parseInt(request.getParameter("min"));
            max = Integer.parseInt(request.getParameter("max"));
        }catch (NumberFormatException e){
            String minText = request.getParameter("min");
            String maxText = request.getParameter("max");
            if (minText == null) minText = String.valueOf(minValue);
            if (maxText == null) maxText = String.valueOf(maxValue);

            if (!minText.equals(minValue+"")) {
                minText = minText.replace(".", "");
            }
            if (!maxText.equals(maxValue+"")) {
                maxText = maxText.replace(".", "");
            }

            try {
                min = Integer.parseInt(minText);
                max = Integer.parseInt(maxText);
            }catch (NumberFormatException e1) {
                request.setAttribute("type", "error");
                request.setAttribute("information", "Giá nhập vào không hợp lệ");
                min = minValue;
                max = maxValue;
            }
        }

        if (min > max) {
            request.setAttribute("type", "alert");
            request.setAttribute("information", "Giá thấp không được vượt qua giá cao");
            min = minValue;
            max = maxValue;
        }else if (min < minValue || min > maxValue || max < minValue || max > maxValue) {
            request.setAttribute("type", "alert");
            request.setAttribute("information", "Giá phải trong 0đ - 10.000.000đ");
            min = minValue;
            max = maxValue;
        }

        String sort = request.getParameter("sort");
        if(sort == null) sort = "";
        String[] colors = request.getParameterValues("color");
        if (colors == null) {
            colors = new String[6];
            for (int i = 0; i < colors.length; i++) {
                colors[i] = "0";
            }
        }

        boolean isColorFilter = false;

        List<String> listColors = new ArrayList<>(Arrays.asList(colors));
        if (!listColors.contains("0")) isColorFilter = true;

        List<ProductMain> products;
        int totalPage;
        String href = "listProduct?";

        if ((min != minValue || max != maxValue) && !isColorFilter) {
            if (min < minValue || max > maxValue) {
                request.setAttribute("type", "error");
                request.setAttribute("information", "Giá nhập vào phải từ 0đ - 10.000.000đ");
                min = minValue;
                max = maxValue;
            }
            products = ProductService.getInstance().getProductPerPageFilterPrice(currentPage, sort, min, max);
            totalPage = (int) Math.ceil((ProductService.getInstance().countFilterPrice(min, max) / productPerPage));
            href += "min="+min+"&max="+max;
        } else if (!(min != minValue || max != maxValue) && isColorFilter) {
            products = ProductService.getInstance().getProductPerPageFilterColor(currentPage, sort, colors);
            totalPage = (int) Math.ceil((ProductService.getInstance().countFilterColor(colors) / productPerPage));

            String txt = "";
            for (int i = 0; i < colors.length; i++) {
                txt += "&color="+colors[i];
            }
            href += txt;

        } else if ((min != minValue || max != maxValue) && isColorFilter) {
            products = ProductService.getInstance().getProductPerPageFilterPriceColor(currentPage, sort, min, max, colors);
            totalPage = (int) Math.ceil((ProductService.getInstance().countFilterPriceColor(min, max, colors) / productPerPage));

            String txt = "";
            for (int i = 0; i < colors.length; i++) {
                txt += "&color="+colors[i];
            }
            href += "min="+min+"&max="+max+txt;

        }else {
            products = ProductService.getInstance().getProductPerPage(currentPage, sort, 15);
            totalPage = (int) Math.ceil((ProductService.getInstance().countAll() / productPerPage));
        }

        href += "&sort="+sort;

        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPage", totalPage);

        request.setAttribute("colors", listColors);
        request.setAttribute("min", min);
        request.setAttribute("max", max);
        request.setAttribute("sort", sort);

        request.setAttribute("select"+sort, "selected");
        request.setAttribute("href", href);

        request.setAttribute("products", products);

        request.getRequestDispatcher("list_product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}