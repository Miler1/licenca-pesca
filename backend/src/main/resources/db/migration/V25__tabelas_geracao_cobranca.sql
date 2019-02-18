CREATE TABLE carteira_pesca.identificacao_segmento (
  id SERIAL NOT NULL, -- identificador do segmento
  codigo CHARACTER VARYING(100), -- codigo de identificação do segmento
  descricao CHARACTER VARYING(100), -- descrição do segmento
  CONSTRAINT pk_identificacao_segmento PRIMARY KEY (id)
) WITH (
  OIDS=FALSE
);

ALTER TABLE carteira_pesca.identificacao_segmento OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.identificacao_segmento TO postgres;
GRANT SELECT, UPDATE, DELETE ON TABLE carteira_pesca.identificacao_segmento to carteira_pesca;
GRANT SELECT, UPDATE ON SEQUENCE carteira_pesca.identificacao_segmento_id_seq to carteira_pesca;

COMMENT ON TABLE carteira_pesca.identificacao_segmento IS 'Entidade responsável por armazenar os dados da identificação do segmento.';
COMMENT ON COLUMN carteira_pesca.identificacao_segmento.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.identificacao_segmento.codigo IS 'Codigo de identificação do segmento.';
COMMENT ON COLUMN carteira_pesca.identificacao_segmento.descricao IS 'Descrição do segmento.';

CREATE TABLE carteira_pesca.identificacao_valor_efetivo_referencia (
  id SERIAL NOT NULL, -- identificador do valor efetivo ou referencia
  codigo CHARACTER VARYING(100), -- codigo de identificação do valor efetivo ou referencia
  descricao CHARACTER VARYING(100), -- descrição do valor efetivo ou referencia
  CONSTRAINT pk_identificacao_valor_efetivo_referencia PRIMARY KEY (id)
) WITH (
  OIDS=FALSE
);

ALTER TABLE carteira_pesca.identificacao_valor_efetivo_referencia OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.identificacao_valor_efetivo_referencia TO postgres;
GRANT SELECT, UPDATE, DELETE ON TABLE carteira_pesca.identificacao_valor_efetivo_referencia to carteira_pesca;
GRANT SELECT, UPDATE ON SEQUENCE carteira_pesca.identificacao_valor_efetivo_referencia_id_seq to carteira_pesca;

COMMENT ON TABLE carteira_pesca.identificacao_valor_efetivo_referencia IS 'Entidade responsável por armazenar os dados de identificação do valor efetivo de referência';
COMMENT ON COLUMN carteira_pesca.identificacao_valor_efetivo_referencia.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.identificacao_valor_efetivo_referencia.codigo IS 'Codigo de identificação do valor efetivo ou referência.';
COMMENT ON COLUMN carteira_pesca.identificacao_valor_efetivo_referencia.descricao IS 'Descrição do valor efetivo ou referência.';

CREATE TABLE carteira_pesca.codigo_barra_cobranca (
  id SERIAL NOT NULL, -- identificador da cobrança
  id_identificacao_segmento INTEGER NOT NULL, -- chave estrangeira para o tipo de identificação do segmento
  id_identificador_valor_efetivo_referencia INTEGER NOT NULL, -- chave estrangeira para o tipo de identificação do valor efetivo referencia
  digito_verificador INTEGER NOT NULL, -- digito verificador do código de barra da cobrança
  valor_efetivo_referencia DOUBLE PRECISION NOT NULL, -- valor da cobrança
  identificacao_empresa CHARACTER VARYING(30) NOT NULL, -- identificação da empresa
  CONSTRAINT pk_codigo_barra_cobranca PRIMARY KEY (id),
  CONSTRAINT fk_cbr_identificacao_segmento FOREIGN KEY (id_identificacao_segmento)
    REFERENCES carteira_pesca.identificacao_segmento (id) MATCH SIMPLE
    ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT fk_cbr_identificador_valor_efetivo_referencia FOREIGN KEY (id_identificador_valor_efetivo_referencia)
    REFERENCES carteira_pesca.identificacao_valor_efetivo_referencia (id) MATCH SIMPLE
    ON UPDATE RESTRICT ON DELETE RESTRICT
) WITH (
  OIDS=FALSE
);

ALTER TABLE carteira_pesca.codigo_barra_cobranca OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.codigo_barra_cobranca TO postgres;
GRANT SELECT, UPDATE, DELETE ON TABLE carteira_pesca.codigo_barra_cobranca to carteira_pesca;
GRANT SELECT, UPDATE ON SEQUENCE carteira_pesca.codigo_barra_cobranca_id_seq to carteira_pesca;

COMMENT ON TABLE carteira_pesca.codigo_barra_cobranca IS 'Entidade responsável por armazenar os dados do codigo de barra da cobrança.';
COMMENT ON COLUMN carteira_pesca.codigo_barra_cobranca.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.codigo_barra_cobranca.id_identificacao_segmento IS 'Identificador único da entidade identificacao_segmento que faz o relacionamento entre identificacao_segmento e codigo_barra_cobranca.';
COMMENT ON COLUMN carteira_pesca.codigo_barra_cobranca.id_identificador_valor_efetivo_referencia IS 'Identificador único da entidade identificacao_valor_efetivo_referencia que faz o relacionamento entre identificacao_valor_efetivo_referencia e codigo_barra_cobranca.';
COMMENT ON COLUMN carteira_pesca.codigo_barra_cobranca.digito_verificador IS 'Digito verificador do código de barra da cobrança.';
COMMENT ON COLUMN carteira_pesca.codigo_barra_cobranca.valor_efetivo_referencia IS 'Valor da cobrança.';
COMMENT ON COLUMN carteira_pesca.codigo_barra_cobranca.identificacao_empresa IS 'Identificação da empresa.';

CREATE INDEX fk_cbr_identificacao_segmento_idx
  ON carteira_pesca.codigo_barra_cobranca (id_identificacao_segmento);

CREATE INDEX fk_cbr_identificador_valor_efetivo_referencia_idx
  ON carteira_pesca.codigo_barra_cobranca (id_identificador_valor_efetivo_referencia);