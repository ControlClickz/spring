package com.br.controlClick.controllers;

import com.br.controlClick.domain.dto.GameDto;
import com.br.controlClick.exceptions.AlreadyExistsException;
import com.br.controlClick.exceptions.NotFoundException;
import com.br.controlClick.services.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
public class GameController {
    private final IGameService service;

    @Autowired
    public GameController(IGameService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createGame(
            @Validated  @RequestBody GameDto dto
            ) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.createGame(dto));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listGames() {
       return ResponseEntity.ok().body(service.listGames());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGame(
            @PathVariable("id") Long id
    ) {
        try {
            return ResponseEntity.ok().body(service.getGame(id));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGame(
            @PathVariable("id") Long id,
            @RequestBody GameDto dto
    ) {
        try {
            return ResponseEntity.ok().body(service.updateGame(id, dto));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGame(
            @PathVariable("id") Long id
    ) {
        try {
            service.deleteGame(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
