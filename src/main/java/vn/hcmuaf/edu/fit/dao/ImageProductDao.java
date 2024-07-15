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
            return handle.createQuery("SELECT id, url FROM image_product WHERE id_product = ? AND isDeleted = 0")
                    .bind(0, productId).mapToBean(ImageProduct.class).stream().collect(Collectors.toList());
        });
        return imageProducts;
    }

    public boolean addFirstImage(String url, int id) {
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO image_product(id, url, id_product, isDeleted) VALUES (1, :url, :id, 0)")
                    .bind("url", url).bind("id", id).execute();
        });
        return i == 1 ? true : false;
    }
    public boolean addImage(AbsModel model, String ip) {
        ImageProduct imageProduct = (ImageProduct) model;
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO image_product(url, id_product, isDeleted) VALUES (:url, :id, 0)")
                    .bind("url", imageProduct.getUrl()).bind("id", imageProduct.getProduct().getId()).execute();
        });
        if (i == 1) {
            imageProduct.setAfterData(imageProduct.logString());
            super.insert(imageProduct, ip, "info", "add image product");
        }
        return false;
    }

    public boolean deleteAllImageOfProduct(int id) {
//        Integer i = JDBIConnector.get().withHandle(handle -> {
//            return handle.createUpdate("DELETE FROM image_product WHERE id_product = :id").bind("id", id).execute();
//        });
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("UPDATE image_product SET isDeleted = 1 WHERE id_product = :id").bind("id", id).execute();
        });
        return i > 0 ? true : false;

    }

    public boolean deleteImage(AbsModel model, String ip) {
        ImageProduct imageProduct = (ImageProduct) model;
//        Integer i = JDBIConnector.get().withHandle(handle -> {
//            return handle.createUpdate("DELETE FROM image_product WHERE id = :id AND id_product  = :product").bind("id", imageProduct.getId())
//                    .bind("product", imageProduct.getProduct().getId()).execute();
//        });
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("UPDATE image_product SET isDeleted = 1 WHERE id = :id AND id_product  = :product").bind("id", imageProduct.getId())
                    .bind("product", imageProduct.getProduct().getId()).execute();
        });
        if (i == 1) {
            imageProduct.setBeforeData(imageProduct.logString());
            super.delete(imageProduct, ip, "danger", "delete image product");
        }
        return false;
    }


//    @Override
//    public boolean delete(AbsModel model, String ip, String level, String address) {
//        ImageProduct imgProduct = (ImageProduct) model;
//        super.delete(imgProduct,ip,level,address);
//        return deleteImage(imgProduct.getId(),imgProduct.getProduct().getId());
//    }
}
