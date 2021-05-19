package com.chlebek.pawproject.service;

import com.chlebek.pawproject.exception.ResourceNotFoundException;
import com.chlebek.pawproject.model.Director;
import com.chlebek.pawproject.model.MovieCategory;
import com.chlebek.pawproject.repository.DirectorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DirectorService {

    private final DirectorRepository directorRepository;

    public Director getDirectorById(Long id) {
        return directorRepository.findById(id)
                .orElseThrow(() -> {
                    throw new ResourceNotFoundException("Director not found with id - " + id);
                });
    }

}
