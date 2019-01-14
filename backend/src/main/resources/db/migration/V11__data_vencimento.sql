ALTER TABLE carteira_pesca.licenca ADD dt_vencimento date NULL;

COMMENT ON COLUMN carteira_pesca.licenca.dt_vencimento IS
'Data de vencimento.';
