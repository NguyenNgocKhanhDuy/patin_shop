package vn.hcmuaf.edu.fit.controller.admin.getData;

import com.google.gson.Gson;
import vn.hcmuaf.edu.fit.bean.Size;
import vn.hcmuaf.edu.fit.dao.SizeDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.List;


@WebServlet(name = "GetSize", value = "/getSize")
public class GetSize extends HttpServlet {
    private Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Size> allsize = SizeDao.getInstance().getAllsize();
        response.setContentType("application/json");
        response.getWriter().println(gson.toJson(allsize));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}