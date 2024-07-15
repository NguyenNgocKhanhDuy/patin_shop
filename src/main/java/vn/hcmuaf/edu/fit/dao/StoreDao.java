package vn.hcmuaf.edu.fit.dao;

import vn.hcmuaf.edu.fit.bean.Store;
import vn.hcmuaf.edu.fit.db.JDBIConnector;

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

    public static void main(String[] args) {
        System.out.println(StoreDao.getInstance().getAllStore());
    }
}
