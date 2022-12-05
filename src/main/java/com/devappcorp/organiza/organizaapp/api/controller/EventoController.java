package com.devappcorp.organiza.organizaapp.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devappcorp.organiza.organizaapp.domain.model.Evento;
import com.devappcorp.organiza.organizaapp.domain.repository.EventoRepository;

@RestController
@RequestMapping("/eventos")
public class EventoController {
    
    @Autowired
    private EventoRepository eventoRepository;

    @GetMapping
    public List<Evento> listar() {
        return eventoRepository.findAll();
    }
}
