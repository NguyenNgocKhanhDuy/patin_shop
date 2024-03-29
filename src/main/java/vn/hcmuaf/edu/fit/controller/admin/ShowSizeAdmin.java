package vn.hcmuaf.edu.fit.controller.admin;

import vn.hcmuaf.edu.fit.bean.Size;
import vn.hcmuaf.edu.fit.bean.User;
import vn.hcmuaf.edu.fit.dao.SizeDao;
import vn.hcmuaf.edu.fit.services.PermissionsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "ShowSizerAdmin", value = "/showSizeAdmin")
public class ShowSizeAdmin extends HttpServlet {
    String rsName = "size";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("auth");
        if (user == null) request.getRequestDispatcher("login.jsp").forward(request, response);
        else {
            int per = PermissionsService.getPermissionsService().checkAccess(rsName, user.getId());
            request.setAttribute("per", per);

            List<Size> allsize = SizeDao.getInstance().getAllsize();
            request.setAttribute("allSize", allsize);


            int currentPage;
            try {
                currentPage = Integer.parseInt(request.getParameter("currentPage"));
            }catch (NumberFormatException e) {
                currentPage = 1;
            }

            String href = "showSizeAdmin?";
            request.setAttribute("href", href);

            double productPerPage = 5.0;

            int totalPage = (int) Math.ceil(allsize.size() / productPerPage);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("productPerPage", (int) productPerPage);

            List<Size> sizes = SizeDao.getInstance().getSizePerPage(currentPage, (int) productPerPage);
            request.setAttribute("sizes", sizes);

            request.setAttribute("type", request.getAttribute("type"));
            request.setAttribute("information", request.getAttribute("information"));

            request.getRequestDispatcher("admin.jsp").forward(request, response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}