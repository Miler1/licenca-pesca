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
