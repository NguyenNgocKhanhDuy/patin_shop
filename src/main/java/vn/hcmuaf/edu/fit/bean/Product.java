package vn.hcmuaf.edu.fit.bean;

import vn.hcmuaf.edu.fit.model.AbsModel;

import java.io.Serializable;

public class Product extends AbsModel implements Serializable {
    private int id;
    private String name;
    private int hot;
    private double salePercent;
    private String information;
    private int isDeleted;
    public Product() {
    }

    public Product(int id, String name, int hot, double salePercent, String information) {
        this.id = id;
        this.name = name;
        this.hot = hot;
        this.salePercent = salePercent;
        this.information = information;
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

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public double getSalePercent() {
        return salePercent;
    }

    public void setSalePercent(double salePercent) {
        this.salePercent = salePercent;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hot=" + hot +
                ", salePercent=" + salePercent +
                ", information='" + information + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }

    public String logString() {
        return "name="+name+", salePercent="+salePercent;
    }
}
