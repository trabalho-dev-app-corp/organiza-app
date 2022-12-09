package com.devappcorp.organiza.organizaapp.domain.service;

import com.devappcorp.organiza.organizaapp.domain.model.TipoUsuario;

public interface TipoUsuarioService {
    TipoUsuario findByNome(String nome);
}
