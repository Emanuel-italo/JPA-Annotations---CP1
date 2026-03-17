package com.example.checkpoint1;

import com.example.checkpoint1.model.*;
import com.example.checkpoint1.util.JpaUtil;
import com.example.checkpoint1.util.TabelaFuncionarios;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== Checkpoint 1 - Demonstração (modo aluno) ===\n");

        Funcionario f1 = new Funcionario("Alice", 40, 50.0);
        FuncionarioSenior f2 = new FuncionarioSenior("Bruno", 47, 70.0);
        Estagiario f3 = new Estagiario("Carla", 30, 30.0);
        FuncionarioPartTime f4 = new FuncionarioPartTime("Diego", 20, 80.0);

        System.out.println(f1.imprimirInformacao());
        System.out.println(f2.imprimirInformacao());
        System.out.println(f3.imprimirInformacao());
        System.out.println(f4.imprimirInformacao());

        System.out.println("\n--- SQL gerado por Reflection (SELECT) ---");
        System.out.println(TabelaFuncionarios.gerarSelect(f1.getClass()));
        System.out.println(TabelaFuncionarios.gerarSelect(f2.getClass()));
        System.out.println(TabelaFuncionarios.gerarSelect(f3.getClass()));
        System.out.println(TabelaFuncionarios.gerarSelect(f4.getClass()));

        System.out.println("\n--- SQL CRUD gerado por Reflection (simulação) ---");
        System.out.println(TabelaFuncionarios.gerarInsert(f1));
        System.out.println(TabelaFuncionarios.gerarUpdate(f2));
        System.out.println(TabelaFuncionarios.gerarDelete(f3));

        try {
            EntityManager em = JpaUtil.getEntityManager();
            System.out.println("\nEntityManager criado com sucesso. Tentando transações de exemplo...");

            EntityTransaction tx = em.getTransaction();
            tx.begin();

            System.out.println("-- SQL (simulado) antes do persist: " + TabelaFuncionarios.gerarInsert(f1));

            // 1. CREATE (Inserindo TODOS no banco de dados)
            System.out.println("\n-> Testando CREATE...");
            em.persist(f1); // Salva Alice
            em.persist(f2); // Salva Bruno
            em.persist(f3); // Salva Carla
            em.persist(f4); // Salva Diego

            // 2. READ (Buscando apenas a Alice para testar a leitura)
            System.out.println("-> Testando READ...");
            Funcionario busca = em.find(Funcionario.class, f1.getId());
            System.out.println("   Encontrado no banco: " + busca.getNome() + " com valor hora de: " + busca.getValorPorHora());

            // 3. UPDATE (Aumentando o valor da hora da Alice)
            System.out.println("-> Testando UPDATE...");
            busca.setValorPorHora(55.0);
            em.merge(busca);

            // 4. DELETE (Deletando o Diego)
            System.out.println("-> Testando DELETE...");
            Funcionario buscaDiego = em.find(Funcionario.class, f4.getId());
            em.remove(buscaDiego);

            tx.commit();
            em.close();

            System.out.println("\nOperações JPA (CRUD completo) executadas. Veja os logs acima para confirmar os SQLs reais gerados pelo Hibernate.");

        } catch (Exception e) {
            System.out.println("(Aviso) Não foi possível executar operações JPA aqui — verifique persistence.xml e driver do Oracle.\nMensagem: " + e.getMessage());
            System.out.println("Como alternativa, mostramos as strings SQL geradas pelo programa acima (que você pode usar para executar no SQL Developer).");
        }

        System.out.println("\n=== Fim da demonstração ===");
    }
}