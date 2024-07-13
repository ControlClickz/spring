package com.br.controlClick.services;

import com.br.controlClick.domain.dto.GameDto;
import com.br.controlClick.domain.dto.UserDto;

import java.util.List;

public interface IUserService {
    UserDto createUser(UserDto dto);
    List<UserDto> listUsers();
    UserDto getUser(Long id);
    UserDto updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
    void favoriteGame(Long userId, Long gameId);
    void unfavoriteGame(Long userId, Long gameId);
    List<GameDto> listFavoriteGames(Long id);
}
