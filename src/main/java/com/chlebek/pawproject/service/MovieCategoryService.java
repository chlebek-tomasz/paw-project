package com.chlebek.pawproject.service;

import com.chlebek.pawproject.exception.ResourceNotFoundException;
import com.chlebek.pawproject.model.MovieCategory;
import com.chlebek.pawproject.repository.MovieCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MovieCategoryService {

    private final MovieCategoryRepository movieCategoryRepository;

    public MovieCategory getMovieCategoryById(Long id) {
        return movieCategoryRepository.findById(id)
                .orElseThrow(() -> {
                    throw new ResourceNotFoundException("Movie category not found with id - " + id);
                });
    }

}
