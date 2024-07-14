package vn.hcmuaf.edu.fit.controller.admin.getData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import vn.hcmuaf.edu.fit.bean.Log;
import vn.hcmuaf.edu.fit.controller.LocalDateTimeAdapter;
import vn.hcmuaf.edu.fit.dao.LogDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.List;


@WebServlet(name = "GetLog", value = "/getLog")
public class GetLog extends HttpServlet {
    private Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,new LocalDateTimeAdapter()).create();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Log> allLog= LogDao.getInstance().getAllLog();
        response.setContentType("application/json");
        response.getWriter().println(gson.toJson(allLog));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}