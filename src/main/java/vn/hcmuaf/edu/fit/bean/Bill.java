package vn.hcmuaf.edu.fit.bean;

import org.jdbi.v3.core.mapper.Nested;
import vn.hcmuaf.edu.fit.model.AbsModel;
import vn.hcmuaf.edu.fit.model.IModel;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Bill extends AbsModel implements Serializable {
    private int id;
    private String name;
    private LocalDateTime date;
    private int status;
    private String payment;
    private String note;
    @Nested("user")
    private User user;
    private int isDeleted;

    public Bill() {
    }

    public Bill(int id, String name, LocalDateTime date, int status, String payment, String note, User user) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.status = status;
        this.payment = payment;
        this.note = note;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    @Nested("user")
    public User getUser() {
        return user;
    }

    @Nested("user")
    public void setUser(User user) {
        this.user = user;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", status=" + status +
                ", payment='" + payment + '\'' +
                ", note='" + note + '\'' +
                ", user=" + user +
                ", isDeleted=" + isDeleted +
                '}';
    }

    public String logString() {
        return "name="+name+", date="+date+", status="+status+"\nuserName="+user.getFullName()+", userEmail="+user.getEmail()+", userPhone="+user.getPhone();
    }
}
