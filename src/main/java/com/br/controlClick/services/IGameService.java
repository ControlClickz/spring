package com.br.controlClick.services;

import com.br.controlClick.domain.dto.GameDto;

import java.util.List;

public interface IGameService {
    GameDto createGame(GameDto dto);
    List<GameDto> listGames();
    GameDto getGame(Long id);
    GameDto updateGame(Long id, GameDto dto);
    void deleteGame(Long id);
}
