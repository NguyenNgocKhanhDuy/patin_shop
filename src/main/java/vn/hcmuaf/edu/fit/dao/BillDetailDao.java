package vn.hcmuaf.edu.fit.dao;

import vn.hcmuaf.edu.fit.bean.BillDetail;
import vn.hcmuaf.edu.fit.db.JDBIConnector;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class BillDetailDao implements Serializable {
    private static BillDetailDao instance;

    public BillDetailDao() {
    }

    public static BillDetailDao getInstance() {
        if (instance == null) instance = new BillDetailDao();
        return instance;
    }

    public int addBillDetail(BillDetail billDetail){
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO bill_detail(bill_id, product_id, size_id, color_id, quantity, price) VALUES (:bill, :product, :size, :color, :quantity, :price)")
                    .bind("bill", billDetail.getBill().getId()).bind("product", billDetail.getProduct().getProductDetail().getProduct().getId()).bind("size", billDetail.getSize().getId()).bind("color", billDetail.getColor().getId()).bind("quantity", billDetail.getQuantity()).bind("price", billDetail.getPrice())
                    .execute();
        });
        return i;
    }

    public List<BillDetail> getAllBillDetail(int id){
        List<BillDetail> billDetails = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT bill.name as bill_name, bill.date as bill_date, user.email as bill_user_email, user.phone as bill_user_phone, quantity, price, size.id as size_id, size.name as size_name, color.id as color_id, color.name as color_name, product.id as product_product_detail_product_id, product.name as product_product_detail_product_name " +
                    "FROM user JOIN bill on bill.user_id = user.id JOIN bill_detail on bill.id = bill_detail.bill_id JOIN product on product.id = bill_detail.product_id JOIN size ON bill_detail.size_id = size.id JOIN color ON bill_detail.color_id = color.id " +
                    "WHERE bill_id = :id").bind("id", id).mapToBean(BillDetail.class).stream().collect(Collectors.toList());
        });
        return billDetails;
    }

    public boolean deleteAllBillDetail(int id){
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("DELETE FROM bill_detail WHERE bill_id = :id").bind("id", id).execute();
        });
        return i > 0 ? true : false;
    }

    public int getToTalPrice(int id){
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT SUM(price) From bill_detail WHERE bill_id = :id").bind("id", id).mapTo(Integer.class).one();
        });
        return i;

    }
}
