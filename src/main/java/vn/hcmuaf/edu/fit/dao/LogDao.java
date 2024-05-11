package vn.hcmuaf.edu.fit.dao;

import vn.hcmuaf.edu.fit.bean.Log;
import vn.hcmuaf.edu.fit.bean.User;
import vn.hcmuaf.edu.fit.db.JDBIConnector;
import vn.hcmuaf.edu.fit.model.AbsModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
                    .bind("createAt", getCreateAtByPreValue(model.getBeforeData(), ip)).bind("updateAt", date)
                    .bind("value", model.getAfterData()).bind("preValue", model.getBeforeData()).execute();
        });
        return i == 1 ? true : false;
    }

    @Override
    public boolean delete(AbsModel model, String ip, String level, String address) {
        LocalDateTime date = LocalDateTime.now();
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO log(ip, level, address, preValue, createAt, updateAt) VALUES (:ip, :level, :address, :preValue, :createAt, :updateAt)")
                    .bind("ip", ip).bind("level", level).bind("address", address)
                    .bind("createAt", getCreateAtByPreValue(model.getBeforeData(), ip)).bind("updateAt", date)
                    .bind("preValue", model.getBeforeData()).execute();
        });
        return i == 1 ? true : false;
    }

    public LocalDateTime getCreateAtByPreValue(String preValue, String ip) {
        List<Log> logs = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT createAt FROM log WHERE value = :preValue AND ip = :ip")
                    .bind("preValue", preValue).bind("ip", ip)
                    .mapToBean(Log.class).stream().collect(Collectors.toList());
        });
        return logs.get(0).getCreateAt();

    }

}
