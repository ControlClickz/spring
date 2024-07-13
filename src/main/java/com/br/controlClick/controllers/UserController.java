package com.br.controlClick.controllers;

import com.br.controlClick.domain.dto.FollowDto;
import com.br.controlClick.domain.dto.UserDto;
import com.br.controlClick.exceptions.AlreadyExistsException;
import com.br.controlClick.exceptions.NotFoundException;
import com.br.controlClick.services.FollowService;
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

    private final FollowService followService;

    @Autowired
    public UserController(IUserService service, FollowService followService) {
        this.service = service;
        this.followService = followService;
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
            @PathVariable("id") Long id
    ) {
        try {
            return ResponseEntity.ok(service.getUser(id));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable("id") Long id,
            @RequestBody UserDto userDto
    ) {
        try {
            return ResponseEntity.ok(service.updateUser(id, userDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(
            @PathVariable("id") Long id
    ) {
        try {
            service.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/follows")
    public ResponseEntity<?> followUser(
            @RequestBody FollowDto dto
            ) {
        try {
            followService.createFollow(dto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("{id}/follows")
    public ResponseEntity<?> listFollows(
            @PathVariable("id") Long id
    ) {
        try {
            return ResponseEntity.ok().body(followService.listFollows(id));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/follows/{followId}")
    public ResponseEntity<?> unfollowUser(
            @PathVariable("followId") Long followId
    ) {
        try {
            followService.unfollow(followId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/{userId}/favorites/{gameId}")
    public ResponseEntity<?> favoriteGame(
            @PathVariable("userId") Long userId,
            @PathVariable("gameId") Long gameId
    ) {
        try {
            service.favoriteGame(userId, gameId);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{userId}/favorites/{gameId}")
    public ResponseEntity<?> unfavoriteGame(
            @PathVariable("userId") Long userId,
            @PathVariable("gameId") Long gameId) {
        try {
            service.unfavoriteGame(userId, gameId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}/favorites")
    public ResponseEntity<?> listFavoriteGames(
            @PathVariable("id") Long id
    ) {
        try {
            return ResponseEntity.ok(service.listFavoriteGames(id));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
