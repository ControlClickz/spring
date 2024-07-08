package com.br.controlClick.domain.mappers;

import com.br.controlClick.domain.dto.UserDto;
import com.br.controlClick.domain.entities.User;

public class UserMapper {
    public static User toEntity(UserDto dto) {
        if(dto == null) {
            return null;
        }

        return User.builder()
                .id(dto.getId())
                .nomeCompleto(dto.getNomeCompleto())
                .nomeUsuario(dto.getNomeUsuario())
                .email(dto.getEmail())
                .senha(dto.getSenha())
                .dataNascimento(dto.getDataNascimento())
                .build();
    }

    public static UserDto toDto(User entity) {
        if(entity == null) {
            return null;
        }

        return UserDto.builder()
                .id(entity.getId())
                .nomeCompleto(entity.getNomeCompleto())
                .nomeUsuario(entity.getNomeUsuario())
                .email(entity.getEmail())
                .senha(entity.getSenha())
                .dataNascimento(entity.getDataNascimento())
                .build();
    }
}
