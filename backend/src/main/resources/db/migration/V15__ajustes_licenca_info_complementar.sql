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
(0, 'Aguardando pagamento do boleto', 'AGUARDANDO_PAGAMENTO_BOLETO'),
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
