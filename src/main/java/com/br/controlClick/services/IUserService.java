package com.br.controlClick.services;

import com.br.controlClick.domain.dto.UserDto;

import java.util.List;

public interface IUserService {
    UserDto createUser(UserDto dto);
    List<UserDto> listUsers();
    UserDto getUser(Long id);
    UserDto updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
    void followUser(Long followerId, Long followedId);
    void unfollowUser(Long followerId, Long followedId);
    void favoriteGame(Long userId, Long gameId);
    void unfavoriteGame(Long userId, Long gameId);
}
