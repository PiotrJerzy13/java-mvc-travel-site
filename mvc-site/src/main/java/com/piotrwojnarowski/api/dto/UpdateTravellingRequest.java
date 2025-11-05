package com.piotrwojnarowski.api.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UpdateTravellingRequest {
    private String destination;
    private String description;
    private LocalDate date;
    private Double rating;
}
