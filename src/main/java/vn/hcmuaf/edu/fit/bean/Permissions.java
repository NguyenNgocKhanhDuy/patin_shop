package vn.hcmuaf.edu.fit.bean;

import org.jdbi.v3.core.mapper.Nested;

import java.io.Serializable;

public class Permissions implements Serializable {
    private int id;
    @Nested
    private Resources resources;
    @Nested
    private User user;
    private int per;

    public Permissions() {
    }

    public Permissions(int id, Resources resources, User user, int per) {
        this.id = id;
        this.resources = resources;
        this.user = user;
        this.per = per;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPer() {
        return per;
    }

    public void setPer(int per) {
        this.per = per;
    }

    @Override
    public String toString() {
        return "Permissions{" +
                "id=" + id +
                ", resources=" + resources +
                ", user=" + user +
                ", per=" + per +
                '}';
    }
}
