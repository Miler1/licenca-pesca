ALTER TABLE carteira_pesca.licenca ADD COLUMN  dt_vencimento_provisoria date;
COMMENT ON COLUMN carteira_pesca.licenca.dt_vencimento_provisoria IS 'Data de vencimento da carteira provisória.';

ALTER TABLE carteira_pesca.convenio  DROP CONSTRAINT fk_condicao_convenio_c;

ALTER TABLE carteira_pesca.convenio  DROP COLUMN id_condicao;
 
DROP TABLE carteira_pesca.condicao_convenio;