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
