package vn.hcmuaf.edu.fit.dao;

import vn.hcmuaf.edu.fit.bean.Store;
import vn.hcmuaf.edu.fit.db.JDBIConnector;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class StoreDao {
    private static StoreDao instance;

    public static StoreDao getInstance() {
        if (instance == null) instance = new StoreDao();
        return instance;
    }

    public List<Store> getAllStore() {
        List<Store> stores = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT store.*, product.name as nameProduct FROM store join product on product.id = store.productID").mapToBean(Store.class).stream().collect(Collectors.toList());
        });
        return stores;
    }

    public boolean insertStore(int product, int size, int color, String name, int quantity, LocalDateTime dateTime, int price, double salePercent) {
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO store(productID, sizeID, colorID, name, quantity, date, price, salePercent) " +
                            "values (:product, :size, :color, :name, :quantity, :date, :price, :salePercent)")
                    .bind("product", product).bind("size", size).bind("color", color).bind("name", name).bind("quantity", quantity).bind("date", dateTime)
                    .bind("price", price).bind("salePercent", salePercent).execute();
        });
        return i==1? true : false;
    }

    public static void main(String[] args) {
        System.out.println(StoreDao.getInstance().getAllStore());
    }
}
