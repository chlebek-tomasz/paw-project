package com.chlebek.pawproject.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MovieRequest {

    private String title;
    private String description;
    private String premiere;
    private Long movieCategoryId;
    private Long directorId;
}
