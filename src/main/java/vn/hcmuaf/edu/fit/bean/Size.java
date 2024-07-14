package vn.hcmuaf.edu.fit.bean;

import vn.hcmuaf.edu.fit.model.AbsModel;

import java.io.Serializable;

public class Size extends AbsModel implements Serializable {
    private int id;
    private String name;

    public Size() {
    }

    public Size(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Size{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public String logString() {
        return "name="+name;
    }
}
