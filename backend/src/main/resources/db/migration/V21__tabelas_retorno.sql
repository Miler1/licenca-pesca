CREATE TABLE carteira_pesca.retorno
(
  id SERIAL NOT NULL,
  id_arquivo INTEGER NOT NULL,
  dt_gravacao_banco DATE NOT NULL,
  dt_processamento TIMESTAMP NULL,
  CONSTRAINT pk_retorno PRIMARY KEY(id),

  CONSTRAINT fk_retorno_arquivo FOREIGN KEY (id_arquivo)
    REFERENCES carteira_pesca.arquivo (id) MATCH SIMPLE
    ON UPDATE RESTRICT ON DELETE RESTRICT
) WITH (
  OIDS=FALSE
);

ALTER TABLE carteira_pesca.retorno OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.retorno TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.retorno TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.retorno IS 'Entidade responsável por armazenar os dados do retorno.';
COMMENT ON COLUMN carteira_pesca.retorno.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.retorno.id_arquivo IS 'Arquivo de retorno que foi gerado.';
COMMENT ON COLUMN carteira_pesca.retorno.dt_gravacao_banco IS 'Data que o arquivo de retorno foi gerado pelo banco.';
COMMENT ON COLUMN carteira_pesca.retorno.dt_processamento IS 'Data de processamento do arquivo.';

ALTER TABLE carteira_pesca.titulo DROP COLUMN dt_geracao_remessa;

ALTER TABLE carteira_pesca.titulo ADD COLUMN valor_pago DOUBLE PRECISION NULL;
COMMENT ON COLUMN carteira_pesca.titulo.valor_pago IS 'Valor que foi pago pelo titulo título.';

ALTER TABLE carteira_pesca.titulo ADD COLUMN id_remessa INTEGER NULL;
ALTER TABLE carteira_pesca.titulo ADD CONSTRAINT fk_remessa_titulo FOREIGN KEY (id_remessa)
  REFERENCES carteira_pesca.remessa (id) MATCH SIMPLE
  ON UPDATE RESTRICT ON DELETE RESTRICT;

COMMENT ON COLUMN carteira_pesca.titulo.id_remessa IS 'Remessa gerada para o título.';

ALTER TABLE carteira_pesca.titulo ADD COLUMN id_retorno INTEGER NULL;
ALTER TABLE carteira_pesca.titulo ADD CONSTRAINT fk_retorno_titulo FOREIGN KEY (id_retorno)
  REFERENCES carteira_pesca.retorno (id) MATCH SIMPLE
  ON UPDATE RESTRICT ON DELETE RESTRICT;

COMMENT ON COLUMN carteira_pesca.titulo.id_retorno IS 'Retorno que o titulo foi processado.';

ALTER TABLE carteira_pesca.remessa DROP COLUMN sequencia_nome_arquivo;
ALTER TABLE carteira_pesca.remessa ADD COLUMN sequencia_nome_arquivo INTEGER NULL;

