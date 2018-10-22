# Configuração do backend

## Ambiente de desenvolvemento

### Criar banco de dados

```sql
createdb -E UTF8 --lc-collate='pt_BR.UTF-8' --lc-ctype='pt_BR.UTF-8' -T template0 carteira_pesca

createdb -E UTF8 --lc-collate='pt_BR.UTF-8' --lc-ctype='pt_BR.UTF-8' -T template0 carteira_pesca_test

```

### Criar role para o banco

```sql
CREATE ROLE carteira_pesca LOGIN
	ENCRYPTED PASSWORD 'carteira_pesca'
	SUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;

```

### Rodando a aplicação

```
mvn spring-boot:run

```

