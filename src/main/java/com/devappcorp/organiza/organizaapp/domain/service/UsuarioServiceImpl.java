package com.devappcorp.organiza.organizaapp.domain.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.devappcorp.organiza.organizaapp.domain.model.TipoUsuario;
import com.devappcorp.organiza.organizaapp.domain.model.Usuario;
import com.devappcorp.organiza.organizaapp.domain.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService{
    @Autowired
    private TipoUsuarioService tipoUsuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByLogin(username);
        if(usuario == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(usuario.getLogin(), usuario.getSenha(), getAuthority(usuario));
    }

    private Set<SimpleGrantedAuthority> getAuthority(Usuario usuario) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        String tipoUsuario = usuario.getTipoUsuario().getNome();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + tipoUsuario));
        return authorities;
    }

    public List<Usuario> findAll() {
        List<Usuario> list = new ArrayList<>();
        usuarioRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Usuario findOne(String username) {
        return usuarioRepository.findByLogin(username);
    }

    @Override
    public Usuario save(Usuario usuario) {
        usuario.setSenha(bcryptEncoder.encode(usuario.getSenha()));

        TipoUsuario tipoUsuario = tipoUsuarioService.findByNome("USUARIO");
        usuario.setTipoUsuario(tipoUsuario);
        return usuarioRepository.save(usuario);
    }
}
