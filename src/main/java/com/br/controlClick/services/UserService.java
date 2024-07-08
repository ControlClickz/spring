package com.br.controlClick.services;

import com.br.controlClick.domain.dto.UserDto;
import com.br.controlClick.domain.entities.User;
import com.br.controlClick.domain.mappers.UserMapper;
import com.br.controlClick.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final IUserRepository repository;

    @Override
    public UserDto createUser(UserDto dto) {
        User user = UserMapper.toEntity(dto);
        repository.save(user);
        return UserMapper.toDto(user);
    }

    @Override
    public List<UserDto> listUsers() {
        return repository.findAll().stream().map(UserMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDto listUser(Long id) throws Exception {
        return UserMapper.toDto(searchUserById(id));
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) throws Exception {
        User user = searchUserById(id);
        user.setNomeCompleto(userDto.getNomeCompleto());
        user.setNomeUsuario(userDto.getNomeUsuario());
        user.setEmail(userDto.getEmail());
        user.setSenha(userDto.getSenha());
        user.setDataNascimento(userDto.getDataNascimento());
        repository.save(user);
        return UserMapper.toDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    private User searchUserById(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }
}
