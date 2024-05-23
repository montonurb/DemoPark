package com.montonurb.demoparkapi.service;

import org.springframework.stereotype.Service;
import com.montonurb.demoparkapi.entity.Usuario;
import com.montonurb.demoparkapi.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario create(Usuario usuario) {
        System.out.println("NomeS: " + usuario.getUsername());
        System.out.println("SenhaS: " + usuario.getPassword());
        return usuarioRepository.save(usuario);
    }
}
