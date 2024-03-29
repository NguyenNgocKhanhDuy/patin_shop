package vn.hcmuaf.edu.fit.controller.admin.update;

import vn.hcmuaf.edu.fit.bean.User;
import vn.hcmuaf.edu.fit.services.PermissionsService;
import vn.hcmuaf.edu.fit.services.ResourcesService;
import vn.hcmuaf.edu.fit.services.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.Date;
import java.util.List;


@WebServlet(name = "UpdateUserAdmin", value = "/updateUserAdmin")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class UpdateUserAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id;
        String email = request.getParameter("email");
        String fullname = request.getParameter("fullname");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String sex = request.getParameter("gender");
        int day;
        int month;
        int year;
        int role;
        int verify;

        if (fullname == null || address == null || phone == null || sex == null){
            request.setAttribute("type", "error");
            request.setAttribute("information", "Lỗi");
            request.getRequestDispatcher("admin.jsp").forward(request, response);
        }else {
            try {
                String information = UserService.getInstance().checkForm(fullname, phone, address);

                if (information.equals("")){
                    id = Integer.parseInt(request.getParameter("id"));
                    role = Integer.parseInt(request.getParameter("role"));
                    verify = Integer.parseInt(request.getParameter("verify"));
                    day = Integer.parseInt(request.getParameter("day"));
                    month= Integer.parseInt(request.getParameter("month"));
                    year = Integer.parseInt(request.getParameter("year"));

                    User user = UserService.getInstance().getUserByID(id);
                    user.setFullName(fullname);
                    user.setAddress(address);
                    user.setPhone(phone);
                    user.setSex(sex);
                    user.setVerify(verify);
                    user.setRole(role);

                    String dob = year+"-"+month+"-"+day;
                    Date date = Date.valueOf(dob);

                    information = UserService.getInstance().checkDOB(day, month, year);

                    if (information.equals("")){
                        user.setDob(date);

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
                            User userOld = UserService.getInstance().getUserByEmail(email);
                            avatar = userOld.getAvatar();
                        }
                        user.setAvatar(avatar);

                        if (UserService.getInstance().updateUser(user)){
                            List<Integer> rsID = ResourcesService.getInstance().getAllID();
                            boolean flag = true;
                            for (int i = 1; i <= role; i++) {
                                for (int j = 0; j < rsID.size(); j++) {
                                    flag = PermissionsService.getPermissionsService().updatePer(id, role);
                                    if (flag == false){
                                        request.setAttribute("type", "error");
                                        request.setAttribute("information", "Lỗi sql");
                                        request.getRequestDispatcher("showUserAdmin").forward(request, response);
                                        break;
                                    }
                                }
                            }
                            request.setAttribute("type", "success");
                            request.setAttribute("information", "Cập nhật thành công");
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
                request.setAttribute("information", "Lỗi");
                request.getRequestDispatcher("showUserAdmin").forward(request, response);
            }
        }
    }
}