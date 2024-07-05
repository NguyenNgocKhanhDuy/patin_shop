package vn.hcmuaf.edu.fit.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import vn.hcmuaf.edu.fit.bean.Bill;
import vn.hcmuaf.edu.fit.bean.User;
import vn.hcmuaf.edu.fit.dao.BillDao;
import vn.hcmuaf.edu.fit.services.BillService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "getBill", value = "/getBill")
public class GetBill extends HttpServlet {
    private Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,new LocalDateTimeAdapter()).create();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("auth");
//        int billStatus;
//        try {
//            billStatus = Integer.parseInt(request.getParameter("billStatus"));
//        }catch (NumberFormatException e){
//            billStatus = 0;
//        }

        List<Bill> bills = BillService.getInstance().getAllBillByUser(user.getId());;

//        if (billStatus == 1 || billStatus == 2) {
//            String status = billStatus == 1 ? "Dang giao" : "Hoan thanh";
//            bills = BillService.getInstance().getAllBillByUserAndStatus(user.getId(), status);
//
//        }else {
//            bills = BillService.getInstance().getAllBillByUser(user.getId());
//        }
        response.setContentType("application/json");
        response.getWriter().println(gson.toJson(bills));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}