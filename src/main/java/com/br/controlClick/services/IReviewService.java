package com.br.controlClick.services;

import com.br.controlClick.domain.dto.ReviewDto;

import java.util.List;

public interface IReviewService {
    ReviewDto createReview(ReviewDto dto);
    List<ReviewDto> listReviews();
    ReviewDto getReview(Long id);
    ReviewDto updateReview(Long id, ReviewDto dto);
    void deleteReview(Long id);
    void addUserToReview(Long id, Long userId);
    void addGameToReview(Long id, Long gameId);
}
