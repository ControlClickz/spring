package com.br.controlClick.controllers;

import com.br.controlClick.domain.dto.UserDto;
import com.br.controlClick.exceptions.AlreadyExistsException;
import com.br.controlClick.exceptions.NotFoundException;
import com.br.controlClick.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService service;

    @Autowired
    public UserController(IUserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createUser(
            @Validated @RequestBody UserDto userDto
    ) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.createUser(userDto));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listUsers() {
        return ResponseEntity.ok(service.listUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(
            @PathVariable Long id
    ) {
        try {
            return ResponseEntity.ok(service.listUser(id));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable Long id,
            @RequestBody UserDto userDto
    ) {
        try {
            return ResponseEntity.ok(service.updateUser(id, userDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUser(
            @PathVariable Long id
    ) {
        service.deleteUser(id);
    }
}
