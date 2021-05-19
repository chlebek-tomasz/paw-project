package com.chlebek.pawproject.service;

import com.chlebek.pawproject.exception.ResourceNotFoundException;
import com.chlebek.pawproject.model.Director;
import com.chlebek.pawproject.model.Movie;
import com.chlebek.pawproject.payload.DirectorRequest;
import com.chlebek.pawproject.repository.DirectorRepository;
import com.chlebek.pawproject.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DirectorService {

    private final DirectorRepository directorRepository;
    private final MovieRepository movieRepository;

    public List<Director> getAllDirectors(Integer page, Integer size) {
        page = page == null ? 0 : page;
        size = size == null ? 10 : size;
        return directorRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    public Director getDirectorById(Long id) {
        return directorRepository.findById(id)
                .orElseThrow(() -> {
                    throw new ResourceNotFoundException("Director not found with id - " + id);
                });
    }

    public List<Movie> getDirectorMovies(Long id, Integer page, Integer size) {
        Director director = getDirectorById(id);
        return movieRepository.findAllByDirectorId(PageRequest.of(page, size), id).getContent();
    }

    public Director addNewDirector(DirectorRequest request) {
        Director director = new Director();
        director.setName(request.getName());
        return directorRepository.save(director);
    }

    public Director editDirector(Long id, DirectorRequest request) {
        Director director = getDirectorById(id);
        director.setName(request.getName());
        return directorRepository.save(director);
    }

    public void deleteDirector(Long id) {
        Director director = getDirectorById(id);
        directorRepository.delete(director);
    }
}
