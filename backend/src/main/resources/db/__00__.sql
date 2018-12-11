CREATE DATABASE carteira_pesca ENCODING='UTF8' LC_COLLATE='pt_BR.UTF-8' LC_CTYPE='pt_BR.UTF-8' TEMPLATE=template0;

CREATE ROLE carteira_pesca LOGIN
  ENCRYPTED PASSWORD 'carteira_pesca'
  SUPERUSER INHERIT CREATEDB NOCREATEROLE NOREPLICATION;

ALTER ROLE carteira_pesca
  SET search_path = carteira_pesca, public;
