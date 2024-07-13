package com.br.controlClick.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_FOLLOW", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ID_USER", "ID_FOLLOWER"})
})
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ID_FOLLOWER")
    private User follower;

    public Follow(User follower, User followed) {
        this.user = follower;
        this.follower = followed;
    }
}
