package com.br.controlClick.services;

import com.br.controlClick.domain.dto.FollowDto;
import com.br.controlClick.domain.entities.Follow;

import java.util.List;

public interface IFollowService {
    FollowDto createFollow(FollowDto dto);
    List<FollowDto> listFollows();
    FollowDto getFollow(Long id);
    FollowDto updateFollow(Long id, FollowDto dto);
    void deleteFollow(Long id);
}
