package vn.hcmuaf.edu.fit.controller.account;

import vn.hcmuaf.edu.fit.bean.Bill;
import vn.hcmuaf.edu.fit.bean.BillDetail;
import vn.hcmuaf.edu.fit.services.BillService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.List;


@WebServlet(name = "ShowOrderDetail", value = "/showOrderDetail")
public class ShowOrderDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id;
        try {
            id = Integer.parseInt(request.getParameter("id"));
            Bill bill = BillService.getInstance().getBill(id);
            request.setAttribute("bill", bill);
            List<BillDetail> billDetail = BillService.getInstance().getBillDetail(id);
            request.setAttribute("billDetail", billDetail);

            request.getRequestDispatcher("account.jsp").forward(request, response);

        }catch (NumberFormatException e){
            request.setAttribute("type", "error");
            request.setAttribute("information", "Lỗi null");
            request.getRequestDispatcher("showColorAdmin").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}