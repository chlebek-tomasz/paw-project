package com.chlebek.pawproject.controller;

import com.chlebek.pawproject.model.Review;
import com.chlebek.pawproject.payload.ReviewRequest;
import com.chlebek.pawproject.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews/")
@AllArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> addNewReview(@RequestBody ReviewRequest request) {
        return new ResponseEntity<>(reviewService.addNewReview(request), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> editReview(@PathVariable Long id,
                                             @RequestBody ReviewRequest request) {
        return new ResponseEntity<>(reviewService.editReview(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
