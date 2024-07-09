package com.br.controlClick.domain.mappers;

import com.br.controlClick.domain.dto.ReviewDto;
import com.br.controlClick.domain.entities.Review;

public class ReviewMapper {
    public static Review toEntity(ReviewDto dto) {
        if (dto == null) {
            return null;
        }

        return Review.builder()
                .id(dto.getId())
                .comentario(dto.getComentario())
                .nota(dto.getNota())
                .build();
    }

    public static ReviewDto toDto(Review entity) {
        if (entity == null) {
            return null;
        }

        return ReviewDto.builder()
                .id(entity.getId())
                .userId(entity.getUser().getId())
                .gameId(entity.getGame().getId())
                .comentario(entity.getComentario())
                .nota(entity.getNota())
                .build();
    }
}
