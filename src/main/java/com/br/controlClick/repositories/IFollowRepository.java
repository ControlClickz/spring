package com.br.controlClick.repositories;

import com.br.controlClick.domain.entities.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFollowRepository extends JpaRepository<Follow, Long> {
}
