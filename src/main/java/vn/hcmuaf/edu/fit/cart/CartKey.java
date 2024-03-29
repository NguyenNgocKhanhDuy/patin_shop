package vn.hcmuaf.edu.fit.cart;


import java.util.Objects;

public class CartKey {
    private int id;
    private int size;
    private int color;

    public CartKey(int id, int size, int color) {
        this.id = id;
        this.size = size;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "CartKey{" +
                "id=" + id +
                ", size=" + size +
                ", color=" + color +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj || !(obj instanceof CartKey)) return false;
        else {
            CartKey c = (CartKey) obj;
            return this.id == c.id && this.size == c.size && this.color == c.color;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, size, color);
    }
}
