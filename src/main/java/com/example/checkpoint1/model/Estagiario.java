package com.example.checkpoint1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "ESTAGIARIOS")
public class Estagiario extends Funcionario {

    public Estagiario() { super(); }

    public Estagiario(String nome, int horasTrabalhadas, double valorPorHora) {
        super(nome, horasTrabalhadas, valorPorHora);
    }

    @Override
    public double calcularSalario() {
        // estagiário recebe 50% do valor por hora nesta implementação didática
        return getHorasTrabalhadas() * getValorPorHora() * 0.5;
    }

    @Override
    public String imprimirInformacao() {
        return String.format("Estagiario[nome=%s, horas=%d, valorHora=%.2f, salario=%.2f]",
                getNome(), getHorasTrabalhadas(), getValorPorHora(), calcularSalario());
    }
}
