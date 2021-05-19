package com.chlebek.pawproject.repository;

import com.chlebek.pawproject.model.Rating;
import org.springframework.data.repository.CrudRepository;

public interface RatingRepository extends CrudRepository<Rating, Long> {
}
