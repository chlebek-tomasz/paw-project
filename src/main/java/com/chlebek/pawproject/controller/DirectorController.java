package com.chlebek.pawproject.controller;

import com.chlebek.pawproject.model.Director;
import com.chlebek.pawproject.model.Movie;
import com.chlebek.pawproject.payload.DirectorRequest;
import com.chlebek.pawproject.service.DirectorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/directors/")
@AllArgsConstructor
public class DirectorController {

    private final DirectorService directorService;

    @GetMapping
    public ResponseEntity<List<Director>> getAllDirectors(@RequestParam("page") Integer page,
                                                          @RequestParam("size") Integer size) {
        return new ResponseEntity<>(directorService.getAllDirectors(page, size), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Director> getDirectorById(@PathVariable Long id) {
        return new ResponseEntity<>(directorService.getDirectorById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/movies")
    public ResponseEntity<List<Movie>> getDirectorMovies(@PathVariable Long id,
                                                         @RequestParam("page") Integer page,
                                                         @RequestParam("size") Integer size) {
        return new ResponseEntity<>(directorService.getDirectorMovies(id, page, size), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Director> addNewDirector(@RequestBody DirectorRequest request) {
        return new ResponseEntity<>(directorService.addNewDirector(request), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Director> editDirector(@PathVariable Long id, @RequestBody DirectorRequest request) {
        return new ResponseEntity<>(directorService.editDirector(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDirector(@PathVariable Long id) {
        directorService.deleteDirector(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
