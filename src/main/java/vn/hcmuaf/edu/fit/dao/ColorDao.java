package vn.hcmuaf.edu.fit.dao;

import vn.hcmuaf.edu.fit.bean.Color;

import vn.hcmuaf.edu.fit.db.JDBIConnector;
import vn.hcmuaf.edu.fit.model.AbsModel;

import java.util.List;
import java.util.stream.Collectors;

public class ColorDao extends AbsDao<Color>{
    private static ColorDao instance;

    public ColorDao() {
    }

    public static ColorDao getInstance() {
        if (instance == null) instance = new ColorDao();
        return instance;
    }

    public List<Color> getProductColor(int productId) {
        List<Color> colors = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT DISTINCT color.id, color.name " +
                    "FROM product_detail JOIN color on product_detail.id_color = color.id " +
                    "WHERE product_detail.id_product = ? AND color.isDeleted = 0 ").bind(0, productId).mapToBean(Color.class).stream().collect(Collectors.toList());
        });
        return colors;
    }

    public Color getColorById(int id) {
        Color color = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT * FROM color WHERE id = ?").bind(0, id).mapToBean(Color.class).one();
        });
        return color;
    }

    public List<Color> getAllColor() {
        List<Color> colors = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT * FROM color WHERE isDeleted = 0").mapToBean(Color.class).stream().collect(Collectors.toList());
        });
        return colors;
    }

    public boolean insertColor(AbsModel model, String ip){
        Color color = (Color) model;
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO color(name,isDeleted) VALUES (:name ,0)").bind("name", color.getName()).execute();
        });
        if (i == 1) {
            color.setAfterData(color.logString());
            super.insert(color, ip, "alert", "insert color");
            return true;
        }
        return false;
    }

    public boolean updateColor(AbsModel model, String ip){
        Color color = (Color) model;
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("UPDATE color SET name = :name WHERE id = :id").bind("name", color.getName()).bind("id", color.getId()).execute();
        });
        if (i == 1) {
            color.setAfterData(color.logString());
            super.insert(color, ip, "alert", "update color");
            return true;
        }
        return false;
    }

    public boolean deleteColor(AbsModel model, String ip) {
        Color color = (Color) model;
//        Integer i = JDBIConnector.get().withHandle(handle -> {
//            return handle.createUpdate("DELETE FROM color WHERE id = ?").bind(0, color.getId()).execute();
//        });
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("UPDATE color SET isDeleted = 1 WHERE id = ?").bind(0, color.getId()).execute();
        });
        if (i == 1) {
            color.setAfterData(color.logString());
            super.insert(color, ip, "danger", "delete color");
            return true;
        }
        return false;
    }

    public List<Color> getColorPerPage(int currentPage, int productPerPage) {
        int start;
        if (currentPage > 1) {
            start =  ((currentPage - 1) * productPerPage);
        } else {
            start = 0;
        }
        List<Color> color = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT * FROM color LIMIT :start, 5").bind("start", start).mapToBean(Color.class).stream().collect(Collectors.toList());
        });
        return color;
    }



    public int getIdByName(String name){
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT id FROM color WHERE name like ?").bind(0, "%" + name.trim().replace(" ", "%") + "%").mapTo(Integer.class).one();
        });
        return i;
    }
}
