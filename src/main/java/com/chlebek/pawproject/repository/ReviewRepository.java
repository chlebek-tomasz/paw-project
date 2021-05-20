package com.chlebek.pawproject.repository;

import com.chlebek.pawproject.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    Page<Review> findAllByMovieId(Pageable pageable, Long id);
}
