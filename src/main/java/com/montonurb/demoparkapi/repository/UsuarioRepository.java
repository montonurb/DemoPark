package com.montonurb.demoparkapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.montonurb.demoparkapi.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}
