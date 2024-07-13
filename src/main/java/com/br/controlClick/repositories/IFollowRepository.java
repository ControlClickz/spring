package com.br.controlClick.repositories;

import com.br.controlClick.domain.entities.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IFollowRepository extends JpaRepository<Follow, Long> {
    @Query("SELECT f FROM Follow f WHERE f.user.id = :userId")
    List<Follow> findAllByUserId(@Param("userId") Long userId);
    @Query("SELECT f FROM Follow f WHERE f.follower.id = :followerId")
    List<Follow> findAllByFollowerId(@Param("followerId") Long followerId);
}
