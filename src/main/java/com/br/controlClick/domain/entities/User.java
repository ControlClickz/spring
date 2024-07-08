package com.br.controlClick.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 10, max = 100, message = "O nome completo precisa estar entre 10-100 caracteres!")
    @Column(name = "NM_COMPLETO")
    private String nomeCompleto;

    @Size(min = 5, max = 15, message = "O nome completo precisa estar entre 5-15 caracteres!")
    @Column(name = "NM_USUARIO")
    private String nomeUsuario;

    //TODO adiciona verificacao de digito minimo
    @Size(max = 15, message = "O nome completo precisa estar entre 5-15 caracteres!")
    private String email;

    @Size(min = 6, message = "O nome completo precisa estar entre 6 caracteres!")
    private String senha;

    @Column(name = "DT_NASCIMENTO")
    private LocalDate dataNascimento;
}
