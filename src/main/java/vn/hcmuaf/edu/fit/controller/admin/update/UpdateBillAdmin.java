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
            String statusText = "";
            if (status == 0){
                statusText = "Đang xử lý";
            } else if (status == 1) {
                statusText = "Đang giao";
            }else {
                statusText = "Đã giao";
            }
            if (BillService.getInstance().updateStatusBill(id, statusText)){
                request.setAttribute("type", "success");
                request.setAttribute("information", "Cập nhật thành công");
                request.getRequestDispatcher("showBillDetailAdmin?id="+id).forward(request, response);
            }else {
                request.setAttribute("type", "error");
                request.setAttribute("information", "Lỗi sql");
                request.getRequestDispatcher("showBillDetailAdmin?id="+id).forward(request, response);
            }

        }catch (NumberFormatException e){
            request.setAttribute("type", "error");
            request.setAttribute("information", "Lỗi null");
            request.getRequestDispatcher("showBillAdmin").forward(request, response);
        }
    }
}