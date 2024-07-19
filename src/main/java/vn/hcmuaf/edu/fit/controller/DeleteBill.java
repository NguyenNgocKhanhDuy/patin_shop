package vn.hcmuaf.edu.fit.controller;

import vn.hcmuaf.edu.fit.bean.Bill;
import vn.hcmuaf.edu.fit.bean.BillDetail;
import vn.hcmuaf.edu.fit.bean.ProductDetail;
import vn.hcmuaf.edu.fit.bean.ProductMain;
import vn.hcmuaf.edu.fit.dao.BillDao;
import vn.hcmuaf.edu.fit.dao.BillDetailDao;
import vn.hcmuaf.edu.fit.dao.LogDao;
import vn.hcmuaf.edu.fit.dao.ProductDetailDao;
import vn.hcmuaf.edu.fit.services.BillService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.List;


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
            Bill bill = BillDao.getInstance().getBill(billId);
            if (bill.getStatus() == 5 || bill.getStatus() == 4) {
                response.getWriter().println("Đơn hàng không thể huỷ");
            }else {
                BillDao.getInstance().deleteBillUser(bill,ipAddress);
                List<BillDetail> billDetail = BillDetailDao.getInstance().getAllBillDetail(billId);
                for (int i = 0; i < billDetail.size(); i++) {
                    int id = billDetail.get(i).getProduct().getProductDetail().getProduct().getId();
                    int size = billDetail.get(i).getSize().getId();
                    int color = billDetail.get(i).getColor().getId();
                    int quantity = billDetail.get(i).getQuantity();
                    ProductDetailDao.getInstance().updateQuantity(id, size, color, quantity);
                }
                response.getWriter().println("Đơn hàng huỷ thành công");


            }


        } catch (Exception e) {
            response.getWriter().println("Bill deleted fail.");
            e.printStackTrace();
        }
    }
}