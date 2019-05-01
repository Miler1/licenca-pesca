CREATE TABLE carteira_pesca.condicao_convenio (
  id SERIAL NOT NULL, 
  codigo CHARACTER VARYING(20),
  descricao CHARACTER VARYING(50),
  CONSTRAINT pk_condicao_convenio PRIMARY KEY (id)
) WITH (
  OIDS=FALSE
);

ALTER TABLE carteira_pesca.condicao_convenio OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.condicao_convenio TO postgres;
GRANT SELECT, UPDATE, DELETE ON TABLE carteira_pesca.condicao_convenio to carteira_pesca;
GRANT SELECT, UPDATE ON SEQUENCE carteira_pesca.condicao_convenio_id_seq to carteira_pesca;

COMMENT ON TABLE carteira_pesca.condicao_convenio IS 'Entidade responsável por armazenar as codições do documento de arrecadação.';
COMMENT ON COLUMN carteira_pesca.condicao_convenio.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.condicao_convenio.codigo IS 'Codigo de identificação da condição.';
COMMENT ON COLUMN carteira_pesca.condicao_convenio.descricao IS 'Descrição da condição.';

INSERT INTO carteira_pesca.condicao_convenio (id, codigo, descricao) VALUES
(1, 'AGUARDANDO_PAGAMENTO', 'Aguardando pagamento'),
(2, 'PAGO', 'Pagamento efetuado'),
(3, 'VENCIDO', 'Vencido');

ALTER TABLE carteira_pesca.convenio
    ADD COLUMN id_condicao INTEGER NOT NULL,
    ADD CONSTRAINT fk_c_condicao_convenio FOREIGN KEY (id_condicao)
        REFERENCES carteira_pesca.condicao_convenio (id);

COMMENT ON COLUMN carteira_pesca.convenio.id_condicao IS 'Indica a condição do convênio.';
