
CREATE TABLE carteira_pesca.taxa_licenca (
  id SERIAL NOT NULL,
  id_licenca INTEGER NOT NULL,
  id_gestao_pagamento INTEGER NOT NULL,
  data_vencimento DATE NOT NULL,
  valor DOUBLE PRECISION NOT NULL,
  codigo_barras CHARACTER VARYING(75) NOT NULL,
  fl_pago BOOLEAN NOT NULL DEFAULT FALSE,
  fl_vencido BOOLEAN NOT NULL DEFAULT FALSE,
  CONSTRAINT pk_taxa_licenca PRIMARY KEY (id),
  CONSTRAINT fk_tl_licenca FOREIGN KEY (id_licenca)
    REFERENCES carteira_pesca.licenca (id) MATCH SIMPLE
    ON UPDATE RESTRICT ON DELETE RESTRICT
) WITH (
  OIDS=FALSE
);

ALTER TABLE carteira_pesca.taxa_licenca OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.taxa_licenca TO postgres;
GRANT SELECT, UPDATE, DELETE ON TABLE carteira_pesca.taxa_licenca to carteira_pesca;
GRANT SELECT, UPDATE ON SEQUENCE carteira_pesca.taxa_licenca_id_seq to carteira_pesca;

COMMENT ON TABLE carteira_pesca.taxa_licenca IS 'Entidade responsável por armazenar os dados do convênio.';
COMMENT ON COLUMN carteira_pesca.taxa_licenca.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.taxa_licenca.id_licenca IS 'Identificador da licença que foi emitida uma taxa';
COMMENT ON COLUMN carteira_pesca.taxa_licenca.id_gestao_pagamento IS 'Identificador da taxa no Gestão de Pagamentos';
COMMENT ON COLUMN carteira_pesca.taxa_licenca.data_vencimento IS 'Data de vencimento.';
COMMENT ON COLUMN carteira_pesca.taxa_licenca.valor IS 'Valor da taxa gerada.';
COMMENT ON COLUMN carteira_pesca.taxa_licenca.codigo_barras IS 'Código de barra da taxa gerada pelo módulo Gestão de Pagamentos.';
COMMENT ON COLUMN carteira_pesca.taxa_licenca.fl_pago IS 'Indica se o pagamento da taxa foi efetuado.';
COMMENT ON COLUMN carteira_pesca.taxa_licenca.fl_vencido IS 'Indica se a taxa está vencida vecimento.';

GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA carteira_pesca TO carteira_pesca;

ALTER TABLE carteira_pesca.licenca DROP COLUMN id_convenio;
ALTER TABLE carteira_pesca.licenca DROP COLUMN id_titulo;
ALTER TABLE carteira_pesca.licenca DROP COLUMN dt_vencimento_boleto;

DROP TABLE carteira_pesca.convenio;
DROP TABLE carteira_pesca.condicao_convenio;
DROP TABLE carteira_pesca.pagamento_convenio;
DROP TABLE carteira_pesca.retorno_convenio;
DROP TABLE carteira_pesca.tipo_arrecadacao;
DROP TABLE carteira_pesca.tipo_pagamento;
DROP TABLE carteira_pesca.tipo_segmento;
DROP TABLE carteira_pesca.tipo_valor_efetivo;
DROP TABLE carteira_pesca.titulo_remessa;
DROP TABLE carteira_pesca.titulo_retorno;
DROP TABLE carteira_pesca.titulo;
DROP TABLE carteira_pesca.beneficiario_titulo;
DROP TABLE carteira_pesca.beneficiario;
DROP TABLE carteira_pesca.motivo_ocorrencia;
DROP TABLE carteira_pesca.motivo;
DROP TABLE carteira_pesca.ocorrencia;

DROP TABLE carteira_pesca.remessa;
DROP TABLE carteira_pesca.retorno;
DROP TABLE carteira_pesca.banco;
DROP TABLE carteira_pesca.especie_documento;
DROP TABLE carteira_pesca.pagador;
