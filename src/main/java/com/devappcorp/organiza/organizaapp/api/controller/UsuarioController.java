package com.devappcorp.organiza.organizaapp.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devappcorp.organiza.organizaapp.config.TokenProvider;
import com.devappcorp.organiza.organizaapp.domain.model.AuthToken;
import com.devappcorp.organiza.organizaapp.domain.model.LoginUsuario;
import com.devappcorp.organiza.organizaapp.domain.model.Usuario;
import com.devappcorp.organiza.organizaapp.domain.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> generateToken(@RequestBody LoginUsuario loginUsuario) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUsuario.getUsername(),
                        loginUsuario.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }

    @PostMapping("/register")
    public Usuario saveUser(@RequestBody Usuario usuario){
        return usuarioService.save(usuario);
    }
}
