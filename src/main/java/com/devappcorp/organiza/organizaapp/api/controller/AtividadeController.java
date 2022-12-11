package com.devappcorp.organiza.organizaapp.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devappcorp.organiza.organizaapp.domain.model.Atividade;
import com.devappcorp.organiza.organizaapp.domain.repository.AtividadeRepository;

@RestController
@RequestMapping("/atividades")
public class AtividadeController {

    @Autowired
    private AtividadeRepository AtividadeRepository;

    @GetMapping
    public List<Atividade> listar() {
        return AtividadeRepository.findAll();
    }
    
}
