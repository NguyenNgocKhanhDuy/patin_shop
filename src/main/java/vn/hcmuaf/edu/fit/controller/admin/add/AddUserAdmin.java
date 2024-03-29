package vn.hcmuaf.edu.fit.controller.admin.add;

import vn.hcmuaf.edu.fit.bean.ImageRating;
import vn.hcmuaf.edu.fit.bean.User;
import vn.hcmuaf.edu.fit.services.PermissionsService;
import vn.hcmuaf.edu.fit.services.RatingService;
import vn.hcmuaf.edu.fit.services.ResourcesService;
import vn.hcmuaf.edu.fit.services.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.Date;
import java.time.DateTimeException;
import java.util.List;


@WebServlet(name = "AddUserAdmin", value = "/addUserAdmin")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class AddUserAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String sex = request.getParameter("gender");
        int day;
        int month;
        int year;
        int role;
        int verify;

        if (fullname == null || address == null || phone == null || sex == null || email == null || password == null){
            request.setAttribute("type", "error");
            request.setAttribute("information", "Lỗi");
            request.getRequestDispatcher("showUserAdmin").forward(request, response);
        }else {
            try {

                String information = UserService.getInstance().checkForm(fullname, email, phone, address);

                if (information.equals("")){
                    role = Integer.parseInt(request.getParameter("role"));
                    verify = Integer.parseInt(request.getParameter("verify"));
                    day = Integer.parseInt(request.getParameter("day"));
                    month= Integer.parseInt(request.getParameter("month"));
                    year = Integer.parseInt(request.getParameter("year"));

                    information = UserService.getInstance().checkDOB(day, month, year);

                    if (information.equals("")){
                        String dob = year+"-"+month+"-"+day;
                        Date date;

                        date = Date.valueOf(dob);

                        Part filePart = request.getPart("file");

                        String avatar = "";

                        if (filePart.getSize() != 0) {
                            String fileName = filePart.getSubmittedFileName();
                            ServletContext servletContext = getServletContext();
                            File root = new File(servletContext.getRealPath("/") + "data/avatar");
                            if (!root.exists()) root.mkdirs();

                            System.out.println(root.getAbsolutePath());

                            avatar = "data/avatar/" + fileName;

                            for (Part part : request.getParts()) {
                                part.write(root.getAbsolutePath() + "/" + fileName);
                            }

                        }else {
                            avatar = "http://localhost:8080/patin_shop/assets/images/logo.PNG";
                        }

                        User user = new User(0, email, password, verify, fullname, address, phone, sex, date, avatar, 0, role);
                        response.getWriter().println(email);

                        if (UserService.getInstance().addUser(user)){
                            int idNew = UserService.getInstance().getUserByEmail(email).getId();
                            List<Integer> rsID = ResourcesService.getInstance().getAllID();
                            boolean flag = true;
                            for (int i = 1; i <= role; i++) {
                                for (int j = 0; j < rsID.size(); j++) {
                                    flag = PermissionsService.getPermissionsService().addPer(rsID.get(j), idNew, i);
                                    if (flag == false){
                                        request.setAttribute("type", "error");
                                        request.setAttribute("information", "Lỗi sql");
                                        request.getRequestDispatcher("showUserAdmin").forward(request, response);
                                        break;
                                    }
                                }
                            }
                            request.setAttribute("type", "success");
                            request.setAttribute("information", "Thêm thành công");
                            request.getRequestDispatcher("showUserAdmin").forward(request, response);
                        }else {
                            request.setAttribute("type", "error");
                            request.setAttribute("information", "Lỗi sql");
                            request.getRequestDispatcher("showUserAdmin").forward(request, response);
                        }


                    }else {
                        request.setAttribute("type", "error");
                        request.setAttribute("information", information);
                        request.getRequestDispatcher("showUserAdmin").forward(request, response);
                    }
                }else {
                    request.setAttribute("type", "error");
                    request.setAttribute("information", information);
                    request.getRequestDispatcher("showUserAdmin").forward(request, response);
                }


            }catch (NumberFormatException e ){
                request.setAttribute("type", "error");
                request.setAttribute("information", "Lỗi parse");
                request.getRequestDispatcher("showUserAdmin").forward(request, response);
            }
        }
    }
}