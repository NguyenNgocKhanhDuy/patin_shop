package vn.hcmuaf.edu.fit.dao;

import vn.hcmuaf.edu.fit.bean.ProductDetail;
import vn.hcmuaf.edu.fit.bean.ProductMain;
import vn.hcmuaf.edu.fit.db.JDBIConnector;
import vn.hcmuaf.edu.fit.model.AbsModel;

import java.util.List;
import java.util.stream.Collectors;

public class ProductDetailDao extends AbsDao<ProductDetail> {
    private static ProductDetailDao instance;

    public ProductDetailDao() {
    }

    public static ProductDetailDao getInstance() {
        if (instance == null) instance = new ProductDetailDao();
        return instance;
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
        ProductDetail productDetail = (ProductDetail) model;
        super.delete(productDetail,ip,level,address);
        return deleteProductDetail(productDetail.getProduct().getId(),productDetail.getSize().getId(),productDetail.getColor().getId());
    }
    public boolean deleteProductDetail(int id, int size, int color) {
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("DELETE FROM product_detail WHERE id_product = :id AND id_size = :size AND id_color = :color").bind("id", id)
                    .bind("size", size).bind("color", color).execute();
        });
        return i == 1 ? true : false;
    }
    public boolean updateProductDetail(ProductDetail product, int oldSize, int oldColor) {
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("UPDATE product_detail SET id_size = :size, id_color = :color, quantity = :quantity, price = :price WHERE id_product = :id AND id_size = :old_size AND id_color = :old_color")
                    .bind("size", product.getSize().getId()).bind("color", product.getColor().getId()).bind("quantity", product.getQuantity())
                    .bind("price", product.getPrice()).bind("id", product.getProduct().getId())
                    .bind("old_size", oldSize).bind("old_color", oldColor).execute();
        });
        return i == 1 ? true : false;
    }

    public boolean addProductDetail(ProductDetail product) {
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO product_detail(id_product, id_size, id_color, quantity, price) VALUES (:product, :size, :color, :quantity, :price)")
                    .bind("product", product.getProduct().getId()).bind("size", product.getSize().getId()).bind("color", product.getColor().getId()).bind("quantity", product.getQuantity()).bind("price", product.getPrice()).execute();
        });
        return i == 1 ? true : false;
    }

    public ProductMain getProductDetail(int id, int size, int color) {
        List<ProductMain> product = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT product.id as product_detail_product_id, product.name as product_detail_product_name, product_detail.quantity as product_detail_quantity, image_product.url as img, product_detail.price * (1-product.sale_percent) as product_detail_price, product.sale_percent as product_detail_product_salePercent, product.information as product_detail_product_information, " +
                            "size.id as product_detail_size_id, size.name product_detail_size_name, color.id as product_detail_color_id, color.name as product_detail_color_name " +
                            "FROM  image_product JOIN product on product.id = image_product.id_product JOIN product_detail on product.id = product_detail.id_product JOIN color on color.id = product_detail.id_color JOIN size on size.id = product_detail.id_size " +
                            "WHERE product_detail.id_product = :id AND product_detail.id_size = :size AND product_detail.id_color = :color AND image_product.id = 1 ")
                    .bind("id", id).bind("size", size).bind("color", color)
                    .mapToBean(ProductMain.class).stream().collect(Collectors.toList());
        });
        if (product.size() != 1) return null;
        return product.get(0);
    }

    public List<ProductMain> getAllProductDetail(int id) {
        List<ProductMain> product = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT product.id as product_detail_product_id, product.name as product_detail_product_name, product_detail.quantity as product_detail_quantity, image_product.url as img, product_detail.price * (1-product.sale_percent) as product_detail_price, product.sale_percent as product_detail_product_salePercent, product.information as product_detail_product_information, " +
                            "size.id as product_detail_size_id, size.name product_detail_size_name, color.id as product_detail_color_id, color.name as product_detail_color_name " +
                            "FROM  image_product JOIN product on product.id = image_product.id_product JOIN product_detail on product.id = product_detail.id_product JOIN color on color.id = product_detail.id_color JOIN size on size.id = product_detail.id_size " +
                            "WHERE product_detail.id_product = :id AND image_product.id = 1 " +
                            "ORDER BY price asc")
                    .bind("id", id).mapToBean(ProductMain.class).stream().collect(Collectors.toList());
        });
        return product;
    }
}