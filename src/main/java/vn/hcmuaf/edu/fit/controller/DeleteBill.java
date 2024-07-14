package vn.hcmuaf.edu.fit.controller;

import vn.hcmuaf.edu.fit.bean.Bill;
import vn.hcmuaf.edu.fit.dao.BillDao;
import vn.hcmuaf.edu.fit.dao.LogDao;
import vn.hcmuaf.edu.fit.services.BillService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;


@WebServlet(name = "DeleteBill", value = "/deleteBill")
public class DeleteBill extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            if(ipAddress == null){
                ipAddress = request.getRemoteAddr();
            }

            int billId= Integer.parseInt(request.getParameter("id"));
            Bill bill = new Bill();
            bill.setId(billId);
            bill.setBeforeData(bill.getName());
            BillService.getInstance().deleteBill(bill,ipAddress);
            response.getWriter().println("Bill deleted.");
        } catch (Exception e) {
            response.getWriter().println("Bill deleted fail.");
            e.printStackTrace();
        }
    }
}