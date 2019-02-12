ALTER TABLE carteira_pesca.titulo ADD COLUMN dt_vencimento_provisoria date;

COMMENT ON COLUMN carteira_pesca.ocorrencia.dt_vencimento_provisoria IS 'Data de vencimento da carteira provisória.';
COMMENT ON COLUMN carteira_pesca.ocorrencia.dt_vencimento IS 'Data de vencimento da carteira.';