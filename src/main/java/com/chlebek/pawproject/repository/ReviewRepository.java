package com.chlebek.pawproject.repository;

import com.chlebek.pawproject.model.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {
}
