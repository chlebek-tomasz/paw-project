package com.chlebek.pawproject.controller;

import com.chlebek.pawproject.service.DirectorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/directors/")
@AllArgsConstructor
public class DirectorController {

    private final DirectorService directorService;

}
