package vn.hcmuaf.edu.fit.dao;

import vn.hcmuaf.edu.fit.model.AbsModel;
import vn.hcmuaf.edu.fit.model.IModel;

public interface IDao<T extends IModel> {
    public void select(AbsModel model,String ip, String level);
    public void insert(AbsModel model, String ip, String level);
    public void update(AbsModel model);
    public void delete(AbsModel model);
}
