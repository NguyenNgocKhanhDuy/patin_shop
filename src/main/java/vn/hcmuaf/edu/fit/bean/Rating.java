package vn.hcmuaf.edu.fit.bean;

import org.jdbi.v3.core.mapper.Nested;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Rating implements Serializable {
    private int id;
    private int score;
    private String content;
    private LocalDateTime date;
    private int like;
    @Nested("user")
    private User user;
    @Nested("product")
    private Product product;

    public Rating() {
    }

    public Rating(int id, int score, String content, LocalDateTime date, int like, User user, Product product) {
        this.id = id;
        this.score = score;
        this.content = content;
        this.date = date;
        this.like = like;
        this.user = user;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    @Nested("user")
    public User getUser() {
        return user;
    }

    @Nested("user")
    public void setUser(User user) {
        this.user = user;
    }

    @Nested("product")
    public Product getProduct() {
        return product;
    }

    @Nested("product")
    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", score=" + score +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", like=" + like +
                ", user=" + user +
                ", product=" + product +
                '}';
    }
}
