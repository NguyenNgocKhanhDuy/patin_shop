package vn.hcmuaf.edu.fit.services;

import vn.hcmuaf.edu.fit.bean.Permissions;
import vn.hcmuaf.edu.fit.dao.PermissionsDao;
import vn.hcmuaf.edu.fit.db.JDBIConnector;

import java.util.List;
import java.util.stream.Collectors;

public class PermissionsService {
    private static PermissionsService permissionsService;

    public PermissionsService() {
    }

    public static PermissionsService getPermissionsService() {
        if (permissionsService == null) permissionsService = new PermissionsService();
        return permissionsService;
    }

    public int checkAccess(String rsName,int uId){

        List<Permissions> list = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT permissions.id, resources.name,permissions.user_id, permissions.permission as per " +
                            "FROM permissions JOIN resources ON permissions.resource_id = resources.id " +
                            "WHERE permissions.user_id = ?  and resources.name = ? and resources.status = 1")
                    .bind(0,uId).bind(1,rsName)
                    .mapToBean((Permissions.class)).stream().collect(Collectors.toList());
        });

        if(list==null) return 0;
        else{
            int max=0;
            for (Permissions p: list  ) {
                if(p.getId()>max) max=p.getPer();
            }
            return max;
        }
    }

    public boolean addPer(int rs, int id, int per){
        return PermissionsDao.getInstance().addPermission(rs, id, per);
    }
    public boolean updatePer(int id, int per){
        return PermissionsDao.getInstance().updatePermission(id, per);
    }

    public boolean deletePer(int id){
        return PermissionsDao.getInstance().deletePermission(id);
    }


}
