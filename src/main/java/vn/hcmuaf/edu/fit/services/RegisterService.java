package vn.hcmuaf.edu.fit.services;


import vn.hcmuaf.edu.fit.bean.User;

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

    public String register(User user, String confirmPass, String ip) {
        String email = user.getEmail();
        String password = user.getPassword();
        if (UserService.getInstance().isUserExists(email)){
            return "Đã tồn tại tài khoản sử dụng email";
        } else if (!password.equals(confirmPass)) {
            return "Mật khẩu không đúng";
        }else {
            UserService.getInstance().register(user, ip, "register");
            return "Đăng ký thành công";
        }
    }

    public long getCurrentTime() {
        Long current = System.currentTimeMillis();
        return current;
    }
}
