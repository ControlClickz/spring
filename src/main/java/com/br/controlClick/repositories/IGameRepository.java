package com.br.controlClick.repositories;

import com.br.controlClick.domain.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGameRepository extends JpaRepository<Game, Long> {
    boolean existsByNome(String nome);
}
