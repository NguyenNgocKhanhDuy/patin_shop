package vn.hcmuaf.edu.fit.controller;

import com.google.gson.Gson;
import vn.hcmuaf.edu.fit.bean.Color;
import vn.hcmuaf.edu.fit.bean.Size;
import vn.hcmuaf.edu.fit.dao.ColorDao;
import vn.hcmuaf.edu.fit.dao.SizeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "ShowSize", value = "/showSize")
public class ShowSize extends HttpServlet {
    private Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        List<Size> sizes = SizeDao.getInstance().getAllsize();
        response.getWriter().println(gson.toJson(sizes));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}