package com.br.controlClick.services;

import com.br.controlClick.domain.dto.ReviewDto;
import com.br.controlClick.domain.entities.Game;
import com.br.controlClick.domain.entities.Review;
import com.br.controlClick.domain.entities.User;
import com.br.controlClick.domain.mappers.ReviewMapper;
import com.br.controlClick.exceptions.AlreadyExistsException;
import com.br.controlClick.exceptions.NotFoundException;
import com.br.controlClick.repositories.IReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService implements IReviewService {
    private final IReviewRepository repository;

    private final GameService gameService;
    private final UserService userService;

    @Override
    @Transactional
    public ReviewDto createReview(ReviewDto dto) {
        if (repository.existsByUserIdAndGameIdAndComentarioAndNota(
                dto.getUserId(), dto.getGameId(), dto.getComentario(), dto.getNota())) {
            throw new AlreadyExistsException("Já existe uma review para este usuário e jogo com os mesmos detalhes!");
        }

        User user = userService.searchUserById(dto.getUserId());
        Game game = gameService.searchGameById(dto.getGameId());

        Review review = ReviewMapper.toEntity(dto);
        review.setUser(user);
        review.setGame(game);

        repository.save(review);

        return ReviewMapper.toDto(review);
    }

    @Override
    public List<ReviewDto> listReviews() {
        return repository.findAll().stream().map(ReviewMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ReviewDto getReview(Long id) {
        return ReviewMapper.toDto(searchReviewById(id));
    }

    @Override
    @Transactional
    public ReviewDto updateReview(Long id, ReviewDto dto) {
        Review review = searchReviewById(id);

        if(dto.getNota() != null) {
            review.setNota(dto.getNota());
        }
        if(dto.getComentario() != null) {
            review.setComentario(dto.getComentario());
        }

        repository.save(review);

        return ReviewMapper.toDto(review);
    }

    @Override
    @Transactional
    public void deleteReview(Long id) {
        Review review = searchReviewById(id);
        repository.deleteById(id);
    }

    @Override
    public void addUserToReview(Long id, Long userId) {
        Review review = searchReviewById(id);

        User user = userService.searchUserById(userId);

        review.addUser(user);
        repository.save(review);
    }

    @Override
    public void addGameToReview(Long id, Long gameId) {
        Review review = searchReviewById(id);

        Game game = gameService.searchGameById(gameId);

        review.addGame(game);
        repository.save(review);
    }

    public Review searchReviewById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Review não encontrada!"));
    }
}
