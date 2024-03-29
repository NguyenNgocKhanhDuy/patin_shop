package vn.hcmuaf.edu.fit.bean;

import org.jdbi.v3.core.mapper.Nested;

import java.io.Serializable;

public class BillDetail implements Serializable {
    @Nested("bill")
    private Bill bill;
    @Nested("product")
    private ProductMain product;
    @Nested("size")
    private Size size;
    @Nested("color")
    private Color color;
    private int quantity;
    private int price;

    public BillDetail() {
    }

    public BillDetail(Bill bill, ProductMain product, Size size, Color color, int quantity, int price) {
        this.bill = bill;
        this.product = product;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
        this.price = price;
    }

    @Nested("bill")
    public Bill getBill() {
        return bill;
    }

    @Nested("bill")
    public void setBill(Bill bill) {
        this.bill = bill;
    }

    @Nested("product")
    public ProductMain getProduct() {
        return product;
    }

    @Nested("product")
    public void setProduct(ProductMain product) {
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

    @Override
    public String toString() {
        return "BillDetail{" +
                "bill=" + bill +
                ", product=" + product +
                ", size=" + size +
                ", color=" + color +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
