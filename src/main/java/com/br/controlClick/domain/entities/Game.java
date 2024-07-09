package com.br.controlClick.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_JOGO")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, message = "O nome do jogo deve possuir pelo menos 1 digito!")
    @Column(name = "NM_JOGO")
    private String nome;

    @Size(min = 1, message = "O nome do jogo deve possuir pelo menos 1 digito!")
    @Column(name = "IMG_JOGO_PERFIL")
    private String imgPerfil;

    @Size(min = 1, message = "O nome do jogo deve possuir pelo menos 1 digito!")
    @Column(name = "IMG_JOGO_BANNER")
    private String imgBanner;

    @Size(min = 1, message = "O nome do jogo deve possuir pelo menos 1 digito!")
    @Column(name = "TX_SINOPSE")
    private String sinopse;

    @Column(name = "DT_LANCAMENTO")
    private LocalDate dataLancamento;

    @Column(name = "VL_MEDIA")
    private double media;

    @ManyToMany
    @JoinTable(name = "TB_FAVORITE",
            joinColumns = @JoinColumn(name = "ID_GAME"),
            inverseJoinColumns = @JoinColumn(name = "ID_USER"))
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "game")
    private Set<Review> reviews = new HashSet<>();
}
