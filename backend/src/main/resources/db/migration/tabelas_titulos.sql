CREATE TABLE carteira_pesca.banco
(
  id SERIAL NOT NULL,
  codigo CHARACTER VARYING(5) NOT NULL,
  nome CHARACTER VARYING(150) NOT NULL

  CONSTRAINT pk_banco PRIMARY KEY(id)

) WITH(OIDS = FALSE);

ALTER TABLE carteira_pesca.banco OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.banco TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.banco TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.banco IS 'Entidade responsável por armazenar os dados das instituições bancárias.';
COMMENT ON COLUMN carteira_pesca.banco.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.banco.codigo IS 'Código de identificação do banco.';
COMMENT ON COLUMN carteira_pesca.banco.nome IS 'Nome do banco.';

CREATE TABLE carteira_pesca.endereco_beneficiario
(
  id SERIAL NOT NULL,
  logradouro CHARACTER VARYING(200) NOT NULL,
  numero CHARACTER VARYING(8) NOT NULL,
  complemento CHARACTER VARYING(50) NULL,
  bairro CHARACTER VARYING(100) NOT NULL,
  cep CHARACTER VARYING(10) NOT NULL,
  municipio CHARACTER VARYING(100) NOT NULL,
  estado CHARACTER VARYING(2) NOT NULL

  CONSTRAINT pk_endereco_beneficiario PRIMARY KEY(id)

) WITH(OIDS = FALSE);

ALTER TABLE carteira_pesca.endereco_beneficiario OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.endereco_beneficiario TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.endereco_beneficiario TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.endereco_beneficiario IS 'Entidade responsável por armazenar o endereço do beneficiário.';
COMMENT ON COLUMN carteira_pesca.endereco_beneficiario.logradouro IS 'Descrição do endereço do beneficiário (rua, av, fazenda, sitio...).';
COMMENT ON COLUMN carteira_pesca.endereco_beneficiario.numero IS 'Número.';
COMMENT ON COLUMN carteira_pesca.endereco_beneficiario.complemento IS 'Complemento.';
COMMENT ON COLUMN carteira_pesca.endereco_beneficiario.bairro IS 'Bairro.';
COMMENT ON COLUMN carteira_pesca.endereco_beneficiario.cep IS 'CEP.';
COMMENT ON COLUMN carteira_pesca.endereco_beneficiario.municipio IS 'Município.';
COMMENT ON COLUMN carteira_pesca.endereco_beneficiario.estado IS 'Sigla do estado.';

CREATE TABLE carteira_pesca.beneficiario
(
  id SERIAL NOT NULL,
  nome CHARACTER VARYING(255) NOT NULL,
  id_banco INTEGER NOT NULL,
  id_endereco INTEGER NOT NULL,
  codigo_beneficiario CHARACTER VARYING(10) NOT NULL,
  digito_codigo_beneficiario CHARACTER VARYING(2) NOT NULL,
  agencia CHARACTER VARYING(5) NOT NULL,
  digito_agencia CHARACTER VARYING(2) NOT NULL,
  convenio CHARACTER VARYING(10) NOT NULL,
  carteira CHARACTER VARYING(5) NOT NULL,
  nosso_numero CHARACTER VARYING(20) NOT NULL,
  digito_nosso_numero CHARACTER VARYING(2) NOT NULL

  CONSTRAINT pk_beneficiario PRIMARY KEY(id),

  CONSTRAINT fk_banco FOREIGN KEY (id_banco)
      REFERENCES carteira_pesca.banco (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,

  CONSTRAINT fk_endereco_beneficiario FOREIGN KEY (id_endereco)
      REFERENCES carteira_pesca.endereco_beneficiario (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT

) WITH(OIDS = FALSE);

ALTER TABLE carteira_pesca.beneficiario OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.beneficiario TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.beneficiario TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.beneficiario IS 'Entidade responsável por armazenar os dados do beneficiario do título.';
COMMENT ON COLUMN carteira_pesca.beneficiario.nome IS 'Nome o beneficiário.';
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

CREATE TABLE carteira_pesca.titulo
(
  id SERIAL NOT NULL,
  id_beneficiario INTEGER NOT NULL,
  dt_criacao DATE NOT NULL,
  dt_processamento DATE NOT NULL,
  dt_vencimento DATE NOT NULL,
  especie_documento CHARACTER VARYING(5) NOT NULL,
  instrucoes TEXT NULL,
  local_pagamento TEXT NULL

  CONSTRAINT pk_titulo PRIMARY KEY(id),

  CONSTRAINT fk_endereco_beneficiario FOREIGN KEY (id_beneficiario)
      REFERENCES carteira_pesca.beneficiario (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT

) WITH(OIDS = FALSE);

ALTER TABLE carteira_pesca.titulo OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.titulo TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.titulo TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.titulo IS 'Entidade responsável por armazenar os dados do beneficiario do título.';
COMMENT ON COLUMN carteira_pesca.titulo.nome IS 'Nome o beneficiário';
COMMENT ON COLUMN carteira_pesca.titulo.id_beneficiario IS 'Código do beneficiário.';
COMMENT ON COLUMN carteira_pesca.titulo.dt_criacao IS 'Data que o titulo foi gerado.';
COMMENT ON COLUMN carteira_pesca.titulo.dt_processamento IS 'Data que o titulo foi processado pelo banco.';
COMMENT ON COLUMN carteira_pesca.titulo.dt_vencimento IS 'Data de vencimento.';
COMMENT ON COLUMN carteira_pesca.titulo.especie_documento IS
  'Tipo de Documento, conforme padrão FEBRABAN de 240 posições, segmento cobrança, que originou o boleto de pagamento (exemplo: DM - Duplicata Mercantil, DS - Duplicata de Prestação de Serviços, NP - Nota Promissória).';
COMMENT ON COLUMN carteira_pesca.titulo.instrucoes IS 'Instruções de pagamento.';
COMMENT ON COLUMN carteira_pesca.titulo.local_pagamento IS 'Locais onde o pagamento poderá ser realizado.';
