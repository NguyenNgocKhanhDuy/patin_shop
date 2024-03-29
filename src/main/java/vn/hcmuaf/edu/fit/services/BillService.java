package vn.hcmuaf.edu.fit.services;

import vn.hcmuaf.edu.fit.bean.*;
import vn.hcmuaf.edu.fit.dao.BillDao;
import vn.hcmuaf.edu.fit.dao.BillDetailDao;

import java.time.LocalDateTime;
import java.util.List;

public class BillService {
    private static BillService instance;

    public BillService() {
    }

    public static BillService getInstance() {
        if (instance == null) instance = new BillService();
        return instance;
    }

    public boolean addBill(Bill bill) {
        return BillDao.getInstance().addBill(bill) == 1 ? true : false;
    }

    public String generateName(Bill bill, List<BillDetail> listBillDetail) {
        String s = "";
        for (BillDetail b: listBillDetail) {
            s += "P"+b.getProduct().getProductDetail().getProduct().getId();
        }
        return "DH"+bill.getId()+s+"U"+bill.getUser().getId();
    }

    public boolean updateName(String name, int id){
        return BillDao.getInstance().updateName(name, id) != 1 ? false : true;
    }

    public boolean addBillDetail(List<BillDetail> listBillDetail) {
        boolean flag = true;
        for (BillDetail b: listBillDetail) {
            flag = BillDetailDao.getInstance().addBillDetail(b) == 0 ? false : true;
            if (flag == false) return flag;
        }
        return flag;
    }


    public Bill getNewBill(int user) {
        return BillDao.getInstance().getNewBill(user);
    }

    public List<Bill> getAllBill() {
        return BillDao.getInstance().getAllBill();
    }

    public List<Bill> getAllBillByUser(int id) {
        return BillDao.getInstance().getAllBillByUser(id);
    }

    public List<Bill> getAllBillByUserAndStatus(int id, String status) {
        return BillDao.getInstance().getAllBillByUserAndStatus(id, status);
    }

    public LocalDateTime getCurrentDate() {
        LocalDateTime localDate = LocalDateTime.now();
        return localDate;
    }

    public List<Bill> getBillPerPage(int currentPage, int productPerPage){
        int start;
        if (currentPage > 1) {
            start =  ((currentPage - 1) * productPerPage);
        } else {
            start = 0;
        }
        return BillDao.getInstance().getBillPerPage(start);
    }

    public List<Bill> getBillPerPageByUser(int currentPage, int productPerPage, int id){
        int start;
        if (currentPage > 1) {
            start =  ((currentPage - 1) * productPerPage);
        } else {
            start = 0;
        }
        return BillDao.getInstance().getBillPerPageByUser(start, id);
    }

    public List<Bill> getBillPerPageByUserAndStatus(int currentPage, int productPerPage, int id, String status){
        int start;
        if (currentPage > 1) {
            start =  ((currentPage - 1) * productPerPage);
        } else {
            start = 0;
        }
        return BillDao.getInstance().getBillPerPageByUserAndStatus(start, id, status);
    }

    public Bill getBill(int id){
        return BillDao.getInstance().getBill(id);
    }

    public List<BillDetail> getBillDetail(int id){
        return BillDetailDao.getInstance().getAllBillDetail(id);
    }

    public List<BillDetail> getAllBillDetail(int id){
        return BillDetailDao.getInstance().getAllBillDetail(id);
    }


    public boolean updateStatusBill(int id, String status) {
        return BillDao.getInstance().updateStatusBill(id, status);
    }

    public boolean deleteBill(int id) {
        return BillDao.getInstance().deleteBill(id) && BillDetailDao.getInstance().deleteAllBillDetail(id);
    }

    public int getTotalBill(int id){
        return BillDetailDao.getInstance().getToTalPrice(id);
    }

}
