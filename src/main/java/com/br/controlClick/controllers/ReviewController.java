package com.br.controlClick.controllers;

import com.br.controlClick.domain.dto.ReviewDto;
import com.br.controlClick.exceptions.AlreadyExistsException;
import com.br.controlClick.exceptions.NotFoundException;
import com.br.controlClick.repositories.IReviewRepository;
import com.br.controlClick.services.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final IReviewService service;

    @Autowired
    public ReviewController(IReviewRepository repository, IReviewService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createReview(
            @RequestBody ReviewDto dto
    ) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.createReview(dto));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listReviews() {
        return ResponseEntity.ok().body(service.listReviews());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReview(
            @PathVariable("id") Long id
    ) {
        try {
            return ResponseEntity.ok().body(service.getReview(id));
        } catch (NotFoundException e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReview(
            @PathVariable("id") Long id,
            @RequestBody ReviewDto dto
    ) {
        try {
            return ResponseEntity.ok().body(service.updateReview(id, dto));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(
            @PathVariable("id") Long id
    ) {
        try {
            service.deleteReview(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
