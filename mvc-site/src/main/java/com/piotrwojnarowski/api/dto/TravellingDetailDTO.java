package com.piotrwojnarowski.api.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class TravellingDetailDTO {
    private Long id;
    private String destination;
    private LocalDate date;
    private String description;
    private List<String> reviews;
    private Double ratingSummary;
}
