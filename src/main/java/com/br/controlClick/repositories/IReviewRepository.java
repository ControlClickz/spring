package com.br.controlClick.repositories;

import com.br.controlClick.domain.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByUserIdAndGameIdAndComentarioAndNota(Long userId, Long gameId, String comentario, double nota);
}
