package vn.hcmuaf.edu.fit.dao;

import vn.hcmuaf.edu.fit.bean.Product;
import vn.hcmuaf.edu.fit.db.JDBIConnector;
import vn.hcmuaf.edu.fit.model.AbsModel;
import vn.hcmuaf.edu.fit.model.IModel;

public class ProductDao2 extends AbsDao<Product>{
    private static ProductDao2 instance;
    public ProductDao2(){
    }
    public static ProductDao2 getInstance(){
        if(instance == null) instance = new ProductDao2();
        return instance;
    }
    @Override
    public boolean update(AbsModel model, String ip, String level, String address) {
        return false;
    }

    @Override
    public boolean delete(AbsModel model, String ip, String level, String address) {
        Product product = (Product) model;
        int id = product.getId();
        if(deleteProduct(id) || deleteProductDetailAll(id)|| ImageProductDao.getInstance().deleteAllImageOfProduct(id)){
            super.delete(model,ip,level,address);
            return true;
        }
        return false;
    }
    public boolean deleteProduct(int id) {
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("DELETE FROM product WHERE id = :id").bind("id", id).execute();
        });
        return i == 1 ? true : false;
    }

    public boolean deleteProductDetailAll(int id) {
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("DELETE FROM product_detail WHERE id_product = :id").bind("id", id).execute();
        });
        return i == 1 ? true : false;
    }
}
