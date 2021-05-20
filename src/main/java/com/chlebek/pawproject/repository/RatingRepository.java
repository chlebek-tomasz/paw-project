package com.chlebek.pawproject.repository;

import com.chlebek.pawproject.model.Movie;
import com.chlebek.pawproject.model.Rating;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RatingRepository extends CrudRepository<Rating, Long> {
    List<Rating> findAllByMovie(Movie movie);
}
