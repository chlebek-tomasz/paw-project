package com.chlebek.pawproject.controller;

import com.chlebek.pawproject.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reviews/")
@AllArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
}
