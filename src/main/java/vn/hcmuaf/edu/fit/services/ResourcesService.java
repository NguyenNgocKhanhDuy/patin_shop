package vn.hcmuaf.edu.fit.services;

import vn.hcmuaf.edu.fit.db.JDBIConnector;

import java.util.List;
import java.util.stream.Collectors;

public class ResourcesService {
    private static ResourcesService instance;

    public ResourcesService() {
    }

    public static ResourcesService getInstance() {
        if (instance == null) instance = new ResourcesService();
        return instance;
    }

    public List<Integer> getAllID(){
        List<Integer> i = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT id FROM resources").mapTo(Integer.class).stream().collect(Collectors.toList());
        });
        return i;
    }
}
