package com.br.controlClick.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@JsonIgnoreProperties(ignoreUnknown = true)
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

    private String imgPerfil;

    private String imgBanner;

    @NotEmpty
    @NotBlank
    @Email
    private String email;

    @JsonIgnore
    private String senha;

    @NotEmpty
    @NotBlank
    @NotNull
    private LocalDate dataNascimento;

    @JsonProperty
    public String getSenha() {
        return senha;
    }

    @JsonProperty
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
