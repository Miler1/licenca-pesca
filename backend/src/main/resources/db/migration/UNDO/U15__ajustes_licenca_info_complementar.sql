ALTER TABLE carteira_pesca.licenca DROP CONSTRAINT fk_l_informacao_complementar;
ALTER TABLE carteira_pesca.licenca DROP COLUMN id_informacao_complementar;
      
ALTER TABLE carteira_pesca.licenca DROP CONSTRAINT fk_l_status;
DROP TABLE carteira_pesca.status;
   
ALTER TABLE carteira_pesca.informacao_complementar ADD COLUMN id_equipamento INTEGER NOT NULL;
ALTER TABLE carteira_pesca.informacao_complementar ADD COLUMN id_modalidade_mais_praticada INTEGER NOT NULL,;

DROP TABLE carteira_pesca.material_pesca;

ALTER TABLE carteira_pesca.informacao_complementar DROP CONSTRAINT fk_ic_material_pesca;
ALTER TABLE carteira_pesca.informacao_complementar DROP COLUMN id_material_pesca;

ALTER TABLE carteira_pesca.licenca ALTER COLUMN tx_protocolo TYPE CHARACTER VARYING(11);