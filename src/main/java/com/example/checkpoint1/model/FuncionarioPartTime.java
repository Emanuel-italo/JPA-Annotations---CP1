package com.example.checkpoint1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "FUNCIONARIOS_PARTTIME")
public class FuncionarioPartTime extends Funcionario {

    public FuncionarioPartTime() { super(); }

    public FuncionarioPartTime(String nome, int horasTrabalhadas, double valorPorHora) {
        super(nome, horasTrabalhadas, valorPorHora);
    }

    @Override
    public double calcularSalario() {
        return getHorasTrabalhadas() * getValorPorHora() * 0.6;
    }

    @Override
    public String imprimirInformacao() {
        return String.format("FuncionarioPartTime[nome=%s, horas=%d, valorHora=%.2f, salario=%.2f]",
                getNome(), getHorasTrabalhadas(), getValorPorHora(), calcularSalario());
    }
}
