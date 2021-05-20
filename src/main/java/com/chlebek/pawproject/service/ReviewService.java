package com.chlebek.pawproject.service;

import com.chlebek.pawproject.exception.ResourceNotFoundException;
import com.chlebek.pawproject.model.Movie;
import com.chlebek.pawproject.model.Review;
import com.chlebek.pawproject.payload.ReviewRequest;
import com.chlebek.pawproject.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieService movieService;

    public List<Review> getMovieReviews(Long id, Integer page, Integer size) {
        page = page == null ? 0 : page;
        size = size == null ? 10 : size;
        return reviewRepository.findAllByMovieId(PageRequest.of(page, size), id).getContent();
    }

    public Review addNewReview(ReviewRequest request) {
        Movie movie = movieService.getMovieById(request.getMovieId());
        Review review = new Review();
        review.setDescription(request.getDescription());
        review.setMovie(movie);
        return reviewRepository.save(review);
    }

    public Review editReview(Long id, ReviewRequest request) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Review not found with id - " + id);
        });
        review.setDescription(request.getDescription());
        return reviewRepository.save(review);
    }

    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Review not found with id - " + id);
        });
        reviewRepository.delete(review);
    }

}
