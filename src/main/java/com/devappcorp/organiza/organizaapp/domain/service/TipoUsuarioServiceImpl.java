package com.devappcorp.organiza.organizaapp.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devappcorp.organiza.organizaapp.domain.model.TipoUsuario;
import com.devappcorp.organiza.organizaapp.domain.repository.TipoUsuarioRepository;

@Service
public class TipoUsuarioServiceImpl implements TipoUsuarioService{
    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Override
    public TipoUsuario findByNome(String nome) {
        TipoUsuario tipoUsuario = tipoUsuarioRepository.findFirstByNome(nome);
        return tipoUsuario;
    }
}
