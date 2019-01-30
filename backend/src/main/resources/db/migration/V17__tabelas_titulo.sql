CREATE TABLE carteira_pesca.banco
(
  id SERIAL NOT NULL,
  codigo CHARACTER VARYING(5) NOT NULL,
  nome CHARACTER VARYING(150) NOT NULL,

  CONSTRAINT pk_banco PRIMARY KEY(id)

) WITH(OIDS = FALSE);

ALTER TABLE carteira_pesca.banco OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.banco TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.banco TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.banco IS 'Entidade responsável por armazenar os dados das instituições bancárias.';
COMMENT ON COLUMN carteira_pesca.banco.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.banco.codigo IS 'Código de identificação do banco.';
COMMENT ON COLUMN carteira_pesca.banco.nome IS 'Nome do banco.';

CREATE TABLE carteira_pesca.endereco
(
  id SERIAL NOT NULL,
  logradouro CHARACTER VARYING(200) NOT NULL,
  numero CHARACTER VARYING(8) NULL,
  complemento CHARACTER VARYING(50) NULL,
  bairro CHARACTER VARYING(100) NOT NULL,
  cep CHARACTER VARYING(10) NOT NULL,
  municipio CHARACTER VARYING(100) NOT NULL,
  estado CHARACTER VARYING(2) NOT NULL,

  CONSTRAINT pk_endereco PRIMARY KEY(id)

) WITH(OIDS = FALSE);

ALTER TABLE carteira_pesca.endereco OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.endereco TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.endereco TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.endereco IS 'Entidade responsável por armazenar o endereço do beneficiário.';
COMMENT ON COLUMN carteira_pesca.endereco.logradouro IS 'Descrição do endereço do beneficiário (rua, av, fazenda, sitio...).';
COMMENT ON COLUMN carteira_pesca.endereco.numero IS 'Número.';
COMMENT ON COLUMN carteira_pesca.endereco.complemento IS 'Complemento.';
COMMENT ON COLUMN carteira_pesca.endereco.bairro IS 'Bairro.';
COMMENT ON COLUMN carteira_pesca.endereco.cep IS 'CEP.';
COMMENT ON COLUMN carteira_pesca.endereco.municipio IS 'Município.';
COMMENT ON COLUMN carteira_pesca.endereco.estado IS 'Sigla do estado.';

