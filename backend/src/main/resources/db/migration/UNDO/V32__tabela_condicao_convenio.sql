
ALTER TABLE carteira_pesca.convenio DROP COLUMN id_condicao;

DROP TABLE carteira_pesca.condicao_convenio;

ALTER TABLE carteira_pesca.licenca ADD dt_vencimento_boleto DATE NULL;

COMMENT ON COLUMN carteira_pesca.licenca.dt_vencimento_boleto IS 'Data de vencimento do boleto.';
