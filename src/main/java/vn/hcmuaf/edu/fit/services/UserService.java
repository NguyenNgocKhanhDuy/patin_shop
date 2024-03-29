package vn.hcmuaf.edu.fit.services;

import vn.hcmuaf.edu.fit.bean.User;
import vn.hcmuaf.edu.fit.dao.UserDao;

import javax.swing.text.DateFormatter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.sql.Date.*;

public class UserService {
    private static UserService instance;

    public UserService() {
    }

    public static UserService getInstance() {
        if (instance == null) instance = new UserService();
        return instance;
    }

    public User checkLogin(String email, String password) {
        return UserDao.getInstance().checkLogin(email, password);
    }

    public List<User> getAllUser() {
        return UserDao.getInstance().getAllUser();
    }

    public boolean isUserExists(String email){
        if (UserDao.getInstance().getUserByEmail(email).size() > 0)
            return true;
        return false;
    }

    public User getUserByEmail(String email) {
        return UserDao.getInstance().getUserByEmail(email).get(0);
    }

    public boolean addUser(User user) {
       return UserDao.getInstance().addUser(user);
    }

    public boolean register(String email, String password, String fullname, String address, String phone) {
        if (UserDao.getInstance().addUser(email, password, 0, fullname, address, phone,"Nam", LocalDateTime.now(), "http://localhost:8080/patin_shop/assets/images/logo.PNG", 0)){
            while (true){
                int code = Integer.parseInt(randomCodeVerify());
                if (!UserDao.getInstance().isExitsCode(code)) {
                    insertVerifyCode(code, email);
                    return MailService.getInstance().sendMailVerify(email, String.valueOf(code));
                }
            }
        }
        return false;
    }

    public boolean reSend(String email) {
        while (true){
            int code = Integer.parseInt(randomCodeVerify());
            if (!UserDao.getInstance().isExitsCode(code)) {
                insertVerifyCode(code, email);
                return MailService.getInstance().sendMailVerify(email, String.valueOf(code));
            }
        }
    }

    public boolean insertVerifyCode(int code, String email) {
        return UserDao.getInstance().verify(code, email);
    }

    public boolean verifyMail(int code, String email) {
        if (UserDao.getInstance().getVerifyByEmail(email) == code){
            return UserDao.getInstance().verify(1, email);
        }
        return false;
    }

    public boolean sendforgetPass(String email) {
        while (true){
            int code = Integer.parseInt(randomCodeVerify());
            if (!UserDao.getInstance().isExitsCodePass(code) && UserDao.getInstance().keyForgetPass(code, email)) {
                return MailService.getInstance().sendMailVerify(email, String.valueOf(code));
            }
        }
    }

    public boolean checkKey(int code, String email) {
        return code == UserDao.getInstance().getKey(email);
    }

    public boolean changePass(String email, String pass, String confirmPass) {
        if (pass.equals(confirmPass)) {
            return UserDao.getInstance().updatePass(email, pass);
        }
        return false;
    }

    public String randomCodeVerify() {
        Random random = new Random();
        String result = "";
        for (int i = 0; i < 6; i++) {
            result += (random.nextInt(9)+1);
        }
        return result;
    }

    public String checkEmail(String email) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9]+[A-Za-z0-9\\.]+@[A-Za-z0-9]+([\\.A-Za-z0-9]+)$");
        Matcher matcher = pattern.matcher(email);
        if (email == null || "".equals(email)) {
            return "Email không được để trống";
        } else if (!matcher.matches()) {
            return "Email phải có định dạng \"[Tên email] @ [Tên miền]\"";
        }
        return "valid";
    }

    public String checkFullName(String fullName) {
        if (fullName == null || "".equals(fullName)) {
            return "Họ tên không được để trống";
        }
        return "valid";
    }

    public String checkAddress(String address) {
        if (address == null || "".equals(address)) {
            return "Địa chỉ không được để trống";
        }
        return "valid";
    }

    public String checkPhone(String phone) {
        Pattern pattern = Pattern.compile("\\d{10}");
        Matcher matcher = pattern.matcher(phone);
        if (phone == null || "".equals(phone)) {
            return "Số điện thoại không được để trống";
        } else if (!matcher.matches()) {
            return "Số điện thoại chỉ chứa số và có độ dài là 10";
        }
        return "valid";
    }

    public String checkForm(String fullName, String email, String phone, String address) {
        if (!checkFullName(fullName).equals("valid")) {
            return checkFullName(fullName);
        } else if (!checkEmail(email).equals("valid")) {
            return checkEmail(email);
        } else if (!checkPhone(phone).equals("valid")) {
            return checkPhone(phone);
        } else if (!checkAddress(address).equals("valid")) {
            return checkAddress(address);
        }
        return "";

    }
    public String checkForm(String fullName, String phone, String address) {
        if (!checkFullName(fullName).equals("valid")) {
            return checkFullName(fullName);
        } else if (!checkPhone(phone).equals("valid")) {
            return checkPhone(phone);
        } else if (!checkAddress(address).equals("valid")) {
            return checkAddress(address);
        }
        return "";
    }
    public String checkForm(String fullName, String email) {
        if (!checkFullName(fullName).equals("valid")) {
            return checkFullName(fullName);
        } else if (!checkEmail(email).equals("valid")) {
            return checkEmail(email);
        }
        return "";
    }

    public boolean updateUser(User user) {
        return UserDao.getInstance().updateUser(user);
    }

    public boolean checkPass(int user, String pass) {
        return UserDao.getInstance().checkPass(user, pass);
    }

    public String checkDOB(int day, int month, int year) {
        LocalDateTime dateTime = LocalDateTime.now();
        int yearNow = dateTime.getYear();
        if (yearNow - year >= 18){
            int dayMax = 0;
            switch (month){
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    dayMax = 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    dayMax = 30;
                    break;
                case 2:
                    dayMax = isLeapYear(year) ? 29 : 28;
                    break;
            }
            if (!(day > 0 && day <= dayMax)) {
                return "Ngày không hợp lệ";
            }
        }else {
            return "Bạn chưa đủ 18 tuổi";
        }
        return "";
    }

    public boolean isLeapYear(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 100 == 0 && year % 400 == 0))
            return true;
        return false;
    }

    public List<User> getUserPerPage(int currentPage, int productPerPage) {
        int start;
        if (currentPage > 1) {
            start =  ((currentPage - 1) * productPerPage);
        } else {
            start = 0;
        }
        return UserDao.getInstance().getUserPerPage(start);
    }

    public User getUserByID(int id) {
        return UserDao.getInstance().getUserByID(id);
    }

    public boolean deleteUser(int id) {
        return UserDao.getInstance().deleteUser(id);
    }

}
