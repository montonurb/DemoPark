package com.montonurb.demoparkapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.montonurb.demoparkapi.dto.UsuarioCreateDTO;
import com.montonurb.demoparkapi.dto.UsuarioResponseDTO;
import com.montonurb.demoparkapi.dto.UsuarioSenhaDTO;
import com.montonurb.demoparkapi.dto.mapper.UsuarioMapper;
import com.montonurb.demoparkapi.entity.Usuario;
import com.montonurb.demoparkapi.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@RequestBody UsuarioCreateDTO usuarioCreateDTO) {
        Usuario user = usuarioService.create(UsuarioMapper.toUsuario(usuarioCreateDTO));

        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id) {
        Usuario user = usuarioService.buscarPorId(id);

        if(user.getId() != null) {
            return ResponseEntity.ok(UsuarioMapper.toDto(user));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(UsuarioMapper.toDto(user));
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> alterarSenha(@PathVariable Long id, @RequestBody UsuarioSenhaDTO usuario) {
        Usuario user = usuarioService.alterarSenha(id, usuario.getSenhaAtual(), usuario.getSenhaNova(), usuario.getSenhaConfirma());

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> buscarUsuarios() {
        List<Usuario> users = usuarioService.buscarTodos();
        return ResponseEntity.ok(UsuarioMapper.toListDto(users));
    }
}
