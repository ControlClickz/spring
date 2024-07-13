package com.br.controlClick.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FollowDto {
    @Positive
    @NotNull
    private Long id;

    @Positive
    @NotNull
    private Long user;

    @Positive
    @NotNull
    private Long follower;
}
