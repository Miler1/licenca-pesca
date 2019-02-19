DROP TABLE carteira_pesca.titulo_remessa;

CREATE TABLE carteira_pesca.titulo_remessa (
  id_titulo  INTEGER NOT NULL,
  id_remessa INTEGER NOT NULL,
  CONSTRAINT pk_titulo_remessa PRIMARY KEY (id_titulo, id_remessa)
) WITH (
  OIDS=FALSE
);

GRANT SELECT, UPDATE, DELETE ON TABLE carteira_pesca.titulo_remessa to carteira_pesca;

COMMENT ON TABLE carteira_pesca.titulo_remessa is 'Tabela que relaciona títulos e remessas';
COMMENT ON COLUMN carteira_pesca.titulo_remessa.id_titulo is 'Referência para o título pertencente';
COMMENT ON COLUMN carteira_pesca.titulo_remessa.id_remessa is 'Referência para a remessa pertencente';


ALTER TABLE carteira_pesca.titulo DROP COLUMN id_remessa;
ALTER TABLE carteira_pesca.titulo DROP COLUMN id_retorno;

ALTER TABLE carteira_pesca.titulo ADD COLUMN fl_gerar_remessa BOOLEAN NOT NULL DEFAULT TRUE;
COMMENT ON COLUMN carteira_pesca.titulo.fl_gerar_remessa is 'Flag que verifica se é necessário gerar (ou regerar) remessa para deteminado titulo';
