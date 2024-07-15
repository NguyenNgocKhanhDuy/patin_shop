package vn.hcmuaf.edu.fit.controller.admin;

import vn.hcmuaf.edu.fit.bean.Bill;
import vn.hcmuaf.edu.fit.bean.Product;
import vn.hcmuaf.edu.fit.bean.ProductMain;
import vn.hcmuaf.edu.fit.bean.User;
import vn.hcmuaf.edu.fit.dao.BillDao;
import vn.hcmuaf.edu.fit.dao.BillDetailDao;
import vn.hcmuaf.edu.fit.dao.ProductDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "ShowStatistic", value = "/showStatistic")
public class ShowStatistic extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("auth");
        if (user == null) request.getRequestDispatcher("login.jsp").forward(request, response);
        else {
//            List<Integer> idProducts = BillDetailDao.getInstance().getBestProductSell();
//            List<Integer> idProducts = BillDetailDao.getInstance().getTopSellerProduct();
//            List<ProductMain> productMains = new ArrayList<>();
//            for (int i = 0; i < idProducts.size(); i++) {
//                productMains.add(ProductDao.getInstance().getProduct(idProducts.get(i)));
//            }
/*            List<ProductMain> statistic = new ArrayList<>();
            for (ProductMain products: productMains) {
                statistic.add(new ProductMain(
                        products.getProductDetail().getProduct().getId(),
                        products.getProductDetail().getSize(),
                        products.getProductDetail().getColor(),
                        products.getProductDetail().getQuantity()
                ));
            }*/

            int i = BillDao.getInstance().getAllBillSuccess().size();

            request.setAttribute("allBill", i);
            request.setAttribute("statistic", "statistic");
            request.getRequestDispatcher("admin.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}