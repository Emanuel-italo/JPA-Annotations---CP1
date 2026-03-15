package com.example.checkpoint1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "FUNCIONARIOS_SENIOR")
public class FuncionarioSenior extends Funcionario {

    public FuncionarioSenior() { super(); }

    public FuncionarioSenior(String nome, int horasTrabalhadas, double valorPorHora) {
        super(nome, horasTrabalhadas, valorPorHora);
    }

    @Override
    public double calcularSalario() {
        double salarioBase = super.calcularSalario();
        int blocosDe15 = getHorasTrabalhadas() / 15;
        double bonus = blocosDe15 * 100.0; 
        return salarioBase + bonus;
    }

    @Override
    public String imprimirInformacao() {
        return String.format("FuncionarioSenior[nome=%s, horas=%d, valorHora=%.2f, salarioComBonus=%.2f]",
                getNome(), getHorasTrabalhadas(), getValorPorHora(), calcularSalario());
    }
}
