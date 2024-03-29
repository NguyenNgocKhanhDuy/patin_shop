package vn.hcmuaf.edu.fit.cart;

import vn.hcmuaf.edu.fit.bean.ProductMain;
import vn.hcmuaf.edu.fit.services.ProductService;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<CartKey, CartProduct> data = new HashMap<CartKey, CartProduct>();

    public Cart() {
    }

    public Map<CartKey, CartProduct> getData() {
        return data;
    }


    public boolean add(int id, int size, int color, int quantity) {
//        Product product = ProductService.getInstance().getProductDetail(id, size, color);
        ProductMain product = ProductService.getInstance().getProductDetail(id, size, color);
        CartKey cartKey = new CartKey(id, size, color);
        if (product == null) return false;
        if (data.containsKey(cartKey)){
            return data.get(cartKey).increase(quantity);
        } else if (quantity > product.getProductDetail().getQuantity()) {
            return false;
        }
        data.put(cartKey, new CartProduct(product, quantity));
        return true;
    }

    public boolean increase(int id, int size, int color) {
//        Product product = ProductService.getInstance().getProductDetail(id, size, color);
        ProductMain product = ProductService.getInstance().getProductDetail(id, size, color);
        if (product == null) return false;
        CartKey cartKey = new CartKey(id, size, color);
        return data.get(cartKey).increaseOne();
    }

    public boolean decrease(int id, int size, int color) {
//        Product product = ProductService.getInstance().getProductDetail(id, size, color);
        ProductMain product = ProductService.getInstance().getProductDetail(id, size, color);
        if (product == null) return false;
        CartKey cartKey = new CartKey(id, size, color);
        return data.get(cartKey).decreaseOne();
    }

    public boolean remove(int id, int size, int color) {
        CartKey cartKey = new CartKey(id, size, color);
        if (!data.containsKey(cartKey)) return false;
        data.remove(cartKey);
        return true;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "data=" + data +
                '}';
    }

    //    public boolean update(int id, int size, int color, int quantity) {
//        CartKey cartKey = new CartKey(id, size, color);
//        if (!data.containsKey(cartKey)) return false;
//        CartProduct cartProduct = data.get(cartKey);
//        return cartProduct.update(quantity);
//    }
}
