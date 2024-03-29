package vn.hcmuaf.edu.fit.bean;

import org.jdbi.v3.core.mapper.Nested;

import java.io.Serializable;

public class ImageRating implements Serializable {
    private int id;
    private String url;
    @Nested("rating")
    private Rating rating;

    public ImageRating() {
    }

    public ImageRating(int id, String url, Rating rating) {
        this.id = id;
        this.url = url;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Nested("rating")
    public Rating getRating() {
        return rating;
    }
    @Nested("rating")
    public void setRating(Rating rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "ImageRating{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", rating=" + rating +
                '}';
    }
}
