package com.chlebek.pawproject.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RatingRequest {
    private int rate;
    private Long movieId;
}
