GRANT ALL ON ALL TABLES TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON ALL TABLES IN SCHEMA carteira_pesca TO ROLE carteira_pesca;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO carteira_pesca;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA carteira_pesca TO carteira_pesca;
ALTER DEFAULT PRIVILEGES IN SCHEMA carteira_pesca GRANT SELECT, UPDATE, INSERT, DELETE ON ALL TABLES TO carteira_pesca;

CREATE TABLE carteira_pesca.protocolo_sequence
(
  id                        INTEGER NOT NULL,
  nom_sequence              CHARACTER VARYING(20) NOT NULL,
  val_sequence              INTEGER NOT NULL,
  idt_modalidade            INTEGER NOT NULL,
  val_sequence_year         INTEGER NOT NULL,

  CONSTRAINT pk_protocolo_sequence PRIMARY KEY (id)

) WITH (OIDS = FALSE );

ALTER TABLE carteira_pesca.protocolo_sequence OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.protocolo_sequence TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.protocolo_sequence TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.protocolo_sequence IS 'Entidade responsável por armazenar a sequência para gerar o número de protocolo.';
COMMENT ON COLUMN carteira_pesca.protocolo_sequence.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.protocolo_sequence.nom_sequence IS 'Nome da sequência do protocolo.';
COMMENT ON COLUMN carteira_pesca.protocolo_sequence.val_sequence IS 'Valor da sequência do protocolo.';
COMMENT ON COLUMN carteira_pesca.protocolo_sequence.idt_modalidade IS 'Identificador representando o id da modalidade da sequência.';
COMMENT ON COLUMN carteira_pesca.protocolo_sequence.val_sequence_year IS 'Ano em vigência da sequência.';

INSERT INTO carteira_pesca.protocolo_sequence VALUES
(0, 'seq_esportiva', 0, 0, 2018),
(1, 'seq_recreativa', 0, 1, 2018);

CREATE TABLE carteira_pesca.protocolo
(
  id                       SERIAL NOT NULL,
  val_codigo               CHARACTER VARYING (11) NOT NULL,
  CONSTRAINT pk_protocolo PRIMARY KEY (id)
) WITH (OIDS = FALSE);

ALTER TABLE carteira_pesca.protocolo OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.protocolo TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.protocolo TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.protocolo IS 'Entidade responsável por armazenar os protocolos.';
COMMENT ON COLUMN carteira_pesca.protocolo.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.protocolo.val_codigo IS 'Valor do código do Protocolo.';

CREATE TABLE carteira_pesca.passaporte
(
  id                    SERIAL NOT NULL,
  val_numero            CHARACTER VARYING(50) NOT NULL,
  CONSTRAINT pk_passaporte PRIMARY KEY (id)
) WITH (OIDS = FALSE);

ALTER TABLE carteira_pesca.passaporte OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.passaporte TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.passaporte TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.passaporte IS 'Entidade responsável por armazenar os dados de passaporte.';
COMMENT ON COLUMN carteira_pesca.passaporte.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.passaporte.val_numero IS 'O número do passaporte.';

CREATE TABLE carteira_pesca.cpf
(
  id                        SERIAL NOT NULL,
  val_numero_cpf            CHARACTER VARYING (50) NOT NULL,
  CONSTRAINT pk_cpf PRIMARY KEY (id)
) WITH (OIDS = FALSE);

ALTER TABLE carteira_pesca.cpf OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.cpf TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.cpf TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.cpf IS 'Entidade responsável por armazenar os dados de CPF.';
COMMENT ON COLUMN carteira_pesca.cpf.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.cpf.val_numero_cpf IS 'O número do CPF.';

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

COMMENT ON TABLE carteira_pesca.solicitante_id IS 'Entidade responsável por armazenar o identificador do solicitante.';
COMMENT ON COLUMN carteira_pesca.solicitante_id.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.solicitante_id.idt_passaporte IS 'Identificador único da entidade solicitante_id que realizará o relacionamento entre passaporte e solicitante_id. ';
COMMENT ON COLUMN carteira_pesca.solicitante_id.idt_cpf IS 'Identificador único da entidade solicitante_id que realizará o relacionamento entre cpf e solicitante_id. ';

CREATE TABLE carteira_pesca.solicitante
(
  id                        SERIAL NOT NULL,
  idt_solicitante_id        INTEGER NOT NULL,
  CONSTRAINT pk_solicitante PRIMARY KEY (id),
  CONSTRAINT fk_solicitante_id FOREIGN KEY (idt_solicitante_id)
    REFERENCES carteira_pesca.solicitante_id MATCH SIMPLE
    ON UPDATE RESTRICT ON DELETE RESTRICT
) WITH (OIDS = FALSE);

ALTER TABLE carteira_pesca.solicitante OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.solicitante TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.solicitante TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.solicitante IS 'Entidade responsável por armazenar os dados do solicitante.';
COMMENT ON COLUMN carteira_pesca.solicitante.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.solicitante.idt_solicitante_id IS 'Identificador único da entidade solicitante que realizará o relacionamento entre solicitante_id e solicitante';

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

ALTER TABLE carteira_pesca.licenca OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.licenca TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.licenca TO carteira_pesca;

COMMENT ON TABLE carteira_pesca.licenca IS 'Entidade responsável por armazenar a sequência para gerar o número de protocolo.';
COMMENT ON COLUMN carteira_pesca.licenca.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.licenca.idt_protocolo IS 'Identificador único da entidade protocolo que realizará o relacionamento entre protocolo e licença. ';
COMMENT ON COLUMN carteira_pesca.licenca.idt_modalidade IS 'Identificador da modalidade. 0 - Esportiva; 1 - Recreativa;';
COMMENT ON COLUMN carteira_pesca.licenca.dat_criacao IS 'Data da criação da licença.';
COMMENT ON COLUMN carteira_pesca.licenca.idt_status IS 'Identificador do status da licença. 0 - Aguardando; 1 - Ativo; 2 - Inválido;';
COMMENT ON COLUMN carteira_pesca.licenca.dat_ativacao IS 'Data em que a licença tornou-se ativa.';

DROP TABLE carteira_pesca.sequencia_protocolo;
DROP TABLE carteira_pesca.licenca;
DROP TABLE carteira_pesca.solicitante;