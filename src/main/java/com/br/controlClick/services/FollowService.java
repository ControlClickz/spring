package com.br.controlClick.services;

import com.br.controlClick.domain.dto.FollowDto;
import com.br.controlClick.domain.entities.Follow;
import com.br.controlClick.domain.entities.User;
import com.br.controlClick.domain.mappers.FollowMapper;
import com.br.controlClick.repositories.IFollowRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService implements IFollowService {
    private final IFollowRepository repository;

    private final UserService userService;

    @Override
    @Transactional
    public FollowDto createFollow(FollowDto dto) {
        User follower = userService.searchUserById(dto.getFollowerId());
        User followed = userService.searchUserById(dto.getFollowingId());

        Follow follow = FollowMapper.toEntity(dto, follower, followed);

        repository.save(follow);

        return FollowMapper.toDto(follow);
    }

    @Override
    public List<FollowDto> listFollows() {
        return null;
    }

    @Override
    public FollowDto getFollow(Long id) {
        return null;
    }

    @Override
    @Transactional
    public FollowDto updateFollow(Long id, FollowDto dto) {
        return null;
    }

    @Override
    @Transactional
    public void deleteFollow(Long id) {

    }
}
