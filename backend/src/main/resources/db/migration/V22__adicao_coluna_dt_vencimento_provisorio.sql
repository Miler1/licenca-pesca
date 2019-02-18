ALTER TABLE carteira_pesca.licenca ADD COLUMN dt_vencimento_provisoria date;

COMMENT ON COLUMN carteira_pesca.licenca.dt_vencimento_provisoria IS 'Data de vencimento da carteira provisória.';
COMMENT ON COLUMN carteira_pesca.licenca.dt_vencimento IS 'Data de vencimento da carteira.';

UPDATE carteira_pesca.status SET descricao = 'Aguardando pagamento do boleto', codigo = 'AGUARDANDO_PAGAMENTO' WHERE id = 0;
UPDATE carteira_pesca.status SET descricao = 'Ativo', codigo = 'ATIVO' WHERE id = 1;
UPDATE carteira_pesca.status SET descricao = 'Invalidado', codigo = 'INVALIDADO' WHERE id = 2;
UPDATE carteira_pesca.status SET descricao = 'Vencido', codigo = 'VENCIDO' WHERE id = 3;
UPDATE carteira_pesca.status SET descricao = 'Renovado', codigo = 'RENOVADO' WHERE id = 4;
INSERT INTO carteira_pesca.status VALUES (5, 'Ativo aguardando pagamento do boleto', 'ATIVO_AGUARDANDO_PAGAMENTO');
