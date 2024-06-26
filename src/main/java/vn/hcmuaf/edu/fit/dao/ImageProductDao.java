package vn.hcmuaf.edu.fit.dao;

import vn.hcmuaf.edu.fit.bean.ImageProduct;
import vn.hcmuaf.edu.fit.db.JDBIConnector;
import vn.hcmuaf.edu.fit.model.AbsModel;

import java.util.List;
import java.util.stream.Collectors;

public class ImageProductDao extends AbsDao<ImageProduct> {
    private static ImageProductDao instance;

    public ImageProductDao() {
    }

    public static ImageProductDao getInstance() {
        if (instance == null) instance = new ImageProductDao();
        return instance;
    }

    public List<ImageProduct> getAllImage(int productId) {
        List<ImageProduct> imageProducts = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT id, url FROM image_product WHERE id_product = ?")
                    .bind(0, productId).mapToBean(ImageProduct.class).stream().collect(Collectors.toList());
        });
        return imageProducts;
    }

    public boolean addFirstImage(String url, int id) {
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO image_product(id, url, id_product) VALUES (1, :url, :id)")
                    .bind("url", url).bind("id", id).execute();
        });
        return i == 1 ? true : false;
    }
    public boolean addImage(String url, int id) {
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO image_product(url, id_product) VALUES (:url, :id)")
                    .bind("url", url).bind("id", id).execute();
        });
        return i == 1 ? true : false;
    }

    public boolean deleteAllImageOfProduct(int id) {
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("DELETE FROM image_product WHERE id_product = :id").bind("id", id).execute();
        });
        return i > 0 ? true : false;

    }

    public boolean deleteImage(int id, int idProduct) {
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("DELETE FROM image_product WHERE id = :id AND id_product  = :product").bind("id", id)
                    .bind("product", idProduct).execute();
        });
        return i == 1 ? true : false;

    }

    @Override
    public void select(AbsModel model, String ip, String level, String address) {

    }
    @Override
    public boolean insert(AbsModel model, String ip, String level, String address) {
        return false;
    }
    @Override
    public boolean update(AbsModel model, String ip, String level, String address) {
    return false;
    }
    @Override
    public boolean delete(AbsModel model, String ip, String level, String address) {
        ImageProduct imgProduct = (ImageProduct) model;
        super.delete(imgProduct,ip,level,address);
        return deleteImage(imgProduct.getId(),imgProduct.getProduct().getId());
    }
}
