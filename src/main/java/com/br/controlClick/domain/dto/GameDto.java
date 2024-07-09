package com.br.controlClick.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class GameDto {
    @Positive
    @NotNull
    private Long id;

    @NotEmpty
    @NotBlank
    private String nomeJogo;

    private String imgPerfil;

    private String imgBanner;

    @NotEmpty
    @NotBlank
    private String sinopse;

    @NotEmpty
    @NotBlank
    private LocalDate dataLancamento;

    @PositiveOrZero
    private double media;
}
