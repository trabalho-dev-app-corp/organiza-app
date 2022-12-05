package com.devappcorp.organiza.organizaapp.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devappcorp.organiza.organizaapp.domain.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long>{
    
}
