package com.anand.reviewms;


import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);

    Boolean addReview(Review review,Long companyId);

    Review getReviewById(Long reviewId);

    void updateReview(Long reviewId, Review review);
}
