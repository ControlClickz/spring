package com.br.controlClick.services;

import com.br.controlClick.domain.dto.GameDto;
import com.br.controlClick.domain.entities.Game;
import com.br.controlClick.domain.entities.User;
import com.br.controlClick.domain.mappers.GameMapper;
import com.br.controlClick.exceptions.AlreadyExistsException;
import com.br.controlClick.exceptions.NotFoundException;
import com.br.controlClick.repositories.IGameRepository;
import com.br.controlClick.repositories.IUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameService implements IGameService{
    private final IGameRepository repository;
    private final IUserRepository userRepository;

    private final UserService userService;

    @Override
    @Transactional
    public GameDto createGame(GameDto dto) {
        if(repository.existsByNome(dto.getNomeJogo())) {
            throw new AlreadyExistsException("O nome do jogo já existe!");
        }

        Game game = GameMapper.toEntity(dto);
        game.setMedia(0);
        game.setReviews(null);
        game.setUsers(null);

        repository.save(game);

        return GameMapper.toDto(game);
    }

    @Override
    public List<GameDto> listGames() {
        return repository.findAll().stream().map(GameMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public GameDto getGame(Long id) {
        return GameMapper.toDto(searchGameById(id));
    }

    @Override
    @Transactional
    public GameDto updateGame(Long id, GameDto dto) {
        Game game = searchGameById(id);

        if (!game.getNome().equals(dto.getNomeJogo()) &&
                repository.existsByNome(dto.getNomeJogo())) {
            throw new AlreadyExistsException("O nome do jogo já existe!");
        }

        game.setNome(dto.getNomeJogo());
        game.setSinopse(dto.getSinopse());
        game.setDataLancamento(dto.getDataLancamento());
        game.setImgPerfil(dto.getImgPerfil());
        game.setImgBanner(dto.getImgBanner());

        repository.save(game);

        return GameMapper.toDto(game);
    }

    @Override
    @Transactional
    public void deleteGame(Long id) {
        Game game = searchGameById(id);
        repository.deleteById(id);
    }

    @Transactional
    public void favoriteGame(Long userId, Long gameId) {
        Game game = searchGameById(gameId);
        User user = userService.searchUserById(userId);

        user.addFavoriteGame(game);
        userRepository.save(user);
    }

    @Transactional
    public void unfavoriteGame(Long userId, Long gameId) {
        Game game = searchGameById(gameId);
        User user = userService.searchUserById(userId);

        user.removeFavoriteGame(game);
        userRepository.save(user);
    }

    public Game searchGameById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Jogo não encontrado!"));
    }
}
