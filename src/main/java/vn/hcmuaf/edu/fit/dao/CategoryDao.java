package vn.hcmuaf.edu.fit.dao;

import vn.hcmuaf.edu.fit.bean.Category;
import vn.hcmuaf.edu.fit.bean.Size;
import vn.hcmuaf.edu.fit.db.JDBIConnector;
import vn.hcmuaf.edu.fit.model.AbsModel;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryDao extends AbsDao<Category>{
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

    public boolean insertCategory(AbsModel model, String ip){
        Category category = (Category) model;
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO category(name) VALUES (:name)").bind("name", category.getName()).execute();
        });
        if (i == 1) {
            category.setAfterData(category.logString());
            super.insert(category, ip, "alert", "insert category");
            return true;
        }
        return false;
    }

    public boolean updateCategory(AbsModel model, String ip){
        Category category = (Category) model;
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("UPDATE category SET name = :name WHERE id = :id").bind("name", category.getName()).bind("id", category.getId()).execute();
        });
        if (i == 1) {
            category.setBeforeData(category.logString());
            category.setAfterData(getCategory(category.getId()).logString());
            super.update(category, ip, "alert", "update category");
            return true;
        }
        return false;
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
    public boolean deleteCategory(AbsModel model, String ip) {
        Category category = (Category) model;
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("DELETE FROM category WHERE id = ?").bind(0, category.getId()).execute();
        });
        if (i == 1) {
            category.setBeforeData(category.logString());
            category.setAfterData(getCategory(category.getId()).logString());
            super.update(category, ip, "danger", "delete category");
            return true;
        }
        return false;
    }
}
