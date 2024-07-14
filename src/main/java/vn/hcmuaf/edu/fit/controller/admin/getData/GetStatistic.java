package vn.hcmuaf.edu.fit.controller.admin.getData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import vn.hcmuaf.edu.fit.bean.Bill;
import vn.hcmuaf.edu.fit.bean.BillDetail;
import vn.hcmuaf.edu.fit.bean.Store;
import vn.hcmuaf.edu.fit.controller.LocalDateTimeAdapter;
import vn.hcmuaf.edu.fit.dao.BillDao;
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


@WebServlet(name = "GetStatistic", value = "/getStatistic")
public class GetStatistic extends HttpServlet {
    private Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,new LocalDateTimeAdapter()).create();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Store> stores = StoreDao.getInstance().getAllStore();
        List<BillDetail> billDetails = BillDetailDao.getInstance().getAllBillDetailSuccess();
        for (int i = 0; i < stores.size(); i++) {
            int idProduct = stores.get(i).getProduct().getId();
            int quantity = stores.get(i).getQuantity();
            for (int j = 0; j < billDetails.size(); j++) {
                if (billDetails.get(j).getProduct().getProductDetail().getProduct().getId() == idProduct){
                    int quantitySell = billDetails.get(j).getQuantity();
                    stores.get(i).setQuantity(quantity - quantitySell);
                }
            }

        }
        response.setContentType("application/json");
        response.getWriter().println(gson.toJson(stores));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}