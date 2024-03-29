package vn.hcmuaf.edu.fit.controller;

import com.google.gson.Gson;
import vn.hcmuaf.edu.fit.bean.Color;
import vn.hcmuaf.edu.fit.dao.ColorDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.List;


@WebServlet(name = "ShowColor", value = "/showColor")
public class ShowColor extends HttpServlet {
    private Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        List<Color> colors = ColorDao.getInstance().getAllColor();
        response.getWriter().println(gson.toJson(colors));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}