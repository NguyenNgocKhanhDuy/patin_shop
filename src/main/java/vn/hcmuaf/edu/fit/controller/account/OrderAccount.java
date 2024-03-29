package vn.hcmuaf.edu.fit.controller.account;

import vn.hcmuaf.edu.fit.bean.Bill;
import vn.hcmuaf.edu.fit.bean.User;
import vn.hcmuaf.edu.fit.services.BillService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "OrderAccount", value = "/orderAccount")
public class OrderAccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("auth");
        int currentPage;
        try {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }catch (NumberFormatException e ){
            currentPage = 1;
        }

        String href = "orderAccount?";
        request.setAttribute("href", href);

        double productPerPage = 5.0;

        int billStatus;
        try {
            billStatus = Integer.parseInt(request.getParameter("billStatus"));
        }catch (NumberFormatException e){
            billStatus = 0;
        }

        List<Bill> allBills = new ArrayList<>();
        List<Bill> bills = new ArrayList<>();

        if (!(billStatus == 0)){
            String status = billStatus == 1 ? "Đang giao" : "Đã giao";
            allBills = BillService.getInstance().getAllBillByUserAndStatus(user.getId(), status);
            bills = BillService.getInstance().getBillPerPageByUserAndStatus(currentPage,(int) productPerPage, user.getId(), status);
        }else {
            allBills = BillService.getInstance().getAllBillByUser(user.getId());
            bills = BillService.getInstance().getBillPerPageByUser(currentPage, (int) productPerPage, user.getId());
        }

        request.setAttribute("billStatus", billStatus);

        int totalPage = (int) Math.ceil(allBills.size() / productPerPage);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("productPerPage", (int) productPerPage);

        request.setAttribute("bills", bills);
        request.getRequestDispatcher("account.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}