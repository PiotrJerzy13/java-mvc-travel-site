package com.piotrwojnarowski.data;

import com.piotrwojnarowski.domain.Travelling;

import java.util.List;
import java.util.Optional;

public interface TravellingRepository {
    List<Travelling> findAll();
    Travelling create(Travelling travelling);
    Optional<Travelling> getById(long id);
    Travelling save(Travelling travelling);
}
