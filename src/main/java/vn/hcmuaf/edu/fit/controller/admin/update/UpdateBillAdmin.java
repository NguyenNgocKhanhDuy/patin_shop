package vn.hcmuaf.edu.fit.controller.admin.update;

import vn.hcmuaf.edu.fit.services.BillService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;


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
                if (BillService.getInstance().updateStatusBill(id, status)){
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