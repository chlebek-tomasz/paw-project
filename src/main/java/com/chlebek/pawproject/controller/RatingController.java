package com.chlebek.pawproject.controller;

import com.chlebek.pawproject.model.Rating;
import com.chlebek.pawproject.payload.RatingRequest;
import com.chlebek.pawproject.service.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ratings/")
@AllArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @PostMapping
    public ResponseEntity<HttpStatus> addNewRating(@RequestBody RatingRequest request) {
        ratingService.addRating(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> editRating(@PathVariable Long id,
                                                 @RequestBody RatingRequest request) {
        ratingService.editRating(id, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRating(@PathVariable Long id) {
        ratingService.deleteRating(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
