package vn.hcmuaf.edu.fit.controller.admin.update;

import vn.hcmuaf.edu.fit.bean.Bill;
import vn.hcmuaf.edu.fit.bean.BillDetail;
import vn.hcmuaf.edu.fit.bean.User;
import vn.hcmuaf.edu.fit.dao.BillDao;
import vn.hcmuaf.edu.fit.dao.BillDetailDao;
import vn.hcmuaf.edu.fit.dao.ProductDetailDao;
import vn.hcmuaf.edu.fit.model.AbsModel;
import vn.hcmuaf.edu.fit.services.BillService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.List;


@WebServlet(name = "UpdateBillAdmin", value = "/updateBillAdmin")
public class UpdateBillAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id;
        int status;
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        try {
            id = Integer.parseInt(request.getParameter("id"));
            status = Integer.parseInt(request.getParameter("status"));
            int oldStatus = BillService.getInstance().getBill(id).getStatus();
            if (oldStatus == 3 && status < 3) {
                request.setAttribute("type", "error");
                request.setAttribute("information", "Đơn hàng đang giao không thể quay lại trạng thái trước");
                request.getRequestDispatcher("showBillDetailAdmin?id="+id).forward(request, response);
            } else if (oldStatus == 4) {
                request.setAttribute("type", "error");
                request.setAttribute("information", "Đơn hàng đã giao không thể thay đổi");
                request.getRequestDispatcher("showBillDetailAdmin?id="+id).forward(request, response);
            } else if (oldStatus == 5) {
                request.setAttribute("type", "error");
                request.setAttribute("information", "Đơn hàng đã bị huỷ");
                request.getRequestDispatcher("showBillDetailAdmin?id="+id).forward(request, response);
            }else  {
                Bill bill = BillDao.getInstance().getBill(id);
//                bill.setId(id);
                bill.setBeforeData(bill.logString());
                String u = ((User) request.getSession().getAttribute("auth")).getEmail();
                if (BillService.getInstance().updateStatusBill(bill, ipAddress, "alert", u+": change status bill", status)){
                    if (status == 5) {
                        List<BillDetail> billDetail = BillDetailDao.getInstance().getAllBillDetail(id);
                        for (int i = 0; i < billDetail.size(); i++) {
                            int idP = billDetail.get(i).getProduct().getProductDetail().getProduct().getId();
                            int size = billDetail.get(i).getSize().getId();
                            int color = billDetail.get(i).getColor().getId();
                            int quantity = billDetail.get(i).getQuantity();
                            ProductDetailDao.getInstance().updateQuantity(idP, size, color, quantity);
                        }
                    }
                    request.setAttribute("type", "success");
                    request.setAttribute("information", "Thay đổi trạng thái thành công");
                    request.getRequestDispatcher("showBillDetailAdmin?id="+id).forward(request, response);
                }else {
                    request.setAttribute("type", "error");
                    request.setAttribute("information", "Lỗi sql");
                    request.getRequestDispatcher("showBillDetailAdmin?id="+id).forward(request, response);
                }
            }
        }catch (NumberFormatException e){
            request.setAttribute("type", "error");
            request.setAttribute("information", "Lỗi null");
            request.getRequestDispatcher("showBillAdmin").forward(request, response);
        }
    }
}