package com.devappcorp.organiza.organizaapp.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.devappcorp.organiza.organizaapp.domain.model.Usuario;
import com.devappcorp.organiza.organizaapp.domain.repository.UsuarioRepository;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final PasswordEncoder encoder;

    public UsuarioController(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/register")
    public ResponseEntity<Usuario> adicionar(@RequestBody Usuario usuario) {
        Usuario usuarioEncriptado = usuario;
        usuarioEncriptado.setPassword(encoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuarioEncriptado);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioEncriptado);
    }

    @GetMapping
    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }
}
