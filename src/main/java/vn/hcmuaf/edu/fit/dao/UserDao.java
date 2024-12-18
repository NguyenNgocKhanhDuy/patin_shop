package vn.hcmuaf.edu.fit.dao;

import vn.hcmuaf.edu.fit.db.JDBIConnector;
import vn.hcmuaf.edu.fit.bean.User;
import vn.hcmuaf.edu.fit.model.AbsModel;
import vn.hcmuaf.edu.fit.services.MailService;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class UserDao extends AbsDao<User>{
    private static UserDao instance;

    public UserDao() {
    }

    public static UserDao getInstance() {
        if (instance == null) instance = new UserDao();
        return instance;
    }

    public List<User> getAllUser(){
        List<User> users = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT * FROM user WHERE isDeleted = 0").mapToBean(User.class).stream().collect(Collectors.toList());
        });
        return users;
    }

    public User checkLogin(AbsModel model, String ip, String address) {
        User user = (User) model;

        String email =user.getEmail();
        String password = user.getPassword();
        List<User> users = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT * " +
                            "FROM user " +
                            "WHERE email = ? AND isDeleted = 0")
                    .bind(0, email)
                    .mapToBean(User.class).stream().collect(Collectors.toList());
        });

        if (users.size() != 1){
            user.setAfterData(user.logString()+"\nStatus: Thất bại");
            super.insert(user, ip, "alert", address);
            return null;
        }

        User u = users.get(0);
        if (!u.getEmail().equals(email) || !u.getPassword().equals(hashPassword(password))){
            user.setAfterData(user.logString()+"\nStatus: Thất bại");
            super.insert(user, ip, "alert", address);
            return null;
        }
        user.setAfterData(user.logString()+"\nStatus: Thành công");
        super.insert(user, ip,"info", address);
        return u;
    }

    public List<User> getUserByEmail(String email) {
        List<User> users = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT * " +
                            "FROM user " +
                            "WHERE email = ? AND isDeleted = 0")
                    .bind(0, email)
                    .mapToBean(User.class).stream().collect(Collectors.toList());
        });

        return users;
    }

//    public boolean addUser(String email, String password, int verify, String fullName, String address, String phone, String sex, LocalDateTime dob, String avatar, int role) {
//        String hashPass = hashPassword(password);
//        Integer i = JDBIConnector.get().withHandle(handle -> {
//            return handle.createUpdate("INSERT INTO user(email, password, verify, fullname, address, phone, sex, dob, avatar, role) " +
//                            "VALUES (:email, :password, :verify, :fullname, :address, :phone, :sex, :dob, :avatar, :role)")
//                    .bind("email", email).bind("password", hashPass)
//                    .bind("verify", verify).bind("fullname", fullName).bind("phone", phone).bind("address", address)
//                    .bind("sex", sex).bind("dob", dob).bind("avatar", avatar)
//                    .bind("role", role).execute();
//        });
//        if (i == 1){
//            return true;
//        }
//        return false;
//    }

