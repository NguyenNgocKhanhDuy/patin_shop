package vn.hcmuaf.edu.fit.controller.admin.delete;

import vn.hcmuaf.edu.fit.services.BillService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;


@WebServlet(name = "DeleteBillAdmin", value = "/deleteBillAdmin")
public class DeleteBillAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id;
        try {
            id = Integer.parseInt(request.getParameter("id"));
            if (BillService.getInstance().deleteBill(id)){
                request.setAttribute("type", "success");
                request.setAttribute("information", "Xoá thành công");
                request.getRequestDispatcher("showBillAdmin").forward(request, response);
            }else {
                request.setAttribute("type", "error");
                request.setAttribute("information", "Lỗi sql");
                request.getRequestDispatcher("showBillAdmin").forward(request, response);
            }
        }catch (NumberFormatException e){
            request.setAttribute("type", "error");
            request.setAttribute("information", "Lỗi");
            request.getRequestDispatcher("showBillAdmin").forward(request, response);
        }
    }
}