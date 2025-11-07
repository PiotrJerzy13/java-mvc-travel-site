package com.piotrwojnarowski.data;

import com.piotrwojnarowski.domain.Travelling;

import java.util.List;
import java.util.Optional;

public interface TravellingRepository {
    List<Travelling> findAll();
    Optional<Travelling> getById(long id);  // ← Returns Optional
    Travelling create(Travelling travelling);
    Travelling save(Travelling travelling);
}
