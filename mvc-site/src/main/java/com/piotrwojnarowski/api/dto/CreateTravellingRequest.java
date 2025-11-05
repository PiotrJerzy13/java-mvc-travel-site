package com.piotrwojnarowski.api.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class CreateTravellingRequest {
    @NotBlank
    private String destination;

    private String description;

    @NotNull
    private LocalDate date;

    private Double rating;
}
