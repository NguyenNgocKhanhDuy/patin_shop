package vn.hcmuaf.edu.fit.bean;

import org.jdbi.v3.core.mapper.Nested;
import vn.hcmuaf.edu.fit.dao.AbsDao;
import vn.hcmuaf.edu.fit.model.AbsModel;

import java.io.Serializable;

public class ProductDetail extends AbsModel implements Serializable {
    @Nested("product")
    private Product product;
    @Nested("size")
    private Size size;
    @Nested("color")
    private Color color;
    private int quantity;
    private int price;

    public ProductDetail() {
    }

    public ProductDetail(Product product, Size size, Color color, int quantity, int price) {
        this.product = product;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
        this.price = price;
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
        return "ProductDetail{" +
                "product=" + product +
                ", size=" + size +
                ", color=" + color +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    public String logString() {
        return "productName="+product.getName()+",Size="+size+", color="+color+", quantity="+quantity+", price="+price;
    }


}
