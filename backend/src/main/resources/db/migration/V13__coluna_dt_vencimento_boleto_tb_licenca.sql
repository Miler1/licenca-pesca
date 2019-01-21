ALTER TABLE carteira_pesca.licenca ADD dt_vencimento_boleto date NULL;

COMMENT ON COLUMN carteira_pesca.licenca.dt_vencimento_boleto IS
'Data de vencimento do boleto.';
