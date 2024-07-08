package com.br.controlClick.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class UserDto {
    @Positive
    @NotNull
    private Long id;

    @NotEmpty
    @NotBlank
    private String nomeCompleto;

    @NotEmpty
    @NotBlank
    private String nomeUsuario;

    @NotEmpty
    @NotBlank
    @Email
    private String email;

    @NotEmpty
    @NotBlank
    private String senha;

    @NotEmpty
    @NotBlank
    @NotNull
    private LocalDate dataNascimento;
}
