ALTER TABLE carteira_pesca.pagador_titulo RENAME TO pagador;

INSERT INTO carteira_pesca.tipo_segmento (id, codigo, descricao) VALUES
(1, 1, 'Prefeituras'),
(2, 2, 'Saneamento'),
(3, 3, 'Energia Elétrica e Gás'),
(4, 4, 'Telecomunicações'),
(5, 5, 'Órgãos Governamentais'),
(6, 6, 'Carnes e Assemelhados ou demais Empresas / Órgãos que serão identificadas através do CNPJ'),
(7, 7, 'Multas de trânsito'),
(8, 9, 'Uso exclusivo do banco');

INSERT INTO carteira_pesca.tipo_valor_efetivo (id, codigo, descricao) VALUES
(1, 6, 'Valor a ser cobrado efetivamente em reais'),
(2, 7, 'Quantidade de moeda'),
(3, 8, 'Valor a ser cobrado efetivamente em reais'),
(4, 9, 'Quantidade de moeda');

CREATE TABLE carteira_pesca.carteira_pesca.convenio (
  id SERIAL NOT NULL, 
  id_tipo_segmento INTEGER NOT NULL,
  id_tipo_valor_efetivo INTEGER NOT NULL, 
  id_pagador INTEGER NOT NULL, 
  valor DOUBLE PRECISION NOT NULL, 
  data_emissao DATE NOT NULL, 
  data_vencimento DATE NOT NULL,
  nosso_numero INTEGER NOT NULL,
  CONSTRAINT pk_convenio PRIMARY KEY (id),
  CONSTRAINT fk_c_tipo_segmento FOREIGN KEY (id_tipo_segmento)
    REFERENCES carteira_pesca.tipo_segmento (id) MATCH SIMPLE
    ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT fk_c_tipo_valor_efetivo FOREIGN KEY (id_tipo_valor_efetivo)
    REFERENCES carteira_pesca.tipo_valor_efetivo (id) MATCH SIMPLE
    ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT fk_c_pagador FOREIGN KEY (id_pagador)
    REFERENCES carteira_pesca.pagador (id) MATCH SIMPLE
    ON UPDATE RESTRICT ON DELETE RESTRICT
) WITH (
  OIDS=FALSE
);

ALTER TABLE carteira_pesca.convenio OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.convenio TO postgres;
GRANT SELECT, UPDATE, DELETE ON TABLE carteira_pesca.convenio to carteira_pesca;
GRANT SELECT, UPDATE ON SEQUENCE carteira_pesca.convenio_id_seq to carteira_pesca;

COMMENT ON TABLE carteira_pesca.convenio IS 'Entidade responsável por armazenar os dados do convênio.';
COMMENT ON COLUMN carteira_pesca.convenio.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.convenio.id_tipo_segmento IS 'Identificador único da entidade tipo_segmento que faz o relacionamento entre tipo_segmento e convenio.';
COMMENT ON COLUMN carteira_pesca.convenio.id_tipo_valor_efetivo IS 'Identificador único da entidade tipo_valor_efetivo que faz o relacionamento entre tipo_valor_efetivo e convenio.';
COMMENT ON COLUMN carteira_pesca.convenio.id_pagador IS 'Identificador único da entidade pagador que faz o relacionamento entre pagador e convenio.';
COMMENT ON COLUMN carteira_pesca.convenio.valor IS 'Valor do convẽnio.';
COMMENT ON COLUMN carteira_pesca.convenio.data_emissao IS 'Data de emissão da nota.';
COMMENT ON COLUMN carteira_pesca.convenio.data_vencimento IS 'Data de vencimento da nota.';
COMMENT ON COLUMN carteira_pesca.convenio.nosso_numero IS 'Número de controle interno.';


CREATE TABLE carteira_pesca.beneficiario (
  id SERIAL NOT NULL, 
  nome CHARACTER VARYING(200) NOT NULL,
  sigla CHARACTER VARYING(10) NULL,
  cpf_cnpj CHARACTER VARYING(14) NOT NULL, 
  id_endereco INTEGER NOT NULL,
  CONSTRAINT pk_beneficiario PRIMARY KEY (id),
  CONSTRAINT fk_b_endereco FOREIGN KEY (id_endereco)
    REFERENCES carteira_pesca.endereco (id) MATCH SIMPLE
    ON UPDATE RESTRICT ON DELETE RESTRICT
) WITH (
  OIDS=FALSE 
);

ALTER TABLE carteira_pesca.beneficiario OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.beneficiario TO postgres;
GRANT SELECT, UPDATE, DELETE ON TABLE carteira_pesca.beneficiario to carteira_pesca;
GRANT SELECT, UPDATE ON SEQUENCE carteira_pesca.beneficiario_id_seq to carteira_pesca;

COMMENT ON TABLE carteira_pesca.beneficiario IS 'Entidade responsável por armazenar os dados de identificação do beneficiário.';
COMMENT ON COLUMN carteira_pesca.beneficiario.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.beneficiario.nome IS 'Nome do beneficiário.';
COMMENT ON COLUMN carteira_pesca.beneficiario.sigla IS 'Sigla do nome beneficiário.';
COMMENT ON COLUMN carteira_pesca.beneficiario.cpf_cnpj IS 'CPF/CNPJ do beneficiário (sem máscara).';
COMMENT ON COLUMN carteira_pesca.beneficiario.id_endereco IS 'Identificador único da entidade endereço que faz o relacionamento entre endereço e beneficiario.';

ALTER TABLE carteira_pesca.beneficiario_titulo DROP COLUMN nome;
ALTER TABLE carteira_pesca.beneficiario_titulo DROP COLUMN sigla;
ALTER TABLE carteira_pesca.beneficiario_titulo DROP COLUMN cpf_cnpj;
ALTER TABLE carteira_pesca.beneficiario_titulo DROP COLUMN id_endereco;

ALTER TABLE carteira_pesca.beneficiario_titulo ADD COLUMN id_beneficiario INTEGER NULL;
COMMENT ON COLUMN carteira_pesca.beneficiario_titulo.id_beneficiario IS 'Identificador único da entidade beneficiario que faz o relacionamento entre beneficiario e beneficiario_titulo.';

ALTER TABLE carteira_pesca.beneficiario_titulo ADD CONSTRAINT fk_bt_beneficiario FOREIGN KEY (id_beneficiario) 
  REFERENCES carteira_pesca.endereco (id) MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT;