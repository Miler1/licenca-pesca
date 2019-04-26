ALTER TABLE carteira_pesca.pagamento_convenio
    DROP COLUMN id_retorno,
    ADD COLUMN id_retorno INTEGER,
    ADD CONSTRAINT fk_pc_retorno FOREIGN KEY (id_retorno)
        REFERENCES carteira_pesca.retorno (id);


COMMENT ON COLUMN carteira_pesca.pagamento_convenio.id_retorno
    IS 'Identificador único da entidade retorno que faz o relacionamento entre retorno e pagamento_convenio - Identifica qual o retorno que foi processado.';

DELETE FROM carteira_pesca.tipo_arquivo WHERE id = 5;

DROP TABLE carteira_pesca.retorno_convenio;