package vn.hcmuaf.edu.fit.dao;

import vn.hcmuaf.edu.fit.bean.User;
import vn.hcmuaf.edu.fit.bean.User2;
import vn.hcmuaf.edu.fit.db.JDBIConnector;
import vn.hcmuaf.edu.fit.model.AbsModel;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

public class UserDao2 extends AbsDao<User2> {
    private static UserDao2 instance;

    public UserDao2() {
    }

    public static UserDao2 getInstance() {
        if (instance == null) instance = new UserDao2();
        return instance;
    }

    @Override
    public void select(AbsModel model,String ip, String level) {
        super.select(model, ip, level);
    }

    @Override
    public void insert(AbsModel model, String ip, String level) {
        super.insert(model, ip, level);
        User2 user = (User2) model;
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO user(email, password, verify, fullname,phone, address, sex, dob, avatar,role) " +
                            "VALUES (:email, :password, :verify, :fullname, :phone, :address, :sex, :dob, :avatar,:role)")
                    .bind("email", user.getEmail()).bind("password", user.getPassword())
                    .bind("verify", user.getVerify()).bind("fullname", user.getFullName()).bind("phone", user.getPhone()).bind("address", user.getAddress())
                    .bind("sex", user.getSex()).bind("dob", user.getDob()).bind("avatar", user.getAvatar())
                    .bind("role", user.getRole()).execute();
        });
    }

    @Override
    public void update(AbsModel model) {

    }

    @Override
    public void delete(AbsModel model) {

    }

    public User checkLogin(AbsModel model, String ip) {
        User2 user2 = (User2) model;
        String email =user2.getEmail();
        String password = user2.getPassword();
        List<User> users = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT * " +
                            "FROM user " +
                            "WHERE verify = 1 and email = ?")
                    .bind(0, email)
                    .mapToBean(User.class).stream().collect(Collectors.toList());
        });

        if (users.size() != 1) return null;
        User user = users.get(0);
        if (!user.getEmail().equals(email) || !user.getPassword().equals(hashPassword(password))){
            super.select(model, ip,"alert");
            return null;
        }
        super.select(model, ip,"info");
        return user;
    }


    public String hashPassword(String password){
        try {
            MessageDigest sha256 = null;
            sha256 = MessageDigest.getInstance("SHA-256");
            byte[] hash = sha256.digest(password.getBytes());
            BigInteger number = new BigInteger(1, hash);
            return number.toString(16);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
