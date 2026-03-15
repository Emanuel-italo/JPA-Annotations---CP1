# Checkpoint 1 - Java Advanced (modo aluno)

**Descrição rápida (aluno):**

Projeto de exemplo para o Checkpoint 1 da disciplina Java Advanced. Contém as classes pedidas (Funcionario, FuncionarioSenior e outras subclasses criativas), anotações personalizadas (@Descricao e @Coluna), geração de SQL via Reflection e integração conceitual com JPA/Hibernate.


---

## Estrutura do projeto

- `pom.xml` - configuração Maven
- `src/main/java/...` - código-fonte (anotações, modelos, util)
- `src/main/resources/META-INF/persistence.xml` - configuração da Persistence Unit (veja abaixo)
- `docs/` - imagens de exemplo / placeholders
- `integrantes.txt` - arquivo com nomes/RMs (substitua pelos membros do grupo)

## Como configurar (passo-a-passo) - aluno explicando

1. **Configurar o Oracle JDBC driver**
   - O driver JDBC da Oracle não é embutido por licença. Baixe o `ojdbc8.jar` ou similar no site da Oracle.
   - Adicione o JAR ao classpath do projeto (no IntelliJ: File > Project Structure > Libraries > +).

2. **Editar `persistence.xml`**
   - Abra `src/main/resources/META-INF/persistence.xml` e substitua `<HOST>`, `<PORT>`, `<SERVICE>`, `<USERNAME>` e `<PASSWORD>` pelos valores do seu Oracle/SQL Developer.
   - Exemplo de URL: `jdbc:oracle:thin:@//localhost:1521/ORCLPDB1`.

3. **Rodar no IDE (IntelliJ recomendado)**
   - Importar como projeto Maven.
   - Certificar-se de que o driver Oracle foi adicionado como library.
   - Rodar `Main.java`.

4. **Verificar os SQLs gerados**
   - O programa imprime as strings SQL geradas via Reflection (SELECT, INSERT, UPDATE, DELETE).
   - Se o `persistence.xml` estiver configurado e o Oracle disponível, o Hibernate também exibirá o SQL no console (propriedade `hibernate.show_sql` está ativada).

## Observações do estudante (pequenas dúvidas / possíveis melhorias)

- Usei um bônus fixo de R$100 por bloco de 15 horas para `FuncionarioSenior`. Poderia ser uma porcentagem ou uma propriedade configurável.
- Para produção eu usaria `BigDecimal` ao invés de `double` para valores monetários.
- A geração de SQL via Reflection aqui é didática e **não é segura** contra SQL Injection — não usar em produção sem parametrização.
- Poderíamos melhorar detectando o campo `@Id` dinamicamente (aqui assumimos `id`) e usando PreparedStatements.

## Como testar (o que o `Main` faz)

- Executando `Main` você verá:
  - Saídas de `imprimirInformacao()` para cada objeto criado.
  - SELECTs gerados via Reflection para cada classe.
  - Exemplos de INSERT/UPDATE/DELETE gerados e impressos.
  - Tentativa de uso do `EntityManager` (vai falhar se `persistence.xml` não estiver configurado).

## Dependências externas

- Java 11
- Maven
- Hibernate 6 (configurado no pom)
- Oracle JDBC driver (não incluído — consulte a seção acima)

## Checklist de entrega (conforme enunciado)
- [x] Projeto Java completo (pastas do IDE não incluídas automaticamente — adicione `.idea` se desejar antes de zipar)
- [x] `src/` com todas as classes implementadas
- [x] `integrantes.txt` (substitua pelos membros do grupo)
- [x] `README.md` com instruções e observações do aluno
- [x] `docs/` com placeholders e instruções para inserir prints reais
- [x] Arquivo `.zip` pronto para envio (este arquivo que você recebeu)

---

Se quiser, eu já envio o .zip do projeto aqui (está anexo). Se precisar que eu preencha `integrantes.txt` com nomes e RMs reais, cole-os aqui e eu os insiro no arquivo e reenvio o .zip.

Boa sorte! \n(Comentário do aluno: me avise se quiser que eu transforme as strings SQL em PreparedStatements para segurança.)
