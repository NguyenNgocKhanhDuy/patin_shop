package vn.hcmuaf.edu.fit.dao;

import vn.hcmuaf.edu.fit.bean.Color;

import vn.hcmuaf.edu.fit.db.JDBIConnector;

import java.util.List;
import java.util.stream.Collectors;

public class ColorDao {
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
                    "WHERE product_detail.id_product = ?").bind(0, productId).mapToBean(Color.class).stream().collect(Collectors.toList());
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
            return handle.createQuery("SELECT * FROM color").mapToBean(Color.class).stream().collect(Collectors.toList());
        });
        return colors;
    }

    public boolean insertColor(Color color){
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO color(name) VALUES (:name)").bind("name", color.getName()).execute();
        });
        return i == 1 ? true : false;
    }

    public boolean updateColor(Color color){
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("UPDATE color SET name = :name WHERE id = :id").bind("name", color.getName()).bind("id", color.getId()).execute();
        });
        return i == 1 ? true : false;
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

    public boolean deleteColor(int id) {
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("DELETE FROM color WHERE id = ?").bind(0, id).execute();
        });
        return i == 1 ? true : false;
    }

    public int getIdByName(String name){
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT id FROM color WHERE name like ?").bind(0, "%" + name.trim().replace(" ", "%") + "%").mapTo(Integer.class).one();
        });
        return i;
    }
}
