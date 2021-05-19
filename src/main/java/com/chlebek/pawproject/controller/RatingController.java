package com.chlebek.pawproject.controller;

import com.chlebek.pawproject.service.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ratings/")
@AllArgsConstructor
public class RatingController {

    private final RatingService ratingService;
}
