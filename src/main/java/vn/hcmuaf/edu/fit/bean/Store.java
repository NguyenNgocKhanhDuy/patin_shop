package vn.hcmuaf.edu.fit.bean;

import org.jdbi.v3.core.mapper.Nested;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Store implements Serializable {
    @Nested("product")
    private Product product;
    @Nested("size")
    private Size size;
    @Nested("color")
    private Color color;
    private String name;
    private LocalDateTime date;
    private int quantity;
    private int price;
    private int priceSell;
    private double salePercent;

    public Store() {
    }

    public Store(Product product, Size size, Color color, String name, LocalDateTime date, int quantity, int price, int priceSell, double salePercent) {
        this.product = product;
        this.size = size;
        this.color = color;
        this.name = name;
        this.date = date;
        this.quantity = quantity;
        this.price = price;
        this.priceSell = priceSell;
        this.salePercent = salePercent;
    }

    @Nested("product")
    public Product getProduct() {
        return product;
    }

    @Nested("product")
    public void setProduct(Product product) {
        this.product = product;
    }

    @Nested("size")
    public Size getSize() {
        return size;
    }

    @Nested("size")
    public void setSize(Size size) {
        this.size = size;
    }

    @Nested("color")
    public Color getColor() {
        return color;
    }

    @Nested("color")
    public void setColor(Color color) {
        this.color = color;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPriceSell() {
        return priceSell;
    }

    public void setPriceSell(int priceSell) {
        this.priceSell = priceSell;
    }

    public double getSalePercent() {
        return salePercent;
    }

    public void setSalePercent(double salePercent) {
        this.salePercent = salePercent;
    }

    @Override
    public String toString() {
        return "Store{" +
                "product=" + product +
                ", size=" + size +
                ", color=" + color +
                ", name='" + name + '\'' +
                ", dateTime=" + date +
                ", quantity=" + quantity +
                ", price=" + price +
                ", priceSell=" + priceSell +
                ", salePercent=" + salePercent +
                '}';
    }
}
