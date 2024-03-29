package vn.hcmuaf.edu.fit.dao;

import vn.hcmuaf.edu.fit.bean.Category;
import vn.hcmuaf.edu.fit.bean.Size;
import vn.hcmuaf.edu.fit.db.JDBIConnector;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryDao {
    private static CategoryDao instance;

    public CategoryDao() {
    }

    public static CategoryDao getInstance() {
        if (instance == null) instance = new CategoryDao();
        return instance;
    }

    public List<Category> getAllCategory() {
        List<Category> selectFromCategory = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT * FROM category").mapToBean(Category.class).stream().collect(Collectors.toList());
        });
        return selectFromCategory;
    }

    public Category getCategory(int id) {
        Category category = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT * FROM category WHERE id = ?").bind(0, id).mapToBean(Category.class).one();
        });
        return category;
    }

    public boolean insertCategory(Category category){
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO category(name) VALUES (:name)").bind("name", category.getName()).execute();
        });
        return i == 1 ? true :false;
    }

    public boolean updateCategory(Category category){
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("UPDATE category SET name = :name WHERE id = :id").bind("name", category.getName()).bind("id", category.getId()).execute();
        });
        return i == 1 ? true : false;
    }

    public List<Category> getCategoryPerPage(int currentPage, int productPerPage) {
        int start;
        if (currentPage > 1) {
            start =  ((currentPage - 1) * productPerPage);
        } else {
            start = 0;
        }
        List<Category> category = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT * FROM category LIMIT :start, 5").bind("start", start).mapToBean(Category.class).stream().collect(Collectors.toList());
        });
        return category;
    }
    public boolean deleteCategory(int id) {
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("DELETE FROM category WHERE id = ?").bind(0, id).execute();
        });
        return i == 1 ? true : false;
    }
}
