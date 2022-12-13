package com.devappcorp.organiza.organizaapp.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devappcorp.organiza.organizaapp.domain.model.Edicao;
//import com.devappcorp.organiza.organizaapp.domain.model.Edicao;
import com.devappcorp.organiza.organizaapp.domain.repository.EdicaoRepository;
//import com.devappcorp.organiza.organizaapp.domain.repository.EdicaoRepository;

@RestController
@RequestMapping("/edicoes")

public class EdicaoController {
    @Autowired
    private EdicaoRepository edicaoRepository;

    @GetMapping
    public List<Edicao> listar() {
        return edicaoRepository.findAll();
    }

    @GetMapping("/{edicaoId}")
    public ResponseEntity<Edicao> buscar(@PathVariable("edicaoId") Long edicaoId) {
        Edicao edicao = edicaoRepository.findById(edicaoId).get();

        if (edicao != null) {
            return ResponseEntity.status(HttpStatus.OK).body(edicao);
        } 

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<Edicao> adicionar(@RequestBody Edicao edicao) {
        edicaoRepository.save(edicao);
        return ResponseEntity.status(HttpStatus.CREATED).body(edicao);
    }
}
