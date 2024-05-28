package com.montonurb.demoparkapi.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.montonurb.demoparkapi.entity.Usuario;
import com.montonurb.demoparkapi.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario create(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Usuário não encontrado!")
        );
    }

    @Transactional
    public Usuario alterarSenha(Long id, String senhaAtual, String senhaNova, String senhaConfirma) {

        if (!senhaNova.equals(senhaConfirma)) {
            throw new RuntimeException("Nova senha não confere com a confirmação !");
        }

        Usuario user = buscarPorId(id);
        if(!user.getPassword().equals(senhaAtual)) {
            throw new RuntimeException("Senha atual incorreta !");
        }
        
        user.setPassword(senhaNova);
        return user;
    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }
}
