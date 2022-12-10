package com.devappcorp.organiza.organizaapp.domain.model;



import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Table(name = "edicoes")
@Entity(name = "Edicao")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Edicao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private int numero;

    private int ano;

    private Date dataInicial;

    private Date dataFinal;

    private String cidade;



}
