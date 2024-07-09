package com.br.controlClick.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_REVIEW", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ID_USER", "ID_JOGO", "TX_COMENTARIO", "VL_NOTA"})
})
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ID_JOGO")
    private Game game;

    @Column(name = "TX_COMENTARIO")
    private String comentario;

    @Column(name = "VL_NOTA")
    private Double nota;

    public void addUser(User user) {
        this.user = user;
        user.getReviews().add(this);
    }

    public void addGame(Game game) {
        this.game = game;
        game.getReviews().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(user, review.user) &&
                Objects.equals(game, review.game) &&
                Objects.equals(comentario, review.comentario) &&
                Objects.equals(nota, review.nota);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, game, comentario, nota);
    }
}
