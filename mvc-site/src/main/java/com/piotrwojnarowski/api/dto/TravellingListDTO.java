package com.piotrwojnarowski.api.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class TravellingListDTO {
    private Long id;
    private String destination;
    private LocalDate date;
    private Double ratingSummary;
}
