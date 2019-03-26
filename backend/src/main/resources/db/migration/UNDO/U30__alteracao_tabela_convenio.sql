ALTER TABLE carteira_pesca.convenio DROP COLUMN linha_digitavel;
ALTER TABLE carteira_pesca.convenio RENAME COLUMN codigo_barras TO codigo_barra;
COMMENT ON COLUMN carteira_pesca.convenio.codigo_barra IS 'Código de barra do boleto.';
