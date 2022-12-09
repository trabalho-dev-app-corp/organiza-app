package com.devappcorp.organiza.organizaapp.infrastructure.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.devappcorp.organiza.organizaapp.domain.model.Usuario;
import com.devappcorp.organiza.organizaapp.domain.repository.UsuarioRepositoryQueries;

import antlr.StringUtils;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepositoryQueries{

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Usuario findByEmail(String email) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
        Root<Usuario> root = criteria.from(Usuario.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(builder.equal(root.get("email"), email));

        criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Usuario> query = manager.createQuery(criteria);
        return query.getSingleResult();

    }
}
    
