package vn.hcmuaf.edu.fit.dao;

import vn.hcmuaf.edu.fit.bean.ProductDetail;
import vn.hcmuaf.edu.fit.model.AbsModel;
import vn.hcmuaf.edu.fit.model.IModel;
import vn.hcmuaf.edu.fit.services.MailService;

import java.time.LocalDateTime;

public abstract class AbsDao<T extends IModel> implements IDao {
    @Override
    public void select(AbsModel model, String ip,String level, String address) {
        LogDao.getInstance().select(model, ip, level, address);
    }

    @Override
    public boolean insert(AbsModel model, String ip, String level, String address) {
        LogDao.getInstance().insert(model,ip, level, address);
        return true;
    }
    @Override
    public boolean delete(AbsModel model, String ip, String level, String address) {
        LogDao.getInstance().delete(model,ip, level, address);
        return true;
    }

    @Override
    public boolean update(AbsModel model, String ip, String level, String address) {
        LogDao.getInstance().update(model, ip, level, address);
        return true;
    }


}
