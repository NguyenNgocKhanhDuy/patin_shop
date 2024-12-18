package vn.hcmuaf.edu.fit.dao;

import vn.hcmuaf.edu.fit.bean.Bill;
import vn.hcmuaf.edu.fit.db.JDBIConnector;
import vn.hcmuaf.edu.fit.model.AbsModel;

import java.util.List;
import java.util.stream.Collectors;

public class BillDao extends AbsDao<Bill>{
    private static BillDao instance;

    public BillDao() {
    }

    public static BillDao getInstance() {
        if(instance == null) instance = new BillDao();
        return instance;
    }

//    @Override
//    public boolean insert(AbsModel model, String ip, String level, String address) {
//        Bill bill = (Bill) model;
//        super.insert(bill,ip,level,address);
//        int i = addBill(bill);
//        return i == 0;
//    }
    public boolean addBill(AbsModel model, String ip, String level, String address) {
        Bill bill =(Bill) model;
        Integer i = JDBIConnector.get().withHandle(handle -> {
            System.out.println(bill.getId());
            return handle.createUpdate("INSERT INTO bill(name, date, status, payment, note, user_id, isDeleted) VALUES (:name, :date, :status, :payment, :note, :user, 0)")
                    .bind("name", bill.getName()).bind("date", bill.getDate()).bind("status", bill.getStatus()).bind("payment", bill.getPayment())
                    .bind("note", bill.getNote()).bind("user", bill.getUser().getId()).execute();
        });
        if (i == 1) {
            bill.setAfterData(bill.logString());
            super.insert(bill, ip, level, address);
            return true;
        }else {
            return false;
        }
    }



    public Bill getNewBill(int idUser) {
        Bill bill = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT * FROM bill WHERE user_id = :user and isDeleted = 0 ORDER BY date DESC LIMIT 1").bind("user", idUser).mapToBean(Bill.class).one();
        });
        return bill;
    }

    public List<Bill> getAllBill(){
        List<Bill> bills = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT * FROM bill WHERE isDeleted = 0").mapToBean(Bill.class).stream().collect(Collectors.toList());
        });
        return bills;
    }

    public List<Bill> getAllBillByUser(int id){
        List<Bill> bills = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT * FROM bill WHERE user_id = :id and isDeleted = 0 AND status != 5 ")
                    .bind("id", id).mapToBean(Bill.class).stream().collect(Collectors.toList());
        });
        return bills;
    }

    public List<Bill> getAllBillByUserAndStatus(int id, String status){
        List<Bill> bills = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT * FROM bill WHERE user_id = :id AND status like :status AND isDeleted = 0")
                    .bind("id", id).bind("status", "%"+status+"%")
                    .mapToBean(Bill.class).stream().collect(Collectors.toList());
        });
        return bills;
    }

    public List<Bill> getBillPerPage(int start) {
        List<Bill> bills = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT bill.*, user.email as user_email FROM bill JOIN user ON bill.user_id = user.id WHERE bill.isDeleted = 0 LIMIT :start, 5").bind("start", start).mapToBean(Bill.class).stream().collect(Collectors.toList());
        });
        return bills;
    }

    public List<Bill> getBillPerPageByUser(int start, int id) {
        List<Bill> bills = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT bill.*, user.email as user_email FROM bill JOIN user ON bill.user_id = user.id WHERE user_id = :id AND bill.isDeleted = 0 LIMIT :start, 5")
                    .bind("id", id).bind("start", start).mapToBean(Bill.class).stream().collect(Collectors.toList());
        });
        return bills;
    }

    public List<Bill> getBillPerPageByUserAndStatus(int start, int id, String status) {
        List<Bill> bills = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT bill.*, user.email as user_email FROM bill JOIN user ON bill.user_id = user.id WHERE user_id = :id AND status like :status AND isDeleted = 0 LIMIT :start, 5")
                    .bind("id", id).bind("start", start).bind("status", "%"+status+"%")
                    .mapToBean(Bill.class).stream().collect(Collectors.toList());
        });
        return bills;
    }

    public Bill getBill(int id) {
        Bill bill = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT bill.*, user.fullName as user_fullName, user.email as user_email, user.phone as user_phone, user.address as user_address " +
                    "FROM bill JOIN user ON bill.user_id = user.id where bill.id = :id AND bill.isDeleted = 0 ").bind("id", id).mapToBean(Bill.class).one();
        });
        return bill;
    }

    public int updateName(String name, int id) {
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("UPDATE bill SET name = ? WHERE id = ?").bind(0, name).bind(1, id).execute();
        });
        return i;
    }


    public boolean updateStatusBill(AbsModel model, String ip, String level, String address, int status){
        Bill bill = (Bill) model;
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("UPDATE bill SET status = :status WHERE id = :id").bind("status", status).bind("id", bill.getId()).execute();
        });
        if (i == 1) {
            bill.setBeforeData(bill.logString());
            bill.setAfterData(getBill(bill.getId()).logString());
            super.update(bill, ip, level, address);
            return true;
        }else {
            return false;
        }
    }
    public boolean deleteBill(AbsModel model, String ip){
        Bill bill = (Bill) model;
        bill.setBeforeData(getBill(bill.getId()).logString());
//        Integer i = JDBIConnector.get().withHandle(handle -> {
//            return handle.createUpdate("DELETE FROM bill WHERE id = :id").bind("id", bill.getId()).execute();
//        });

        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("UPDATE bill SET isDeleted = 1 WHERE id = :id").bind("id", bill.getId()).execute();
        });
        if (i == 1){
            super.delete(bill,ip,"danger","delete bill");
            return true;
        }
        return false;
    }

    public boolean deleteBillUser(AbsModel model, String ip){
        Bill bill = (Bill) model;
        bill.setBeforeData(getBill(bill.getId()).logString());
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("UPDATE bill SET status = 5 WHERE id = :id").bind("id", bill.getId()).execute();
        });
        if (i == 1){
            super.delete(bill,ip,"danger","user delete bill");
            return true;
        }
        return false;
    }

    public List<Integer> getBillIDByUser(int idUser) {
        List<Integer> integers = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT id FROM bill WHERE user_id = ?").bind(0, idUser).mapTo(Integer.class).stream().collect(Collectors.toList());
        });
        return integers;
    }



    @Override
    public void select(AbsModel model, String ip, String level, String address) {

    }

    public List<Bill> getAllBillSuccess() {
        List<Bill> bills = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT * FROM bill WHERE status = 4 AND isDeleted = 0 ").mapToBean(Bill.class).stream().collect(Collectors.toList());
        });
        return bills;
    }
}
