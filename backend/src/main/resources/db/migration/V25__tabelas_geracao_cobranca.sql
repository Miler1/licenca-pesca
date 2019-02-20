CREATE TABLE carteira_pesca.tipo_segmento (
  id SERIAL NOT NULL, 
  codigo CHARACTER VARYING(100), 
  descricao CHARACTER VARYING(100), 
  CONSTRAINT pk_tipo_segmento PRIMARY KEY (id)
) WITH (
  OIDS=FALSE
);

ALTER TABLE carteira_pesca.tipo_segmento OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.tipo_segmento TO postgres;
GRANT SELECT, UPDATE, DELETE ON TABLE carteira_pesca.tipo_segmento to carteira_pesca;
GRANT SELECT, UPDATE ON SEQUENCE carteira_pesca.tipo_segmento_id_seq to carteira_pesca;

COMMENT ON TABLE carteira_pesca.tipo_segmento IS 'Entidade responsável por armazenar os dados da identificação do tipo_segmento.';
COMMENT ON COLUMN carteira_pesca.tipo_segmento.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.tipo_segmento.codigo IS 'Codigo de identificação do tipo_segmento.';
COMMENT ON COLUMN carteira_pesca.tipo_segmento.descricao IS 'Descrição do tipo_segmento.';

CREATE TABLE carteira_pesca.tipo_valor_efetivo (
  id SERIAL NOT NULL,
  codigo CHARACTER VARYING(100), 
  descricao CHARACTER VARYING(100),
  CONSTRAINT pk_tipo_valor_efetivo PRIMARY KEY (id)
) WITH (
  OIDS=FALSE
);

ALTER TABLE carteira_pesca.tipo_valor_efetivo OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.tipo_valor_efetivo TO postgres;
GRANT SELECT, UPDATE, DELETE ON TABLE carteira_pesca.tipo_valor_efetivo to carteira_pesca;
GRANT SELECT, UPDATE ON SEQUENCE carteira_pesca.tipo_valor_efetivo_id_seq to carteira_pesca;

COMMENT ON TABLE carteira_pesca.tipo_valor_efetivo IS 'Entidade responsável por armazenar os dados de identificação do valor efetivo de referência.';
COMMENT ON COLUMN carteira_pesca.tipo_valor_efetivo.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.tipo_valor_efetivo.codigo IS 'Codigo de identificação do valor efetivo ou referência.';
COMMENT ON COLUMN carteira_pesca.tipo_valor_efetivo.descricao IS 'Descrição do valor efetivo ou referência.';

CREATE TABLE carteira_pesca.codigo_barra_cobranca (
  id SERIAL NOT NULL,
  id_tipo_segmento INTEGER NOT NULL, 
  id_tipo_valor_efetivo INTEGER NOT NULL,
  digito_verificador INTEGER NOT NULL, 
  tipo_valor_efetivo_referencia DOUBLE PRECISION NOT NULL, 
  identificacao_empresa CHARACTER VARYING(30) NOT NULL, 
  CONSTRAINT pk_codigo_barra_cobranca PRIMARY KEY (id),
  CONSTRAINT fk_cbr_tipo_segmento FOREIGN KEY (id_tipo_segmento)
    REFERENCES carteira_pesca.tipo_segmento (id) MATCH SIMPLE
    ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT fk_cbr_tipo_valor_efetivo FOREIGN KEY (id_tipo_valor_efetivo)
    REFERENCES carteira_pesca.tipo_valor_efetivo (id) MATCH SIMPLE
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
COMMENT ON COLUMN carteira_pesca.codigo_barra_cobranca.id_tipo_segmento IS 'Identificador único da entidade tipo_segmento que faz o relacionamento entre tipo_segmento e codigo_barra_cobranca.';
COMMENT ON COLUMN carteira_pesca.codigo_barra_cobranca.id_tipo_valor_efetivo IS 'Identificador único da entidade tipo_valor_efetivo que faz o relacionamento entre tipo_valor_efetivo e codigo_barra_cobranca.';
COMMENT ON COLUMN carteira_pesca.codigo_barra_cobranca.digito_verificador IS 'Digito verificador do código de barra da cobrança.';
COMMENT ON COLUMN carteira_pesca.codigo_barra_cobranca.tipo_valor_efetivo_referencia IS 'Valor da cobrança.';
COMMENT ON COLUMN carteira_pesca.codigo_barra_cobranca.identificacao_empresa IS 'Identificação da empresa.';