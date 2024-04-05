package vn.hcmuaf.edu.fit.dao;

import vn.hcmuaf.edu.fit.model.AbsModel;
import vn.hcmuaf.edu.fit.model.IModel;

public interface IDao<T extends IModel> {
    public void select(AbsModel model,String ip, String level, String address);
    public boolean insert(AbsModel model, String ip, String level, String address);
    public boolean update(AbsModel model, String ip, String level, String address);
    public boolean delete(AbsModel model, String ip, String level, String address);
}
