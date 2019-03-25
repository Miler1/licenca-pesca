ALTER TABLE carteira_pesca.convenio ADD COLUMN linha_digitavel CHARACTER VARYING(75) NOT NULL;
COMMENT ON COLUMN carteira_pesca.convenio.linha_digitavel IS 'Conteúdo da linha digitável do boleto.';

ALTER TABLE carteira_pesca.convenio RENAME COLUMN codigo_barra TO codigo_barras;
COMMENT ON COLUMN carteira_pesca.convenio.codigo_barras IS 'Conteúdo do código de barras do boleto.';
