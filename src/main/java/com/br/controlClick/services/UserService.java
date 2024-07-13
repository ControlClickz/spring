package com.br.controlClick.services;

import com.br.controlClick.domain.dto.GameDto;
import com.br.controlClick.domain.dto.UserDto;
import com.br.controlClick.domain.entities.Game;
import com.br.controlClick.domain.entities.Role;
import com.br.controlClick.domain.entities.User;
import com.br.controlClick.domain.mappers.GameMapper;
import com.br.controlClick.domain.mappers.UserMapper;
import com.br.controlClick.exceptions.AlreadyExistsException;
import com.br.controlClick.exceptions.NotFoundException;
import com.br.controlClick.repositories.IRoleRepository;
import com.br.controlClick.repositories.IUserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final IUserRepository repository;
    private final IRoleRepository roleRepository;

    private final GameService gameService;

    @PersistenceContext
    private EntityManager entityManager;

    private Role createRoleIfNotExists(String roleName) {
        Role role = roleRepository.findByNome(roleName);

        if(role == null) {
            role = new Role();
            role.setNome(roleName);
            role = roleRepository.save(role);
        }

        return role;
    }

    @Override
    @Transactional
    public UserDto createUser(UserDto dto) {
        if(isEmailExists(dto.getEmail())) {
            throw new AlreadyExistsException("O email informado já possui um cadastro!");
        } else if(isUserNameExists(dto.getNomeUsuario())) {
            throw new AlreadyExistsException("Nome de usuário já cadastrado!");
        }

        User user = UserMapper.toEntity(dto);
        Role userRole = createRoleIfNotExists("user");
        user.setRole(userRole);
        user.setImgPerfil(null);
        user.setImgBanner(null);
        user.setBio(null);
        user.setFollows(0L);
        user.setFollowers(0L);

        repository.save(user);

        return UserMapper.toDto(user);
    }

    @Override
    public List<UserDto> listUsers() {
        return repository.findAll().stream().map(UserMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getUser(Long id) {
        return UserMapper.toDto(searchUserById(id));
    }

    @Override
    @Transactional
    public UserDto updateUser(Long id, UserDto userDto) {
        User user = searchUserById(id);

        if (!user.getEmail().equals(userDto.getEmail()) &&
                isEmailExists(userDto.getEmail())
        ) {
            throw new AlreadyExistsException("O email informado já possui um cadastro!");
        } else if (!user.getNomeUsuario().equals(userDto.getNomeUsuario()) &&
                isUserNameExists(userDto.getNomeUsuario())) {
            throw new AlreadyExistsException("Nome de usuário já cadastrado!");
        }

        if (userDto.getNomeCompleto() != null) {
            user.setNomeCompleto(userDto.getNomeCompleto());
        }
        if(userDto.getNomeUsuario() != null) {
            user.setNomeUsuario(userDto.getNomeUsuario());
        }
        if (userDto.getSenha() != null) {
            user.setSenha(userDto.getSenha());
        }
        if (userDto.getImgPerfil() != null) {
            user.setImgPerfil(userDto.getImgPerfil());
        }
        if (userDto.getImgBanner() != null) {
            user.setImgBanner(userDto.getImgBanner());
        }
        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }
        if (userDto.getDataNascimento() != null) {
            user.setDataNascimento(userDto.getDataNascimento());
        }
        if (userDto.getBio() != null) {
            user.setBio(userDto.getBio());
        }

        repository.save(user);

        return UserMapper.toDto(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User user = searchUserById(id);
        repository.deleteById(id);
    }

    @Transactional
    public void favoriteGame(Long userId, Long gameId) {
        Game game = gameService.searchGameById(gameId);
        User user = searchUserById(userId);

        if (!user.getGames().contains(game)) {
            user.addFavoriteGame(game);
            repository.save(user);
        }
    }

    @Transactional
    public void unfavoriteGame(Long userId, Long gameId) {
        Game game = gameService.searchGameById(gameId);
        User user = searchUserById(userId);

        if (user.getGames().contains(game)) {
            user.removeFavoriteGame(game);
            entityManager.merge(user);
        }
    }

    @Override
    public List<GameDto> listFavoriteGames(Long id) {
        User user = searchUserById(id);

        return user.getGames().stream().map(GameMapper::toDto).collect(Collectors.toList());
    }

    public User searchUserById(Long id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
    }

    private boolean isEmailExists(String email) {
        return repository.existsByEmail(email);
    }

    private boolean isUserNameExists(String username) {
        return repository.existsByNomeUsuario(username);
    }
}
