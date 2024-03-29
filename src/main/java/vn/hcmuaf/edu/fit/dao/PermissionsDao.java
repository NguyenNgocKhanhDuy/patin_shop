package vn.hcmuaf.edu.fit.dao;

import vn.hcmuaf.edu.fit.db.JDBIConnector;

public class PermissionsDao {
    private static PermissionsDao instance;

    public PermissionsDao() {
    }

    public static PermissionsDao getInstance() {
        if (instance == null) instance = new PermissionsDao();
        return instance;
    }

    public boolean addPermission(int rs, int id, int per){
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO permissions(resource_id, user_id, permission) VALUES (:rs, :id, :per)")
                    .bind("rs", rs).bind("id", id).bind("per", per).execute();
        });
        return i == 1 ? true :false;
    }

    public boolean deletePermission(int id){
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("DELETE FROM permissions WHERE user_id = :id")
                    .bind("id", id).execute();
        });
        return i > 0 ? true :false;
    }

    public boolean updatePermission(int id, int per){
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("UPDATE permissions SET permission = :per WHERE user_id = :id")
                    .bind("id", id).bind("per", per).execute();
        });
        return i > 0 ? true :false;
    }
}
