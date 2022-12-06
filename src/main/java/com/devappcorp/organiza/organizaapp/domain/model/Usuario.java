package com.devappcorp.organiza.organizaapp.domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String nome;

    private String email;

    private String afiliacao;

    @ManyToMany 
    @JoinTable( 
        name = "usarios_tipos", 
        joinColumns = @JoinColumn(
          name = "usuario_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(
          name = "tipo_usuario_id", referencedColumnName = "id"))
    private List<TipoUsuario> tiposUsuario = new ArrayList<>();
}
