package com.devappcorp.organiza.organizaapp.domain.model;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
@Table(name = "edicoes")
@Entity(name = "Edicao")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Edicao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private int numero;

    private int ano;

    private String dataInicial;

    private String dataFinal;

    private String cidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_id")
    @JsonBackReference
    private Evento evento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizador_id")
    private Usuario organizador;
}
