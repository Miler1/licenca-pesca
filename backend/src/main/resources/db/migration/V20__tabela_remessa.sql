CREATE TABLE carteira_pesca.remessa
(
  id SERIAL NOT NULL,
  id_arquivo INTEGER NOT NULL,
  numero_sequencial INTEGER NOT NULL,
  sequencia_nome_arquivo CHARACTER VARYING(2) NOT NULL,
  dt_cadastro TIMESTAMP NOT NULL DEFAULT now(),

  CONSTRAINT pk_remessa PRIMARY KEY(id),

  CONSTRAINT fk_arquivo FOREIGN KEY (id_arquivo)
    REFERENCES carteira_pesca.arquivo (id) MATCH SIMPLE
    ON UPDATE RESTRICT ON DELETE RESTRICT

) WITH(OIDS = FALSE);

ALTER TABLE carteira_pesca.remessa OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.remessa TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.remessa TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.remessa IS 'Entidade responsável por armazenar os dados das remessas enviadas ao banco.';
COMMENT ON COLUMN carteira_pesca.remessa.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.remessa.id_arquivo IS 'Arquivo de remessa que foi gerado.';
COMMENT ON COLUMN carteira_pesca.remessa.numero_sequencial IS 'Número sequencial de remessa que foram enviadas. Não poderão jamais enviar o mesmo número ou ser zerado.';
COMMENT ON COLUMN carteira_pesca.remessa.sequencia_nome_arquivo IS 'Identificador sequencia de arquivos enviados no mesmo dia. 2 digitos alfanuméricos de 0 a Z';
COMMENT ON COLUMN carteira_pesca.remessa.dt_cadastro IS 'Data que o arquivo de remessa foi gerado.';
