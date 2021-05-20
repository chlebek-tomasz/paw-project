package com.chlebek.pawproject.repository;

import com.chlebek.pawproject.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    boolean existsByTitle(String title);
    Page<Movie> findAllByCategoryId(Pageable pageable, Long categoryId);
    Page<Movie> findAllByDirectorId(Pageable pageable, Long directorId);
    Page<Movie> findAllByTitle(Pageable pageable, String title);
    Page<Movie> findAllByCategoryIdAndDirectorId(Pageable pageable, Long categoryId, Long directorId);
    Page<Movie> findAllByCategoryIdAndTitle(Pageable pageable, Long categoryId, String title);
    Page<Movie> findAllByDirectorIdAndTitle(Pageable pageable, Long directorId, String title);
    Page<Movie> findAllByCategoryIdAndDirectorIdAndTitle(Pageable pageable, Long categoryId, Long directorId, String title);
    Page<Movie> findAllByOrderByRatingDesc(Pageable pageable);
}
