package vn.hcmuaf.edu.fit.services;

import vn.hcmuaf.edu.fit.bean.ImageRating;
import vn.hcmuaf.edu.fit.bean.Rating;
import vn.hcmuaf.edu.fit.bean.RatingLike;
import vn.hcmuaf.edu.fit.dao.RatingDao;

import java.util.List;

public class RatingService {
    private static RatingService instance;

    public RatingService() {
    }

    public static RatingService getInstance() {
        if (instance == null) instance = new RatingService();
        return instance;
    }

    public List<Rating> getAllRatingPerPage(int id, int currentPage, String sortType){
        if (sortType.equals("like")){
            return RatingDao.getInstance().getAllRatingOfProductPerPageSortLike(id, currentPage);
        } else if (sortType.equals("date")) {
            return RatingDao.getInstance().getAllRatingOfProductPerPageSortDate(id, currentPage);
        }else {
            return RatingDao.getInstance().getAllRatingOfProductPerPage(id, currentPage);
        }
    }

    public boolean addRatingHasContent(Rating rating){
        if (RatingDao.getInstance().addRatingHasContent(rating) != 1){
            return false;
        }
        return true;
    }

    public boolean addRatingNoContent(Rating rating){
        if (RatingDao.getInstance().addRatingNoContent(rating) != 1){
            return false;
        }
        return true;
    }
    public boolean addRatingImg(ImageRating imgRating){
        if (RatingDao.getInstance().addRatingImg(imgRating) == 0){
            return false;
        }
        return true;
    }

    public Rating getNewRating() {
        return RatingDao.getInstance().getNewRating();
    }
    public boolean updateContentRating(int id){
        if (RatingDao.getInstance().updateContentRating(id) != 1)
            return false;
        return true;
    }

    public boolean addRatingLike(RatingLike ratingLike){
        return RatingDao.getInstance().addInRatingLike(ratingLike);
    }
}
