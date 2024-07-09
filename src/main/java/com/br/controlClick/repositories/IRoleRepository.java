package com.br.controlClick.repositories;

import com.br.controlClick.domain.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Role findByNome(String role);
}
