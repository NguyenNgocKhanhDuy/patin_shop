package vn.hcmuaf.edu.fit.dao;

import vn.hcmuaf.edu.fit.bean.Log;
import vn.hcmuaf.edu.fit.db.JDBIConnector;
import vn.hcmuaf.edu.fit.model.AbsModel;

public class LogDao{
    private static LogDao instance;

    public LogDao() {
    }

    public static LogDao getInstance() {
        if (instance == null) instance = new LogDao();
        return instance;
    }

    public void insert(AbsModel model, String level) {
        JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO log(ip, level, preValue, value) VALUES (1, :level, :preValue, :value)")
                    .bind("level", level)
                    .bind("preValue", model.getBeforeData()).bind("value", model.getAfterData())
                    .execute();
        });
    }

    public void update(AbsModel model) {

    }

    public void delete(AbsModel model) {

    }
}
