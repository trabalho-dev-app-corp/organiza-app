package com.devappcorp.organiza.organizaapp.domain.service;

import java.util.List;

import com.devappcorp.organiza.organizaapp.domain.model.Usuario;

public interface UsuarioService {
    Usuario save(Usuario usuario);
    List<Usuario> findAll();
    Usuario findOne(String username);
}
