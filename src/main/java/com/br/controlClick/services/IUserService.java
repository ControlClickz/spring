package com.br.controlClick.services;

import com.br.controlClick.domain.dto.UserDto;

import java.util.List;

public interface IUserService {
    UserDto createUser(UserDto dto);

    List<UserDto> listUsers();

    UserDto listUser(Long id) throws Exception;

    UserDto updateUser(Long id, UserDto userDto) throws Exception;

    void deleteUser(Long id);
}
