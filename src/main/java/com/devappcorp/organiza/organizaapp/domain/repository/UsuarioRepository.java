package com.devappcorp.organiza.organizaapp.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devappcorp.organiza.organizaapp.domain.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryQueries {
    Optional<Usuario> findByUsername(String username);
}
