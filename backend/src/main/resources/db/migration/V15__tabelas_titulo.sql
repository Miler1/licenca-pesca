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
  numero CHARACTER VARYING(8) NOT NULL,
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

CREATE TABLE carteira_pesca.beneficiario
(
  id SERIAL NOT NULL,
  nome CHARACTER VARYING(200) NOT NULL,
  cpf_cnpj CHARACTER VARYING(14) NOT NULL,
  id_banco INTEGER NOT NULL,
  id_endereco INTEGER NOT NULL,
  codigo_beneficiario CHARACTER VARYING(10) NOT NULL,
  digito_codigo_beneficiario CHARACTER VARYING(2) NOT NULL,
  agencia CHARACTER VARYING(5) NOT NULL,
  digito_agencia CHARACTER VARYING(2) NOT NULL,
  convenio CHARACTER VARYING(10) NOT NULL,
  carteira CHARACTER VARYING(5) NOT NULL,
  nosso_numero CHARACTER VARYING(20) NOT NULL,
  digito_nosso_numero CHARACTER VARYING(2) NOT NULL,
  fl_ativo BOOLEAN NOT NULL DEFAULT TRUE,

  CONSTRAINT pk_beneficiario PRIMARY KEY(id),

  CONSTRAINT fk_banco FOREIGN KEY (id_banco)
      REFERENCES carteira_pesca.banco (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,

  CONSTRAINT fk_endereco FOREIGN KEY (id_endereco)
      REFERENCES carteira_pesca.endereco (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT

) WITH(OIDS = FALSE);

ALTER TABLE carteira_pesca.beneficiario OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.beneficiario TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.beneficiario TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.beneficiario IS 'Entidade responsável por armazenar os dados do beneficiario do título.';
COMMENT ON COLUMN carteira_pesca.beneficiario.nome IS 'Nome o beneficiário.';
COMMENT ON COLUMN carteira_pesca.beneficiario.cpf_cnpj IS 'CPF/CNPJ do beneficário (sem mascara).';
COMMENT ON COLUMN carteira_pesca.beneficiario.id_banco IS 'Identifica a qual banco está vinculado a conta.';
COMMENT ON COLUMN carteira_pesca.beneficiario.id_endereco IS 'Identifica a qual o endereço do beneficiário.';
COMMENT ON COLUMN carteira_pesca.beneficiario.codigo_beneficiario IS 'Código do beneficiário.';
COMMENT ON COLUMN carteira_pesca.beneficiario.digito_codigo_beneficiario IS 'Digíto código do beneficiário.';
COMMENT ON COLUMN carteira_pesca.beneficiario.agencia IS 'Número agencia.';
COMMENT ON COLUMN carteira_pesca.beneficiario.digito_agencia IS 'Digíto número agência.';
COMMENT ON COLUMN carteira_pesca.beneficiario.convenio IS 'Número do convênio.';
COMMENT ON COLUMN carteira_pesca.beneficiario.carteira IS 'Número da carteira.';
COMMENT ON COLUMN carteira_pesca.beneficiario.nosso_numero IS
  'Código de controle que permite ao Banco e ao beneficiário identificar os dados da cobrança que deu origem ao boleto de pagamento.';
COMMENT ON COLUMN carteira_pesca.beneficiario.digito_nosso_numero IS 'Digíto nosso número.';
COMMENT ON COLUMN carteira_pesca.beneficiario.fl_ativo IS 'Indica se a conta do beneficiário está ativa.';

CREATE TABLE carteira_pesca.especie_documento
(
  id INTEGER NOT NULL,
  codigo CHARACTER VARYING(10) NOT NULL,
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
  dt_criacao DATE NOT NULL,
  dt_processamento DATE NOT NULL,
  dt_vencimento DATE NOT NULL,
  especie_documento CHARACTER VARYING(5) NOT NULL,
  instrucoes TEXT NULL,
  local_pagamento TEXT NULL,

  CONSTRAINT pk_titulo PRIMARY KEY(id),

  CONSTRAINT fk_endereco FOREIGN KEY (id_beneficiario)
      REFERENCES carteira_pesca.beneficiario (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT

) WITH(OIDS = FALSE);

ALTER TABLE carteira_pesca.titulo OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.titulo TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.titulo TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.titulo IS 'Entidade responsável por armazenar os dados do beneficiario do título.';
COMMENT ON COLUMN carteira_pesca.titulo.id_beneficiario IS 'Beneficiário do título.';
COMMENT ON COLUMN carteira_pesca.titulo.id_pagador IS 'Responsável pela pagamento do titulo.';
COMMENT ON COLUMN carteira_pesca.titulo.id_especie_documento IS 'Espécie de documento.';
COMMENT ON COLUMN carteira_pesca.titulo.dt_criacao IS 'Data que o titulo foi gerado.';
COMMENT ON COLUMN carteira_pesca.titulo.dt_processamento IS 'Data que o titulo foi processado pelo banco.';
COMMENT ON COLUMN carteira_pesca.titulo.dt_vencimento IS 'Data de vencimento.';
COMMENT ON COLUMN carteira_pesca.titulo.instrucoes IS 'Instruções de pagamento.';
COMMENT ON COLUMN carteira_pesca.titulo.local_pagamento IS 'Locais onde o pagamento poderá ser realizado.';

INSERT INTO carteira_pesca.banco (id, codigo, nome) VALUES (1, '237', 'Banco Bradesco S.A.');

INSERT INTO carteira_pesca.endereco (id, logradouro, numero, bairro, cep, municipio, estado) 
    VALUES (1, 'Av Mário Ypiranga', '3280', 'Parque Dez de Novembro', '69050-030', 'Manaus', 'AM');

INSERT INTO carteira_pesca.beneficiario (id, nome, cpf_cnpj, id_banco, id_endereco, codigo_beneficiario, digito_codigo_beneficiario, agencia, digito_agencia, convenio, carteira, nosso_numero, digito_nosso_numero, fl_ativo)
  VALUES (1, 'Instituto de Proteção Ambiental do Amazonas', '04624888000194', 1, 1, '16065', '2', '3739', '7', '4928031', '09', '00000001798', '4', TRUE);


INSERT INTO carteira_pesca.especie_documento (id, codigo, descricao) VALUES (1, 'DM', 'Duplicata Mercantil');
INSERT INTO carteira_pesca.especie_documento (id, codigo, descricao) VALUES (2, 'NP', 'Nota Promissória');
INSERT INTO carteira_pesca.especie_documento (id, codigo, descricao) VALUES (3, 'NS', 'Nota de Seguro');
INSERT INTO carteira_pesca.especie_documento (id, codigo, descricao) VALUES (4, 'CS', 'Cobrança Seriada');
INSERT INTO carteira_pesca.especie_documento (id, codigo, descricao) VALUES (5, 'REC', 'Recibo');
INSERT INTO carteira_pesca.especie_documento (id, codigo, descricao) VALUES (6, 'LC', 'Letras de Câmbio');
INSERT INTO carteira_pesca.especie_documento (id, codigo, descricao) VALUES (7, 'ND', 'Nota de Débito');
INSERT INTO carteira_pesca.especie_documento (id, codigo, descricao) VALUES (8, 'NS', 'Duplicata de Serviços');
INSERT INTO carteira_pesca.especie_documento (id, codigo, descricao) VALUES (9, 'CC', 'Cartão de crédito');
INSERT INTO carteira_pesca.especie_documento (id, codigo, descricao) VALUES (10, 'OUTROS', 'Outros');
