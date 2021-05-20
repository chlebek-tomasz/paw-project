package com.chlebek.pawproject.service;

import com.chlebek.pawproject.exception.NoContentException;
import com.chlebek.pawproject.exception.ResourceExistsException;
import com.chlebek.pawproject.exception.ResourceNotFoundException;
import com.chlebek.pawproject.model.Director;
import com.chlebek.pawproject.model.Movie;
import com.chlebek.pawproject.model.MovieCategory;
import com.chlebek.pawproject.payload.MovieRequest;
import com.chlebek.pawproject.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieCategoryService movieCategoryService;
    private final DirectorService directorService;

    public List<Movie> getMovies(Integer page, Integer size) {
        page = page == null ? 0 : page;
        size = size == null ? 10 : size;
        return movieRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> {
            throw new NoContentException("Movie not found with id - " + id);
        });
    }

    public Movie addNewMovie(MovieRequest request) {
        if (movieRepository.existsByTitle(request.getTitle()))
            throw new ResourceExistsException("Movie with title - " + request.getTitle() + " - already exists!");
        return movieRepository.save(buildMovieObject(request));
    }

    public Movie editMovie(Long id, MovieRequest request) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Movie not found with id - " + id);
        });
        if (movieRepository.existsByTitle(request.getTitle()))
            throw new ResourceExistsException("Movie with title - " + request.getTitle() + " - already exists!");
        MovieCategory movieCategory = movieCategoryService.getMovieCategoryById(request.getMovieCategoryId());
        Director director = directorService.getDirectorById(request.getDirectorId());
        movie.setTitle(request.getTitle());
        movie.setDescription(request.getDescription());
        movie.setPremiere(request.getPremiere());
        movie.setCategory(movieCategory);
        movie.setDirector(director);
        return movieRepository.save(movie);
    }

    public void deleteMovie(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Movie not found with id - " + id);
        });
        movieRepository.delete(movie);
    }

    public List<Movie> getMoviesBySearchParam(Integer page, Integer size, String title, Long categoryId, Long directorId) {
        page = page == null ? 0 : page;
        size = size == null ? 10 : size;
        if (title != null && categoryId != null && directorId != null)
            return movieRepository.findAllByCategoryIdAndDirectorIdAndTitle(PageRequest.of(page, size),
                    categoryId, directorId, title).getContent();
        else if (title == null && categoryId != null && directorId != null)
            return movieRepository.findAllByCategoryIdAndDirectorId(PageRequest.of(page, size),
                    categoryId, directorId).getContent();
        else if (title != null && categoryId != null && directorId == null)
            return movieRepository.findAllByCategoryIdAndTitle(PageRequest.of(page, size),
                    categoryId, title).getContent();
        else if (title != null && categoryId == null && directorId != null)
            return movieRepository.findAllByDirectorIdAndTitle(PageRequest.of(page, size),
                    directorId, title).getContent();
        else if (title != null && categoryId == null && directorId == null)
            return movieRepository.findAllByTitle(PageRequest.of(page, size), title).getContent();
        else if (title == null && categoryId != null && directorId == null)
            return movieRepository.findAllByCategoryId(PageRequest.of(page, size), categoryId).getContent();
        else if (title == null && categoryId == null && directorId != null)
            return movieRepository.findAllByDirectorId(PageRequest.of(page, size), directorId).getContent();
        else return getMovies(page, size);
    }

    public List<Movie> getTopRatedMovies(Integer page, Integer size) {
        page = page == null ? 0 : page;
        size = size == null ? 10 : size;
        return movieRepository.findAllByOrderByRatingDesc(PageRequest.of(page, size)).getContent();
    }

    private Movie buildMovieObject(MovieRequest request) {
        MovieCategory movieCategory = movieCategoryService.getMovieCategoryById(request.getMovieCategoryId());
        Director director = directorService.getDirectorById(request.getDirectorId());
        return Movie.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .premiere(request.getPremiere())
                .category(movieCategory)
                .director(director)
                .build();
    }

}
