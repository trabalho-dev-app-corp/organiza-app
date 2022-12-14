package com.devappcorp.organiza.organizaapp.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devappcorp.organiza.organizaapp.domain.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService{

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(String.format("Usuário %s não foi encontrado!", username)));
    }
    
}
