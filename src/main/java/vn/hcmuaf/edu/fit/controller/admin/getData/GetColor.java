package vn.hcmuaf.edu.fit.controller.admin.getData;

import com.google.gson.Gson;
import vn.hcmuaf.edu.fit.bean.Color;
import vn.hcmuaf.edu.fit.bean.Size;
import vn.hcmuaf.edu.fit.dao.ColorDao;
import vn.hcmuaf.edu.fit.dao.SizeDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.List;


@WebServlet(name = "GetColor", value = "/getColor")
public class GetColor extends HttpServlet {
    private Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Color> allColor = ColorDao.getInstance().getAllColor();
        response.setContentType("application/json");
        response.getWriter().println(gson.toJson(allColor));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}