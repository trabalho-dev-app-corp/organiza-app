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

import com.devappcorp.organiza.organizaapp.domain.model.Espaco;
import com.devappcorp.organiza.organizaapp.domain.repository.EspacoRepository;

@RestController
@RequestMapping("/espacos")
public class EspacoController {

    @Autowired
    private EspacoRepository espacoRepository;

    @GetMapping
    public List<Espaco> listar() {
        return espacoRepository.findAll();
    }

    @GetMapping("/{espacoId}")
    public ResponseEntity<Espaco> buscar(@PathVariable("espacoId") Long espacoId) {
        Espaco espaco = espacoRepository.findById(espacoId).get();

        if (espaco != null) {
            return ResponseEntity.status(HttpStatus.OK).body(espaco);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<Espaco> adicionar(@RequestBody Espaco espaco) {
        espacoRepository.save(espaco);
        return ResponseEntity.status(HttpStatus.CREATED).body(espaco);
    }

    @PutMapping("/{espacoId}")
    public ResponseEntity<Espaco> editar(@PathVariable("espacoId") Long espacoId, @RequestBody Espaco espacoInfo) {
        Espaco espaco = espacoRepository.findById(espacoId).get();

        espaco.setNome(espacoInfo.getNome());
        espaco.setLocalizacao(espacoInfo.getLocalizacao());
        espaco.setCapacidade(espacoInfo.getCapacidade());
        espaco.setRecursos(espacoInfo.getRecursos());
        final Espaco espacoAtualizado = espacoRepository.save(espaco);
        return ResponseEntity.ok(espacoAtualizado);
    }

    @DeleteMapping("/{espacoId}")
    public Map<String, Boolean> deletar(@PathVariable("espacoId") Long espacoId) {
        Espaco espaco = espacoRepository.findById(espacoId).get();

        espacoRepository.delete(espaco);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deletado", Boolean.TRUE);
        return response;
    }
}
