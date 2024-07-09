package com.br.controlClick.domain.mappers;

import com.br.controlClick.domain.dto.FollowDto;
import com.br.controlClick.domain.entities.Follow;
import com.br.controlClick.domain.entities.User;

public class FollowMapper {
    public static Follow toEntity(FollowDto dto, User follower, User followed) {
        if (dto == null) {
            return null;
        }

        return Follow.builder()
                .id(dto.getId())
                .follower(follower)
                .followed(followed)
                .build();
    }

    public static FollowDto toDto(Follow entity) {
        if (entity == null) {
            return null;
        }

        return FollowDto.builder()
                .id(entity.getId())
                .followerId(entity.getFollower().getId())
                .followingId(entity.getFollowed().getId())
                .build();
    }
}