CREATE TABLE carteira_pesca.beneficiario_titulo
(
  id SERIAL NOT NULL,
  nome CHARACTER VARYING(200) NOT NULL,
  sigla CHARACTER VARYING(10) NULL,
  cpf_cnpj CHARACTER VARYING(14) NOT NULL,
  id_banco INTEGER NOT NULL,
  id_endereco INTEGER NOT NULL,
  codigo_beneficiario CHARACTER VARYING(10) NOT NULL,
  digito_codigo_beneficiario CHARACTER VARYING(2) NOT NULL,
  agencia CHARACTER VARYING(5) NOT NULL,
  digito_agencia CHARACTER VARYING(1) NOT NULL,
  conta_corrente CHARACTER VARYING(5) NOT NULL,
  digito_conta_corrente CHARACTER VARYING(1) NOT NULL,
  convenio CHARACTER VARYING(10) NOT NULL,
  carteira CHARACTER VARYING(5) NOT NULL,
  fl_ativo BOOLEAN NOT NULL DEFAULT TRUE,

  CONSTRAINT pk_beneficiario PRIMARY KEY(id),

  CONSTRAINT fk_banco FOREIGN KEY (id_banco)
      REFERENCES carteira_pesca.banco (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,

  CONSTRAINT fk_endereco FOREIGN KEY (id_endereco)
      REFERENCES carteira_pesca.endereco (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT

) WITH(OIDS = FALSE);

ALTER TABLE carteira_pesca.beneficiario_titulo OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.beneficiario_titulo TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.beneficiario_titulo TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.beneficiario_titulo IS 'Entidade responsável por armazenar os dados do beneficiario do título.';
COMMENT ON COLUMN carteira_pesca.beneficiario_titulo.nome IS 'Nome do beneficiário.';
COMMENT ON COLUMN carteira_pesca.beneficiario_titulo.sigla IS 'sigla do nome beneficiário.';
COMMENT ON COLUMN carteira_pesca.beneficiario_titulo.cpf_cnpj IS 'CPF/CNPJ do beneficário (sem mascara).';
COMMENT ON COLUMN carteira_pesca.beneficiario_titulo.id_banco IS 'Identifica a qual banco está vinculado a conta.';
COMMENT ON COLUMN carteira_pesca.beneficiario_titulo.id_endereco IS 'Identifica a qual o endereço do beneficiário.';
COMMENT ON COLUMN carteira_pesca.beneficiario_titulo.codigo_beneficiario IS 'Código do beneficiário.';
COMMENT ON COLUMN carteira_pesca.beneficiario_titulo.digito_codigo_beneficiario IS 'Digíto código do beneficiário.';
COMMENT ON COLUMN carteira_pesca.beneficiario_titulo.agencia IS 'Número agencia.';
COMMENT ON COLUMN carteira_pesca.beneficiario_titulo.digito_agencia IS 'Digíto número agência.';
COMMENT ON COLUMN carteira_pesca.beneficiario_titulo.convenio IS 'Número do convênio.';
COMMENT ON COLUMN carteira_pesca.beneficiario_titulo.carteira IS 'Número da carteira.';

COMMENT ON COLUMN carteira_pesca.beneficiario_titulo.fl_ativo IS 'Indica se a conta do beneficiário está ativa.';

CREATE TABLE carteira_pesca.especie_documento
(
  id INTEGER NOT NULL,
  codigo CHARACTER VARYING(10) NOT NULL,
  codigo_remessa CHARACTER VARYING(2) NOT NULL,
  descricao CHARACTER VARYING(100) NOT NULL,

  CONSTRAINT pk_especie_documento PRIMARY KEY(id)

) WITH(OIDS = FALSE);

ALTER TABLE carteira_pesca.especie_documento OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.especie_documento TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.especie_documento TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.especie_documento IS 'Entidade responsável por armazenar os espécies de documentos.';
COMMENT ON COLUMN carteira_pesca.especie_documento.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.especie_documento.codigo IS 'Código de identificação da especie documento.';
COMMENT ON COLUMN carteira_pesca.especie_documento.descricao IS 'Descrição espécie de documento.';

CREATE TABLE carteira_pesca.pagador_titulo
(
  id SERIAL NOT NULL,
  nome CHARACTER VARYING(200) NOT NULL,
  cpf_passaporte CHARACTER VARYING(28) NOT NULL,
  id_endereco INTEGER NOT NULL,

  CONSTRAINT pk_pagador_titulo PRIMARY KEY(id),

  CONSTRAINT fk_endereco FOREIGN KEY (id_endereco)
    REFERENCES carteira_pesca.endereco (id) MATCH SIMPLE
    ON UPDATE RESTRICT ON DELETE RESTRICT


) WITH(OIDS = FALSE);

ALTER TABLE carteira_pesca.pagador_titulo OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.pagador_titulo TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.pagador_titulo TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.pagador_titulo IS 'Entidade responsável por armazenar o pagador do título.';
COMMENT ON COLUMN carteira_pesca.pagador_titulo.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.pagador_titulo.nome IS 'Nome pagador.';
COMMENT ON COLUMN carteira_pesca.pagador_titulo.cpf_passaporte IS 'CPF se for brasileiro ou Passaporte caso pessoa estrangeira.';

CREATE TABLE carteira_pesca.titulo
(
  id SERIAL NOT NULL,
  id_beneficiario INTEGER NOT NULL,
  id_pagador INTEGER NOT NULL,
  id_especie_documento INTEGER NOT NULL,
  valor DOUBLE PRECISION NOT NULL,
  dt_emissao DATE NOT NULL,
  dt_processamento DATE NOT NULL,
  dt_vencimento DATE NOT NULL,
  instrucoes TEXT NULL,
  local_pagamento TEXT NULL,
  fl_enviado_banco BOOLEAN NOT NULL DEFAULT FALSE,
  nosso_numero CHARACTER VARYING(11) NOT NULL,

  CONSTRAINT pk_titulo PRIMARY KEY(id),

  CONSTRAINT fk_endereco FOREIGN KEY (id_beneficiario)
      REFERENCES carteira_pesca.beneficiario_titulo (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT

) WITH(OIDS = FALSE);

ALTER TABLE carteira_pesca.titulo OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.titulo TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.titulo TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.titulo IS 'Entidade responsável por armazenar os dados do beneficiario do título.';
COMMENT ON COLUMN carteira_pesca.titulo.id_beneficiario IS 'Beneficiário do título.';
COMMENT ON COLUMN carteira_pesca.titulo.id_pagador IS 'Responsável pela pagamento do titulo.';
COMMENT ON COLUMN carteira_pesca.titulo.id_especie_documento IS 'Espécie de documento.';
COMMENT ON COLUMN carteira_pesca.titulo.dt_emissao IS 'Data que o titulo foi gerado.';
COMMENT ON COLUMN carteira_pesca.titulo.dt_processamento IS 'Data que o titulo foi processado pelo banco.';
COMMENT ON COLUMN carteira_pesca.titulo.dt_vencimento IS 'Data de vencimento.';
COMMENT ON COLUMN carteira_pesca.titulo.instrucoes IS 'Instruções de pagamento.';
COMMENT ON COLUMN carteira_pesca.titulo.local_pagamento IS 'Locais onde o pagamento poderá ser realizado.';
COMMENT ON COLUMN carteira_pesca.titulo.nosso_numero IS
  'Código de controle que permite ao Banco e ao beneficiário identificar os dados da cobrança que deu origem ao boleto de pagamento.';

INSERT INTO carteira_pesca.banco (id, codigo, nome) VALUES (1, '237', 'Banco Bradesco S.A.');

INSERT INTO carteira_pesca.endereco(logradouro, numero, complemento, bairro, cep, municipio, estado) VALUES ('Av Mário Ypiranga', '3280', NULL, 'Parque Dez de Novembro', '69050-030', 'Manaus', 'AM');

INSERT INTO carteira_pesca.beneficiario_titulo (nome, sigla, cpf_cnpj, id_banco, id_endereco, codigo_beneficiario, digito_codigo_beneficiario, agencia, digito_agencia, conta_corrente, digito_conta_corrente, convenio, carteira, fl_ativo)
  VALUES ('Instituto de Proteção Ambiental do Amazonas', 'IPAAM', '04624888000194', 1, 1, '16065', '2', '3739', '7', '16065', '2', '4928031', '09', TRUE);

INSERT INTO carteira_pesca.especie_documento (id, codigo, codigo_remessa, descricao) VALUES (1, 'DM', '01', 'Duplicata Mercantil');
INSERT INTO carteira_pesca.especie_documento (id, codigo, codigo_remessa, descricao) VALUES (2, 'NP', '02','Nota Promissória');
INSERT INTO carteira_pesca.especie_documento (id, codigo, codigo_remessa, descricao) VALUES (3, 'NS', '03','Nota de Seguro');
INSERT INTO carteira_pesca.especie_documento (id, codigo, codigo_remessa, descricao) VALUES (4, 'CS', '04','Cobrança Seriada');
INSERT INTO carteira_pesca.especie_documento (id, codigo, codigo_remessa, descricao) VALUES (5, 'REC', '05', 'Recibo');
INSERT INTO carteira_pesca.especie_documento (id, codigo, codigo_remessa, descricao) VALUES (6, 'LC', '10', 'Letras de Câmbio');
INSERT INTO carteira_pesca.especie_documento (id, codigo, codigo_remessa, descricao) VALUES (7, 'ND', '11', 'Nota de Débito');
INSERT INTO carteira_pesca.especie_documento (id, codigo, codigo_remessa, descricao) VALUES (8, 'NS', '12', 'Duplicata de Serviços');
INSERT INTO carteira_pesca.especie_documento (id, codigo, codigo_remessa, descricao) VALUES (9, 'CC', '31','Cartão de crédito');
INSERT INTO carteira_pesca.especie_documento (id, codigo, codigo_remessa, descricao) VALUES (10, 'OUTROS', '99', 'Outros');
