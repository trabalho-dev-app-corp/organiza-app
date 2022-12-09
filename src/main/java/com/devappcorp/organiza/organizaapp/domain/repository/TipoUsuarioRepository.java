package com.devappcorp.organiza.organizaapp.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devappcorp.organiza.organizaapp.domain.model.TipoUsuario;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long>{
    TipoUsuario findFirstByNome(String nome);
}
