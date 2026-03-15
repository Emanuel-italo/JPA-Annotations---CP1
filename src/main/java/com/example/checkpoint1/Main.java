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
            em.persist(f1); 
            tx.commit();
            em.close();
            System.out.println("Operação JPA (persist) executada (ou tentada). Veja logs do provedor para SQL real se configurado.");
        } catch (Exception e) {
            System.out.println("(Aviso) Não foi possível executar operações JPA aqui — verifique persistence.xml e driver do Oracle.\nMensagem: " + e.getMessage());
            System.out.println("Como alternativa, mostramos as strings SQL geradas pelo programa acima (que você pode usar para executar no SQL Developer)."); 
        }

        System.out.println("\n=== Fim da demonstração ===");
    }
}
