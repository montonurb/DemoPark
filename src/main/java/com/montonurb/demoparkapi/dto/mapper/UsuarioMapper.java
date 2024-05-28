package com.montonurb.demoparkapi.dto.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.montonurb.demoparkapi.dto.UsuarioCreateDTO;
import com.montonurb.demoparkapi.dto.UsuarioResponseDTO;
import com.montonurb.demoparkapi.entity.Usuario;

public class UsuarioMapper {
    public static Usuario toUsuario(UsuarioCreateDTO usuarioCreateDTO) {
        return new ModelMapper().map(usuarioCreateDTO, Usuario.class);
    }

    public static UsuarioResponseDTO toDto(Usuario usuario) {
        String role = usuario.getRole().name().substring("ROLE_".length());
        PropertyMap<Usuario, UsuarioResponseDTO> props = new PropertyMap<Usuario,UsuarioResponseDTO>() {
            @Override
            protected void configure() {
                map().setRole(role);
            }
        };

        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        return mapper.map(usuario, UsuarioResponseDTO.class);
    }

    public static List<UsuarioResponseDTO> toListDto(List<Usuario> usuarios) {
        return usuarios.stream().map(user -> toDto(user)).collect(Collectors.toList());
    }
}
