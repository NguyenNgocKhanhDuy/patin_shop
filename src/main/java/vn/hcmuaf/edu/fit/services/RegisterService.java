package vn.hcmuaf.edu.fit.services;


import vn.hcmuaf.edu.fit.bean.User2;
import vn.hcmuaf.edu.fit.dao.UserDao2;

public class RegisterService {
    private static RegisterService instance;

    public RegisterService() {
    }

    public static RegisterService getInstance() {
        if (instance == null) instance = new RegisterService();
        return instance;
    }

    public String checkFormLogin(String fullName, String email, String phone) {
        if (!UserService.getInstance().checkFullName(fullName).equals("valid")) {
            return UserService.getInstance().checkFullName(fullName);
        } else if (!UserService.getInstance().checkEmail(email).equals("valid")) {
            return UserService.getInstance().checkEmail(email);
        } else if (!UserService.getInstance().checkPhone(phone).equals("valid")) {
            return UserService.getInstance().checkPhone(phone);
        }
        return "";
    }

    public String register(String email, String password, String confirmPass, String fullname, String address, String phone, String ip) {
        if (UserService.getInstance().isUserExists(email)){
            return "Đã tồn tại tài khoản sử dụng email";
        } else if (!password.equals(confirmPass)) {
            return "Mật khẩu không đúng";
        }else {
            User2 user2 = new User2();
            user2.setEmail(email);
            user2.setPassword(password);
            user2.setFullName(fullname);
            user2.setAddress(address);
            user2.setPhone(phone);
            UserDao2.getInstance().insert(user2, ip, "info");
//            UserService.getInstance().register(email, password, fullname, address, phone);
            return "Đăng ký thành công";
        }
    }

    public long getCurrentTime() {
        Long current = System.currentTimeMillis();
        return current;
    }
}
