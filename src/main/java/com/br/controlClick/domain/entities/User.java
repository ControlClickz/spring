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
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_USER", uniqueConstraints = {@UniqueConstraint(columnNames = {"EMAIL"}, name = "UK_USER_EMAIL")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 10, max = 100, message = "O nome completo precisa estar entre 10-100 digitos!")
    @Column(name = "NM_COMPLETO")
    private String nomeCompleto;

    @Size(min = 5, max = 15, message = "O nome de usuário precisa estar entre 5-15 digitos!")
    @Column(name = "NM_USUARIO", unique = true)
    private String nomeUsuario;

    @Column(name = "IMG_USUARIO_PERFIL")
    private String imgPerfil;

    @Column(name = "IMG_USUARIO_BANNER")
    private String imgBanner;

    //TODO adicionar verificacao de digito minimo
    @Column(unique = true)
    private String email;

    @Size(min = 6, message = "A senha deve possuir no mínimo 6 digitos!")
    private String senha;

    @Column(name = "DT_NASCIMENTO")
    private LocalDate dataNascimento;

    @Column(name = "TX_BIOGRAFIA")
    private String bio;

    @ManyToMany
    private Set<Game> games = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Review> reviews = new HashSet<>();

    @OneToMany(mappedBy = "follower")
    private Set<Follow> followers = new HashSet<>();

    @OneToMany(mappedBy = "followed")
    private Set<Follow> following = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "ID_ROLE")
    private Role role;

    public void addFavoriteGame(Game game) {
        this.games.add(game);
        game.getUsers().add(this);
    }

    public void removeFavoriteGame(Game game) {
        this.games.remove(game);
        game.getUsers().remove(this);
    }

    public void followUser(User userToFollow) {
        Follow follow = new Follow(this, userToFollow);
        this.following.add(follow);
        userToFollow.getFollowers().add(follow);
    }

    public void unfollowUser(User userToUnfollow) {
        Follow follow = new Follow(this, userToUnfollow);
        this.following.remove(follow);
        userToUnfollow.getFollowers().remove(follow);
    }
}
