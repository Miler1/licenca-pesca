CREATE SCHEMA carteira_pesca;

GRANT ALL ON SCHEMA carteira_pesca TO postgres;
GRANT USAGE ON SCHEMA carteira_pesca TO central_colosso;
ALTER SCHEMA carteira_pesca OWNER TO postgres;