package vn.hcmuaf.edu.fit.dao;

import vn.hcmuaf.edu.fit.model.AbsModel;
import vn.hcmuaf.edu.fit.model.IModel;

public abstract class AbsDao<T extends IModel> implements IDao {
    @Override
    public void select(AbsModel model, String ip,String level) {
        LogDao.getInstance().insert(model, ip, level);
    }

    @Override
    public void insert(AbsModel model, String ip, String level) {
        LogDao.getInstance().insert(model,ip, level);
    }
}
