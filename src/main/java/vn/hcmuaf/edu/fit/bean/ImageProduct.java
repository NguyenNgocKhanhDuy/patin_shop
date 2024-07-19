package vn.hcmuaf.edu.fit.bean;

import org.jdbi.v3.core.mapper.Nested;
import vn.hcmuaf.edu.fit.model.AbsModel;

import java.io.Serializable;

public class ImageProduct extends AbsModel implements Serializable {
    private int id;
    private String url;
    @Nested("product")
    private Product product;
    private int isDeleted;

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

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String
    toString() {
        return "ImageProduct{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", product=" + product +
                ", isDeleted=" + isDeleted +
                '}';
    }

    public String logString() {
        return "productName="+product.getName()+", productID="+product.getId()+"id="+id;
    }

}
