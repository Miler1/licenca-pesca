DROP TABLE carteira_pesca.retorno;

ALTER TABLE carteira_pesca.titulo ADD COLUMN dt_geracao_remessa TIMESTAMP NULL;
COMMENT ON COLUMN carteira_pesca.titulo.dt_geracao_remessa IS 'Data de geração da remessa.';

ALTER TABLE carteira_pesca.titulo DROP COLUMN valor_pago;

ALTER TABLE carteira_pesca.titulo DROP CONSTRAINT fk_remessa_titulo;
ALTER TABLE carteira_pesca.titulo DROP COLUMN id_remessa;

ALTER TABLE carteira_pesca.titulo DROP CONSTRAINT fk_retorno_titulo;
ALTER TABLE carteira_pesca.titulo DROP COLUMN id_retorno;

COMMENT ON COLUMN carteira_pesca.titulo.id_retorno IS 'Retorno que o titulo foi processado.';

ALTER TABLE carteira_pesca.remessa DROP COLUMN sequencia_nome_arquivo;
ALTER TABLE carteira_pesca.remessa ADD COLUMN sequencia_nome_arquivo CHARACTER VARYING(2);

COMMENT ON COLUMN carteira_pesca.remessa.sequencia_nome_arquivo IS 'Sequência com nome do arquivo.';