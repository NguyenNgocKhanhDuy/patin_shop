package vn.hcmuaf.edu.fit.dao;

import vn.hcmuaf.edu.fit.bean.Log;
import vn.hcmuaf.edu.fit.bean.Size;
import vn.hcmuaf.edu.fit.bean.User;
import vn.hcmuaf.edu.fit.db.JDBIConnector;
import vn.hcmuaf.edu.fit.model.AbsModel;
import vn.hcmuaf.edu.fit.services.MailService;

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
            return handle.createUpdate("INSERT INTO log(ip, level, address,preValue, value, createAt) VALUES (:ip, :level, :address,:preValue, :value, :createAt)")
                    .bind("level", level).bind("ip", ip).bind("createAt", date).bind("address", address)
                    .bind("preValue",model.getBeforeData())
                    .bind("value", model.getAfterData())
                    .execute();
        });
        if (address.equals("danger")) {
            MailService.getInstance().sendMailToAdmin("Cảnh báo danger: "+address+"\n"+date);
        }
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
        return logs.size() == 1 ? logs.get(0).getCreateAt() : null;
    }

    public List<Log> getAllLog() {
        List<Log> logs = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("Select * from log").mapToBean(Log.class).stream().collect(Collectors.toList());
        });
        return  logs;
    }
    public List<Log> getLogPerPage(int currentPage, int productPerPage) {
        int start;
        if (currentPage > 1) {
            start =  ((currentPage - 1) * productPerPage);
        } else {
            start = 0;
        }
        List<Log> log = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT * FROM log LIMIT :start, 5").bind("start", start).mapToBean(Log.class).stream().collect(Collectors.toList());
        });
        return log;
    }

    public static void main(String[] args) {
        System.out.println(LogDao.getInstance().getAllLog());
    }
}