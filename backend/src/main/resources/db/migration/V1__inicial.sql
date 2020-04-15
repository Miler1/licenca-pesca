CREATE SCHEMA carteira_pesca;

GRANT ALL ON SCHEMA carteira_pesca TO postgres;
GRANT USAGE ON SCHEMA carteira_pesca TO carteira_pesca;
ALTER SCHEMA carteira_pesca OWNER TO postgres;

--v2
CREATE TABLE carteira_pesca.modalidade
(
  id                       INTEGER NOT NULL,
  nome                     CHARACTER VARYING (50) NOT NULL,

  CONSTRAINT pk_modalidade PRIMARY KEY (id)
) WITH (OIDS = FALSE);

ALTER TABLE carteira_pesca.modalidade OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.modalidade TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.modalidade TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.modalidade IS
'Entidade responsável por armazenar as possíveis modalidade de pesca.';

COMMENT ON COLUMN carteira_pesca.modalidade.id IS
'Identificador único da entidade.';

COMMENT ON COLUMN carteira_pesca.modalidade.nome IS
'Nome da modalidade.';

INSERT INTO carteira_pesca.modalidade VALUES
(0, 'Pesca Esportiva'),
(1, 'Pesca Recreativa');

CREATE TABLE carteira_pesca.local_pesca
(
  id                     INTEGER NOT NULL,
  descricao              CHARACTER VARYING (50) NOT NULL,

  CONSTRAINT pk_local_pesca PRIMARY KEY (id)
) WITH (OIDS = FALSE);

ALTER TABLE carteira_pesca.local_pesca OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.local_pesca TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.local_pesca TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.local_pesca IS
'Entidade responsável por armazenar as possíveis opções de local.';

COMMENT ON COLUMN carteira_pesca.local_pesca.id IS
'Identificador único da entidade.';

COMMENT ON COLUMN carteira_pesca.local_pesca.descricao IS
'Descrição das opções de renda mensal.';

INSERT INTO carteira_pesca.local_pesca VALUES
(0, 'Localmente'),
(1, 'Em outros estados'),
(2, 'Em outros países');

CREATE TABLE carteira_pesca.renda_mensal
(
  id                         INTEGER NOT NULL,
  descricao                  CHARACTER VARYING (50) NOT NULL,

  CONSTRAINT pk_renda_mensal PRIMARY KEY (id)

) WITH (OIDS = FALSE);

ALTER TABLE carteira_pesca.renda_mensal OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.renda_mensal TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.renda_mensal TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.renda_mensal IS
'Entidade responsável por armazenar as possíveis opções de renda mensal.';

COMMENT ON COLUMN carteira_pesca.renda_mensal.id IS
'Identificador único da entidade.';

COMMENT ON COLUMN carteira_pesca.renda_mensal.descricao IS
'Descrição das opções de renda mensal.';

INSERT INTO carteira_pesca.renda_mensal VALUES
(0, 'Nenhuma renda'),
(1, 'Até 1 salário mínimo'),
(2, 'De 1 a 3 salários mínimos'),
(3, 'De 3 a 6 salários mínimos'),
(4, 'De 6 a 9 salários mínimos'),
(5, 'De 9 a 12 salários mínimos'),
(6, 'De 12 a 15 salários mínimos'),
(7, 'Mais de 15 salários mínimos');

CREATE TABLE carteira_pesca.preferencia_local_pesca
(
  id                              INTEGER NOT NULL,
  descricao                       CHARACTER VARYING (50) NOT NULL,

  CONSTRAINT pk_preferencia_local_pesca PRIMARY KEY (id)

) WITH (OIDS = FALSE);

ALTER TABLE carteira_pesca.preferencia_local_pesca OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.preferencia_local_pesca TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.preferencia_local_pesca TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.preferencia_local_pesca IS
'Entidade responsável por armazenar as possíveis opções de local de pesca, podendo ser Mar ou Água Doce.';

COMMENT ON COLUMN carteira_pesca.preferencia_local_pesca.id IS
'Identificador único da entidade.';

COMMENT ON COLUMN carteira_pesca.preferencia_local_pesca.descricao IS
'Descrição das opções de local de pesca, podendo ser Mar ou Água Doce.';

INSERT INTO carteira_pesca.preferencia_local_pesca VALUES
(0, 'Mar'),
(1, 'Água doce');

CREATE TABLE carteira_pesca.equipamento
(
  id                       INTEGER NOT NULL,
  nome                     CHARACTER VARYING (50) NOT NULL,

  CONSTRAINT pk_equipamento PRIMARY KEY (id)

) WITH (OIDS = FALSE);

ALTER TABLE carteira_pesca.equipamento OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.equipamento TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.equipamento TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.equipamento IS
'Entidade responsável por armazenar os materiais/equipamento utilizados na pesca.';

COMMENT ON COLUMN carteira_pesca.equipamento.id IS
'Identificador único da entidade.';

COMMENT ON COLUMN carteira_pesca.equipamento.nome IS
'Nome dos materiais/equipamentoa.';

INSERT INTO carteira_pesca.equipamento VALUES
(0, 'Linha Caniço'),
(1, 'Carretilha Molinete'),
(2, 'Fly'),
(3, 'Outros');

CREATE TABLE carteira_pesca.tipo_isca
(
  id                              INTEGER NOT NULL,
  descricao                       CHARACTER VARYING (50) NOT NULL,

  CONSTRAINT pk_tipo_isca PRIMARY KEY (id)

) WITH (OIDS = FALSE);

ALTER TABLE carteira_pesca.tipo_isca OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.tipo_isca TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.tipo_isca TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.tipo_isca IS
'Entidade responsável por armazenar as possíveis tipos de isca usados na pesca.';

COMMENT ON COLUMN carteira_pesca.tipo_isca.id IS
'Identificador único da entidade.';

COMMENT ON COLUMN carteira_pesca.tipo_isca.descricao IS
'Descrição dos tipos de isca.';

INSERT INTO carteira_pesca.tipo_isca VALUES
(0, 'Natural'),
(1, 'Artificial');

