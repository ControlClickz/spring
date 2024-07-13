package com.br.controlClick.services;

import com.br.controlClick.domain.dto.FollowDto;
import com.br.controlClick.domain.entities.Follow;

import java.util.List;

public interface IFollowService {
    FollowDto createFollow(FollowDto dto);
    List<FollowDto> listFollows();
    List<FollowDto> listFollows(Long userId);
    FollowDto getFollow(Long id);
    void unfollow(Long id);
}
