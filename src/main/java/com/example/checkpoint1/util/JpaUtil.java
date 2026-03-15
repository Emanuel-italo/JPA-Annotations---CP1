package com.example.checkpoint1.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Utilitário para obter EntityManagerFactory a partir do persistence.xml.
 * Eu (aluno) deixei visível onde configurar a Persistence Unit no resources.
 */
public class JpaUtil {

    private static final String PERSISTENCE_UNIT_NAME = "checkpointPU";
    private static EntityManagerFactory emf;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            // Em execução real, este código tentará inicializar conexão com o BD.
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return emf;
    }

    public static EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }
}
