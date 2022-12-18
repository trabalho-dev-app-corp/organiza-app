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
    
    @GetMapping("/{atividadeId}")
    public ResponseEntity<Atividade> buscar(@PathVariable("atividadeId") Long atividadeId) {
        Atividade atividade = AtividadeRepository.findById(atividadeId).get();

        if (atividade != null) {
            return ResponseEntity.status(HttpStatus.OK).body(atividade);
        } 

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<Atividade> adicionar(@RequestBody Atividade atividade) {
        AtividadeRepository.save(atividade);
        return ResponseEntity.status(HttpStatus.CREATED).body(atividade);
    }

    @PutMapping("/{atividadeId}")
    public ResponseEntity<Atividade> editar(@PathVariable("atividadeId") Long atividadeId, @RequestBody Atividade atividadeInfo) {
        Atividade atividade = AtividadeRepository.findById(atividadeId).get();

        atividade.setNome(atividadeInfo.getNome());
        atividade.setTipo(atividadeInfo.getTipo());
        atividade.setDescricao(atividadeInfo.getDescricao());
        atividade.setData_atv(atividadeInfo.getData_atv());
        atividade.setHorario_inicial(atividadeInfo.getHorario_inicial());
        atividade.setHorario_final(atividadeInfo.getHorario_final());

        final Atividade atividadeAtualizado = AtividadeRepository.save(atividade);
        return ResponseEntity.ok(atividadeAtualizado);
    }

    @DeleteMapping("/{atividadeId}")
    public Map<String, Boolean> deletar(@PathVariable("atividadeId") Long atividadeId) {
        Atividade atividade = AtividadeRepository.findById(atividadeId).get();

        AtividadeRepository.delete(atividade);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Atividade Deletada", Boolean.TRUE);
        return response;
    }

    @PutMapping("/favoritos/{atividadeId}")
    public ResponseEntity<Atividade> favoritar(@PathVariable("atividadeId") Long atividadeId, @RequestBody Atividade atividadeInfo) {
        Atividade atividade = AtividadeRepository.findById(atividadeId).get();

        atividade.setFavorito(atividadeInfo.getFavorito());

        final Atividade atividadeAtualizado = AtividadeRepository.save(atividade);
        return ResponseEntity.ok(atividadeAtualizado);
    }
    
    // @GetMapping("/favoritos")
    // public String listarFav() {
        
    //     String[] listaAtv = new String[0];
    //     int cont = 0;
    //     for (Atividade atividade : AtividadeRepository.findAll()) {
    //         if (atividade.getFavorito() == "1") {
                
    //             listaAtv[cont] = atividade.getNome();
    //             listaAtv[2] = "aggaga";

    //         }
    //         cont++;
    //     }
        

    //     return listaAtv[2];
    // }


}
