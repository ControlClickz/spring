package com.br.controlClick.domain.mappers;

import com.br.controlClick.domain.dto.FollowDto;
import com.br.controlClick.domain.entities.Follow;
import com.br.controlClick.domain.entities.User;

public class FollowMapper {
    public static Follow toEntity(FollowDto dto, User user, User follower) {
        if (dto == null) {
            return null;
        }

        return Follow.builder()
                .id(dto.getId())
                .user(user)
                .follower(follower)
                .build();
    }

    public static FollowDto toDto(Follow entity) {
        if (entity == null) {
            return null;
        }

        return FollowDto.builder()
                .id(entity.getId())
                .user(entity.getUser().getId())
                .follower(entity.getFollower().getId())
                .build();
    }
}
