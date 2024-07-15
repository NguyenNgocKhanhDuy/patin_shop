package vn.hcmuaf.edu.fit.dao;

import vn.hcmuaf.edu.fit.bean.Bill;
import vn.hcmuaf.edu.fit.bean.BillDetail;
import vn.hcmuaf.edu.fit.bean.ProductMain;
import vn.hcmuaf.edu.fit.db.JDBIConnector;
import vn.hcmuaf.edu.fit.model.AbsModel;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class BillDetailDao extends AbsDao<BillDetail> {
    private static BillDetailDao instance;

    public BillDetailDao() {
    }

    public static BillDetailDao getInstance() {
        if (instance == null) instance = new BillDetailDao();
        return instance;
    }
    @Override
    public boolean insert(AbsModel model, String ip, String level, String address) {
        BillDetail billDetail = (BillDetail) model;
        billDetail.setAfterData(billDetail.logString());
        super.insert(billDetail,ip,level,address);
        return addBillDetail(billDetail) == 0;
    }
    public int addBillDetail(BillDetail billDetail){
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO bill_detail(bill_id, product_id, size_id, color_id, quantity, price,isDeleted) VALUES (:bill, :product, :size, :color, :quantity, :price, 0)")
                    .bind("bill", billDetail.getBill().getId()).bind("product", billDetail.getProduct().getProductDetail().getProduct().getId()).bind("size", billDetail.getSize().getId()).bind("color", billDetail.getColor().getId()).bind("quantity", billDetail.getQuantity()).bind("price", billDetail.getPrice())
                    .execute();
        });
        return i;
    }

    public List<BillDetail> getAllBillDetail(int id){
        List<BillDetail> billDetails = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT bill.name as bill_name, bill.date as bill_date, user.email as bill_user_email, user.phone as bill_user_phone, quantity, price, size.id as size_id, size.name as size_name, color.id as color_id, color.name as color_name, product.id as product_product_detail_product_id, product.name as product_product_detail_product_name " +
                    "FROM user JOIN bill on bill.user_id = user.id JOIN bill_detail on bill.id = bill_detail.bill_id JOIN product on product.id = bill_detail.product_id JOIN size ON bill_detail.size_id = size.id JOIN color ON bill_detail.color_id = color.id " +
                    "WHERE bill_id = :id AND bill_detail.isDeleted = 0").bind("id", id).mapToBean(BillDetail.class).stream().collect(Collectors.toList());
        });
        return billDetails;
    }

    public List<BillDetail> getAllBillDetailSuccess() {
        List<BillDetail> billDetails = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT SUM(quantity) as quantity, price, size.id as size_id, color.id as color_id, product.id as product_product_detail_product_id " +
                    "FROM  bill_detail JOIN product on product.id = bill_detail.product_id JOIN size ON bill_detail.size_id = size.id JOIN color ON bill_detail.color_id = color.id " +
                    "WHERE bill_detail.isDeleted = 0 " +
                    "GROUP BY bill_detail.product_id").mapToBean(BillDetail.class).stream().collect(Collectors.toList());
        });
        return billDetails;
    }

    public boolean deleteAllBillDetail(AbsModel model){
        Bill bill = (Bill) model;
//        Integer i = JDBIConnector.get().withHandle(handle -> {
//            return handle.createUpdate("DELETE FROM bill_detail WHERE bill_id = :id").bind("id", bill.getId()).execute();
//        });
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("UPDATE bill_detail SET isDeleted = 1 WHERE bill_id = :id").bind("id", bill.getId()).execute();
        });
        return i > 0 ? true : false;
    }

    public int getToTalPrice(int id){
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT SUM(price) From bill_detail WHERE bill_id = :id").bind("id", id).mapTo(Integer.class).one();
        });
        return i;

    }

    @Override
    public void select(AbsModel model, String ip, String level, String address) {

    }

    public List<Integer> getBestProductSell() {
        List<Integer> integers = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT product_id FROM bill_detail JOIN bill ON bill_detail.bill_id = bill.id " +
                            "WHERE bill.status = 4 " +
                            "GROUP BY product_id " +
                            "HAVING COUNT(*) >= ALL (SELECT COUNT(*) FROM bill_detail GROUP BY product_id) ")
                    .mapTo(Integer.class).stream().collect(Collectors.toList());
        });
        return integers;
    }


}
