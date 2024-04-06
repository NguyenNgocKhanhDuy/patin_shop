package vn.hcmuaf.edu.fit.bean;

import org.jdbi.v3.core.mapper.reflect.ColumnName;
import vn.hcmuaf.edu.fit.model.AbsModel;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;



public class User extends AbsModel implements Serializable{
    private int id;
    private String email;
    private String password;
    private int verify;
    private String fullName;
    private String address;
    private String phone;
    private String sex;
    private Date dob;
    private String avatar;
    private int keyPass;
    private int role;

    public User() {
    }

    public User(int id, String email, String password, int verify, String fullName, String address, String phone, String sex, Date dob, String avatar, int keyPass, int role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.verify = verify;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.sex = sex;
        this.dob = dob;
        this.avatar = avatar;
        this.keyPass = keyPass;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getVerify() {
        return verify;
    }

    public void setVerify(int verify) {
        this.verify = verify;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getKeyPass() {
        return keyPass;
    }

    public void setKeyPass(int keyPass) {
        this.keyPass = keyPass;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", verify=" + verify +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", sex='" + sex + '\'' +
                ", dob=" + dob +
                ", avatar='" + avatar + '\'' +
                ", keyPass=" + keyPass +
                ", role=" + role +
                '}';
    }


    @Override
    public String getBeforeData() {
        return this.email;
    }

    @Override
    public String getAfterData() {
        return this.fullName;
    }
}
