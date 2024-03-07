package com.anand.reviewms.impl;


import com.anand.reviewms.Review;
import com.anand.reviewms.ReviewRepository;
import com.anand.reviewms.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public Boolean addReview(Review review,Long companyId) {
        if (companyId!=null && review!=null){
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public void updateReview(Long reviewId, Review updatedReview) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review!=null){
            updatedReview.setTitle(updatedReview.getTitle());
            updatedReview.setDescription(updatedReview.getDescription());
            updatedReview.setRating(updatedReview.getRating());
            updatedReview.setCompanyId(updatedReview.getCompanyId());
            reviewRepository.save(updatedReview);
        }
    }
}
