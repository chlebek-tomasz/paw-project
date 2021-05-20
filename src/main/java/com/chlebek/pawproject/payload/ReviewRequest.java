package com.chlebek.pawproject.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ReviewRequest {
    private String description;
    private Long movieId;
}