//    public boolean addUser(User user) {
//        String hashPass = hashPassword(user.getPassword());
//        Integer i = JDBIConnector.get().withHandle(handle -> {
//            return handle.createUpdate("INSERT INTO user(email, password, verify, fullname,phone, address, sex, dob, avatar,role) " +
//                            "VALUES (:email, :password, :verify, :fullname, :phone, :address, :sex, :dob, :avatar,:role)")
//                    .bind("email", user.getEmail()).bind("password", hashPass)
//                    .bind("verify", user.getVerify()).bind("fullname", user.getFullName()).bind("phone", user.getPhone()).bind("address", user.getAddress())
//                    .bind("sex", user.getSex()).bind("dob", user.getDob()).bind("avatar", user.getAvatar())
//                    .bind("role", user.getRole()).execute();
//        });
//        if (i == 1)
//            return true;
//        return false;
//    }

    public boolean isExitsCode(int code) {
        List<User> users = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT id " +
                    "FROM user " +
                    "WHERE verify = ?").bind(0, code).mapToBean(User.class).stream().collect(Collectors.toList());
        });
        if (users.size() > 0) return true;
        return false;
    }

    public boolean verify(int code, String email) {
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("UPDATE user SET verify = :code WHERE email = :email")
                    .bind("code", code).bind("email", email).execute();
        });
        if(i == 1) return true;
        return false;
    }

    public int getVerifyByEmail(String email) {
        List<User> users = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT verify FROM user WHERE email = ?")
                    .bind(0, email).mapToBean(User.class).stream().collect(Collectors.toList());
        });
        return users.get(0).getVerify();
    }

    public boolean keyForgetPass(int code, String email) {
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("UPDATE user SET keyPass = ? WHERE email = ?")
                    .bind(0, code).bind(1, email).execute();
        });
        if(i == 1) return true;
        return false;
    }

    public int getKey(String email) {
        List<User> users = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT keyPass FROM user WHERE email = ?").bind(0, email).mapToBean(User.class).stream().collect(Collectors.toList());
        });
        return users.get(0).getKeyPass();
    }

    public boolean isExitsCodePass(int code) {
        List<User> users = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT id " +
                    "FROM user " +
                    "WHERE keyPass = ?").bind(0, code).mapToBean(User.class).stream().collect(Collectors.toList());
        });
        if (users.size() > 0) return true;
        return false;
    }



//    public boolean updateUser(User user) {
//        Integer i = JDBIConnector.get().withHandle(handle -> {
//            return handle.createUpdate("UPDATE user SET fullname = :fullname, address = :address, phone = :phone, sex = :sex, dob = :dob, avatar = :avatar, role = :role, verify = :verify WHERE id = :id")
//                    .bind("id", user.getId()).bind("fullname", user.getFullName()).bind("address", user.getAddress()).bind("phone", user.getPhone())
//                    .bind("avatar", user.getAvatar()).bind("sex", user.getSex()).bind("dob", user.getDob()).bind("role", user.getRole()).bind("verify", user.getVerify())
//                    .execute();
//        });
//        if(i == 1) return true;
//        return false;
//    }

    public boolean updatePass(AbsModel model, String ip, String password) {
        User u = (User) model;
        String hashPass = hashPassword(password);

        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("UPDATE user SET password = ? WHERE email = ?")
                    .bind(0, hashPass).bind(1, u.getEmail()).execute();
        });
        if(i == 1){
            u.setAfterData(u.logString());
            super.insert(u, ip, "alert", "user change pass");
            return true;
        }
        return false;
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

    public boolean checkPass(int user, String pass) {
        String p = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT password FROM user WHERE id = :user").bind("user", user).mapTo(String.class).one();
        });
        return hashPassword(pass).equals(p);
    }

    public List<User> getUserPerPage(int start) {
        List<User> users = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT * FROM user LIMIT :start, 5").bind("start", start).mapToBean(User.class).stream().collect(Collectors.toList());
        });
        return users;
    }

    public User getUserByID(int id) {
        User user = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT * FROM user WHERE id = ? AND isDeleted = 0").bind(0, id).mapToBean(User.class).one();
        });
        return user;
    }


    public boolean deleteUser(AbsModel model, String ip) {
        User u = (User) model;
//        Integer i = JDBIConnector.get().withHandle(handle -> {
//            return handle.createUpdate("DELETE FROM user WHERE id = ?").bind(0, u.getId()).execute();
//        });
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("UPDATE user SET isDeleted = 1 WHERE id = ?").bind(0, u.getId()).execute();
        });
        if(i == 1) {
            u.setBeforeData(u.logString());
            super.delete(u, ip, "danger", "delete user");
            System.out.println("SEND");
            MailService.getInstance().sendMailToAdmin("Cảnh báo danger: delete user "+"\n"+ LocalDateTime.now());
            return true;
        }
        return false;
    }



    public boolean updateUser(AbsModel model, String ip, String level, String address, boolean isAdmin) {
        User user = (User) model;
        user.setBeforeData(user.logStringForUpdateUser());
        if (isAdmin) {
            Integer i = JDBIConnector.get().withHandle(handle -> {
                return handle.createUpdate("UPDATE user SET fullname = :fullname, address = :address, phone = :phone, sex = :sex, dob = :dob, avatar = :avatar, role = :role, verify = :verify WHERE id = :id")
                        .bind("id", user.getId()).bind("fullname", user.getFullName()).bind("address", user.getAddress()).bind("phone", user.getPhone())
                        .bind("avatar", user.getAvatar()).bind("sex", user.getSex()).bind("dob", user.getDob()).bind("role", user.getRole()).bind("verify", user.getVerify())
                        .execute();
            });
            if(i == 1){
                user.setAfterData(getUserByEmail(user.getEmail()).get(0).logStringForUpdateUser());
                super.update(user, ip, level, address);
                return true;
            }
        }else {
            Integer i = JDBIConnector.get().withHandle(handle -> {
                return handle.createUpdate("UPDATE user SET fullname = :fullname, address = :address, phone = :phone, sex = :sex, dob = :dob, avatar = :avatar, verify = :verify WHERE id = :id")
                        .bind("id", user.getId()).bind("fullname", user.getFullName()).bind("address", user.getAddress()).bind("phone", user.getPhone())
                        .bind("avatar", user.getAvatar()).bind("sex", user.getSex()).bind("dob", user.getDob()).bind("verify", user.getVerify())
                        .execute();
            });
            if(i == 1){
                user.setAfterData(getUserByEmail(user.getEmail()).get(0).logStringForUpdateUser());
                super.update(user, ip, level, address);
                return true;
            }
        }
        return false;
    }

