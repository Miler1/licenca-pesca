ALTER TABLE carteira_pesca.tipo_arquivo ALTER COLUMN codigo SET DATA TYPE CHARACTER VARYING(50);
INSERT INTO carteira_pesca.tipo_arquivo (id, codigo, descricao) VALUES (4, 'DOCUMENTO_ARRECADACAO', 'Arquivo .pdf do documento de arreacadação gerado');

CREATE TABLE carteira_pesca.tipo_arrecadacao (
  id SERIAL NOT NULL,
  codigo CHARACTER VARYING(4) NOT NULL,
  descricao CHARACTER VARYING(150) NULL,
  CONSTRAINT pk_tipo_arrecadacao PRIMARY KEY (id)
) WITH (
  OIDS=FALSE
);

ALTER TABLE carteira_pesca.tipo_arrecadacao OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.tipo_arrecadacao TO postgres;
GRANT SELECT, UPDATE, DELETE ON TABLE carteira_pesca.tipo_arrecadacao to carteira_pesca;
GRANT SELECT, UPDATE ON SEQUENCE carteira_pesca.tipo_arrecadacao_id_seq to carteira_pesca;

COMMENT ON TABLE carteira_pesca.tipo_arrecadacao IS 'Entidade responsável por armazenar os dados de tipo de arrecadação.';
COMMENT ON COLUMN carteira_pesca.tipo_arrecadacao.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.tipo_arrecadacao.codigo IS 'Nome do tipo de arrecadação.';
COMMENT ON COLUMN carteira_pesca.tipo_arrecadacao.descricao IS 'Descrição do tipo de arrecadação.';

INSERT INTO carteira_pesca.tipo_arrecadacao (id, codigo, descricao) VALUES
(1, 1, 'Guichê de Caixa com fatura/guia de arrecadação'),
(2, 2, 'Arrecadação Eletrônica com fatura/guia de arrecadação (terminais de auto - atendimento, ATM, home/office banking)'),
(3, 3, 'Internet com fatura/guia de arrecadação'),
(4, 4, 'Outros meios com fatura/guia de arrecadação'),
(5, 5, 'Correspondentes bancários com fatura/guia de arrecadação'),
(6, 6, 'Telefone com fatura/guia de arrecadação'),
(7, 7, 'Casas lotéricas com fatura/guia de arrecadação'),
(8, 'a', 'Guichê de Caixa sem fatura/guia de arrecadação'),
(9, 'b', 'Arrecadação Eletrônica sem fatura/guia de arrecadação (terminais de auto - atendimento, ATM, home/office banking)'),
(10, 'c', 'Internet sem fatura/guia de arrecadação'),
(11, 'd', 'Correspondentes bancários sem fatura/guia de arrecadação'),
(12, 'e', 'Telefone sem fatura/guia de arrecadação'),
(13, 'f', 'Outros meios sem fatura/guia de arrecadação'),
(14, 'g', 'Casas lotéricas sem fatura/guia de arrecadação');

CREATE TABLE carteira_pesca.tipo_pagamento (
  id SERIAL NOT NULL,
  descricao CHARACTER VARYING(150) NULL,
  CONSTRAINT pk_tipo_pagamento PRIMARY KEY (id)
) WITH (
  OIDS=FALSE
);

ALTER TABLE carteira_pesca.tipo_pagamento OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.tipo_pagamento TO postgres;
GRANT SELECT, UPDATE, DELETE ON TABLE carteira_pesca.tipo_pagamento to carteira_pesca;
GRANT SELECT, UPDATE ON SEQUENCE carteira_pesca.tipo_pagamento_id_seq to carteira_pesca;

COMMENT ON TABLE carteira_pesca.tipo_pagamento IS 'Entidade responsável por armazenar os dados de identificação do beneficiário.';
COMMENT ON COLUMN carteira_pesca.tipo_pagamento.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.tipo_pagamento.descricao IS 'Nome do beneficiário.';

INSERT INTO carteira_pesca.tipo_pagamento (id, descricao) VALUES
(1, 'Dinheiro'),
(2, 'Cheque'),
(3, 'Não identificado/outras formas');

