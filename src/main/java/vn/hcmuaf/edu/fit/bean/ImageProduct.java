package vn.hcmuaf.edu.fit.bean;

import org.jdbi.v3.core.mapper.Nested;

import java.io.Serializable;

public class ImageProduct implements Serializable {
    private int id;
    private String url;
    @Nested("product")
    private Product product;

    public ImageProduct() {
    }

    public ImageProduct(int id, String url, Product product) {
        this.id = id;
        this.url = url;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Nested("product")
    public Product getProduct() {
        return product;
    }

    @Nested("product")
    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ImageProduct{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", product=" + product +
                '}';
    }
}
