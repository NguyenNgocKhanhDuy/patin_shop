package vn.hcmuaf.edu.fit.bean;

import org.jdbi.v3.core.mapper.Nested;

import java.io.Serializable;

public class RatingLike implements Serializable {
    @Nested("rating")
    private Rating rating;
    private int like;
    @Nested("user")
    private User user;

    public RatingLike() {
    }

    public RatingLike(Rating rating, int like, User user) {
        this.rating = rating;
        this.like = like;
        this.user = user;
    }

    @Nested("rating")
    public Rating getRating() {
        return rating;
    }

    @Nested("rating")
    public void setRating(Rating rating) {
        this.rating = rating;
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

    @Override
    public String toString() {
        return "RatingLike{" +
                "rating=" + rating +
                ", like=" + like +
                ", user=" + user +
                '}';
    }
}
