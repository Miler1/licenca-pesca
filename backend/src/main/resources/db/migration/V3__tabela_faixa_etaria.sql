CREATE TABLE carteira_pesca.faixa_etaria
(
  id INTEGER NOT NULL, 
  descricao character varying(255) NOT NULL, 
  CONSTRAINT pk_faixa_etaria PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE carteira_pesca.faixa_etaria
  OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.faixa_etaria TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.faixa_etaria TO carteira_pesca;
COMMENT ON TABLE carteira_pesca.faixa_etaria
  IS 'Entidade responsável por armazenar as possíveis faixas etária dos praticantes de pesca.';
COMMENT ON COLUMN carteira_pesca.faixa_etaria.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.faixa_etaria.descricao IS 'Descrição da faixa etária.';

INSERT INTO carteira_pesca.faixa_etaria VALUES (1, 'De 0 a 20 anos'), (2, 'De 21 a 30 anos'), 
(3, 'De 31 a 40 anos'), (4, 'De 41 a 50 anos'), (5, 'De 51 a 60 anos'), (6, 'De 61 a 70 anos'), 
(7, 'Até 120 anos');

ALTER TABLE carteira_pesca.informacao_complementar RENAME COLUMN nu_faixa_etaria TO id_faixa_etaria;
 
ALTER TABLE carteira_pesca.informacao_complementar ADD
   CONSTRAINT fk_ic_faixa_etaria FOREIGN KEY (id_faixa_etaria)
      REFERENCES carteira_pesca.faixa_etaria (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT;

COMMENT ON COLUMN carteira_pesca.informacao_complementar.id_faixa_etaria IS 'Identificador único da entidade faixa_etaria que realizará o relacionamento entre faixa_etaria e informacao_complementar.';
