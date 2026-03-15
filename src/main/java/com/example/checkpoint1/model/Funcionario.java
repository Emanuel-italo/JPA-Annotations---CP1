package com.example.checkpoint1.model;

import com.example.checkpoint1.annotations.Coluna;
import com.example.checkpoint1.annotations.Descricao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Descricao(descricao = "funcionarios")
@Table(name = "FUNCIONARIOS")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Coluna(nome = "id", tipo = "NUMBER")
    private Long id;

    @Coluna(nome = "nome", tipo = "VARCHAR2(100)")
    private String nome;

    @Coluna(nome = "horas_trabalhadas", tipo = "NUMBER")
    private int horasTrabalhadas;

    @Coluna(nome = "valor_por_hora", tipo = "NUMBER")
    private double valorPorHora;

   
    public Funcionario() {
       
    }

    public Funcionario(String nome, int horasTrabalhadas, double valorPorHora) {
        this.nome = nome;
        this.horasTrabalhadas = horasTrabalhadas;
        this.valorPorHora = valorPorHora;
    }

    // Getters / Setters (simples)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getHorasTrabalhadas() { return horasTrabalhadas; }
    public void setHorasTrabalhadas(int horasTrabalhadas) { this.horasTrabalhadas = horasTrabalhadas; }

    public double getValorPorHora() { return valorPorHora; }
    public void setValorPorHora(double valorPorHora) { this.valorPorHora = valorPorHora; }

    
    public double calcularSalario() {
        return horasTrabalhadas * valorPorHora;
    }


    public String imprimirInformacao() {
        return String.format("Funcionario[id=%s, nome=%s, horas=%d, valorHora=%.2f, salario=%.2f]",
                id, nome, horasTrabalhadas, valorPorHora, calcularSalario());
    }
}
