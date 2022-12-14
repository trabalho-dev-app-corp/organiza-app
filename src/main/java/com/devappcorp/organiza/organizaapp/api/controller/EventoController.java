package com.devappcorp.organiza.organizaapp.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/{eventoId}")
    public ResponseEntity<Evento> buscar(@PathVariable("eventoId") Long eventoId) {
        Evento evento = eventoRepository.findById(eventoId).get();

        if (evento != null) {
            return ResponseEntity.status(HttpStatus.OK).body(evento);
        } 

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Evento> adicionar(@RequestBody Evento evento) {
        eventoRepository.save(evento);
        return ResponseEntity.status(HttpStatus.CREATED).body(evento);
    }

    @PutMapping("/{eventoId}")
    public ResponseEntity<Evento> editar(@PathVariable("eventoId") Long eventoId, @RequestBody Evento eventoInfo) {
        Evento evento = eventoRepository.findById(eventoId).get();

        evento.setNome(eventoInfo.getNome());
        evento.setSigla(eventoInfo.getSigla());
        evento.setDescricao(eventoInfo.getDescricao());
        final Evento eventoAtualizado = eventoRepository.save(evento);
        return ResponseEntity.ok(eventoAtualizado);
    }

    @DeleteMapping("/{eventoId}")
    public Map<String, Boolean> deletar(@PathVariable("eventoId") Long eventoId) {
        Evento evento = eventoRepository.findById(eventoId).get();

        eventoRepository.delete(evento);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deletado", Boolean.TRUE);
        return response;
    }    
}
