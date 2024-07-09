package com.br.controlClick.services;

import com.br.controlClick.domain.dto.UserDto;

import java.util.List;

public interface IUserService {
    UserDto createUser(UserDto dto);

    List<UserDto> listUsers();

    UserDto listUser(Long id);

    UserDto updateUser(Long id, UserDto userDto);

    void deleteUser(Long id);
}
