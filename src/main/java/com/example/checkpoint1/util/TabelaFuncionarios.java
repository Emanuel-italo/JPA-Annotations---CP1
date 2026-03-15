package com.example.checkpoint1.util;

import com.example.checkpoint1.annotations.Coluna;
import com.example.checkpoint1.annotations.Descricao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilitária que, via Reflection, gera SQL (SELECT/INSERT/UPDATE/DELETE)
 * a partir das anotações @Descricao (nome da tabela) e @Coluna (nome das colunas).
 * Implementação didática: tenta cobrir casos comuns e é clara para leitura.
 */
public class TabelaFuncionarios {

    // Gera SELECT simples: SELECT col1, col2 FROM table;
    public static String gerarSelect(Class<?> clazz) {
        Descricao desc = clazz.getAnnotation(Descricao.class);
        String table = (desc != null) ? desc.descricao() : clazz.getSimpleName().toLowerCase();
        List<String> cols = new ArrayList<>();
        for (Field f : clazz.getDeclaredFields()) {
            Coluna c = f.getAnnotation(Coluna.class);
            if (c != null) cols.add(c.nome());
        }
        if (cols.isEmpty()) cols.add("*");
        String columnList = String.join(", ", cols);
        return String.format("SELECT %s FROM %s;", columnList, table);
    }

    // Gera INSERT baseado nos campos anotados (assume campo 'id' é gerado)
    public static String gerarInsert(Object obj) {
        Class<?> clazz = obj.getClass();
        Descricao desc = clazz.getAnnotation(Descricao.class);
        String table = (desc != null) ? desc.descricao() : clazz.getSimpleName().toLowerCase();
        List<String> cols = new ArrayList<>();
        List<String> vals = new ArrayList<>();
        for (Field f : clazz.getDeclaredFields()) {
            Coluna c = f.getAnnotation(Coluna.class);
            if (c != null) {
                String nomeCol = c.nome();
                if ("id".equalsIgnoreCase(nomeCol)) continue; // pula id auto
                cols.add(nomeCol);
                f.setAccessible(true);
                try {
                    Object value = f.get(obj);
                    if (value == null) vals.add("NULL"); else vals.add(formatValue(value));
                } catch (IllegalAccessException e) {
                    vals.add("NULL");
                }
            }
        }
        return String.format("INSERT INTO %s (%s) VALUES (%s);", table, String.join(", ", cols), String.join(", ", vals));
    }

    // Gera UPDATE simples: atualiza por id (assume campo id existe)
    public static String gerarUpdate(Object obj) {
        Class<?> clazz = obj.getClass();
        Descricao desc = clazz.getAnnotation(Descricao.class);
        String table = (desc != null) ? desc.descricao() : clazz.getSimpleName().toLowerCase();
        List<String> assigns = new ArrayList<>();
        Object idValue = null;
        for (Field f : clazz.getDeclaredFields()) {
            Coluna c = f.getAnnotation(Coluna.class);
            if (c != null) {
                f.setAccessible(true);
                try {
                    Object value = f.get(obj);
                    if ("id".equalsIgnoreCase(c.nome())) {
                        idValue = value;
                    } else {
                        assigns.add(String.format("%s = %s", c.nome(), formatValue(value)));
                    }
                } catch (IllegalAccessException e) {
                    // ignore in this didactic example
                }
            }
        }
        String where = (idValue != null) ? String.format("WHERE id = %s", formatValue(idValue)) : "-- NO ID FIELD --";
        return String.format("UPDATE %s SET %s %s;", table, String.join(", ", assigns), where);
    }

    // Gera DELETE por id
    public static String gerarDelete(Object obj) {
        Class<?> clazz = obj.getClass();
        Descricao desc = clazz.getAnnotation(Descricao.class);
        String table = (desc != null) ? desc.descricao() : clazz.getSimpleName().toLowerCase();
        Object idValue = null;
        for (Field f : clazz.getDeclaredFields()) {
            Coluna c = f.getAnnotation(Coluna.class);
            if (c != null && "id".equalsIgnoreCase(c.nome())) {
                f.setAccessible(true);
                try { idValue = f.get(obj); } catch (IllegalAccessException e) {}
            }
        }
        String where = (idValue != null) ? String.format("WHERE id = %s", formatValue(idValue)) : "-- NO ID FIELD --";
        return String.format("DELETE FROM %s %s;", table, where);
    }

    // Ajuda a formatar valores para SQL (didático; não seguro para produção -> SQL Injection)
    private static String formatValue(Object value) {
        if (value == null) return "NULL";
        if (value instanceof Number) return value.toString();
        // simples escape para string: substituir ' por ''
        return "'" + value.toString().replace("'", "''") + "'";
    }
}
