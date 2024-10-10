package com.lucasmoraist.balancea.domain.enums;

public enum Categories {
    ALIMENTACAO("Alimentação"),
    SAUDE("Saúde"),
    MORADIA("Moradia"),
    TRANSPORTE("Transporte"),
    EDUCACAO("Educação"),
    LAZER("Lazer"),
    IMPREVISTOS("Imprevistos"),
    OUTROS("Outros");

    private final String name;

    public String getName() {
        return name;
    }

    Categories(String name) {
        this.name = name;
    }
}
