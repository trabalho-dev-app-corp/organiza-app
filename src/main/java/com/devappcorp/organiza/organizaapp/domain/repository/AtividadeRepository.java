package com.devappcorp.organiza.organizaapp.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devappcorp.organiza.organizaapp.domain.model.Atividade;

public interface AtividadeRepository extends JpaRepository<Atividade, Long>{
    
}