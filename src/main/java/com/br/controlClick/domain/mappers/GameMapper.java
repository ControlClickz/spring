package com.br.controlClick.domain.mappers;

import com.br.controlClick.domain.dto.GameDto;
import com.br.controlClick.domain.entities.Game;

public class GameMapper {
    public static Game toEntity(GameDto dto) {
        if(dto == null) {
            return null;
        }

        return Game.builder()
                .id(dto.getId())
                .nome(dto.getNomeJogo())
                .imgPerfil(dto.getImgPerfil())
                .imgBanner(dto.getImgBanner())
                .sinopse(dto.getSinopse())
                .dataLancamento(dto.getDataLancamento())
                .media(dto.getMedia())
                .build();
    }

    public static GameDto toDto(Game entity) {
        if (entity == null) {
            return null;
        }

        return GameDto.builder()
                .id(entity.getId())
                .nomeJogo(entity.getNome())
                .imgPerfil(entity.getImgPerfil())
                .imgBanner(entity.getImgBanner())
                .sinopse(entity.getSinopse())
                .dataLancamento(entity.getDataLancamento())
                .media(entity.getMedia())
                .build();
    }
}
