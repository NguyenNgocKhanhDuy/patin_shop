package vn.hcmuaf.edu.fit.dao;

import vn.hcmuaf.edu.fit.model.AbsModel;
import vn.hcmuaf.edu.fit.model.IModel;

public abstract class AbsDao<T extends IModel> implements IDao {
    @Override
    public void select(AbsModel model, String ip,String level, String address) {
        LogDao.getInstance().insert(model, ip, level, address);
    }

    @Override
    public boolean insert(AbsModel model, String ip, String level, String address) {
        LogDao.getInstance().insert(model,ip, level, address);
        return true;
    }


}
