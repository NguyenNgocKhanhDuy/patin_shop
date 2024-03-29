package vn.hcmuaf.edu.fit.dao;

import vn.hcmuaf.edu.fit.bean.ImageRating;
import vn.hcmuaf.edu.fit.bean.Rating;
import vn.hcmuaf.edu.fit.bean.RatingLike;
import vn.hcmuaf.edu.fit.db.JDBIConnector;

import java.util.List;
import java.util.stream.Collectors;

public class RatingDao {
    private static RatingDao instance;

    public RatingDao() {
    }

    public static RatingDao getInstance() {
        if (instance == null) instance = new RatingDao();
        return instance;
    }

    public List<Rating> getAllRating(){
        List<Rating> ratings = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT * FROM rating").mapToBean(Rating.class).stream().collect(Collectors.toList());
        });
        return ratings;
    }

    public List<Rating> getAllRatingOfProduct(int id){
        List<Rating> ratings = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT rating.id, rating.score, rating.content, rating.date, user.fullname as user_fullname, user.avatar as user_avatar " +
                            "FROM rating JOIN user ON rating.user_id = user.id " +
                            "WHERE product_id = :id")
                    .bind("id", id).mapToBean(Rating.class).stream().collect(Collectors.toList());
        });
        return ratings;
    }

    public int getSizeAllRating(int id){
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT count(*) " +
                            "FROM rating  " +
                            "WHERE product_id = :id AND rating.content IS NOT NULL ")
                    .bind("id", id).mapTo(Integer.class).one();
        });
        return i;
    }

    public List<ImageRating> getImageRatingOfProduct(int id){
        List<ImageRating> ratings = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT image_rating.url, image_rating.rating_id " +
                            "FROM image_rating JOIN rating ON image_rating.rating_id = rating.id " +
                            "WHERE product_id = :id ")
                    .bind("id", id).mapToBean(ImageRating.class).stream().collect(Collectors.toList());
        });
        return ratings;
    }

    public List<Rating> getAllRatingOfProductPerPage(int id, int currentPage){
        int start = getStartIndex(currentPage, 3);
        List<Rating> ratings = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT rating.id, rating.score, rating.content, rating.date, user.fullname as user_fullname, user.avatar as user_avatar " +
                            "FROM rating JOIN user ON rating.user_id = user.id " +
                            "WHERE product_id = :id  " +
                            "LIMIT :start, 3")
                    .bind("id", id).bind("start", start)
                    .mapToBean(Rating.class).stream().collect(Collectors.toList());
        });
        return ratings;
    }

    public List<Rating> getAllRatingOfProductPerPageSortLike(int id, int currentPage){
        int start = getStartIndex(currentPage, 3);
        List<Rating> ratings = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT rating.id, rating.score, rating.content, rating.date, user.fullname as user_fullname, user.avatar as user_avatar, COUNT(rating_detail_like.like) as count_like " +
                            "FROM rating_detail_like JOIN rating on rating.id = rating_detail_like.rating_id JOIN user ON rating.user_id = user.id " +
                            "WHERE product_id = :id AND rating_detail_like.like = 1 AND rating.content IS NOT NULL " +
                            "GROUP BY rating.id " +
                            "ORDER BY count_like desc " +
                            "LIMIT :start, 3")
                    .bind("id", id).bind("start", start)
                    .mapToBean(Rating.class).stream().collect(Collectors.toList());
        });
        return ratings;
    }

    public List<Rating> getAllRatingOfProductPerPageSortDate(int id, int currentPage){
        int start = getStartIndex(currentPage, 3);
        List<Rating> ratings = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT rating.id, rating.score, rating.content, rating.date, user.fullname as user_fullname, user.avatar as user_avatar " +
                            "FROM rating JOIN user ON rating.user_id = user.id " +
                            "WHERE product_id = :id AND rating.content IS NOT NULL " +
                            "ORDER BY rating.date desc "+
                            "LIMIT :start, 3")
                    .bind("id", id).bind("start", start)
                    .mapToBean(Rating.class).stream().collect(Collectors.toList());
        });
        return ratings;
    }

    public int getStartIndex(int currentPage, int productPerPage) {
        if (currentPage > 1) {
            return  ((currentPage - 1) * productPerPage);
        } else {
            return  0;
        }
    }

    public List<RatingLike> getLike(int productID) {
        List<RatingLike> ratingLikes = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT rating.id as rating_id, rating_detail_like.user_id as user_id " +
                    "FROM rating_detail_like JOIN rating ON rating_detail_like.rating_id = rating.id " +
                    "WHERE rating_detail_like.like = 1 and rating.product_id = ?")
                    .bind(0, productID).mapToBean(RatingLike.class).stream().collect(Collectors.toList());
        });
        return ratingLikes;
    }



    public int addRatingImg(ImageRating imgRating){
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("INSERT Into image_rating(url, rating_id) values (?, ?)")
                    .bind(0, imgRating.getUrl()).bind(1, imgRating.getRating().getId()).execute();
        });
        return i;
    }

    public int addRatingHasContent(Rating rating){
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO rating(score, content, date, user_id, product_id) VALUES (:score, :content, :date, :user_id, :product_id) ")
                    .bind("score", rating.getScore()).bind("content", rating.getContent()).bind("date", rating.getDate())
                    .bind("user_id", rating.getUser().getId()).bind("product_id", rating.getProduct().getId()).execute();
        });
        return i;
    }

    public int addRatingNoContent(Rating rating){
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO rating(score, date, user_id, product_id) VALUES (:score, :date, :user_id, :product_id) ")
                    .bind("score", rating.getScore()).bind("date", rating.getDate())
                    .bind("user_id", rating.getUser().getId()).bind("product_id", rating.getProduct().getId()).execute();
        });
        return i;
    }

    public Rating getNewRating() {
        Rating rating = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT * " +
                            "FROM rating " +
                            "ORDER BY date desc " +
                            "LIMIT 0, 1").mapToBean(Rating.class).one();
        });
        return rating;
    }

    public int updateContentRating(int id){
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("UPDATE rating SET content = \" \" WHERE id = ?").bind(0, id).execute();
        });
        return i;
    }

    public boolean addInRatingLike(RatingLike ratingLike){
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO rating_detail_like(rating_id, like, user_id) VALUES (:id, 1, :user)")
                    .bind("id", ratingLike.getRating().getId()).bind("user", ratingLike.getUser().getId()).execute();
        });
        return i == 1 ? true : false;

    }

}
