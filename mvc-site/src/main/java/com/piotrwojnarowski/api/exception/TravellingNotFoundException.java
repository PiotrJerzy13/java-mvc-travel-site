package com.piotrwojnarowski.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TravellingNotFoundException extends RuntimeException {
    public TravellingNotFoundException(Long id) {
        super("Travelling " + id + " not found");
    }
}
