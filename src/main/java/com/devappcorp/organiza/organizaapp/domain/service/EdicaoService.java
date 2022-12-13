package com.devappcorp.organiza.organizaapp.domain.service;

public class EdicaoService {
    //injeção com construtor ao invés de @Autowired
    final EdicaoService edicaoService;

    public EdicaoService(EdicaoService edicaoService){
        this.edicaoService = edicaoService;
    }


}
