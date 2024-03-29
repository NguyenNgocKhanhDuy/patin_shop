package vn.hcmuaf.edu.fit.controller;

import vn.hcmuaf.edu.fit.bean.Bill;
import vn.hcmuaf.edu.fit.bean.BillDetail;
import vn.hcmuaf.edu.fit.bean.User;
import vn.hcmuaf.edu.fit.services.BillService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.List;


@WebServlet(name = "PaymentDetail", value = "/paymentDetail")
public class PaymentDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("auth");
        if (user == null){
            response.getWriter().println("null");
        }else {
            request.setAttribute("user", user);

        Bill newBill = BillService.getInstance().getNewBill(user.getId());
        List<BillDetail> listBill = BillService.getInstance().getAllBillDetail(newBill.getId());

        request.setAttribute("bill", newBill);
        request.setAttribute("bills", listBill);

        request.getRequestDispatcher("payment_detail.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}