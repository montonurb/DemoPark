package com.montonurb.demoparkapi.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import com.montonurb.demoparkapi.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false, length = 100)
    private String username;
    @Column(name = "password", nullable = false, length = 200)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 25)
    private Role role;

    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    private String criadoPor;
    private String modificadoPor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (this == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public String toString() {
        return "Usuario{id=" + id + "}";
    }
}
