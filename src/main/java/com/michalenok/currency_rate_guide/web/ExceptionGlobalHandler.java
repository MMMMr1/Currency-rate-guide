package com.michalenok.currency_rate_guide.web;

import com.michalenok.currency_rate_guide.model.error.ExceptionErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionGlobalHandler {
    /**
     * 404
     */
    @ExceptionHandler(value = {NoSuchElementException.class })
    public ResponseEntity<List<ExceptionErrorDTO>> ArgumentNotFoundException(
            RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(List.of(new ExceptionErrorDTO(e.getMessage())));
    }

    /**
     * 500
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public List<ExceptionErrorDTO> handler(Throwable e) {
        return List.of(new ExceptionErrorDTO(e.getMessage()));
    }
}
