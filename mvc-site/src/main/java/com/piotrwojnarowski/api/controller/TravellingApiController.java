package com.piotrwojnarowski.api.controller;

import com.piotrwojnarowski.api.dto.TravellingDetailDTO;
import com.piotrwojnarowski.api.dto.UpdateTravellingRequest;
import com.piotrwojnarowski.api.dto.CreateTravellingRequest;
import com.piotrwojnarowski.api.exception.TravellingNotFoundException;
import com.piotrwojnarowski.api.mapper.TravellingMapper;
import com.piotrwojnarowski.data.TravellingRepository;
import com.piotrwojnarowski.domain.Travelling;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.net.URI;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/travellings")
@RequiredArgsConstructor
public class TravellingApiController {

    private final TravellingRepository repository;
    private final TravellingMapper mapper;

    @GetMapping
    public ResponseEntity<List<TravellingDetailDTO>> list() {
        var items = repository.findAll().stream().map(mapper::toDetailDto).toList();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TravellingDetailDTO> get(@PathVariable Long id) {
        var t = repository.getById(id).orElseThrow(() -> new TravellingNotFoundException(id));
        return ResponseEntity.ok(mapper.toDetailDto(t));
    }

    @PostMapping
    public ResponseEntity<TravellingDetailDTO> create(@Valid @RequestBody CreateTravellingRequest req) {
        var entity  = mapper.toEntity(req);
        var created = repository.create(entity);
        var dto     = mapper.toDetailDto(created);
        return ResponseEntity.created(URI.create("/api/travellings/" + dto.getId())).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TravellingDetailDTO> update(@PathVariable Long id,
                                                      @Valid @RequestBody UpdateTravellingRequest req) {
        var existing = repository.getById(id).orElseThrow(() -> new TravellingNotFoundException(id));
        mapper.updateEntity(existing, req);
        repository.save(existing);
        return ResponseEntity.ok(mapper.toDetailDto(existing));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TravellingDetailDTO> patch(@PathVariable Long id,
                                                     @RequestBody UpdateTravellingRequest req) {
        var existing = repository.getById(id).orElseThrow(() -> new TravellingNotFoundException(id));
        mapper.mergeEntity(existing, req);
        repository.save(existing);
        return ResponseEntity.ok(mapper.toDetailDto(existing));
    }
}
