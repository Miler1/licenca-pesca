ALTER TABLE carteira_pesca.licenca ADD COLUMN dt_vencimento_provisoria date;

COMMENT ON COLUMN carteira_pesca.licenca.dt_vencimento_provisoria IS 'Data de vencimento da carteira provisória.';
COMMENT ON COLUMN carteira_pesca.licenca.dt_vencimento IS 'Data de vencimento da carteira.';