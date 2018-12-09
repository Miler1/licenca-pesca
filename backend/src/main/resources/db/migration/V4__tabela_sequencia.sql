CREATE TABLE carteira_pesca.protocolo_sequence
(
  id                        INTEGER NOT NULL,
  nom_sequence              CHARACTER VARYING (20) NOT NULL,
  val_sequence              INTEGER NOT NULL,
  idt_modalidade            INTEGER NOT NULL,
  val_sequence_year         INTEGER NOT NULL,

  CONSTRAINT pk_protocolo_sequence PRIMARY KEY (id),

  CONSTRAINT fk_modalidade FOREIGN KEY (idt_modalidade)
    REFERENCES carteira_pesca.modalidade MATCH SIMPLE
    ON UPDATE RESTRICT ON DELETE RESTRICT

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
