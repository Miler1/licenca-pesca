
CREATE TABLE carteira_pesca.taxa_licenca (
  id SERIAL NOT NULL,
  id_licenca INTEGER NOT NULL,
  id_gestao_pagamento INTEGER NOT NULL,
  data_vencimento DATE NOT NULL,
  valor DOUBLE PRECISION NOT NULL,
  codigo_barras INTEGER NOT NULL,
  fl_pago BOOLEAN NOT NULL DEFAULT FALSE,
  data_pagamento DATE,
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
COMMENT ON COLUMN carteira_pesca.taxa_licenca.codigo_barras IS 'Código de barra do taxa gerada pelo módulo Gestão de Pagamentos.';
COMMENT ON COLUMN carteira_pesca.taxa_licenca.fl_pago IS 'Indica se o pagamento da taxa foi efetuado.';
COMMENT ON COLUMN carteira_pesca.taxa_licenca.data_pagamento IS 'Data que o pagamento foi efetuado.';



