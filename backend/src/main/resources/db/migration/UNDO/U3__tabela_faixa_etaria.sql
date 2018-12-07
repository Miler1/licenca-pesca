
ALTER TABLE carteira_pesca.informacao_complementar RENAME COLUMN id_faixa_etaria TO nu_faixa_etaria;
 
ALTER TABLE carteira_pesca.informacao_complementar DROP CONSTRAINT fk_ic_faixa_etaria;

COMMENT ON COLUMN carteira_pesca.informacao_complementar.nu_faixa_etaria IS 'Faixa etária.';

DROP TABLE carteira_pesca.faixa_etaria;