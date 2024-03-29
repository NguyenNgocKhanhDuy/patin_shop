package vn.hcmuaf.edu.fit.bean;

import org.jdbi.v3.core.mapper.Nested;

import java.io.Serializable;

public class ProductMain implements Serializable {
    private int minPrice;
    private int maxPrice;
    private String img;
    @Nested("product_detail")
    private ProductDetail productDetail;

    public ProductMain() {
    }

    public ProductMain(int minPrice, int maxPrice, String img, ProductDetail productDetail) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.img = img;
        this.productDetail = productDetail;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Nested("product_detail")
    public ProductDetail getProductDetail() {
        return productDetail;
    }

    @Nested("product_detail")
    public void setProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }

    @Override
    public String toString() {
        return "ProductMain{" +
                "minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", img='" + img + '\'' +
                ", productDetail=" + productDetail +
                '}';
    }
}
