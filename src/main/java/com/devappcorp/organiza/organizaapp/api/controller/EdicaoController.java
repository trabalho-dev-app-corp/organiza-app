package com.devappcorp.organiza.organizaapp.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.devappcorp.organiza.organizaapp.domain.DTO.OrganizadorDTO;
import com.devappcorp.organiza.organizaapp.domain.model.Evento;
import com.devappcorp.organiza.organizaapp.domain.model.Usuario;
import com.devappcorp.organiza.organizaapp.domain.repository.EventoRepository;
import com.devappcorp.organiza.organizaapp.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devappcorp.organiza.organizaapp.domain.model.Edicao;

import com.devappcorp.organiza.organizaapp.domain.repository.EdicaoRepository;


@RestController
@RequestMapping("/edicoes")

public class EdicaoController {
    @Autowired
    private EdicaoRepository edicaoRepository;
    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

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
    @Transactional
    public ResponseEntity<Edicao> adicionar(@RequestBody Edicao edicao) {
        //edicao.setEvento(eventoRepository.findById());
        edicaoRepository.save(edicao);
        return ResponseEntity.status(HttpStatus.CREATED).body(edicao);
    }

    @PutMapping("/{edicaoId}")
    @Transactional
    public ResponseEntity<Edicao> editar(@RequestBody Edicao edicaoAtualizada, @PathVariable Long edicaoId){
        Edicao edicao = edicaoRepository.findById(edicaoId).get();

        edicao.setNumero(edicaoAtualizada.getNumero());
        edicao.setAno(edicaoAtualizada.getAno());
        edicao.setDataInicial(edicaoAtualizada.getDataInicial());
        edicao.setDataFinal(edicaoAtualizada.getDataFinal());
        edicao.setCidade(edicaoAtualizada.getCidade());
        return ResponseEntity.ok(edicaoRepository.save(edicao));
    }

    @DeleteMapping("/{edicaoId}")
    public Map<String, Boolean> deletar(@PathVariable("edicaoId") Long edicaoId) {
        Edicao edicao = edicaoRepository.findById(edicaoId).get();

        edicaoRepository.delete(edicao);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deletado", Boolean.TRUE);
        return response;
    }

    @GetMapping("/{edicaoId}/evento")
    public ResponseEntity<Evento> retornarEvento(@PathVariable("edicaoId") Long edicaoId){
        Edicao edicao = edicaoRepository.findById(edicaoId).get();
        return ResponseEntity.ok(edicao.getEvento());
    }

    @GetMapping("/{edicaoId}/organizador")
    public ResponseEntity<Usuario> retornarOrganizador(@PathVariable("edicaoId") Long edicaoId){
        Edicao edicao = edicaoRepository.findById(edicaoId).get();
        return ResponseEntity.ok(edicao.getOrganizador());
    }


    @PostMapping("/{edicaoId}/organizador")
    public ResponseEntity<Usuario> autorizarOrganizador(@PathVariable Long edicaoId, @RequestBody OrganizadorDTO organizadorDTO){
        Usuario organizador = usuarioRepository.findById(organizadorDTO.getOrganizadorId()).get();
        Edicao edicao = edicaoRepository.findById(edicaoId).get();
        edicao.setOrganizador(organizador);
        edicaoRepository.save(edicao);

        return ResponseEntity.ok(organizador);
    }

    @DeleteMapping("/{eventoId}/organizador")
    public Map<String, Boolean>  desautorizarOrganizador(@PathVariable Long edicaoId) {
        Edicao edicao = edicaoRepository.findById(edicaoId).get();
        edicao.setOrganizador(null);
        edicaoRepository.save(edicao);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Organizador desautorizado com sucesso.", Boolean.TRUE);
        return response;
    }



}
