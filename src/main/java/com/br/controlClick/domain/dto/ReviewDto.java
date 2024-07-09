package com.br.controlClick.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewDto {
    @Positive
    @NotNull
    private Long id;

    @Positive
    @NotNull
    private Long userId;

    @Positive
    @NotNull
    private Long gameId;

    @NotEmpty
    @NotBlank
    private String comentario;

    @PositiveOrZero
    private Double nota;
}
