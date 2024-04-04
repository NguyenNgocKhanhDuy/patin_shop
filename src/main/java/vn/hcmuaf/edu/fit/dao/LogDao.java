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
    public void select(AbsModel model,String ip, String level) {

    }

    @Override
    public void insert(AbsModel model, String ip, String level) {
        LocalDateTime date = LocalDateTime.now();
        JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO log(ip, level, preValue, value, createAt) VALUES (:ip, :level, :preValue, :value, :createAt)")
                    .bind("level", level).bind("ip", ip).bind("createAt", date)
                    .bind("preValue", model.getBeforeData()).bind("value", model.getAfterData())
                    .execute();
        });
    }

    public void update(AbsModel model) {

    }

    public void delete(AbsModel model) {

    }
}
