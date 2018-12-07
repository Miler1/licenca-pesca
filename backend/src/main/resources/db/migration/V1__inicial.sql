CREATE ROLE carteira_pesca LOGIN
  ENCRYPTED PASSWORD 'carteira_pesca'
  NOSUPERUSER INHERIT CREATEDB NOCREATEROLE NOREPLICATION;

ALTER ROLE carteira_pesca
  SET search_path = carteira_pesca, public;

CREATE SCHEMA carteira_pesca;