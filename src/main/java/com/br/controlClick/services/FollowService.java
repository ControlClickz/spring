package com.br.controlClick.services;

import com.br.controlClick.domain.dto.FollowDto;
import com.br.controlClick.domain.entities.Follow;
import com.br.controlClick.domain.entities.User;
import com.br.controlClick.domain.mappers.FollowMapper;
import com.br.controlClick.domain.mappers.UserMapper;
import com.br.controlClick.exceptions.NotFoundException;
import com.br.controlClick.repositories.IFollowRepository;
import com.br.controlClick.repositories.IUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FollowService implements IFollowService {
    private final IFollowRepository repository;
    private final IUserRepository userRepository;

    private final UserService userService;

    @Override
    @Transactional
    public FollowDto createFollow(FollowDto dto) {
        User user = userService.searchUserById(dto.getUser());
        User following = userService.searchUserById(dto.getFollower());
        user.setFollows(user.getFollows() + 1);
        following.setFollowers(following.getFollowers() + 1);

        Follow follow = FollowMapper.toEntity(dto, user, following);

        repository.save(follow);
        userRepository.save(user);
        userRepository.save(following);

        return FollowMapper.toDto(follow);
    }

    @Override
    public List<FollowDto> listFollows() {
        return repository.findAll().stream().map(FollowMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<FollowDto> listFollows(Long userId) {
        return repository.findAllByUserId(userId).stream().map(FollowMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<FollowDto> listFollowers(Long userId) {
        return repository.findAllByFollowerId(userId).stream().map(FollowMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public FollowDto getFollow(Long id) {
        return FollowMapper.toDto(searchFollowById(id));
    }

    @Override
    @Transactional
    public void unfollow(Long id) {
        Follow follow = searchFollowById(id);
        User user = userService.searchUserById(follow.getUser().getId());
        User following = userService.searchUserById(follow.getFollower().getId());

        user.setFollows(user.getFollows() - 1);
        following.setFollowers(following.getFollowers() - 1);

        userRepository.save(user);
        userRepository.save(following);

        repository.deleteById(id);
    }

    public Follow searchFollowById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Follow não encontrado!"));
    }
}
