package com.devappcorp.organiza.organizaapp.domain.repository;

import com.devappcorp.organiza.organizaapp.domain.model.Usuario;

public interface UsuarioRepositoryQueries {
    public Usuario findByEmail(String email);
}
