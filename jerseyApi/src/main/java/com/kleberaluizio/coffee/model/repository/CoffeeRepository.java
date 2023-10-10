package com.kleberaluizio.coffee.model.repository;

import com.kleberaluizio.coffee.model.Coffee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CoffeeRepository extends Repository {
    private static List<Coffee> cafes = null;

    static{
        cafes = new ArrayList<>();

        Coffee tresCoracoes = new Coffee();

        tresCoracoes.setId(10L);
        tresCoracoes.setNome("Café três corações");
        tresCoracoes.setDataDeFabricacao(LocalDate.now());
        tresCoracoes.setDataDeValidade(LocalDate.now().plusYears(1));
        tresCoracoes.setPreco(20.50);

        cafes.add(tresCoracoes);

        Coffee pilao = new Coffee(11L, "Café Pilão", 21.50,LocalDate.now(),LocalDate.now().plusYears(1));

        cafes.add(pilao);
    }

    public static List<Coffee> findAll(){
        return cafes;
    }

}
