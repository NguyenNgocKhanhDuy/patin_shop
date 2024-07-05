package vn.hcmuaf.edu.fit.controller.admin.getData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import vn.hcmuaf.edu.fit.bean.BillDetail;
import vn.hcmuaf.edu.fit.controller.LocalDateTimeAdapter;
import vn.hcmuaf.edu.fit.services.BillService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.List;


@WebServlet(name = "GetBillDetail", value = "/getBillDetail")
public class GetBillDetail extends HttpServlet {
    private Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,new LocalDateTimeAdapter()).create();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id;
        try{
            id = Integer.parseInt(request.getParameter("id"));
            List<BillDetail> billDetail = BillService.getInstance().getBillDetail(id);
            response.setContentType("application/json");
            response.getWriter().println(gson.toJson(billDetail));
        }catch (NumberFormatException e) {

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}