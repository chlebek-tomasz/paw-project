package com.chlebek.pawproject.controller;

import com.chlebek.pawproject.model.Movie;
import com.chlebek.pawproject.payload.MovieRequest;
import com.chlebek.pawproject.repository.MovieRepository;
import com.chlebek.pawproject.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies/")
@AllArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies(@RequestParam(name = "page", required = false) Integer page,
                                                 @RequestParam(name = "size", required = false) Integer size) {
        return new ResponseEntity<>(movieService.getMovies(page, size), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        return new ResponseEntity<>(movieService.getMovieById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Movie> addNewMovie(@RequestBody MovieRequest request) {
        return new ResponseEntity<>(movieService.addNewMovie(request), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> editMovie(@PathVariable Long id,
                                           @RequestBody MovieRequest request) {
        return new ResponseEntity<>(movieService.editMovie(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Movie>> getMoviesBySearchParam(@RequestParam(name = "page", required = false) Integer page,
                                                              @RequestParam(name = "size", required = false) Integer size,
                                                              @RequestParam(name = "name", required = false) String name,
                                                              @RequestParam(name = "categoryId", required = false) Long categoryId,
                                                              @RequestParam(name = "directorId", required = false) Long directorId) {
        return new ResponseEntity<>(movieService.getMoviesBySearchParam(page, size, name, categoryId, directorId), HttpStatus.OK);
    }


}