//    @Override
//    public boolean delete(AbsModel model, String ip, String level, String address) {
//        User user = (User) model;
//        super.delete(user,ip,level,address);
//        return deleteUser(user.getId());
//    }

    @Override
    public void select(AbsModel model, String ip, String level, String address) {

    }

    public boolean addUser(AbsModel model, String ip, String level, String address) {
        User user = (User) model;
        String hashPass = hashPassword(user.getPassword());
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO user(email, password, verify, fullname,phone, address, sex, dob, avatar,role, isDeleted) " +
                            "VALUES (:email, :password, :verify, :fullname, :phone, :address, :sex, :dob, :avatar,:role, 0)")
                    .bind("email", user.getEmail()).bind("password", hashPass)
                    .bind("verify", user.getVerify()).bind("fullname", user.getFullName()).bind("phone", user.getPhone()).bind("address", user.getAddress())
                    .bind("sex", user.getSex()).bind("dob", user.getDob()).bind("avatar", user.getAvatar())
                    .bind("role", user.getRole()).execute();
        });


        if (i == 1){
            user.setAfterData(user.logString());
            super.insert(user, ip, level, address);
            return true;
        }else {
            return false;
        }
    }

    public boolean addLoginGoogle(AbsModel model, String ip, String level, String address) {
        User user = (User) model;
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO user(email, verify, fullname, avatar, role, isDeleted) " +
                            "VALUES (:email, :verify, :fullname, :avatar, :role, 0)")
                    .bind("email", user.getEmail())
                    .bind("verify", 1).bind("fullname", user.getFullName())
                    .bind("avatar", user.getAvatar()).bind("role", 0).execute();
        });

        if (i == 1) {
            user.setAfterData(user.logString()+"\nStatus: Thành công");
            super.insert(user, ip, level, address);
            return true;
        }else {
            return false;
        }
    }

    public List<User> getAdmin() {
        List<User> users = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT email FROM user WHERE role = 2 AND isDeleted = 0").mapToBean(User.class).stream().collect(Collectors.toList());
        });
        return users;
    }
}
