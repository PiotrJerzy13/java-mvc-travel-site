package com.piotrwojnarowski.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Travelling {
    private long id;
    private String name;
    private String location;
    private LocalDate visitedAt;
    private Category category;
    private String review;
    private Double rating;
}
