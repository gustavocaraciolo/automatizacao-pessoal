package com.br.gustavocaraciolo.utils;

public enum DiaDaSemanaEnum {
    SEGUNDA_FEIRA("segunda-feira"),
    TERCA_FEIRA("terça-feira"),
    QUARTA_FEIRA("quarta-feira"),
    QUINTA_FEIRA("quinta-feira"),
    SEXTA_FEIRA("sexta-feira"),
    SABADO("sábado"),
    DOMINGO("domingo");


    public final String label;

    private DiaDaSemanaEnum(String label) {
        this.label = label;
    }
}
