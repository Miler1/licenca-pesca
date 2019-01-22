ALTER TABLE carteira_pesca.solicitante ADD COLUMN numero_tentativas integer NOT NULL default 0;
COMMENT ON COLUMN carteira_pesca.solicitante.numero_tentativas IS 'Número de tentativas de acesso as licencas.';

ALTER TABLE carteira_pesca.solicitante ADD COLUMN data_ultima_tentativa timestamp without time zone;
COMMENT ON COLUMN carteira_pesca.solicitante.data_ultima_tentativa IS 'Data da última tentativa de acesso as licenças antes do bloqueio do solicitante em caso de errar as perguntas mais de três vezes.';

ALTER TABLE carteira_pesca.solicitante ADD COLUMN data_desbloqueio timestamp without time zone;
COMMENT ON COLUMN carteira_pesca.solicitante.data_desbloqueio IS 'Data de desbloqueio do solicitante.';
