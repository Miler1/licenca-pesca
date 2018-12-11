ALTER TABLE carteira_pesca.licenca ADD tx_caminho_boleto varchar(255) NULL;

COMMENT ON COLUMN carteira_pesca.licenca.tx_caminho_boleto IS
'Caminho do boleto.';

ALTER TABLE carteira_pesca.licenca ADD tx_caminho_carteira varchar(255) NULL;

COMMENT ON COLUMN carteira_pesca.licenca.tx_caminho_carteira IS
'Caminho da carteira.';