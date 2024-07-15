package vn.hcmuaf.edu.fit.controller.admin.getData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import vn.hcmuaf.edu.fit.bean.Bill;
import vn.hcmuaf.edu.fit.bean.User;
import vn.hcmuaf.edu.fit.controller.LocalDateTimeAdapter;
import vn.hcmuaf.edu.fit.services.BillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


@WebServlet(name = "getBillAdmin", value = "/getBillAdmin")
public class GetBillAdmin extends HttpServlet {
    private Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,new LocalDateTimeAdapter()).create();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        List<Bill> bills = BillService.getInstance().getAllBill();;

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