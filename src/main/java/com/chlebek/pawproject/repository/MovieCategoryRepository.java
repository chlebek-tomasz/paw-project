package com.chlebek.pawproject.repository;

import com.chlebek.pawproject.model.MovieCategory;
import org.springframework.data.repository.CrudRepository;

public interface MovieCategoryRepository extends CrudRepository<MovieCategory, Long> {
}
