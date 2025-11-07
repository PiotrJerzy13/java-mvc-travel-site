package com.piotrwojnarowski.data;

import com.piotrwojnarowski.domain.Category;
import com.piotrwojnarowski.domain.Travelling;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class InMemoryTravellingRepository implements TravellingRepository {

    private final List<Travelling> travellings = new ArrayList<>();

    @PostConstruct
    private void createTestData() {
        create(Travelling.builder()
                .name("Colosseum")
                .location("Rome, Italy")
                .visitedAt(LocalDate.of(2012, 5, 3))
                .category(Category.CULTURE)
                .build());

        create(Travelling.builder()
                .name("Grand Canyon")
                .location("Arizona, USA")
                .visitedAt(LocalDate.of(2016, 8, 18))
                .category(Category.NATURE)
                .build());

        create(Travelling.builder()
                .name("Tokyo Skytree")
                .location("Tokyo, Japan")
                .visitedAt(LocalDate.of(2019, 4, 10))
                .category(Category.ARCHITECTURE)
                .build());

        create(Travelling.builder()
                .name("Great Barrier Reef")
                .location("Queensland, Australia")
                .visitedAt(LocalDate.of(2015, 11, 22))
                .category(Category.NATURE)
                .build());

        create(Travelling.builder()
                .name("Machu Picchu")
                .location("Cusco Region, Peru")
                .visitedAt(LocalDate.of(2017, 6, 14))
                .category(Category.HISTORY)
                .build());

        create(Travelling.builder()
                .name("Sagrada Família")
                .location("Barcelona, Spain")
                .visitedAt(LocalDate.of(2021, 9, 7))
                .category(Category.ARCHITECTURE)
                .build());

        create(Travelling.builder()
                .name("Petra")
                .location("Ma'an, Jordan")
                .visitedAt(LocalDate.of(2014, 10, 2))
                .category(Category.HISTORY)
                .build());

        create(Travelling.builder()
                .name("Niagara Falls")
                .location("Ontario, Canada")
                .visitedAt(LocalDate.of(2020, 7, 25))
                .category(Category.NATURE)
                .build());

        create(Travelling.builder()
                .name("Statue of Liberty")
                .location("New York City, USA")
                .visitedAt(LocalDate.of(2018, 3, 9))
                .category(Category.ARCHITECTURE)
                .build());

        create(Travelling.builder()
                .name("Acropolis of Athens")
                .location("Athens, Greece")
                .visitedAt(LocalDate.of(2023, 4, 16))
                .category(Category.CULTURE)
                .build());
    }

    @Override
    public List<Travelling> findAll() {
        return travellings;
    }

    @Override
    public Travelling create(Travelling travelling) {
        long currentMax = travellings.stream()
                .map(Travelling::getId)
                .max(Long::compare)
                .orElse(0L);
        travelling.setId(currentMax + 1);
        travellings.add(travelling);
        return travelling;
    }

    @Override
    public Optional<Travelling> getById(long id) {
        return travellings.stream()
                .filter(travelling -> travelling.getId() == id)
                .findFirst();
    }

    @Override
    public Travelling save(Travelling travelling) {
        getById(travelling.getId()).ifPresent(existing -> travellings.remove(existing));
        travellings.add(travelling);
        return travelling;
    }
}
