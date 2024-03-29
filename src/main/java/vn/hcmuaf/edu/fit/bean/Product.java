package vn.hcmuaf.edu.fit.bean;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private int hot;
    private double salePercent;
    private String information;

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


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hot=" + hot +
                ", salePercent=" + salePercent +
                ", information='" + information + '\'' +
                '}';
    }
}