CREATE TABLE carteira_pesca.pagamento_convenio (
  id SERIAL NOT NULL,
  data_pagamento DATE,
  data_credito DATE,
  valor_recebido DOUBLE PRECISION,
  valor_tarifa DOUBLE PRECISION,
  id_tipo_pagamento INTEGER NOT NULL,
  id_tipo_arrecadacao INTEGER NOT NULL,
  CONSTRAINT pk_pagamento_convenio PRIMARY KEY (id),
  CONSTRAINT fk_pc_tipo_pagamento FOREIGN KEY (id_tipo_pagamento)
    REFERENCES carteira_pesca.tipo_pagamento (id) MATCH SIMPLE
    ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT fk_pc_tipo_arrecadacao FOREIGN KEY (id_tipo_arrecadacao)
    REFERENCES carteira_pesca.tipo_arrecadacao (id) MATCH SIMPLE
    ON UPDATE RESTRICT ON DELETE RESTRICT
) WITH (
  OIDS=FALSE
);

ALTER TABLE carteira_pesca.pagamento_convenio OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.pagamento_convenio TO postgres;
GRANT SELECT, UPDATE, DELETE ON TABLE carteira_pesca.pagamento_convenio to carteira_pesca;
GRANT SELECT, UPDATE ON SEQUENCE carteira_pesca.pagamento_convenio_id_seq to carteira_pesca;

COMMENT ON TABLE carteira_pesca.pagamento_convenio IS 'Entidade responsável por armazenar os dados de pagamento do convênio.';
COMMENT ON COLUMN carteira_pesca.pagamento_convenio.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.pagamento_convenio.data_pagamento IS 'Data que foi realizado o pagamento.';
COMMENT ON COLUMN carteira_pesca.pagamento_convenio.data_credito IS 'Data que o valor paga foi creditado.';
COMMENT ON COLUMN carteira_pesca.pagamento_convenio.valor_recebido IS 'Valor efetivo recebido pelo pagamento.';
COMMENT ON COLUMN carteira_pesca.pagamento_convenio.valor_tarifa IS 'Valor da tarifa referente a cada comprovante arrecadado.';
COMMENT ON COLUMN carteira_pesca.pagamento_convenio.id_tipo_pagamento IS 'Identificador único da entidade tipo_pagamento que faz o relacionamento entre tipo_pagamento e pagamento_convenio.';
COMMENT ON COLUMN carteira_pesca.pagamento_convenio.id_tipo_arrecadacao IS 'Identificador único da entidade tipo_arrecadacao que faz o relacionamento entre tipo_arrecadacao e pagamento_convenio.';



ALTER TABLE carteira_pesca.licenca ADD COLUMN id_convenio INTEGER NULL;
COMMENT ON COLUMN carteira_pesca.licenca.id_convenio IS 'Identificador único da entidade convenio que faz o relacionamento entre convenio e licenca - Identifica o convênio que foi gerado para a licença.';
ALTER TABLE carteira_pesca.licenca ADD CONSTRAINT fk_l_convenio FOREIGN KEY (id_convenio)
  REFERENCES carteira_pesca.convenio (id) MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE carteira_pesca.convenio ADD COLUMN id_pagamento INTEGER NULL;
COMMENT ON COLUMN carteira_pesca.convenio.id_pagamento IS 'Identificador único da entidade pagamento_convenio que faz o relacionamento entre pagamento_convenio e convenio - Referencia as informações do pagamento.';
ALTER TABLE carteira_pesca.convenio ADD CONSTRAINT fk_c_pagamento_convenio FOREIGN KEY (id_pagamento)
  REFERENCES carteira_pesca.pagamento_convenio (id) MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE carteira_pesca.pagamento_convenio ADD COLUMN id_retorno INTEGER NOT NULL;
COMMENT ON COLUMN carteira_pesca.pagamento_convenio.id_retorno IS 'Identificador único da entidade retorno que faz o relacionamento entre retorno e pagamento_convenio - Identifica qual o retorno que foi processado.';
ALTER TABLE carteira_pesca.pagamento_convenio ADD CONSTRAINT fk_pc_retorno FOREIGN KEY (id_retorno)
  REFERENCES carteira_pesca.retorno (id) MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE carteira_pesca.retorno RENAME COLUMN qtd_titulo_cobranca TO qtd_registros;
ALTER TABLE carteira_pesca.retorno RENAME COLUMN valor_titulo_cobranca TO valor_registros;

ALTER TABLE carteira_pesca.retorno ADD COLUMN tipo_retorno CHARACTER VARYING(10) NULL;
COMMENT ON COLUMN carteira_pesca.retorno.tipo_retorno IS 'Indica o tipo arquivo de retorno que foi processado (TITULO, CONVENIO).';
