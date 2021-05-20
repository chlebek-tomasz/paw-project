package com.chlebek.pawproject.service;

import com.chlebek.pawproject.exception.ResourceNotFoundException;
import com.chlebek.pawproject.model.Movie;
import com.chlebek.pawproject.model.Rating;
import com.chlebek.pawproject.payload.RatingRequest;
import com.chlebek.pawproject.repository.MovieRepository;
import com.chlebek.pawproject.repository.RatingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@AllArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final MovieService movieService;
    private final MovieRepository movieRepository;

    public List<Rating> getRatingsByMovieId(Long movieId) {
        Movie movie = movieService.getMovieById(movieId);
        return ratingRepository.findAllByMovie(movie);
    }

    public void addRating(RatingRequest request) {
        Movie movie = movieService.getMovieById(request.getMovieId());
        Rating rating = new Rating();
        rating.setRate(request.getRate());
        rating.setMovie(movie);
        ratingRepository.save(rating);
        updateMovieRating(rating.getMovie());
    }

    public void editRating(Long id, RatingRequest request) {
        Rating rating = ratingRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Rating not found with id - " + id);
        });
        rating.setRate(request.getRate());
        ratingRepository.save(rating);
        updateMovieRating(rating.getMovie());
    }

    public void deleteRating(Long id) {
        Rating rating = ratingRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Rating not found with id - " + id);
        });
        Movie movie = rating.getMovie();
        ratingRepository.delete(rating);
        updateMovieRating(movie);
    }

    private void updateMovieRating(Movie movie) {
        List<Rating> ratings = ratingRepository.findAllByMovie(movie);
        int count = ratings.size();
        int summary = ratings.stream().mapToInt(Rating::getRate).sum();
        movie.setRating(summary / count);
        movieRepository.save(movie);
    }
}
