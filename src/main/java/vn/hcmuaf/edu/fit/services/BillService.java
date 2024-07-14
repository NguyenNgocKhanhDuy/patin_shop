package vn.hcmuaf.edu.fit.services;

import vn.hcmuaf.edu.fit.bean.*;
import vn.hcmuaf.edu.fit.dao.BillDao;
import vn.hcmuaf.edu.fit.dao.BillDetailDao;
import vn.hcmuaf.edu.fit.model.AbsModel;

import javax.management.MBeanAttributeInfo;
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

    public boolean addBill(AbsModel model, String ip, String level, String address) {
        return BillDao.getInstance().addBill(model, ip, level, address);
    }

    public String generateName(int id, LocalDateTime dateTime) {
//        String s = "";
//        for (BillDetail b: listBillDetail) {
//            s += "P"+b.getProduct().getProductDetail().getProduct().getId();
//        }
        return "DH"+id+"D"+dateTime;
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


    public boolean updateStatusBill(AbsModel model, String ip, String level, String address, int status) {
        return BillDao.getInstance().updateStatusBill(model, ip, level, address, status);
    }

    public boolean deleteBill(AbsModel model,String ip) {
        return BillDetailDao.getInstance().deleteAllBillDetail(model) && BillDao.getInstance().deleteBill(model,ip);
    }

    public int getTotalBill(int id){
        return BillDetailDao.getInstance().getToTalPrice(id);
    }

    public static void main(String[] args) {
        System.out.println(BillService.getInstance().getBillDetail(26));;
    }
}
