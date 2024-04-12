package vn.hcmuaf.edu.fit.dao;

import vn.hcmuaf.edu.fit.bean.Log;
import vn.hcmuaf.edu.fit.db.JDBIConnector;
import vn.hcmuaf.edu.fit.model.AbsModel;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LogDao implements IDao{
    private static LogDao instance;

    public LogDao() {
    }

    public static LogDao getInstance() {
        if (instance == null) instance = new LogDao();
        return instance;
    }

    @Override
    public void select(AbsModel model,String ip, String level, String address) {

    }

    @Override
    public boolean insert(AbsModel model, String ip, String level, String address) {
        LocalDateTime date = LocalDateTime.now();
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO log(ip, level, address, value, createAt) VALUES (:ip, :level, :address, :value, :createAt)")
                    .bind("level", level).bind("ip", ip).bind("createAt", date).bind("address", address)
                    .bind("value", model.getAfterData())
                    .execute();
        });
        return i == 1 ? true : false;
    }

    @Override
    public boolean update(AbsModel model, String ip, String level, String address) {
        LocalDateTime date = LocalDateTime.now();
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO log(ip, level, address, value, preValue, createAt, updateAt) VALUES (:ip, :level, :address, :value, :preValue, :createAt, :updateAt)")
                    .bind("ip", ip).bind("level", level).bind("address", address)
                    .bind("createAt", date).bind("updateAt", date)
                    .bind("value", model.getAfterData()).bind("preValue", model.getBeforeData()).execute();
        });
        return i == 1 ? true : false;
    }

//    @Override
//    public boolean delete(AbsModel model, String ip, String level, String address) {
//        LocalDateTime date = LocalDateTime.now();
//        Integer i = JDBIConnector.get().withHandle(handle -> {
//            return handle.createUpdate("INSERT INTO log(ip, level, address, preValue, createAt, updateAt) VALUES (:ip, :level, :address, :preValue, :createAt, :updateAt)")
//                    .bind("ip", ip).bind("level", level).bind("address", address)
//                    .bind("createAt", getCreateAtByPreValue(model.getBeforeData())).bind("updateAt", date)
//                    .bind("preValue", model.getBeforeData()).execute();
//        });
//        return i == 1 ? true : false;
//    }
    @Override
    public boolean delete(AbsModel model, String ip, String level, String address) {
        LocalDateTime date = LocalDateTime.now();
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO log(ip, level, address, preValue, updateAt) VALUES (:ip, :level, :address, :preValue, :updateAt)")
                    .bind("ip", ip).bind("level", level).bind("address", address)
                    .bind("updateAt", date)
                    .bind("preValue", model.getBeforeData()).execute();
        });
        return i == 1 ? true : false;
    }

//    public LocalDateTime getCreateAtByPreValue(String preValue) {
//        Log log = JDBIConnector.get().withHandle(handle -> {
//            return handle.createQuery("SELECT creatAt FROM log WHERE preValue = ?")
//                    .bind(0, preValue).mapToBean(Log.class).one();
//        });
//        return log.getCreateAt();
//    }
}
