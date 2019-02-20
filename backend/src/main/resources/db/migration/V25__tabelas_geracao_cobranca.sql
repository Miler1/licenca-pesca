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