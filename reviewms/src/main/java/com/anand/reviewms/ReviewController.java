package com.anand.reviewms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> getAll(@RequestParam Long companyId) {
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long companyId, @RequestBody Review review) {
        if (
                reviewService.addReview(review, companyId)) {
            return new ResponseEntity<>(String.format("Successfully added Review for company with id %d", companyId), HttpStatus.OK);

        }
        return new ResponseEntity<>(String.format("Company with id %d not found and review not saved", companyId), HttpStatus.NOT_FOUND);

    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId) {
        return new ResponseEntity<>(reviewService.getReviewById(reviewId), HttpStatus.OK);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId, @RequestBody Review review) {
        reviewService.updateReview(reviewId, review);
        return new ResponseEntity<>("Review updated", HttpStatus.OK);
    }
}
