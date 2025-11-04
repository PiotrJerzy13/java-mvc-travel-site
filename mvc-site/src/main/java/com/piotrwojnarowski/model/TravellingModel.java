package com.piotrwojnarowski.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class TravellingModel {

    private long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Location cannot be empty")
    private String location;

    @NotNull(message = "Visited date is required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate visitedAt;

    @NotBlank(message = "Category cannot be empty")
    private String category;

    private String review;
}
