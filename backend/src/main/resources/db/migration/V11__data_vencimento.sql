ALTER TABLE carteira_pesca.licenca ADD dt_vencimento date NULL;

COMMENT ON COLUMN carteira_pesca.licenca.dt_vencimento IS
'Data de vencimento.';

COMMENT ON COLUMN carteira_pesca.licenca.id_solicitante IS 'Identificador único da entidade solicitante que realizará o relacionamento entre solicitante e licenca.';
