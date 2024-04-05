package vn.hcmuaf.edu.fit.dao;

import vn.hcmuaf.edu.fit.bean.Log;
import vn.hcmuaf.edu.fit.db.JDBIConnector;
import vn.hcmuaf.edu.fit.model.AbsModel;

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
        return false;
    }

    @Override
    public boolean delete(AbsModel model, String ip, String level, String address) {
        return false;
    }

}