CREATE TABLE carteira_pesca.informacao_complementar
(
  id                                                 SERIAL NOT NULL,
  id_modalidade                                      INTEGER NOT NULL,
  id_local_pesca                                     INTEGER NOT NULL,
  id_renda_mensal                                    INTEGER NOT NULL,
  nu_dia_ano                                         CHARACTER VARYING (50) NOT NULL,
  nu_gasto_medio                                     CHARACTER VARYING (50) NOT NULL,
  nu_faixa_etaria                                    INTEGER NOT NULL,
  id_preferencia_local_pesca                         INTEGER NOT NULL,
  id_equipamento                                     INTEGER NOT NULL,
  id_tipo_isca                                       INTEGER NOT NULL,
  id_modalidade_mais_praticada                       INTEGER NOT NULL,
  fl_agencia_turismo                                 BOOLEAN,

  CONSTRAINT pk_informacao_complementar PRIMARY KEY (id),

  CONSTRAINT fk_ic_modalidade FOREIGN KEY (id_modalidade)
      REFERENCES carteira_pesca.modalidade (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,     

  CONSTRAINT fk_ic_local_pesca FOREIGN KEY (id_local_pesca)
      REFERENCES carteira_pesca.local_pesca (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,

  CONSTRAINT fk_ic_renda_mensal FOREIGN KEY (id_renda_mensal)
      REFERENCES carteira_pesca.renda_mensal (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT, 

  CONSTRAINT fk_ic_preferencia_local_pesca FOREIGN KEY (id_preferencia_local_pesca)
      REFERENCES carteira_pesca.preferencia_local_pesca (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,

  CONSTRAINT fk_ic_equipamento FOREIGN KEY (id_equipamento)
      REFERENCES carteira_pesca.equipamento (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,

  CONSTRAINT fk_ic_tipo_isca FOREIGN KEY (id_tipo_isca)
      REFERENCES carteira_pesca.tipo_isca (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,

  CONSTRAINT fk_ic_modalidade_mais_praticada FOREIGN KEY (id_modalidade_mais_praticada)
      REFERENCES carteira_pesca.modalidade (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT 

) WITH (OIDS = FALSE);

ALTER TABLE carteira_pesca.informacao_complementar OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.informacao_complementar TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.informacao_complementar TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.informacao_complementar IS
'Entidade responsável por armazenar os dados da etapa informação complementar.';

COMMENT ON COLUMN carteira_pesca.informacao_complementar.id IS
'Identificador único da entidade.';

COMMENT ON COLUMN carteira_pesca.informacao_complementar.id_modalidade IS
'Identificador único da entidade modalidade que realizará o relacionamento entre modalidade e informacao_complementar.';

COMMENT ON COLUMN carteira_pesca.informacao_complementar.id_local_pesca IS
'Identificador único da entidade local_pesca que realizará o relacionamento entre local_pesca e informacao_complementar.';

COMMENT ON COLUMN carteira_pesca.informacao_complementar.id_renda_mensal IS
'Identificador único da entidade renda_mensal que realizará o relacionamento entre renda_mensal e informacao_complementar.';

COMMENT ON COLUMN carteira_pesca.informacao_complementar.nu_dia_ano IS
'Média de quantos dias por ano costuma pescar.';

COMMENT ON COLUMN carteira_pesca.informacao_complementar.nu_gasto_medio IS
'Gasto médio com a pesca (incluindo meios de transporte, alimentação, hospedagem, equipamentos e outros).';

COMMENT ON COLUMN carteira_pesca.informacao_complementar.nu_faixa_etaria IS
'Faixa etária.';

COMMENT ON COLUMN carteira_pesca.informacao_complementar.id_preferencia_local_pesca IS
'Identificador único da entidade preferencia_local_pesca que realizará o relacionamento entre preferencia_local_pesca e informacao_complementar.';

COMMENT ON COLUMN carteira_pesca.informacao_complementar.id_equipamento IS
'Identificador único da entidade equipamento que realizará o relacionamento entre equipamento e informacao_complementar.';

COMMENT ON COLUMN carteira_pesca.informacao_complementar.id_tipo_isca IS
'Identificador único da entidade tipo_isca que realizará o relacionamento entre tipo_isca e informacao_complementar.';

COMMENT ON COLUMN carteira_pesca.informacao_complementar.id_modalidade_mais_praticada IS
'Identificador único da entidade modalidade_mais_praticada que realizará o relacionamento entre modalidade_mais_praticada e informacao_complementar.';

COMMENT ON COLUMN carteira_pesca.informacao_complementar.fl_agencia_turismo IS
'Indica se possui o costume de programar as pescarias com agência de turismo.';


--v3
CREATE TABLE carteira_pesca.faixa_etaria
(
  id                      INTEGER NOT NULL,
  descricao               CHARACTER VARYING (50) NOT NULL,

  CONSTRAINT pk_faixa_etaria PRIMARY KEY (id)

) WITH (OIDS = FALSE);

ALTER TABLE carteira_pesca.faixa_etaria OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.faixa_etaria TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.faixa_etaria TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.faixa_etaria IS
'Entidade responsável por armazenar as possíveis faixas etária dos praticantes de pesca.';

COMMENT ON COLUMN carteira_pesca.faixa_etaria.id IS
'Identificador único da entidade.';

COMMENT ON COLUMN carteira_pesca.faixa_etaria.descricao IS
'Descrição da faixa etária.';

INSERT INTO carteira_pesca.faixa_etaria VALUES
(0, 'De 0 a 20 anos'),
(1, 'De 21 a 30 anos'),
(2, 'De 31 a 40 anos'),
(3, 'De 41 a 50 anos'),
(4, 'De 51 a 60 anos'),
(5, 'De 61 a 70 anos'),
(6, 'Até 120 anos');

ALTER TABLE carteira_pesca.informacao_complementar RENAME COLUMN nu_faixa_etaria TO id_faixa_etaria;
ALTER TABLE carteira_pesca.informacao_complementar ADD
   CONSTRAINT fk_ic_faixa_etaria FOREIGN KEY (id_faixa_etaria)
      REFERENCES carteira_pesca.faixa_etaria (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT;

COMMENT ON COLUMN carteira_pesca.informacao_complementar.id_faixa_etaria IS
'Identificador único da entidade faixa_etaria que realizará o relacionamento entre faixa_etaria e informacao_complementar.';

--v4
CREATE TABLE carteira_pesca.protocolo_sequence
(
  id                        INTEGER NOT NULL,
  nom_sequence              CHARACTER VARYING (20) NOT NULL,
  val_sequence              INTEGER NOT NULL,
  idt_modalidade            INTEGER NOT NULL,
  val_sequence_year         INTEGER NOT NULL,

  CONSTRAINT pk_protocolo_sequence PRIMARY KEY (id)

) WITH (OIDS = FALSE );

ALTER TABLE carteira_pesca.protocolo_sequence OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.protocolo_sequence TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.protocolo_sequence TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.protocolo_sequence IS
'Entidade responsável por armazenar a sequência para gerar o número de protocolo.';

COMMENT ON COLUMN carteira_pesca.protocolo_sequence.id IS
'Identificador único da entidade.';

COMMENT ON COLUMN carteira_pesca.protocolo_sequence.nom_sequence IS
'Nome da sequência do protocolo.';

COMMENT ON COLUMN carteira_pesca.protocolo_sequence.val_sequence IS
'Valor da sequência do protocolo.';

COMMENT ON COLUMN carteira_pesca.protocolo_sequence.idt_modalidade IS
'Identificador representando o id da modalidade da sequência.';

COMMENT ON COLUMN carteira_pesca.protocolo_sequence.val_sequence_year IS
'Ano em vigência da sequência.';

INSERT INTO carteira_pesca.protocolo_sequence VALUES
(0, 'seq_esportiva', 0, 0, 2018),
(1, 'seq_recreativa', 0, 1, 2018);

--v5
CREATE TABLE carteira_pesca.protocolo
(
  id                       SERIAL NOT NULL,
  val_codigo               CHARACTER VARYING (11) NOT NULL,

  CONSTRAINT pk_protocolo PRIMARY KEY (id)

) WITH (OIDS = FALSE);

ALTER TABLE carteira_pesca.protocolo OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.protocolo TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.protocolo TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.protocolo IS
'Entidade responsável por armazenar os protocolos.';

COMMENT ON COLUMN carteira_pesca.protocolo.id IS
'Identificador único da entidade.';

COMMENT ON COLUMN carteira_pesca.protocolo.val_codigo IS
'Valor do código do Protocolo.';

CREATE TABLE carteira_pesca.passaporte
(
  id                    SERIAL NOT NULL,
  val_numero            CHARACTER VARYING (50) NOT NULL,

  CONSTRAINT pk_passaporte PRIMARY KEY (id)

) WITH (OIDS = FALSE);

ALTER TABLE carteira_pesca.passaporte OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.passaporte TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.passaporte TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.passaporte IS
'Entidade responsável por armazenar os dados de passaporte.';

COMMENT ON COLUMN carteira_pesca.passaporte.id IS
'Identificador único da entidade.';

COMMENT ON COLUMN carteira_pesca.passaporte.val_numero IS
'O número do passaporte.';

CREATE TABLE carteira_pesca.cpf
(
  id                        SERIAL NOT NULL,
  val_numero_cpf            CHARACTER VARYING (50) NOT NULL,

  CONSTRAINT pk_cpf PRIMARY KEY (id)

) WITH (OIDS = FALSE);

ALTER TABLE carteira_pesca.cpf OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.cpf TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.cpf TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.cpf IS
'Entidade responsável por armazenar os dados de CPF.';

COMMENT ON COLUMN carteira_pesca.cpf.id IS
'Identificador único da entidade.';

COMMENT ON COLUMN carteira_pesca.cpf.val_numero_cpf IS
'O número do CPF.';

CREATE TABLE carteira_pesca.solicitante_id
(
  id                        SERIAL NOT NULL,
  idt_cpf                   INTEGER,
  idt_passaporte            INTEGER,

  CONSTRAINT pk_solicitante_id PRIMARY KEY (id),

  CONSTRAINT fk_cpf FOREIGN KEY (idt_cpf)
    REFERENCES carteira_pesca.cpf MATCH SIMPLE
    ON UPDATE RESTRICT ON DELETE RESTRICT,

  CONSTRAINT fk_passaporte FOREIGN KEY (idt_passaporte)
  REFERENCES carteira_pesca.passaporte MATCH SIMPLE
    ON UPDATE RESTRICT ON DELETE RESTRICT

) WITH (OIDS = FALSE);

ALTER TABLE carteira_pesca.solicitante_id OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.solicitante_id TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.solicitante_id TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.solicitante_id IS
'Entidade responsável por armazenar o identificador do solicitante.';

COMMENT ON COLUMN carteira_pesca.solicitante_id.id IS
'Identificador único da entidade.';

COMMENT ON COLUMN carteira_pesca.solicitante_id.idt_passaporte IS
'Identificador único da entidade solicitante_id que realizará o relacionamento entre passaporte e solicitante_id. ';

COMMENT ON COLUMN carteira_pesca.solicitante_id.idt_cpf IS
'Identificador único da entidade solicitante_id que realizará o relacionamento entre cpf e solicitante_id. ';

CREATE TABLE carteira_pesca.solicitante
(
  id                        SERIAL NOT NULL,
  idt_solicitante_id        INTEGER NOT NULL,

  CONSTRAINT pk_solicitante PRIMARY KEY (id),

  CONSTRAINT fk_solicitante_id FOREIGN KEY (idt_solicitante_id)
    REFERENCES carteira_pesca.solicitante_id MATCH SIMPLE
    ON UPDATE RESTRICT ON DELETE RESTRICT

) WITH (OIDS = FALSE);

ALTER TABLE carteira_pesca.solicitante_id OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.solicitante_id TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.solicitante_id TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.solicitante IS
'Entidade responsável por armazenar os dados do solicitante.';

COMMENT ON COLUMN carteira_pesca.solicitante.id IS
'Identificador único da entidade.';

COMMENT ON COLUMN carteira_pesca.solicitante.idt_solicitante_id IS
'Identificador único da entidade solicitante que realizará o relacionamento entre solicitante_id e solicitante';

CREATE TABLE carteira_pesca.licenca
(
  id                        SERIAL NOT NULL,
  idt_protocolo             INTEGER NOT NULL,
  idt_modalidade            INTEGER NOT NULL,
  dat_criacao               DATE NOT NULL,
  idt_status                INTEGER NOT NULL,
  dat_ativacao              DATE,
  idt_solicitante           INTEGER,

  CONSTRAINT pk_licenca PRIMARY KEY (id),

  CONSTRAINT fk_protocolo FOREIGN KEY (idt_protocolo)
    REFERENCES carteira_pesca.protocolo MATCH SIMPLE
    ON UPDATE RESTRICT ON DELETE RESTRICT,

  CONSTRAINT fk_solicitante FOREIGN KEY (idt_solicitante)
    REFERENCES carteira_pesca.solicitante_id MATCH SIMPLE
    ON UPDATE RESTRICT ON DELETE RESTRICT

) WITH (OIDS = FALSE );

ALTER TABLE carteira_pesca.protocolo_sequence OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.protocolo_sequence TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.protocolo_sequence TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.licenca IS
'Entidade responsável por armazenar a sequência para gerar o número de protocolo.';

COMMENT ON COLUMN carteira_pesca.licenca.id IS
'Identificador único da entidade.';

COMMENT ON COLUMN carteira_pesca.licenca.idt_protocolo IS
'Identificador único da entidade protocolo que realizará o relacionamento entre protocolo e licença. ';

COMMENT ON COLUMN carteira_pesca.licenca.idt_modalidade IS
'Identificador da modalidade. 0 - Esportiva; 1 - Recreativa;';

COMMENT ON COLUMN carteira_pesca.licenca.dat_criacao IS
'Data da criação da licença.';

COMMENT ON COLUMN carteira_pesca.licenca.idt_status IS
'Identificador do status da licença. 0 - Aguardando; 1 - Ativo; 2 - Inválido;';

COMMENT ON COLUMN carteira_pesca.licenca.dat_ativacao IS
'Data em que a licença tornou-se ativa.';

--v6
DELETE FROM carteira_pesca.local_pesca;

ALTER TABLE carteira_pesca.local_pesca RENAME descricao TO descricao_pt;
ALTER TABLE carteira_pesca.local_pesca ADD COLUMN descricao_en CHARACTER VARYING(50) NOT NULL;
ALTER TABLE carteira_pesca.local_pesca ADD COLUMN descricao_es CHARACTER VARYING(50) NOT NULL;

COMMENT ON COLUMN carteira_pesca.local_pesca.descricao_pt IS 
'Descrição das opções de local de pesca em português.';

COMMENT ON COLUMN carteira_pesca.local_pesca.descricao_en IS 
'Descrição das opções de local de pesca em inglês.';

COMMENT ON COLUMN carteira_pesca.local_pesca.descricao_es IS 
'Descrição das opções de local de pesca em espanhol.';

INSERT INTO carteira_pesca.local_pesca (id, descricao_pt, descricao_en, descricao_es) VALUES
(0, 'Localmente', 'Locally', 'Localmente'),
(1, 'Em outros estados', 'In other states', 'En otros estados'),
(2, 'Em outros países', 'in other countries', 'En otros paises'); 

DELETE FROM carteira_pesca.renda_mensal;

ALTER TABLE carteira_pesca.renda_mensal RENAME descricao TO descricao_pt;
ALTER TABLE carteira_pesca.renda_mensal ADD COLUMN descricao_en CHARACTER VARYING(50) NOT NULL;
ALTER TABLE carteira_pesca.renda_mensal ADD COLUMN descricao_es CHARACTER VARYING(50) NOT NULL;

COMMENT ON COLUMN carteira_pesca.renda_mensal.descricao_pt IS 
'Descrição das opções de renda mensal em português.';

COMMENT ON COLUMN carteira_pesca.renda_mensal.descricao_en IS 
'Descrição das opções de renda mensal em inglês.';

COMMENT ON COLUMN carteira_pesca.renda_mensal.descricao_es IS 
'Descrição das opções de renda mensal em espanhol.';

INSERT INTO carteira_pesca.renda_mensal (id, descricao_pt, descricao_en, descricao_es) VALUES 
(0, 'Nenhuma renda', 'Nenhuma renda', 'Ninguna renta'), 
(1, 'Até 1 salário mínimo', 'Up to a minimum wage', 'Hasta un salario mínimo'), 
(2, 'De 1 a 3 salários mínimos', 'From 1 to 3 minimum wages', 'De 1 a 3 salarios mínimos'), 
(3, 'De 3 a 6 salários mínimos', 'From 3 to 6 minimum wages', 'De 3 a 6 salarios mínimos'), 
(4, 'De 6 a 9 salários mínimos', 'From 6 to 9 minimum wages', 'De 6 a 9 salarios mínimos'), 
(5, 'De 9 a 12 salários mínimos', 'From 9 to 12 minimum wages', 'De 9 a 12 salarios mínimos'), 
(6, 'De 12 a 15 salários mínimos', 'From 12 to 15 minimum wages', 'De 12 a 15 salarios mínimos'), 
(7, 'Mais de 15 salários mínimos', 'More then 6 to 9 minimum wages', 'Más de 15 salarios mínimos');

DELETE FROM carteira_pesca.preferencia_local_pesca;

ALTER TABLE carteira_pesca.preferencia_local_pesca RENAME descricao TO descricao_pt;
ALTER TABLE carteira_pesca.preferencia_local_pesca ADD COLUMN descricao_en CHARACTER VARYING(50) NOT NULL;
ALTER TABLE carteira_pesca.preferencia_local_pesca ADD COLUMN descricao_es CHARACTER VARYING(50) NOT NULL;

COMMENT ON COLUMN carteira_pesca.preferencia_local_pesca.descricao_pt IS 
'Descrição das opções de local de pesca em português, podendo ser Mar ou Água Doce.';

COMMENT ON COLUMN carteira_pesca.preferencia_local_pesca.descricao_en IS 
'Descrição das opções de local de pesca em inglês, podendo ser Mar ou Água Doce.';

COMMENT ON COLUMN carteira_pesca.preferencia_local_pesca.descricao_es IS 
'Descrição das opções de local de pesca em espanhol, podendo ser Mar ou Água Doce.';

INSERT INTO carteira_pesca.preferencia_local_pesca (id, descricao_pt, descricao_en, descricao_es) VALUES 
(0, 'Mar', 'Sea', 'Mar'), 
(1, 'Água doce', 'Fresh water', 'Agua dulce');

DELETE FROM carteira_pesca.equipamento;

ALTER TABLE carteira_pesca.equipamento RENAME nome TO nome_pt;
ALTER TABLE carteira_pesca.equipamento ADD COLUMN nome_en CHARACTER VARYING(50) NOT NULL;
ALTER TABLE carteira_pesca.equipamento ADD COLUMN nome_es CHARACTER VARYING(50) NOT NULL;

COMMENT ON COLUMN carteira_pesca.equipamento.nome_pt IS 
'Nome dos materiais/equipamentos em português.';

COMMENT ON COLUMN carteira_pesca.equipamento.nome_en IS 
'Nome dos materiais/equipamentos em inglês.';

COMMENT ON COLUMN carteira_pesca.equipamento.nome_es IS 
'Nome dos materiais/equipamentos em espanhol.';

INSERT INTO carteira_pesca.equipamento (id, nome_pt, nome_en, nome_es) VALUES 
(0, 'Linha Caniço', 'Caniço Line', 'Línea Caniço'), 
(1, 'Carretilha Molinete', 'Windlass Reel', 'Carretilla Molinete'), 
(2, 'Fly', 'Fly', 'Fly'), 
(3, 'Outros', 'Others', 'Otros');

DELETE FROM carteira_pesca.tipo_isca;

ALTER TABLE carteira_pesca.tipo_isca RENAME descricao TO descricao_pt;
ALTER TABLE carteira_pesca.tipo_isca ADD COLUMN descricao_en CHARACTER VARYING(50) NOT NULL;
ALTER TABLE carteira_pesca.tipo_isca ADD COLUMN descricao_es CHARACTER VARYING(50) NOT NULL;

COMMENT ON COLUMN carteira_pesca.tipo_isca.descricao_pt IS 
'Descrição dos tipos de isca em português.';

COMMENT ON COLUMN carteira_pesca.tipo_isca.descricao_en IS 
'Descrição dos tipos de isca em inglês.';

COMMENT ON COLUMN carteira_pesca.tipo_isca.descricao_es IS 
'Descrição dos tipos de isca em espanhol.';

INSERT INTO carteira_pesca.tipo_isca (id, descricao_pt, descricao_en, descricao_es) VALUES 
(0, 'Natural', 'Natural' ,'Natural'), 
(1, 'Artificial', 'Artificial', 'Artificial');

DELETE FROM carteira_pesca.faixa_etaria;

ALTER TABLE carteira_pesca.faixa_etaria RENAME descricao TO descricao_pt;
ALTER TABLE carteira_pesca.faixa_etaria ADD COLUMN descricao_en CHARACTER VARYING(50) NOT NULL;
ALTER TABLE carteira_pesca.faixa_etaria ADD COLUMN descricao_es CHARACTER VARYING(50) NOT NULL;

COMMENT ON COLUMN carteira_pesca.faixa_etaria.descricao_pt IS 
'Descrição da faixa etária em português.';

COMMENT ON COLUMN carteira_pesca.faixa_etaria.descricao_en IS 
'Descrição da faixa etária em inglês.';

COMMENT ON COLUMN carteira_pesca.faixa_etaria.descricao_es IS 
'Descrição da faixa etária em espanhol.';

INSERT INTO carteira_pesca.faixa_etaria (id, descricao_pt, descricao_en, descricao_es) VALUES
(0, 'De 0 a 20 anos', 'To 0 to 20 years old', 'De 0 a 20 años'),
(1, 'De 21 a 30 anos', 'To 21 to 30 years old', 'De 21 a 30 año'),
(2, 'De 31 a 40 anos', 'To 31 to 40 years old', 'De 31 a 40 años'),
(3, 'De 41 a 50 anos', 'To 41 to 50 years old', 'De 41 a 50 años'),
(4, 'De 51 a 60 anos', 'To 51 to 60 years old', 'De 51 a 60 años'),
(5, 'De 61 a 70 anos', 'To 61 to 70 years old', 'De 61 a 70 años'),
(6, 'Mais que 71 anos', 'More then 71 years old', 'Más de 71 años');

DELETE FROM carteira_pesca.modalidade;

ALTER TABLE carteira_pesca.modalidade RENAME nome TO nome_pt;
ALTER TABLE carteira_pesca.modalidade ADD COLUMN nome_en CHARACTER VARYING(50) NOT NULL;
ALTER TABLE carteira_pesca.modalidade ADD COLUMN nome_es CHARACTER VARYING(50) NOT NULL;

COMMENT ON COLUMN carteira_pesca.modalidade.nome_pt IS 
'Nome da modalidade em português.';

COMMENT ON COLUMN carteira_pesca.modalidade.nome_en IS 
'Nome da modalidade em inglês.';

COMMENT ON COLUMN carteira_pesca.modalidade.nome_es IS 
'Nome da modalidade em espanhol.';

INSERT INTO carteira_pesca.modalidade (id, nome_pt, nome_en, nome_es) VALUES
(0, 'Pesca Esportiva', 'Sport Fishing', 'Pesca Deportiva'),
(1, 'Pesca Recreativa', 'Recreational Fishing', 'Pesca Recreativa');

--v7
ALTER TABLE carteira_pesca.licenca ADD tx_caminho_boleto varchar(255) NULL;

COMMENT ON COLUMN carteira_pesca.licenca.tx_caminho_boleto IS
'Caminho do boleto.';

ALTER TABLE carteira_pesca.licenca ADD tx_caminho_carteira varchar(255) NULL;

COMMENT ON COLUMN carteira_pesca.licenca.tx_caminho_carteira IS
'Caminho da carteira.';

--v8
ALTER TABLE carteira_pesca.solicitante OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.solicitante TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.solicitante TO carteira_pesca;

GRANT SELECT, USAGE ON carteira_pesca.cpf_id_seq TO carteira_pesca;
GRANT SELECT, USAGE ON carteira_pesca.informacao_complementar_id_seq TO carteira_pesca;
GRANT SELECT, USAGE ON carteira_pesca.licenca_id_seq TO carteira_pesca;
GRANT SELECT, USAGE ON carteira_pesca.passaporte_id_seq TO carteira_pesca;
GRANT SELECT, USAGE ON carteira_pesca.protocolo_id_seq TO carteira_pesca;
GRANT SELECT, USAGE ON carteira_pesca.solicitante_id_id_seq TO carteira_pesca;
GRANT SELECT, USAGE ON carteira_pesca.solicitante_id_seq TO carteira_pesca;

GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.licenca TO carteira_pesca;

DROP TABLE carteira_pesca.protocolo_sequence;
DROP TABLE carteira_pesca.solicitante;
DROP TABLE carteira_pesca.licenca;
DROP TABLE carteira_pesca.protocolo;
DROP TABLE carteira_pesca.solicitante_id;
DROP TABLE carteira_pesca.cpf;
DROP TABLE carteira_pesca.passaporte;

CREATE TABLE carteira_pesca.sequencia_protocolo
(
  id integer NOT NULL, 
  nm_sequencia character varying(20) NOT NULL, 
  vl_sequencia integer NOT NULL, 
  id_modalidade integer NOT NULL, 
  nu_ano_sequencia integer NOT NULL, 
  CONSTRAINT pk_sequencia_protocolo PRIMARY KEY (id),
  CONSTRAINT fk_modalidade FOREIGN KEY (id_modalidade)
      REFERENCES carteira_pesca.modalidade (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE carteira_pesca.sequencia_protocolo
  OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.sequencia_protocolo TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.sequencia_protocolo TO carteira_pesca;
COMMENT ON TABLE carteira_pesca.sequencia_protocolo
  IS 'Entidade responsável por armazenar a sequência para gerar o número de protocolo.';
COMMENT ON COLUMN carteira_pesca.sequencia_protocolo.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.sequencia_protocolo.nm_sequencia IS 'Nome da sequência do protocolo.';
COMMENT ON COLUMN carteira_pesca.sequencia_protocolo.vl_sequencia IS 'Valor da sequência do protocolo.';
COMMENT ON COLUMN carteira_pesca.sequencia_protocolo.id_modalidade IS 'Identificador representando o id da modalidade da sequência.';
COMMENT ON COLUMN carteira_pesca.sequencia_protocolo.nu_ano_sequencia IS 'Ano em vigência da sequência.';

CREATE TABLE carteira_pesca.solicitante
(
  id serial NOT NULL, 
  nu_cpf integer, 
  nu_passaporte integer, 
  CONSTRAINT pk_solicitante PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE carteira_pesca.solicitante
  OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.solicitante TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.solicitante TO carteira_pesca;
GRANT SELECT, USAGE ON carteira_pesca.solicitante_id_seq TO carteira_pesca;
COMMENT ON TABLE carteira_pesca.solicitante
  IS 'Entidade responsável por armazenar os dados do solicitante.';
COMMENT ON COLUMN carteira_pesca.solicitante.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.solicitante.nu_cpf IS 'CPF do solicitante';
COMMENT ON COLUMN carteira_pesca.solicitante.nu_passaporte IS 'Passaporte do solicitante';

CREATE TABLE carteira_pesca.licenca
(
  id serial NOT NULL, 
  tx_protocolo character varying(11) NOT NULL, 
  id_modalidade integer NOT NULL, 
  dt_criacao date NOT NULL, 
  id_status integer NOT NULL, 
  dt_ativacao date, 
  id_solicitante integer,
  tx_caminho_boleto character varying(255), 
  tx_caminho_carteira character varying(255), 
  CONSTRAINT pk_licenca PRIMARY KEY (id),
  CONSTRAINT fk_solicitante FOREIGN KEY (id_solicitante)
      REFERENCES carteira_pesca.solicitante (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE carteira_pesca.licenca
  OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.licenca TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.licenca TO carteira_pesca;
GRANT SELECT, USAGE ON carteira_pesca.licenca_id_seq TO carteira_pesca;
COMMENT ON TABLE carteira_pesca.licenca
  IS 'Entidade responsável por armazenar a sequência para gerar o número de protocolo.';
COMMENT ON COLUMN carteira_pesca.licenca.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.licenca.tx_protocolo IS 'Código do protocolo da licença. ';
COMMENT ON COLUMN carteira_pesca.licenca.id_modalidade IS 'Identificador da modalidade. 0 - Esportiva; 1 - Recreativa;';
COMMENT ON COLUMN carteira_pesca.licenca.dt_criacao IS 'Data da criação da licença.';
COMMENT ON COLUMN carteira_pesca.licenca.id_status IS 'Identificador do status da licença. 0 - Aguardando; 1 - Ativo; 2 - Inválido;';
COMMENT ON COLUMN carteira_pesca.licenca.dt_ativacao IS 'Data em que a licença tornou-se ativa.';
COMMENT ON COLUMN carteira_pesca.licenca.tx_caminho_boleto IS 'Caminho do boleto.';
COMMENT ON COLUMN carteira_pesca.licenca.tx_caminho_carteira IS 'Caminho da carteira.';

--v9
ALTER TABLE carteira_pesca.solicitante ALTER COLUMN nu_cpf TYPE TEXT;
ALTER TABLE carteira_pesca.solicitante ALTER COLUMN nu_passaporte TYPE TEXT;

--v10
INSERT INTO carteira_pesca.sequencia_protocolo VALUES
(0, 'sequencia_esportiva', 0, 0, 2018),
(1, 'sequencia_esportiva', 0, 1, 2018);

--v11
ALTER TABLE carteira_pesca.licenca ADD dt_vencimento date NULL;

COMMENT ON COLUMN carteira_pesca.licenca.dt_vencimento IS
'Data de vencimento.';


--v12

COMMENT ON COLUMN carteira_pesca.licenca.id_solicitante IS 'Identificador único da entidade solicitante que realizará o relacionamento entre solicitante e licenca.';

--v13
ALTER TABLE carteira_pesca.licenca ADD dt_vencimento_boleto date NULL;

COMMENT ON COLUMN carteira_pesca.licenca.dt_vencimento_boleto IS
'Data de vencimento do boleto.';

--v14
ALTER TABLE carteira_pesca.solicitante ADD COLUMN numero_tentativas integer default 0;
COMMENT ON COLUMN carteira_pesca.solicitante.numero_tentativas IS 'Número de tentativas de acesso as licencas.';

ALTER TABLE carteira_pesca.solicitante ADD COLUMN data_ultima_tentativa timestamp without time zone;
COMMENT ON COLUMN carteira_pesca.solicitante.data_ultima_tentativa IS 'Data da última tentativa de acesso as licenças antes do bloqueio do solicitante em caso de errar as perguntas mais de três vezes.';

ALTER TABLE carteira_pesca.solicitante ADD COLUMN data_desbloqueio timestamp without time zone;
COMMENT ON COLUMN carteira_pesca.solicitante.data_desbloqueio IS 'Data de desbloqueio do solicitante.';

--v15
ALTER TABLE carteira_pesca.licenca ADD COLUMN id_informacao_complementar integer;
COMMENT ON COLUMN carteira_pesca.licenca.id_informacao_complementar IS 'Identificador único da entidade informacao_complementar que realizará o relacionamento entre informacao_complementar e licenca.';

ALTER TABLE carteira_pesca.licenca ADD 
 CONSTRAINT fk_l_informacao_complementar FOREIGN KEY (id_informacao_complementar)
      REFERENCES carteira_pesca.informacao_complementar (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT;
      
CREATE TABLE carteira_pesca.status
(
id INTEGER NOT NULL,
descricao CHARACTER VARYING (400) NOT NULL,
codigo CHARACTER VARYING (40) NOT NULL,
CONSTRAINT pk_status PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE carteira_pesca.status
  OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.status TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.status TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.status
  IS 'Entidade responsável por armazenar as informações dos possíveis status da licença.';
COMMENT ON COLUMN carteira_pesca.status.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.status.descricao IS 'Descrição do status da licença.';
COMMENT ON COLUMN carteira_pesca.status.codigo IS 'Código do status da licença.';

COMMENT ON COLUMN carteira_pesca.licenca.id_status IS 'Identificador único da entidade status que realizará o relacionamento entre status e informacao_complementar.';

ALTER TABLE carteira_pesca.licenca ADD 
  CONSTRAINT fk_l_status FOREIGN KEY (id_status)
      REFERENCES carteira_pesca.status (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
      
INSERT INTO carteira_pesca.status VALUES
(0, 'Aguardando pagamento do boleto', 'AGUARDANDO_PAGAMENTO'),
(1, 'Ativo', 'ATIVO'),
(2, 'Invalidado', 'INVALIDADO'),
(3, 'Vencido', 'VENCIDO'),
(4, 'Renovado', 'RENOVADO');

ALTER TABLE carteira_pesca.informacao_complementar DROP COLUMN id_equipamento;

ALTER TABLE carteira_pesca.informacao_complementar DROP COLUMN id_modalidade_mais_praticada;

CREATE TABLE carteira_pesca.material_pesca
(
id INTEGER NOT NULL,
descricao_pt CHARACTER VARYING (50) NOT NULL,
descricao_en CHARACTER VARYING (50) NOT NULL,
descricao_es CHARACTER VARYING (50) NOT NULL,
CONSTRAINT pk_material_pesca PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE carteira_pesca.material_pesca
  OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.material_pesca TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.material_pesca TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.material_pesca
  IS 'Entidade responsável por armazenar as informações dos materiais utilizados na pasca.';
COMMENT ON COLUMN carteira_pesca.material_pesca.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.material_pesca.descricao_pt IS 'Descrição do material em português.';
COMMENT ON COLUMN carteira_pesca.material_pesca.descricao_en IS 'Descrição do material em inglês.';
COMMENT ON COLUMN carteira_pesca.material_pesca.descricao_es IS 'Descrição do material em espanhol.';

INSERT INTO carteira_pesca.material_pesca VALUES
(0, 'Linha Caniço', 'Caniço line','Línea Caniço'),
(1, 'Carretilha Molinete', 'Windlass Reel','Carretilla Molinete'),
(2, 'Fly', 'Fly','Fly'),
(3, 'Outros', 'Others','Otro');

ALTER TABLE carteira_pesca.informacao_complementar ADD COLUMN id_material_pesca INTEGER NOT NULL;
COMMENT ON COLUMN carteira_pesca.informacao_complementar.id_material_pesca IS 'Identificador único da entidade material_pesca que realizará o relacionamento entre material_pesca e informacao_complementar.';

ALTER TABLE carteira_pesca.informacao_complementar ADD
 CONSTRAINT fk_ic_material_pesca FOREIGN KEY (id_material_pesca)
      REFERENCES carteira_pesca.material_pesca (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE carteira_pesca.licenca ALTER COLUMN tx_protocolo TYPE CHARACTER VARYING(15);

--v16
CREATE TABLE carteira_pesca.peixe_mais_pescado
(
id INTEGER NOT NULL,
descricao_pt CHARACTER VARYING (50) NOT NULL,
descricao_en CHARACTER VARYING (50) NOT NULL,
descricao_es CHARACTER VARYING (50) NOT NULL,
CONSTRAINT pk_peixe_mais_pescado PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE carteira_pesca.peixe_mais_pescado
  OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.peixe_mais_pescado TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.peixe_mais_pescado TO carteira_pesca;
COMMENT ON TABLE carteira_pesca.peixe_mais_pescado
  IS 'Entidade responsável por armazenar os tipos de peixes mais pescados.';
COMMENT ON COLUMN carteira_pesca.peixe_mais_pescado.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.peixe_mais_pescado.descricao_pt IS 'Descrição do peixe em português. ';
COMMENT ON COLUMN carteira_pesca.peixe_mais_pescado.descricao_en IS 'Descrição do peixe em inglês. ';
COMMENT ON COLUMN carteira_pesca.peixe_mais_pescado.descricao_es IS 'Descrição do peixe em espanhol. ';

INSERT INTO carteira_pesca.peixe_mais_pescado (id, descricao_pt, descricao_en, descricao_es) VALUES
(0, 'Apapá', 'Apapá', 'Apapá'),
(1, 'Aruanã', 'Aruanã', 'Aruanã'),
(2, 'Caparari', 'Caparari', 'Caparari'),
(3, 'Jacundá', 'Jacundá', 'Jacundá'),
(4, 'Matrinxã', 'Matrinxã', 'Matrinxã'),
(5, 'Pescada', 'Pescada', 'Pescada'),
(6, 'Piranha', 'Piranha', 'Piranha'),
(7, 'Pirarara', 'Pirarara', 'Pirarara'),
(8, 'Pirarucu', 'Pirarucu', 'Pirarucu'),
(9, 'Piraíba', 'Piraíba', 'Piraíba'),
(10, 'Surubim', 'Surubim', 'Surubim'),
(11, 'Tambaqui', 'Tambaqui', 'Tambaqui'),
(12, 'Traíra', 'Traíra', 'Traíra'),
(13, 'Tucunaré', 'Tucunaré', 'Tucunaré'),
(14, 'Outros', 'Others', 'Otro');

ALTER TABLE carteira_pesca.informacao_complementar ADD COLUMN id_peixe_mais_pescado integer;
COMMENT ON COLUMN carteira_pesca.informacao_complementar.id_peixe_mais_pescado IS 'Identificador único da entidade peixe_mais_pescado que realizará o relacionamento entre peixe_mais_pescado e informacao_complementar.';

ALTER TABLE carteira_pesca.informacao_complementar ADD
  CONSTRAINT fk_ic_peixe_mais_pescado FOREIGN KEY (id_peixe_mais_pescado)
      REFERENCES carteira_pesca.peixe_mais_pescado (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT;

--v17
ALTER TABLE carteira_pesca.informacao_complementar DROP COLUMN id_faixa_etaria;

--v18
CREATE TABLE carteira_pesca.pais
(
	id SERIAL NOT NULL,
	nome CHARACTER VARYING (255) NOT NULL,
	sigla CHARACTER VARYING (2) NOT NULL,
	CONSTRAINT pk_pais PRIMARY KEY (id)
) WITH (
  OIDS=FALSE
);

ALTER TABLE carteira_pesca.pais
  OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.pais TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.pais TO carteira_pesca;
COMMENT ON TABLE carteira_pesca.pais
  IS 'Entidade responsável por armazenar os países.';
COMMENT ON COLUMN carteira_pesca.pais.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.pais.nome IS 'Nome do País. ';
COMMENT ON COLUMN carteira_pesca.pais.sigla IS 'Sigla do País. ';

INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(1, 'Afeganistão','AF');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(2,'África do Sul','ZA');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(3,'Albânia','AL');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(4,'Alemanha','DE');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(5,'Andorra','AD');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(6,'Angola','AO');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(7,'Anguilla','AI');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(8,'Antártida','AQ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(9,'Antígua e Barbuda','AG');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(10,'Antilhas Holandesas','AN');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(11,'Arábia Saudita','SA');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(12,'Argélia','DZ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(13,'Argentina','AR');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(14,'Armênia','AM');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(15,'Aruba','AW');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(16,'Austrália','AU');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(17,'Áustria','AT');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(18,'Azerbaijão','AZ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(19,'Bahamas','BS');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(20,'Bahrein','BH');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(21,'Bangladesh','BD');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(22,'Barbados','BB');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(23,'Belarus','BY');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(24,'Bélgica','BE');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(25,'Belize','BZ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(26,'Benin','BJ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(27,'Bermudas','BM');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(28,'Bolívia','BO');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(29,'Bósnia-Herzegóvina','BA');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(30,'Botsuana','BW');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(31,'Brasil','BR');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(32,'Brunei','BN');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(33,'Bulgária','BG');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(34,'Burkina Fasso','BF');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(35,'Burundi','BI');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(36,'Butão','BT');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(37,'Cabo Verde','CV');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(38,'Camarões','CM');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(39,'Camboja','KH');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(40,'Canadá','CA');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(41,'Cazaquistão','KZ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(42,'Chade','TD');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(43,'Chile','CL');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(44,'China','CN');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(45,'Chipre','CY');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(46,'Cingapura','SG');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(47,'Colômbia','CO');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(48,'Congo','CG');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(49,'Coréia do Norte','KP');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(50,'Coréia do Sul','KR');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(51,'Costa do Marfim','CI');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(52,'Costa Rica','CR');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(53,'Croácia (Hrvatska)','HR');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(54,'Cuba','CU');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(55,'Dinamarca','DK');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(56,'Djibuti','DJ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(57,'Dominica','DM');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(58,'Egito','EG');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(59,'El Salvador','SV');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(60,'Emirados Árabes Unidos','AE');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(61,'Equador','EC');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(62,'Eritréia','ER');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(63,'Eslováquia','SK');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(64,'Eslovênia','SI');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(65,'Espanha','ES');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(66,'Estados Unidos','US');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(67,'Estônia','EE');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(68,'Etiópia','ET');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(69,'Federação Russa','RU');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(70,'Fiji','FJ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(71,'Filipinas','PH');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(72,'Finlândia','FI');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(73,'França','FR');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(74,'Gabão','GA');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(75,'Gâmbia','GM');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(76,'Gana','GH');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(77,'Geórgia','GE');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(78,'Gibraltar','GI');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(79,'Grã-Bretanha (Reino Unido - Uk)','GB');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(80,'Granada','GD');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(81,'Grécia','GR');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(82,'Groelândia','GL');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(83,'Guadalupe','GP');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(84,'Guam (Território Dos Estados Unidos)','GU');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(85,'Guatemala','GT');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(86,'Guernsey','GG');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(87,'Guiana','GY');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(88,'Guiana Francesa','GF');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(89,'Guiné','GN');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(90,'Guiné Equatorial','GQ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(91,'Guiné-Bissau','GW');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(92,'Haiti','HT');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(93,'Holanda','NL');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(94,'Honduras','HN');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(95,'Hong Kong','HK');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(96,'Hungria','HU');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(97,'Iêmen','YE');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(98,'Ilha Bouvet (Território da Noruega)','BV');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(99,'Ilha do Homem','IM');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(100,'Ilha Natal','CX');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(101,'Ilha Pitcairn','PN');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(102,'Ilha Reunião','RE');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(103,'Ilhas Aland','AX');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(104,'Ilhas Cayman','KY');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(105,'Ilhas Cocos','CC');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(106,'Ilhas Comores','KM');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(107,'Ilhas Cook','CK');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(108,'Ilhas Faroes','FO');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(109,'Ilhas Falkland (Malvinas)','FK');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(110,'Ilhas Geórgia do Sul e Sandwich do Sul','GS');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(111,'Ilhas Heard e Mcdonald (Território da Austrália)','HM');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(112,'Ilhas Marianas do Norte','MP');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(113,'Ilhas Marshall','MH');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(114,'Ilhas Menores Dos Estados Unidos','UM');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(115,'Ilhas Norfolk','NF');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(116,'Ilhas Seychelles','SC');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(117,'Ilhas Solomão','SB');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(118,'Ilhas Svalbard e Jan Mayen','SJ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(119,'Ilhas Tokelau','TK');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(120,'Ilhas Turks e Caicos','TC');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(121,'Ilhas Virgens (Estados Unidos)','VI');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(122,'Ilhas Virgens (Inglaterra)','VG');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(123,'Ilhas Wallis e Futuna','WF');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(124,'Índia','IN');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(125,'Indonésia','ID');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(126,'Irã','IR');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(127,'Iraque','IQ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(128,'Irlanda','IE');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(129,'Islândia','IS');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(130,'Israel','IL');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(131,'Itália','IT');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(132,'Jamaica','JM');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(133,'Japão','JP');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(134,'Jersey','JE');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(135,'Jordânia','JO');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(136,'Kênia','KE');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(137,'Kiribati','KI');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(138,'Kuait','KW');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(139,'Laos','LA');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(140,'Látvia','LV');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(141,'Lesoto','LS');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(142,'Líbano','LB');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(143,'Libéria','LR');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(144,'Líbia','LY');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(145,'Liechtenstein','LI');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(146,'Lituânia','LT');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(147,'Luxemburgo','LU');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(148,'Macau','MO');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(149,'Macedônia (República Yugoslava)','MK');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(150,'Madagascar','MG');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(151,'Malásia','MY');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(152,'Malaui','MW');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(153,'Maldivas','MV');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(154,'Mali','ML');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(155,'Malta','MT');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(156,'Marrocos','MA');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(157,'Martinica','MQ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(158,'Maurício','MU');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(159,'Mauritânia','MR');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(160,'Mayotte','YT');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(161,'México','MX');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(162,'Micronésia','FM');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(163,'Moçambique','MZ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(164,'Moldova','MD');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(165,'Mônaco','MC');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(166,'Mongólia','MN');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(167,'Montenegro','ME');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(168,'Montserrat','MS');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(169,'Myanma','MM');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(170,'Namíbia','NA');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(171,'Nauru','NR');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(172,'Nepal','NP');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(173,'Nicarágua','NI');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(174,'Níger','NE');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(175,'Nigéria','NG');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(176,'Niue','NU');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(177,'Noruega','NO');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(178,'Nova Caledônia','NC');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(179,'Nova Zelândia','NZ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(180,'Omã','OM');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(181,'Palau','PW');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(182,'Panamá','PA');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(183,'Papua-Nova Guiné','PG');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(184,'Paquistão','PK');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(185,'Paraguai','PY');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(186,'Peru','PE');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(187,'Polinésia Francesa','PF');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(188,'Polônia','PL');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(189,'Porto Rico','PR');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(190,'Portugal','PT');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(191,'Qatar','QA');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(192,'Quirguistão','KG');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(193,'República Centro-Africana','CF');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(194,'República Democrática do Congo','CD');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(195,'República Dominicana','DO');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(196,'República Tcheca','CZ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(197,'Romênia','RO');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(198,'Ruanda','RW');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(199,'Saara Ocidental','EH');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(200,'Saint Vincente e Granadinas','VC');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(201,'Samoa Ocidental','AS');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(202,'Samoa Ocidental','WS');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(203,'San Marino','SM');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(204,'Santa Helena','SH');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(205,'Santa Lúcia','LC');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(206,'São Bartolomeu','BL');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(207,'São Cristóvão e Névis','KN');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(208,'São Martim','MF');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(209,'São Tomé e Príncipe','ST');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(210,'Senegal','SN');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(211,'Serra Leoa','SL');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(212,'Sérvia','RS');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(213,'Síria','SY');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(214,'Somália','SO');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(215,'Sri Lanka','LK');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(216,'St. Pierre And Miquelon','PM');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(217,'Suazilândia','SZ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(218,'Sudão','SD');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(219,'Suécia','SE');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(220,'Suíça','CH');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(221,'Suriname','SR');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(222,'Tadjiquistão','TJ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(223,'Tailândia','TH');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(224,'Taiwan','TW');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(225,'Tanzânia','TZ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(226,'Território Britânico do Oceano Índico','IO');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(227,'Territórios do Sul da França','TF');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(228,'Territórios Palestinos Ocupados','PS');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(229,'Timor Leste','TP');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(230,'Togo','TG');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(231,'Tonga','TO');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(232,'Trinidad And Tobago','TT');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(233,'Tunísia','TN');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(234,'Turcomenistão','TM');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(235,'Turquia','TR');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(236,'Tuvalu','TV');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(237,'Ucrânia','UA');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(238,'Uganda','UG');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(239,'Uruguai','UY');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(240,'Uzbequistão','UZ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(241,'Vanuatu','VU');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(242,'Vaticano','VA');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(243,'Venezuela','VE');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(244,'Vietnã','VN');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(245,'Zâmbia','ZM');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(246,'Zimbábue','ZW');

CREATE TABLE carteira_pesca.endereco_estrangeiro
(
    id SERIAL NOT NULL,
    descricao_endereco CHARACTER VARYING(400) NOT NULL,
    cidade CHARACTER VARYING(70) NOT NULL,
    estado CHARACTER VARYING(70) NOT NULL,
    id_nacionalidade INTEGER NOT NULL,
    id_pais INTEGER NOT NULL,  
    CONSTRAINT pk_estrangeiro PRIMARY KEY (id),
    CONSTRAINT fk_ee_nacionalidade FOREIGN KEY (id_nacionalidade)
      REFERENCES carteira_pesca.pais (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
    CONSTRAINT fk_ee_pais FOREIGN KEY (id_pais)
      REFERENCES carteira_pesca.pais (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
) WITH (
  OIDS=FALSE
);

ALTER TABLE carteira_pesca.endereco_estrangeiro
  OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.endereco_estrangeiro TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.endereco_estrangeiro TO carteira_pesca;
COMMENT ON TABLE carteira_pesca.endereco_estrangeiro
  IS 'Entidade responsável por armazenar o endereço estrangeiro.';
COMMENT ON COLUMN carteira_pesca.endereco_estrangeiro.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.endereco_estrangeiro.descricao_endereco IS 'Descrição do endereço.';
COMMENT ON COLUMN carteira_pesca.endereco_estrangeiro.cidade IS 'Nome da cidade.';
COMMENT ON COLUMN carteira_pesca.endereco_estrangeiro.estado IS 'Nome do estado.';
COMMENT ON COLUMN carteira_pesca.endereco_estrangeiro.id_nacionalidade IS 'Identificador único da entidade país que faz relacionamento entre pais e endereco_estrangeiro.';
COMMENT ON COLUMN carteira_pesca.endereco_estrangeiro.id_pais IS 'Identificador único da entidade país que faz relacionamento entre pais e endereco_estrangeiro.';


ALTER TABLE carteira_pesca.solicitante ADD COLUMN id_endereco_estrangeiro INTEGER;
COMMENT ON COLUMN carteira_pesca.solicitante.id_endereco_estrangeiro IS 'Identificador único da entidade endereco_estrangeiro que realizará o relacionamento entre endereco_estrangeiro e solicitante.';

ALTER TABLE carteira_pesca.solicitante ADD CONSTRAINT fk_s_endereco_estrangeiro FOREIGN KEY (id_endereco_estrangeiro)
      REFERENCES carteira_pesca.endereco_estrangeiro (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT;

--v19
CREATE TABLE carteira_pesca.banco
(
  id SERIAL NOT NULL,
  codigo CHARACTER VARYING(5) NOT NULL,
  nome CHARACTER VARYING(150) NOT NULL,
  CONSTRAINT pk_banco PRIMARY KEY(id)
) WITH (
  OIDS=FALSE
);

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
) WITH (
  OIDS=FALSE
);

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
  CONSTRAINT fk_bt_banco FOREIGN KEY (id_banco)
    REFERENCES carteira_pesca.banco (id) MATCH SIMPLE
    ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT fk_bt_endereco FOREIGN KEY (id_endereco)
    REFERENCES carteira_pesca.endereco (id) MATCH SIMPLE
    ON UPDATE RESTRICT ON DELETE RESTRICT
) WITH (
  OIDS=FALSE
);

ALTER TABLE carteira_pesca.beneficiario_titulo OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.beneficiario_titulo TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.beneficiario_titulo TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.beneficiario_titulo IS 'Entidade responsável por armazenar os dados do beneficiario do título.';
COMMENT ON COLUMN carteira_pesca.beneficiario_titulo.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.beneficiario_titulo.nome IS 'Nome do beneficiário.';
COMMENT ON COLUMN carteira_pesca.beneficiario_titulo.sigla IS 'sigla do nome beneficiário.';
COMMENT ON COLUMN carteira_pesca.beneficiario_titulo.cpf_cnpj IS 'CPF/CNPJ do beneficiário (sem mascara).';
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
) WITH (
  OIDS=FALSE
);

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
  CONSTRAINT fk_pt_endereco FOREIGN KEY (id_endereco)
    REFERENCES carteira_pesca.endereco (id) MATCH SIMPLE
    ON UPDATE RESTRICT ON DELETE RESTRICT
) WITH (
  OIDS=FALSE
);

ALTER TABLE carteira_pesca.pagador_titulo OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.pagador_titulo TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.pagador_titulo TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.pagador_titulo IS 'Entidade responsável por armazenar o pagador do título.';
COMMENT ON COLUMN carteira_pesca.pagador_titulo.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.pagador_titulo.nome IS 'Nome pagador.';
COMMENT ON COLUMN carteira_pesca.pagador_titulo.cpf_passaporte IS 'CPF se for brasileiro ou Passaporte caso pessoa estrangeira.';

CREATE TABLE carteira_pesca.tipo_arquivo
(
  id SERIAL NOT NULL,
  codigo CHARACTER VARYING(20) NOT NULL,
  descricao CHARACTER VARYING(100) NOT NULL,
  CONSTRAINT pk_tipo_arquivo PRIMARY KEY(id)
) WITH (
  OIDS=FALSE
);

ALTER TABLE carteira_pesca.tipo_arquivo OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.tipo_arquivo TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.tipo_arquivo TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.tipo_arquivo IS 'Entidade responsável por armazenar os tipo de arquivo.';
COMMENT ON COLUMN carteira_pesca.tipo_arquivo.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.tipo_arquivo.codigo IS 'Codigo do tipo de arquivo.';
COMMENT ON COLUMN carteira_pesca.tipo_arquivo.descricao IS 'Descrição do tipo de documento.';

CREATE TABLE carteira_pesca.arquivo
(
  id SERIAL NOT NULL,
  caminho_arquivo CHARACTER VARYING(150) NOT NULL,
  nome CHARACTER VARYING(100) NOT NULL,
  dt_cadastro TIMESTAMP NOT NULL DEFAULT now(),
  id_tipo_arquivo INTEGER NOT NULL,
  CONSTRAINT pk_arquivo PRIMARY KEY(id),
  CONSTRAINT fk_a_tipo_arquivo FOREIGN KEY (id_tipo_arquivo)
    REFERENCES carteira_pesca.tipo_arquivo (id) MATCH SIMPLE
    ON UPDATE RESTRICT ON DELETE RESTRICT
) WITH (
  OIDS=FALSE
);

ALTER TABLE carteira_pesca.arquivo OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.arquivo TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.arquivo TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.arquivo IS 'Entidade responsável por armazenar os dados das instituições bancárias.';
COMMENT ON COLUMN carteira_pesca.arquivo.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.arquivo.caminho_arquivo IS 'Caminho completo onde o arquivo está salvo.';
COMMENT ON COLUMN carteira_pesca.arquivo.nome IS 'Nome do arquivo.';
COMMENT ON COLUMN carteira_pesca.arquivo.dt_cadastro IS 'Data de cadastro do arquivo.';
COMMENT ON COLUMN carteira_pesca.arquivo.id_tipo_arquivo IS 'Tipo de arquivo.';

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
  dt_geracao_remessa TIMESTAMP NULL,
  dt_pagamento DATE NULL,
  id_arquivo INTEGER NOT NULL,
  CONSTRAINT pk_titulo PRIMARY KEY(id),
  CONSTRAINT fk_t_beneficiario_titulo FOREIGN KEY (id_beneficiario)
    REFERENCES carteira_pesca.beneficiario_titulo (id) MATCH SIMPLE
    ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT fk_t_pagador_titulo FOREIGN KEY (id_pagador)
    REFERENCES carteira_pesca.pagador_titulo (id) MATCH SIMPLE
    ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT fk_t_especie_documento FOREIGN KEY (id_especie_documento)
    REFERENCES carteira_pesca.especie_documento (id) MATCH SIMPLE
    ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT fk_t_arquivo FOREIGN KEY (id_arquivo)
    REFERENCES carteira_pesca.arquivo (id) MATCH SIMPLE
    ON UPDATE RESTRICT ON DELETE RESTRICT
) WITH (
  OIDS=FALSE
);

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
COMMENT ON COLUMN carteira_pesca.titulo.nosso_numero IS 'Código de controle que permite ao Banco e ao beneficiário identificar os dados da cobrança que deu origem ao boleto de pagamento.';
COMMENT ON COLUMN carteira_pesca.titulo.dt_geracao_remessa IS 'Data de geração da remessa.';

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

INSERT INTO carteira_pesca.tipo_arquivo (id, codigo, descricao) VALUES (1, 'BOLETO', 'Arquivo pdf do boleto que foi gerado');
INSERT INTO carteira_pesca.tipo_arquivo (id, codigo, descricao) VALUES (2, 'REMESSA', 'Arquivo com os dados dos boletos que são enviados ao banco');
INSERT INTO carteira_pesca.tipo_arquivo (id, codigo, descricao) VALUES (3, 'RETORNO', 'Arquivo com os dados dos boletos que foram processador pelo banco');

ALTER TABLE carteira_pesca.licenca ADD COLUMN id_titulo INTEGER NULL;

ALTER TABLE carteira_pesca.licenca ADD CONSTRAINT fk_l_titulo FOREIGN KEY (id_titulo)
  REFERENCES carteira_pesca.titulo (id) MATCH SIMPLE
  ON UPDATE RESTRICT ON DELETE RESTRICT;

 --v20
 CREATE TABLE carteira_pesca.remessa
(
  id SERIAL NOT NULL,
  id_arquivo INTEGER NOT NULL,
  numero_sequencial INTEGER NOT NULL,
  sequencia_nome_arquivo CHARACTER VARYING(2) NOT NULL,
  dt_cadastro TIMESTAMP NOT NULL DEFAULT now(),
  CONSTRAINT pk_remessa PRIMARY KEY(id),
  CONSTRAINT fk_r_arquivo FOREIGN KEY (id_arquivo)
    REFERENCES carteira_pesca.arquivo (id) MATCH SIMPLE
    ON UPDATE RESTRICT ON DELETE RESTRICT
) WITH (
  OIDS=FALSE
);

ALTER TABLE carteira_pesca.remessa OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.remessa TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.remessa TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.remessa IS 'Entidade responsável por armazenar os dados das remessas enviadas ao banco.';
COMMENT ON COLUMN carteira_pesca.remessa.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.remessa.id_arquivo IS 'Arquivo de remessa que foi gerado.';
COMMENT ON COLUMN carteira_pesca.remessa.numero_sequencial IS 'Número sequencial de remessa que foram enviadas. Não poderão jamais enviar o mesmo número ou ser zerado.';
COMMENT ON COLUMN carteira_pesca.remessa.sequencia_nome_arquivo IS 'Identificador sequencia de arquivos enviados no mesmo dia. 2 digitos alfanuméricos de 0 a Z';
COMMENT ON COLUMN carteira_pesca.remessa.dt_cadastro IS 'Data que o arquivo de remessa foi gerado.';

--v21
CREATE TABLE carteira_pesca.retorno
(
  id SERIAL NOT NULL,
  id_arquivo INTEGER NOT NULL,
  dt_gravacao_banco DATE,
  dt_processamento TIMESTAMP,
  qtd_titulo_cobranca INTEGER,
  valor_titulo_cobranca DOUBLE PRECISION,
  qtd_confirmacao_entrada INTEGER,
  valor_confirmacao_entrada DOUBLE PRECISION,
  qtd_liquidacao INTEGER,
  valor_liquidacao DOUBLE PRECISION,
  qtd_baixados INTEGER,
  valor_baixados DOUBLE PRECISION,
  qtd_abatimento_cancelamento INTEGER,
  valor_abatimento_cancelamento DOUBLE PRECISION,
  qtd_vencimento_alterado INTEGER,
  valor_vencimento_alterado DOUBLE PRECISION,
  qtd_abatimento_concedido INTEGER,
  valor_abatimento_concedido DOUBLE PRECISION,
  qtd_confirmacao_instrucao_protesto INTEGER,
  valor_confirmacao_instrucao_protesto DOUBLE PRECISION,
  qtd_total_rateio_efetuado INTEGER,
  valor_total_rateio_efetuado DOUBLE PRECISION,
  numero_aviso_bancario INTEGER,
  CONSTRAINT pk_retorno PRIMARY KEY(id),
  CONSTRAINT fk_r_arquivo FOREIGN KEY (id_arquivo)
    REFERENCES carteira_pesca.arquivo (id) MATCH SIMPLE
    ON UPDATE RESTRICT ON DELETE RESTRICT
) WITH (
  OIDS=FALSE
);

ALTER TABLE carteira_pesca.retorno OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.retorno TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.retorno TO carteira_pesca;
COMMENT ON TABLE carteira_pesca.retorno IS 'Entidade responsável por armazenar os dados do retorno.';
COMMENT ON COLUMN carteira_pesca.retorno.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.retorno.id_arquivo IS 'Arquivo de retorno que foi gerado.';
COMMENT ON COLUMN carteira_pesca.retorno.dt_gravacao_banco IS 'Data que o arquivo de retorno foi gerado pelo banco.';
COMMENT ON COLUMN carteira_pesca.retorno.dt_processamento IS 'Data de processamento do arquivo.';
COMMENT ON COLUMN carteira_pesca.retorno.qtd_titulo_cobranca IS 'Quantidade de títulos em cobraca contidos no retorno.';
COMMENT ON COLUMN carteira_pesca.retorno.valor_titulo_cobranca IS 'Valor dos títulos em cobraca contidos no retorno.';
COMMENT ON COLUMN carteira_pesca.retorno.qtd_confirmacao_entrada IS 'Quantidade de títulos em confirmação de entrada.';
COMMENT ON COLUMN carteira_pesca.retorno.valor_confirmacao_entrada IS 'Valor dos títulos em confirmação de entrada.';
COMMENT ON COLUMN carteira_pesca.retorno.qtd_liquidacao IS 'Quantidade de títulos em liquidação.';
COMMENT ON COLUMN carteira_pesca.retorno.valor_liquidacao IS 'Valor dos títulos em liquidação.';
COMMENT ON COLUMN carteira_pesca.retorno.qtd_baixados IS 'Quantidade de títulos baixados.';
COMMENT ON COLUMN carteira_pesca.retorno.valor_baixados IS 'Valor dos títulos baixados.';
COMMENT ON COLUMN carteira_pesca.retorno.qtd_abatimento_cancelamento IS 'Quantidade de títulos abatidos de cancelamento.';
COMMENT ON COLUMN carteira_pesca.retorno.valor_abatimento_cancelamento IS 'Valor dos títulos abatidos de cancelamento.';
COMMENT ON COLUMN carteira_pesca.retorno.qtd_vencimento_alterado IS 'Quantidade de títulos com vencimento alterado.';
COMMENT ON COLUMN carteira_pesca.retorno.valor_vencimento_alterado IS 'Valor dos títulos com vencimento alterado.';
COMMENT ON COLUMN carteira_pesca.retorno.qtd_abatimento_concedido IS 'Quantidade de títulos que tiveram abatimento concedido.';
COMMENT ON COLUMN carteira_pesca.retorno.valor_abatimento_concedido IS 'Valor dos títulos que tiveram abatimento concedido.';
COMMENT ON COLUMN carteira_pesca.retorno.qtd_confirmacao_instrucao_protesto IS 'Quantidade de títulos que tiveram confirmação de instrução de protesto.';
COMMENT ON COLUMN carteira_pesca.retorno.valor_confirmacao_instrucao_protesto IS 'Valor dos títulos que tiveram confirmação de instrução de protesto.';
COMMENT ON COLUMN carteira_pesca.retorno.qtd_total_rateio_efetuado IS 'Quantidade de títulos que tiveram rateio efetuado.';
COMMENT ON COLUMN carteira_pesca.retorno.valor_total_rateio_efetuado IS 'Valor dos títulos que tiveram rateio efetuado.';
COMMENT ON COLUMN carteira_pesca.retorno.numero_aviso_bancario IS 'Número do aviso registrado pelo banco.';

ALTER TABLE carteira_pesca.titulo DROP COLUMN dt_geracao_remessa;

ALTER TABLE carteira_pesca.titulo ADD COLUMN valor_pago DOUBLE PRECISION;
COMMENT ON COLUMN carteira_pesca.titulo.valor_pago IS 'Valor que foi pago pelo titulo título.';

ALTER TABLE carteira_pesca.titulo ADD COLUMN id_remessa INTEGER;
ALTER TABLE carteira_pesca.titulo ADD CONSTRAINT fk_remessa_titulo FOREIGN KEY (id_remessa)
  REFERENCES carteira_pesca.remessa (id) MATCH SIMPLE
  ON UPDATE RESTRICT ON DELETE RESTRICT;

COMMENT ON COLUMN carteira_pesca.titulo.id_remessa IS 'Remessa gerada para o título.';

ALTER TABLE carteira_pesca.titulo ADD COLUMN id_retorno INTEGER NULL;
ALTER TABLE carteira_pesca.titulo ADD CONSTRAINT fk_retorno_titulo FOREIGN KEY (id_retorno)
  REFERENCES carteira_pesca.retorno (id) MATCH SIMPLE
  ON UPDATE RESTRICT ON DELETE RESTRICT;

COMMENT ON COLUMN carteira_pesca.titulo.id_retorno IS 'Retorno que o titulo foi processado.';

ALTER TABLE carteira_pesca.remessa DROP COLUMN sequencia_nome_arquivo;
ALTER TABLE carteira_pesca.remessa ADD COLUMN sequencia_nome_arquivo INTEGER;

COMMENT ON COLUMN carteira_pesca.remessa.sequencia_nome_arquivo IS 'Sequência com nome do arquivo.';

--v22
ALTER TABLE carteira_pesca.licenca ADD COLUMN dt_vencimento_provisoria date;

COMMENT ON COLUMN carteira_pesca.licenca.dt_vencimento_provisoria IS 'Data de vencimento da carteira provisória.';
COMMENT ON COLUMN carteira_pesca.licenca.dt_vencimento IS 'Data de vencimento da carteira.';

UPDATE carteira_pesca.status SET descricao = 'Aguardando pagamento do boleto', codigo = 'AGUARDANDO_PAGAMENTO' WHERE id = 0;
UPDATE carteira_pesca.status SET descricao = 'Ativo', codigo = 'ATIVO' WHERE id = 1;
UPDATE carteira_pesca.status SET descricao = 'Invalidado', codigo = 'INVALIDADO' WHERE id = 2;
UPDATE carteira_pesca.status SET descricao = 'Vencido', codigo = 'VENCIDO' WHERE id = 3;
UPDATE carteira_pesca.status SET descricao = 'Renovado', codigo = 'RENOVADO' WHERE id = 4;
INSERT INTO carteira_pesca.status VALUES (5, 'Ativo aguardando pagamento do boleto', 'ATIVO_AGUARDANDO_PAGAMENTO');

--v23
create table carteira_pesca.motivo
(
  id        serial not null,
  descricao text   not null,
  sucesso   boolean  default false not null,
  constraint pk_motivo
    primary key (id),
  constraint motivo_descricao_key
    unique (descricao)
);

grant select, update, delete on table carteira_pesca.motivo to carteira_pesca;
grant select, update on sequence carteira_pesca.motivo_id_seq to carteira_pesca;

comment on table carteira_pesca.motivo is 'Tabela que guarda os motivos relacionados às ocorrências que são informados no arquivo de retorno';

comment on column carteira_pesca.motivo.id is 'identificador da tabela mtivo';

comment on column carteira_pesca.motivo.descricao is 'Texto que descreve o motivo';

comment on column carteira_pesca.motivo.sucesso is 'Flag que indica se o motivo é de sucesso ou de falha';

create table carteira_pesca.ocorrencia
(
  id          serial   not null,
  descricao   text     not null,
  codigo      smallint not null,
  constraint pk_ocorrencia
    primary key (id)
);

grant select, update, delete on table carteira_pesca.ocorrencia to carteira_pesca;

grant select, update on sequence carteira_pesca.ocorrencia_id_seq to carteira_pesca;

comment on table carteira_pesca.ocorrencia is 'Tabela que guarda as ocorrências relacionadas aos arquivo de retorno';

comment on column carteira_pesca.ocorrencia.id is 'Identificador da tabela de ocorrêcias';

comment on column carteira_pesca.ocorrencia.descricao is 'Texto que descreve o texto da ocorrência';

comment on column carteira_pesca.ocorrencia.codigo is 'Código único que identifica cada ocorrência de acordo com os dados do arquivo de retorno';

create unique index ocorrencia_codigo_uindex
  on carteira_pesca.ocorrencia (codigo);

create table carteira_pesca.motivo_ocorrencia
(
  id            serial   not null,
  id_motivo     integer  not null,
  id_ocorrencia integer  not null,
  codigo_motivo smallint not null,
  constraint pk_motivo_ocorrencia
    primary key (id),
  constraint motivo_ocorrencia_id_motivo_id_ocorrencia_key
    unique (id_motivo, id_ocorrencia),
  constraint fk_mo_motivo
    foreign key (id_motivo) references carteira_pesca.motivo (id),
  constraint fk_mo_ocorrencia
    foreign key (id_ocorrencia) references carteira_pesca.ocorrencia (id)
);

grant select, update, delete on table carteira_pesca.motivo_ocorrencia to carteira_pesca;

grant select, update on sequence carteira_pesca.motivo_ocorrencia_id_seq to carteira_pesca;

comment on table carteira_pesca.motivo_ocorrencia is 'Tabela que relaciona o motivo à ocorrência dos arquivos de retorno de remessa';

comment on column carteira_pesca.motivo_ocorrencia.id is 'Identificador da tabela motivo_ocorrencia';

comment on column carteira_pesca.motivo_ocorrencia.id_motivo is 'Motivo a qual está relacionada';

comment on column carteira_pesca.motivo_ocorrencia.id_ocorrencia is 'Ocorrência a qual está relacionada';

comment on column carteira_pesca.motivo_ocorrencia.codigo_motivo is 'Numero do codigo do motivo, ele é único para cada canjunto (motivo,ocorrência)';

-- Ocorrências com motivos descritos
INSERT INTO carteira_pesca.ocorrencia (id, descricao, codigo) VALUES (1, 'Entrada confirmada', 2);
INSERT INTO carteira_pesca.ocorrencia (id, descricao, codigo) VALUES (2, 'Entrada Rejeitada', 3);
INSERT INTO carteira_pesca.ocorrencia (id, descricao, codigo) VALUES (3, 'Liquidação', 6);
INSERT INTO carteira_pesca.ocorrencia (id, descricao, codigo) VALUES (4, 'Baixado Automaticamente via Arquivo', 9);
INSERT INTO carteira_pesca.ocorrencia (id, descricao, codigo) VALUES (5, 'Baixado pelo Banco', 10);
INSERT INTO carteira_pesca.ocorrencia (id, descricao, codigo) VALUES (6, 'Liquidação em cartório', 15);
INSERT INTO carteira_pesca.ocorrencia (id, descricao, codigo) VALUES (7, 'Liquidação após baixa ou Título não registrado', 17);
INSERT INTO carteira_pesca.ocorrencia (id, descricao, codigo) VALUES (8, 'Entrada Rejeitada por CEP irregular', 24);
INSERT INTO carteira_pesca.ocorrencia (id, descricao, codigo) VALUES (9, 'Baixa Rejeitada', 27);
INSERT INTO carteira_pesca.ocorrencia (id, descricao, codigo) VALUES (10, 'Débito de Tarifas/Custas', 28);
INSERT INTO carteira_pesca.ocorrencia (id, descricao, codigo) VALUES (11, 'Ocorrência do Pagador', 29);
INSERT INTO carteira_pesca.ocorrencia (id, descricao, codigo) VALUES (12, 'Alteração de Outros Dados Rejeitados', 30);
INSERT INTO carteira_pesca.ocorrencia (id, descricao, codigo) VALUES (13, 'Instrução Rejeitada', 32);
INSERT INTO carteira_pesca.ocorrencia (id, descricao, codigo) VALUES (14, 'Desagendamento do Débito Automático', 35);

-- Ocorrências sem motivos descritos
INSERT INTO carteira_pesca.ocorrencia (id, descricao, codigo) VALUES (15, 'Arquivo de Títulos pendentes' , 11);
INSERT INTO carteira_pesca.ocorrencia (id, descricao, codigo) VALUES (16, 'Abatimento Concedido' , 12);
INSERT INTO carteira_pesca.ocorrencia (id, descricao, codigo) VALUES (17, 'Abatimento Cancelado' , 13);
INSERT INTO carteira_pesca.ocorrencia (id, descricao, codigo) VALUES (18, 'Vencimento Alterado' , 14);
INSERT INTO carteira_pesca.ocorrencia (id, descricao, codigo) VALUES (19, 'Título Pago em Cheque – Vinculado' , 16);
INSERT INTO carteira_pesca.ocorrencia (id, descricao, codigo) VALUES (20, 'Acerto de Depositária' , 18);
INSERT INTO carteira_pesca.ocorrencia (id, descricao, codigo) VALUES (21, 'Confirmação Recebimento de Instrução de Protesto' , 19);
INSERT INTO carteira_pesca.ocorrencia (id, descricao, codigo) VALUES (22, 'Confirmação Recebimento Instrução Sustação de Protesto' , 20);
INSERT INTO carteira_pesca.ocorrencia (id, descricao, codigo) VALUES (23, 'Acerto do Controle do Participante' , 21);
INSERT INTO carteira_pesca.ocorrencia (id, descricao, codigo) VALUES (24, 'Título Com Pagamento Cancelado' , 22);
INSERT INTO carteira_pesca.ocorrencia (id, descricao, codigo) VALUES (25, 'Entrada do Título em Cartório' , 23);
INSERT INTO carteira_pesca.ocorrencia (id, descricao, codigo) VALUES (26, 'Confirmação Recebimento de Instrução de Protesto Falimentar' , 25);
INSERT INTO carteira_pesca.ocorrencia (id, descricao, codigo) VALUES (27, 'Confirmação Pedido Alteração Outros Dados' , 33);
INSERT INTO carteira_pesca.ocorrencia (id, descricao, codigo) VALUES (28, 'Retirado de Cartório e Manutenção Carteira' , 34);
INSERT INTO carteira_pesca.ocorrencia (id, descricao, codigo) VALUES (29, 'Estorno de pagamento' , 40);
INSERT INTO carteira_pesca.ocorrencia (id, descricao, codigo) VALUES (30, 'Sustado judicial' , 55);
INSERT INTO carteira_pesca.ocorrencia (id, descricao, codigo) VALUES (31, 'Acerto dos dados do rateio de Crédito' , 68);
INSERT INTO carteira_pesca.ocorrencia (id, descricao, codigo) VALUES (32, 'Cancelamento dos dados do rateio' , 69);
INSERT INTO carteira_pesca.ocorrencia (id, descricao, codigo) VALUES (33, 'Confirmação Recebimento Pedido de Negativação' , 73);
INSERT INTO carteira_pesca.ocorrencia (id, descricao, codigo) VALUES (34, 'Confirmação Pedido de Excl de Negativação' , 74);

ALTER SEQUENCE carteira_pesca.ocorrencia_id_seq RESTART WITH 35;

INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (1, 'Acond. De papelatas (RPB)s PERSONAL', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (2, 'Acondicionador de papeletas (RPB)S', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (3, 'Agência Beneficiário não prevista', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (4, 'Agência/Conta/dígito inválidos', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (5, 'Alteração de produto negociado', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (6, 'Arq retorno Hora/Hora', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (7, 'Arq. Títulos a vencer mensal', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (8, 'Arquivo Retorno Antecipado', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (9, 'Baixa Comandada pelo cliente', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (10, 'Baixa por acerto cliente', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (11, 'Baixa registro em duplicidade', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (12, 'Baixado Conforme Instruções da Agência', true);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (13, 'Cadastro Concessionária serv. Publ.', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (14, 'Cadastro de Mensagem Fixa', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (15, 'Cadastro de reembolso de diferença', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (16, 'Canalização de Crédito', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (17, 'Cancelado pelo pagador e baixado, conforme negociação', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (18, 'Cancelado pelo Pagador e Mantido Pendente, conforme negociação', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (19, 'Característica da cobrança incompatível', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (20, 'Características da cobrança incompatíveis', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (21, 'Carteira inválida', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (22, 'Carteira/Agência/Conta/nosso número inválidos', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (23, 'CEP Inválido', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (24, 'CEP irregular - Banco Correspondente', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (25, 'CEP referente a um Banco correspondente', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (26, 'Classif. Extrato Conta Corrente', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (27, 'Cobrado baixa manual', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (28, 'Código da multa inválido', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (29, 'Código da ocorrência inválida', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (30, 'Código da ocorrência não numérico', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (31, 'Código de desconto inválido', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (32, 'Código de desconto via Telebradesco inválido', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (33, 'Código de juros de mora inválido', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (34, 'Código de ocorrência não permitido para a carteira', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (35, 'Código do Banco inválido', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (36, 'Código do desconto inválido', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (37, 'Código do movimento não permitido para a carteira', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (38, 'Código do registro detalhe inválido', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (39, 'Código para baixa/devolução inválido', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (40, 'Código para baixa/devolução via Tele Bradesco inválido', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (41, 'Concessão abatimento - Já existe abatimento anterior', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (42, 'Concessão de desconto - Já existe desconto anterior', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (43, 'Contabilidade especial', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (44, 'Custas de protesto', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (45, 'Data da emissão inválida', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (46, 'Data da multa inválida', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (47, 'Data de Juros de mora Inválida', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (48, 'Data de vencimento anterior a data de emissão', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (49, 'Data de vencimento inválida', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (50, 'Data do desconto inválida', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (51, 'Débito automático agendado', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (52, 'Débito não agendado - Beneficiário não autorizado pelo Pagador', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (53, 'Débito não agendado - Beneficiário não participa do débito automático', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (54, 'Débito não agendado - Código de moeda diferente de R$', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (55, 'Débito não agendado - Conforme seu pedido, Título não registrado', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (56, 'Débito não agendado - Data de vencimento inválida', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (57, 'Débito não agendado - Data de vencimento inválida/vencida', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (58, 'Débito não agendado - erro nos dados de remessa', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (59, 'Débito não agendado - Pagador não consta no cadastro de autorizante', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (60, 'Débito não agendado - Tipo do número de inscrição do pagador debitado inválido', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (61, 'Débito não agendado – Tipo de número de inscrição do debitado inválido', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (62, 'Desconto a conceder não confere', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (63, 'E-mail Pagador não lido no prazo 5 dias', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (64, 'E-mail pagador não recebido', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (65, 'Email Pagador não enviado – título com débito automático', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (66, 'Email pagador não enviado – título de cobrança sem registro', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (67, 'Emissão Extrato mov. Carteira', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (68, 'Emissão papeleta sem valor', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (69, 'Endereço do pagador não informado', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (70, 'Entrada para Título já cadastrado', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (71, 'Espécie do Título inválido', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (72, 'Espécie não permitida para a carteira', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (73, 'Extrato de protesto', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (74, 'Formulário A4 serrilhado', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (75, 'Fornecimento de Impressoras', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (76, 'Fornecimento de máquina FAX', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (77, 'Fornecimento de máquinas óticas', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (78, 'Fornecimento de softwares consulta', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (79, 'Fornecimento de softwares transmiss', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (80, 'Fornecimento Micro Completo', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (81, 'Fornecimento MODEN', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (82, 'Identificação da emissão do bloqueto inválida', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (83, 'Impressão de títulos baixados', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (84, 'Impressão de títulos pagos', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (85, 'Inclusão Bloqueada face a determinação Judicial', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (86, 'Instrução não permitida título negativado', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (87, 'Limite excedido', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (88, 'Listagem auxiliar de crédito', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (89, 'Mensagem campo local de pagto', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (90, 'Movimento para Título não cadastrado', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (91, 'Nº de inscrição do Pagador/avalista inválidos (CPF/CNPJ)', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (92, 'Nome do Pagador inválido', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (93, 'Nome do pagador não informado', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (94, 'Nosso número duplicado', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (95, 'Nosso número inválido', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (96, 'Número autorização inexistente', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (97, 'Ocorrência aceita', true);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (98, 'Pagador aceita/reconhece o faturamento', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (99, 'Pagador alega que faturamento e indevido', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (100, 'Pagador Eletrônico DDA - Esse motivo somente será disponibilizado no arquivo retorno para as empresas cadastradas nessa condição.', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (101, 'Pagador/avalista não informado', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (102, 'Pagamento Parcial', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (103, 'Papeleta formulário branco', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (104, 'Pedido de sustação/excl p/ Título sem instrução de protesto/Negativação', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (105, 'Pedido para protesto/ Negativação não permitido para o título', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (106, 'Prazo para baixa e devolução inválido', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (107, 'Prazo para protesto/ Negativação inválido', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (108, 'Rateio não efetuado, cód. Calculo 2 (VLR. Registro) e v', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (109, 'Realimentação pagto', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (110, 'Reativação de título', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (111, 'Relatório fluxo de pagto', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (112, 'Repasse de Créditos', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (113, 'Sem uso', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (114, 'Seu número do documento inválido', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (115, 'Seu número inválido', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (116, 'Tarifa alteração de vencimento', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (117, 'Tarifa baixa por contabilidade', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (118, 'Tarifa cadastro cartela instrução permanente', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (119, 'Tarifa cancelamento de abatimento', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (120, 'Tarifa cancelamento desconto', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (121, 'Tarifa concessão abatimento', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (122, 'Tarifa concessão desconto', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (123, 'Tarifa de outras instruções', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (124, 'Tarifa de outras ocorrências', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (125, 'Tarifa de permanência título cadastrado', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (126, 'Tarifa de protesto/Incl Negativação', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (127, 'Tarifa de registro', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (128, 'Tarifa de sustação/Excl Negativação', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (129, 'Tarifa emissão 2ª via papeleta', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (130, 'Tarifa emissão de contra recibo', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (131, 'Tarifa emissão Papeleta', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (132, 'Tarifa fornec papeleta semi preenchida', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (133, 'Tarifa impressão de títulos pendentes', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (134, 'Tarifa Rateio de Crédito', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (135, 'Tarifa reapresentação automática título', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (136, 'Tarifa reg. Pagto outras mídias', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (137, 'Tarifa Reg/Pagto – Net Empresa', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (138, 'Tarifa reg/pagto Bradesco Expresso', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (139, 'Tarifa reg/pgto – guichê caixa', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (140, 'Tarifa registro título déb. Automático', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (141, 'Tarifa regravação arquivo retorno', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (142, 'Tarifa título Baix. Pg. Cartório', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (143, 'Tarifa título baixado acerto BCO', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (144, 'Tarifa título baixado conf. Pedido', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (145, 'Tarifa título baixado decurso prazo', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (146, 'Tarifa título baixado franco pagto', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (147, 'Tarifa título baixado Judicialmente', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (148, 'Tarifa título baixado não pago', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (149, 'Tarifa título baixado p/ devolução', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (150, 'Tarifa título baixado protestado', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (151, 'Tarifa título baixado rastreamento', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (152, 'Tarifa título baixado SUS/SEM/REM/CARTÓRIO', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (153, 'Tarifa título baixado SUST/RET/CARTÓRIO', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (154, 'Tarifa título baixado via remessa', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (155, 'Tarifa título Déb. Postagem', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (156, 'Tarifa título pago BDN', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (157, 'Tarifa título pago Cartão de Crédito', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (158, 'Tarifa título pago cics', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (159, 'Tarifa título pago Comp Eletrônica', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (160, 'Tarifa título pago compensação', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (161, 'Tarifa título pago Fone Fácil', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (162, 'Tarifa título pago Internet', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (163, 'Tarifa título pago no Bradesco', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (164, 'Tarifa título pago Pág-Contas', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (165, 'Tarifa título pago Pagfor', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (166, 'Tarifa título pago retaguarda', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (167, 'Tarifa título pago Subcentro', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (168, 'Tarifa título pago term. gerencial serviços', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (169, 'Tarifa título pago Term. Multi Função', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (170, 'Tarifa título pago vencido', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (171, 'Tarifa título transferido desconto', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (172, 'Telefone beneficiário não informado / inconsistente', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (173, 'Tentativas esgotadas, baixado', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (174, 'Tentativas esgotadas, pendente', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (175, 'Tipo de inscrição do pagador avalista inválidos', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (176, 'Tipo/número de inscrição do pagador inválidos', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (177, 'Tipo/Número de inscrição do pagador/avalista inválidos', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (178, 'Titulo Baixado e Transferido para Desconto', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (179, 'Título Baixado pelo Banco por decurso Prazo', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (180, 'Titulo Baixado Transferido Carteira', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (181, 'Título com ordem de protesto emitido', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (182, 'Título com ordem/pedido de protesto/Negativação emitido', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (183, 'Título com pagamento vinculado', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (184, 'Título excluído', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (185, 'Título pago com cheque', true);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (186, 'Título pago com dinheiro', true);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (187, 'Título Penhorado – Instrução Não Liberada pela Agência', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (188, 'Título Protestado', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (189, 'TR Emissão aviso rateio', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (190, 'TR Tít. Baixado por decurso prazo', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (191, 'TR. Agendamento Déb Aut', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (192, 'TR. Agendamento rat. Crédito', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (193, 'Tr. credito online', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (194, 'Tr. tentativa cons deb aut', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (195, 'Transferência para desconto não permitido para a carteira', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (196, 'Valor do abatimento inválido', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (197, 'Valor do abatimento maior/igual ao valor do Título', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (198, 'Valor do desconto maior/igual ao valor do Título', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (199, 'Valor do IOF inválido', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (200, 'Valor do título inválido', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (201, 'Valor/taxa de juros de mora inválido', false);
INSERT INTO carteira_pesca.motivo (id, descricao, sucesso) VALUES (202, 'Vencimento fora do prazo de operação', false);

ALTER SEQUENCE carteira_pesca.motivo_id_seq RESTART WITH 203;

INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (1, 97, 1, 0);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (2, 35, 1, 1);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (3, 37, 1, 4);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (4, 20, 1, 15);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (5, 48, 1, 17);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (6, 71, 1, 21);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (7, 45, 1, 24);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (8, 201, 1, 27);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (9, 107, 1, 38);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (10, 105, 1, 39);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (11, 106, 1, 43);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (12, 92, 1, 45);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (13, 176, 1, 46);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (14, 69, 1, 47);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (15, 23, 1, 48);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (16, 25, 1, 50);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (17, 91, 1, 53);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (18, 101, 1, 54);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (19, 51, 1, 67);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (20, 58, 1, 68);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (21, 59, 1, 69);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (22, 52, 1, 70);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (23, 53, 1, 71);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (24, 54, 1, 72);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (25, 57, 1, 73);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (26, 60, 1, 75);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (27, 100, 1, 76);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (28, 114, 1, 86);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (29, 65, 1, 89);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (30, 66, 1, 90);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (31, 38, 2, 2);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (32, 29, 2, 3);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (33, 34, 2, 4);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (34, 30, 2, 5);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (35, 4, 2, 7);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (36, 95, 2, 8);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (37, 94, 2, 9);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (38, 21, 2, 10);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (39, 82, 2, 13);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (40, 49, 2, 16);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (41, 202, 2, 18);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (42, 200, 2, 20);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (43, 71, 2, 21);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (44, 72, 2, 22);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (45, 45, 2, 24);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (46, 36, 2, 28);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (47, 107, 2, 38);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (48, 3, 2, 44);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (49, 93, 2, 45);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (50, 176, 2, 46);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (51, 69, 2, 47);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (52, 23, 2, 48);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (53, 24, 2, 50);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (54, 70, 2, 63);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (55, 87, 2, 65);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (56, 96, 2, 66);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (57, 58, 2, 68);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (58, 59, 2, 69);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (59, 52, 2, 70);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (60, 53, 2, 71);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (61, 54, 2, 72);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (62, 56, 2, 73);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (63, 55, 2, 74);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (64, 61, 2, 75);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (65, 186, 3, 0);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (66, 185, 3, 15);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (67, 102, 3, 18);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (68, 108, 3, 42);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (69, 97, 4, 0);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (70, 9, 4, 10);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (71, 12, 5, 0);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (72, 188, 5, 14);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (73, 184, 5, 15);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (74, 179, 5, 16);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (75, 180, 5, 17);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (76, 178, 5, 20);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (77, 186, 6, 0);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (78, 185, 6, 15);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (79, 186, 7, 0);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (80, 185, 7, 15);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (81, 23, 8, 48);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (82, 34, 9, 4);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (83, 4, 9, 7);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (84, 95, 9, 8);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (85, 21, 9, 10);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (86, 22, 9, 15);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (87, 181, 9, 40);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (88, 40, 9, 42);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (89, 90, 9, 60);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (90, 195, 9, 77);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (91, 183, 9, 85);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (92, 125, 10, 2);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (93, 128, 10, 3);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (94, 126, 10, 4);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (95, 123, 10, 5);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (96, 124, 10, 6);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (97, 44, 10, 8);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (98, 127, 10, 12);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (99, 163, 10, 13);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (100, 160, 10, 14);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (101, 148, 10, 15);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (102, 116, 10, 16);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (103, 121, 10, 17);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (104, 119, 10, 18);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (105, 122, 10, 19);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (106, 120, 10, 20);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (107, 158, 10, 21);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (108, 162, 10, 22);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (109, 168, 10, 23);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (110, 164, 10, 24);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (111, 161, 10, 25);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (112, 155, 10, 26);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (113, 133, 10, 27);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (114, 156, 10, 28);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (115, 169, 10, 29);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (116, 83, 10, 30);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (117, 84, 10, 31);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (118, 165, 10, 32);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (119, 139, 10, 33);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (120, 166, 10, 34);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (121, 167, 10, 35);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (122, 157, 10, 36);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (123, 159, 10, 37);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (124, 142, 10, 38);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (125, 143, 10, 39);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (126, 11, 10, 40);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (127, 145, 10, 41);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (128, 147, 10, 42);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (129, 154, 10, 43);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (130, 151, 10, 44);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (131, 144, 10, 45);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (132, 150, 10, 46);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (133, 149, 10, 47);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (134, 146, 10, 48);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (135, 153, 10, 49);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (136, 152, 10, 50);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (137, 171, 10, 51);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (138, 27, 10, 52);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (139, 10, 10, 53);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (140, 117, 10, 54);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (141, 194, 10, 55);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (142, 193, 10, 56);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (143, 138, 10, 57);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (144, 131, 10, 58);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (145, 132, 10, 59);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (146, 2, 10, 60);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (147, 1, 10, 61);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (148, 103, 10, 62);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (149, 74, 10, 63);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (150, 79, 10, 64);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (151, 78, 10, 65);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (152, 80, 10, 66);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (153, 81, 10, 67);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (154, 76, 10, 68);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (155, 77, 10, 69);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (156, 75, 10, 70);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (157, 110, 10, 71);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (158, 5, 10, 72);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (159, 130, 10, 73);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (160, 129, 10, 74);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (161, 141, 10, 75);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (162, 7, 10, 76);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (163, 88, 10, 77);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (164, 118, 10, 78);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (165, 16, 10, 79);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (166, 14, 10, 80);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (167, 135, 10, 81);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (168, 140, 10, 82);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (169, 134, 10, 83);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (170, 68, 10, 84);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (171, 113, 10, 85);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (172, 15, 10, 86);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (173, 111, 10, 87);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (174, 67, 10, 88);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (175, 89, 10, 89);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (176, 13, 10, 90);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (177, 26, 10, 91);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (178, 43, 10, 92);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (179, 109, 10, 93);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (180, 112, 10, 94);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (181, 136, 10, 96);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (182, 137, 10, 97);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (183, 170, 10, 98);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (184, 190, 10, 99);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (185, 8, 10, 100);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (186, 6, 10, 101);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (187, 191, 10, 102);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (188, 192, 10, 105);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (189, 189, 10, 106);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (190, 73, 10, 107);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (191, 99, 11, 78);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (192, 98, 11, 95);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (193, 35, 12, 1);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (194, 34, 12, 4);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (195, 30, 12, 5);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (196, 95, 12, 8);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (197, 19, 12, 15);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (198, 49, 12, 16);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (199, 48, 12, 17);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (200, 202, 12, 18);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (201, 45, 12, 24);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (202, 33, 12, 26);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (203, 201, 12, 27);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (204, 31, 12, 28);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (205, 198, 12, 29);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (206, 62, 12, 30);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (207, 42, 12, 31);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (208, 199, 12, 32);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (209, 196, 12, 33);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (210, 197, 12, 34);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (211, 107, 12, 38);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (212, 105, 12, 39);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (213, 182, 12, 40);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (214, 39, 12, 42);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (215, 176, 12, 46);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (216, 23, 12, 48);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (217, 177, 12, 53);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (218, 101, 12, 54);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (219, 28, 12, 57);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (220, 46, 12, 58);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (221, 90, 12, 60);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (222, 47, 12, 79);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (223, 50, 12, 80);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (224, 183, 12, 85);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (225, 63, 12, 88);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (226, 64, 12, 91);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (227, 35, 13, 1);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (228, 38, 13, 2);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (229, 34, 13, 4);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (230, 30, 13, 5);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (231, 4, 13, 7);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (232, 95, 13, 8);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (233, 21, 13, 10);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (234, 20, 13, 15);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (235, 49, 13, 16);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (236, 48, 13, 17);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (237, 202, 13, 18);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (238, 200, 13, 20);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (239, 71, 13, 21);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (240, 72, 13, 22);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (241, 45, 13, 24);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (242, 32, 13, 28);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (243, 198, 13, 29);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (244, 62, 13, 30);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (245, 42, 13, 31);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (246, 196, 13, 33);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (247, 197, 13, 34);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (248, 41, 13, 36);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (249, 107, 13, 38);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (250, 105, 13, 39);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (251, 182, 13, 40);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (252, 104, 13, 41);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (253, 39, 13, 42);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (254, 93, 13, 45);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (255, 176, 13, 46);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (256, 69, 13, 47);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (257, 23, 13, 48);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (258, 25, 13, 50);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (259, 175, 13, 53);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (260, 90, 13, 60);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (261, 183, 13, 85);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (262, 115, 13, 86);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (263, 187, 13, 94);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (264, 86, 13, 97);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (265, 85, 13, 98);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (266, 172, 13, 99);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (267, 173, 14, 81);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (268, 174, 14, 82);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (269, 18, 14, 83);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, codigo_motivo) VALUES (270, 17, 14, 84);

ALTER SEQUENCE carteira_pesca.motivo_ocorrencia_id_seq RESTART WITH 271;

--v24
create table carteira_pesca.titulo_remessa
(
  id         serial not null,
  id_titulo  int    not null,
  id_remessa int    not null,
  constraint pk_titulo_remessa
    primary key (id),
  constraint fk_trem_titulo
    foreign key (id_titulo) references carteira_pesca.titulo (id),
  constraint fk_trem_remessa
    foreign key (id_remessa) references carteira_pesca.remessa (id)
);

grant select, update, delete on table carteira_pesca.titulo_remessa to carteira_pesca;
grant select, update on sequence carteira_pesca.titulo_remessa_id_seq to carteira_pesca;

comment on table carteira_pesca.titulo_remessa is 'Tabela que relaciona títulos e remessas';
comment on column carteira_pesca.titulo_remessa.id is 'Identificador da tabela';
comment on column carteira_pesca.titulo_remessa.id_titulo is 'Referência para o título pertencente';
comment on column carteira_pesca.titulo_remessa.id_remessa is 'Referência para a remessa pertencente';

create table carteira_pesca.titulo_retorno
(
  id                   serial not null,
  id_titulo            int    not null,
  id_retorno           int    not null,
  id_motivo_ocorrencia int    not null,
  constraint pk_titulo_retorno
    primary key (id),
  constraint fk_tret_titulo
    foreign key (id_titulo) references carteira_pesca.titulo (id),
  constraint fk_tret_retorno
    foreign key (id_retorno) references carteira_pesca.retorno (id),
  constraint fk_tret_motivo_ocorrencia
    foreign key (id_motivo_ocorrencia) references carteira_pesca.motivo_ocorrencia (id)
);

grant select, update, delete on table carteira_pesca.titulo_retorno to carteira_pesca;
grant select, update on sequence carteira_pesca.titulo_retorno_id_seq to carteira_pesca;

comment on table carteira_pesca.titulo_retorno is 'Tabela que relaciona títulos e retornos';
comment on column carteira_pesca.titulo_retorno.id is 'Identificador da tabela';
comment on column carteira_pesca.titulo_retorno.id_titulo is 'Referência para o título pertencente';
comment on column carteira_pesca.titulo_retorno.id_retorno is 'Referência para o retorno pertencente';
comment on column carteira_pesca.titulo_retorno.id_motivo_ocorrencia is 'Referência para o motivo da ocorrência pertencente';

--v25
CREATE TABLE carteira_pesca.tipo_segmento (
  id SERIAL NOT NULL, 
  codigo INTEGER,
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
  codigo INTEGER,
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

--v26
DROP TABLE carteira_pesca.titulo_remessa;

CREATE TABLE carteira_pesca.titulo_remessa (
  id_titulo  INTEGER NOT NULL,
  id_remessa INTEGER NOT NULL,
  CONSTRAINT pk_titulo_remessa PRIMARY KEY (id_titulo, id_remessa),
  CONSTRAINT fk_trem_titulo
    FOREIGN KEY (id_titulo) REFERENCES carteira_pesca.titulo (id),
  CONSTRAINT fk_trem_remessa
    FOREIGN KEY (id_remessa) REFERENCES carteira_pesca.remessa (id)
);

GRANT SELECT, UPDATE, DELETE ON TABLE carteira_pesca.titulo_remessa to carteira_pesca;

COMMENT ON TABLE carteira_pesca.titulo_remessa is 'Tabela que relaciona títulos e remessas';
COMMENT ON COLUMN carteira_pesca.titulo_remessa.id_titulo is 'Referência para o título pertencente';
COMMENT ON COLUMN carteira_pesca.titulo_remessa.id_remessa is 'Referência para a remessa pertencente';


ALTER TABLE carteira_pesca.titulo DROP COLUMN id_remessa;
ALTER TABLE carteira_pesca.titulo DROP COLUMN id_retorno;

ALTER TABLE carteira_pesca.titulo ADD COLUMN fl_gerar_remessa BOOLEAN NOT NULL DEFAULT TRUE;
COMMENT ON COLUMN carteira_pesca.titulo.fl_gerar_remessa is 'Flag que verifica se é necessário gerar (ou regerar) remessa para deteminado titulo';

--v27
ALTER TABLE carteira_pesca.pagador_titulo RENAME TO pagador;
ALTER TABLE carteira_pesca.beneficiario_titulo RENAME CONSTRAINT pk_beneficiario TO pk_beneficiario_titulo;

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
(1, 6, 'Valor a ser cobrado efetivamente em reais calculado pelo módulo 10'),
(2, 7, 'Quantidade de moeda calculado pelo módulo 10'),
(3, 8, 'Valor a ser cobrado efetivamente em reais calculado pelo módulo 11'),
(4, 9, 'Quantidade de moeda calculado pelo módulo 11');

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

CREATE TABLE carteira_pesca.convenio (
  id SERIAL NOT NULL, 
  id_tipo_segmento INTEGER NOT NULL,
  id_tipo_valor_efetivo INTEGER NOT NULL, 
  id_pagador INTEGER NOT NULL, 
  id_beneficiario INTEGER NOT NULL,
  valor DOUBLE PRECISION NOT NULL, 
  data_emissao DATE NOT NULL, 
  data_vencimento DATE NOT NULL,
  codigo_barra CHARACTER VARYING(75) NOT NULL,
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
    ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT fk_c_beneficiario FOREIGN KEY (id_beneficiario)
    REFERENCES carteira_pesca.beneficiario (id) MATCH SIMPLE
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
COMMENT ON COLUMN carteira_pesca.convenio.id_beneficiario IS 'Identificador único da entidade beneficiario que faz o relacionamento entre beneficiário e convenio.';
COMMENT ON COLUMN carteira_pesca.convenio.valor IS 'Valor do convênio.';
COMMENT ON COLUMN carteira_pesca.convenio.data_emissao IS 'Data de emissão da nota.';
COMMENT ON COLUMN carteira_pesca.convenio.data_vencimento IS 'Data de vencimento da nota.';
COMMENT ON COLUMN carteira_pesca.convenio.codigo_barra IS 'Código de barra do boleto.';
COMMENT ON COLUMN carteira_pesca.convenio.nosso_numero IS 'Número de controle interno.';

INSERT INTO carteira_pesca.beneficiario (nome, sigla, cpf_cnpj, id_endereco) SELECT nome, sigla, cpf_cnpj, id_endereco FROM carteira_pesca.beneficiario_titulo;

ALTER TABLE carteira_pesca.beneficiario_titulo DROP COLUMN nome;
ALTER TABLE carteira_pesca.beneficiario_titulo DROP COLUMN sigla;
ALTER TABLE carteira_pesca.beneficiario_titulo DROP COLUMN cpf_cnpj;
ALTER TABLE carteira_pesca.beneficiario_titulo DROP COLUMN id_endereco;

ALTER TABLE carteira_pesca.beneficiario_titulo ADD COLUMN id_beneficiario INTEGER NULL;
COMMENT ON COLUMN carteira_pesca.beneficiario_titulo.id_beneficiario IS 'Identificador único da entidade beneficiario que faz o relacionamento entre beneficiario e beneficiario_titulo.';

ALTER TABLE carteira_pesca.beneficiario_titulo ADD CONSTRAINT fk_bt_beneficiario FOREIGN KEY (id_beneficiario) 
  REFERENCES carteira_pesca.endereco (id) MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT;

UPDATE carteira_pesca.beneficiario_titulo SET id_beneficiario = (SELECT b.id FROM carteira_pesca.beneficiario b, carteira_pesca.beneficiario_titulo bt WHERE b.id = bt.id);

--v28
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

--v29
GRANT SELECT, USAGE ON ALL SEQUENCES IN SCHEMA carteira_pesca TO carteira_pesca;

--v30
ALTER TABLE carteira_pesca.convenio ADD COLUMN linha_digitavel CHARACTER VARYING(75);
COMMENT ON COLUMN carteira_pesca.convenio.linha_digitavel IS 'Conteúdo da linha digitável do boleto.';

UPDATE carteira_pesca.convenio SET linha_digitavel = '';
ALTER TABLE carteira_pesca.convenio ALTER COLUMN  linha_digitavel SET NOT NULL;

ALTER TABLE carteira_pesca.convenio RENAME COLUMN codigo_barra TO codigo_barras;
COMMENT ON COLUMN carteira_pesca.convenio.codigo_barras IS 'Conteúdo do código de barras do boleto.';

--v31
CREATE TABLE carteira_pesca.retorno_convenio
(
    id                 SERIAL  NOT NULL,
    id_arquivo         INTEGER NOT NULL,
    dt_geracao_arquivo DATE,
    dt_processamento   TIMESTAMP,
    numero_arquivo     INTEGER,
    qtd_registros      INTEGER,
    valor_recebido     DOUBLE PRECISION,

    CONSTRAINT pk_retorno_convenio PRIMARY KEY (id),

    CONSTRAINT fk_rc_arquivo FOREIGN KEY (id_arquivo)
        REFERENCES carteira_pesca.arquivo (id)
);

ALTER TABLE carteira_pesca.retorno_convenio OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.retorno_convenio TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.retorno_convenio TO carteira_pesca;
GRANT SELECT, USAGE ON SEQUENCE carteira_pesca.retorno_convenio_id_seq TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.retorno_convenio IS 'Entidade responsável por armazenar os dados do retorno do convenio.';
COMMENT ON COLUMN carteira_pesca.retorno_convenio.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.retorno_convenio.id_arquivo IS 'Arquivo de retorno que foi gerado.';
COMMENT ON COLUMN carteira_pesca.retorno_convenio.dt_geracao_arquivo IS 'Data que o arquivo de retorno foi gerado pelo banco.';
COMMENT ON COLUMN carteira_pesca.retorno_convenio.dt_processamento IS 'Data de processamento do arquivo.';
COMMENT ON COLUMN carteira_pesca.retorno_convenio.numero_arquivo IS 'Número seqüencial do arquivo.';
COMMENT ON COLUMN carteira_pesca.retorno_convenio.qtd_registros IS 'Quantidade de registros (linhas) do arquivo.';
COMMENT ON COLUMN carteira_pesca.retorno_convenio.valor_recebido IS 'Valor total recebido dos registros do arquivo.';

INSERT INTO carteira_pesca.tipo_arquivo (id, codigo, descricao)
VALUES (5, 'RETORNO_ARRECADACAO', 'Arquivo com os dados dos boletos que foram processador pelo banco');

ALTER TABLE carteira_pesca.pagamento_convenio
    ALTER COLUMN id_tipo_pagamento DROP NOT NULL,
    DROP COLUMN id_retorno,
    ADD COLUMN id_retorno INTEGER,
    ADD CONSTRAINT fk_pc_retorno_convenio FOREIGN KEY (id_retorno)
        REFERENCES carteira_pesca.retorno_convenio (id);

COMMENT ON COLUMN carteira_pesca.pagamento_convenio.id_retorno
    IS 'Identificador único da entidade retorno_convenio que faz o relacionamento entre retorno e pagamento_convenio - Identifica qual o retorno que foi processado.';

    --v32
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
    ADD CONSTRAINT fk_condicao_convenio_c FOREIGN KEY (id_condicao)
        REFERENCES carteira_pesca.condicao_convenio (id);

COMMENT ON COLUMN carteira_pesca.convenio.id_condicao IS 'Indica a condição do convênio.';

ALTER TABLE carteira_pesca.licenca DROP COLUMN dt_vencimento_provisoria;

--v33
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

