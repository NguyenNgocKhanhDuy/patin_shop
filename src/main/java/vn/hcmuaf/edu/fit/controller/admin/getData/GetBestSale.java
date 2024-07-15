package vn.hcmuaf.edu.fit.controller.admin.getData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import vn.hcmuaf.edu.fit.bean.BillDetail;
import vn.hcmuaf.edu.fit.bean.Store;
import vn.hcmuaf.edu.fit.controller.LocalDateTimeAdapter;
import vn.hcmuaf.edu.fit.dao.BillDetailDao;
import vn.hcmuaf.edu.fit.dao.StoreDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


@WebServlet(name = "GetBestSale", value = "/getBestSale")
public class GetBestSale extends HttpServlet {
    private Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,new LocalDateTimeAdapter()).create();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<BillDetail> billDetails = BillDetailDao.getInstance().getBestProductSell();

        response.setContentType("application/json");
        response.getWriter().println(gson.toJson(billDetails));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}