ALTER TABLE carteira_pesca.solicitante OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.solicitante TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.solicitante TO carteira_pesca;

GRANT SELECT, USAGE ON carteira_pesca.cpf_id_seq TO carteira_pesca;
GRANT SELECT, USAGE ON carteira_pesca.informacao_complementar_id_seq TO carteira_pesca;
GRANT SELECT, USAGE ON carteira_pesca.licenca_id_seq TO carteira_pesca;
GRANT SELECT, USAGE ON carteira_pesca.passaporte_id_seq TO carteira_pesca;
GRANT SELECT, USAGE ON carteira_pesca.protocolo_id_seq TO carteira_pesca;
GRANT SELECT, USAGE ON carteira_pesca.solicitante_id_id_seq TO carteira_pesca;
GRANT SELECT, USAGE ON carteira_pesca.solicitante_id_seq TO carteira_pesca;

GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.licenca TO carteira_pesca;

DROP TABLE carteira_pesca.protocolo_sequence;
DROP TABLE carteira_pesca.solicitante;
DROP TABLE carteira_pesca.licenca;
DROP TABLE carteira_pesca.protocolo;
DROP TABLE carteira_pesca.solicitante_id;
DROP TABLE carteira_pesca.cpf;
DROP TABLE carteira_pesca.passaporte;

CREATE TABLE carteira_pesca.sequencia_protocolo
(
  id integer NOT NULL, 
  nm_sequencia character varying(20) NOT NULL, 
  vl_sequencia integer NOT NULL, 
  id_modalidade integer NOT NULL, 
  nu_ano_sequencia integer NOT NULL, 
  CONSTRAINT pk_sequencia_protocolo PRIMARY KEY (id),
  CONSTRAINT fk_modalidade FOREIGN KEY (id_modalidade)
      REFERENCES carteira_pesca.modalidade (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE carteira_pesca.sequencia_protocolo
  OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.sequencia_protocolo TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.sequencia_protocolo TO carteira_pesca;
COMMENT ON TABLE carteira_pesca.sequencia_protocolo
  IS 'Entidade responsável por armazenar a sequência para gerar o número de protocolo.';
COMMENT ON COLUMN carteira_pesca.sequencia_protocolo.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.sequencia_protocolo.nm_sequencia IS 'Nome da sequência do protocolo.';
COMMENT ON COLUMN carteira_pesca.sequencia_protocolo.vl_sequencia IS 'Valor da sequência do protocolo.';
COMMENT ON COLUMN carteira_pesca.sequencia_protocolo.id_modalidade IS 'Identificador representando o id da modalidade da sequência.';
COMMENT ON COLUMN carteira_pesca.sequencia_protocolo.nu_ano_sequencia IS 'Ano em vigência da sequência.';

CREATE TABLE carteira_pesca.solicitante
(
  id serial NOT NULL, 
  nu_cpf integer, 
  nu_passaporte integer, 
  CONSTRAINT pk_solicitante PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE carteira_pesca.solicitante
  OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.solicitante TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.solicitante TO carteira_pesca;
GRANT SELECT, USAGE ON carteira_pesca.solicitante_id_seq TO carteira_pesca;
COMMENT ON TABLE carteira_pesca.solicitante
  IS 'Entidade responsável por armazenar os dados do solicitante.';
COMMENT ON COLUMN carteira_pesca.solicitante.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.solicitante.nu_cpf IS 'CPF do solicitante';
COMMENT ON COLUMN carteira_pesca.solicitante.nu_passaporte IS 'Passaporte do solicitante';

CREATE TABLE carteira_pesca.licenca
(
  id serial NOT NULL, 
  tx_protocolo character varying(11) NOT NULL, 
  id_modalidade integer NOT NULL, 
  dt_criacao date NOT NULL, 
  id_status integer NOT NULL, 
  dt_ativacao date, 
  id_solicitante integer,
  tx_caminho_boleto character varying(255), 
  tx_caminho_carteira character varying(255), 
  CONSTRAINT pk_licenca PRIMARY KEY (id),
  CONSTRAINT fk_solicitante FOREIGN KEY (id_solicitante)
      REFERENCES carteira_pesca.solicitante (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE carteira_pesca.licenca
  OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.licenca TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.licenca TO carteira_pesca;
GRANT SELECT, USAGE ON carteira_pesca.licenca_id_seq TO carteira_pesca;
COMMENT ON TABLE carteira_pesca.licenca
  IS 'Entidade responsável por armazenar a sequência para gerar o número de protocolo.';
COMMENT ON COLUMN carteira_pesca.licenca.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.licenca.tx_protocolo IS 'Código do protocolo da licença. ';
COMMENT ON COLUMN carteira_pesca.licenca.id_modalidade IS 'Identificador da modalidade. 0 - Esportiva; 1 - Recreativa;';
COMMENT ON COLUMN carteira_pesca.licenca.dt_criacao IS 'Data da criação da licença.';
COMMENT ON COLUMN carteira_pesca.licenca.id_status IS 'Identificador do status da licença. 0 - Aguardando; 1 - Ativo; 2 - Inválido;';
COMMENT ON COLUMN carteira_pesca.licenca.dt_ativacao IS 'Data em que a licença tornou-se ativa.';
COMMENT ON COLUMN carteira_pesca.licenca.tx_caminho_boleto IS 'Caminho do boleto.';
COMMENT ON COLUMN carteira_pesca.licenca.tx_caminho_carteira IS 'Caminho da carteira.';
