ALTER TABLE carteira_pesca.pagador RENAME TO pagador_titulo;

ALTER TABLE carteira_pesca.beneficiario_titulo ADD COLUMN nome CHARACTER VARYING(200) NULL;
COMMENT ON COLUMN carteira_pesca.beneficiario_titulo.nome IS 'Nome do beneficiário.';

ALTER TABLE carteira_pesca.beneficiario_titulo ADD COLUMN sigla CHARACTER VARYING(10) NULL;
COMMENT ON COLUMN carteira_pesca.beneficiario_titulo.sigla IS 'sigla do nome beneficiário.';

ALTER TABLE carteira_pesca.beneficiario_titulo ADD COLUMN cpf_cnpj CHARACTER VARYING(14) NULL;
COMMENT ON COLUMN carteira_pesca.beneficiario_titulo.cpf_cnpj IS 'CPF/CNPJ do beneficiário (sem mascara).';

ALTER TABLE carteira_pesca.beneficiario_titulo ADD COLUMN id_endereco INTEGER NULL;
COMMENT ON COLUMN carteira_pesca.beneficiario_titulo.id_endereco IS 'Identifica a qual o endereço do beneficiário.';

UPDATE carteira_pesca.beneficiario_titulo SET nome = (SELECT b.nome FROM carteira_pesca.beneficiario b, carteira_pesca.beneficiario_titulo bt WHERE bt.id_beneficiario = b.id);
UPDATE carteira_pesca.beneficiario_titulo SET sigla = (SELECT b.sigla FROM carteira_pesca.beneficiario b, carteira_pesca.beneficiario_titulo bt WHERE bt.id_beneficiario = b.id);
UPDATE carteira_pesca.beneficiario_titulo SET cpf_cnpj = (SELECT b.cpf_cnpj FROM carteira_pesca.beneficiario b, carteira_pesca.beneficiario_titulo bt WHERE bt.id_beneficiario = b.id);
UPDATE carteira_pesca.beneficiario_titulo SET id_endereco = (SELECT b.id_endereco FROM carteira_pesca.beneficiario b, carteira_pesca.beneficiario_titulo bt WHERE bt.id_beneficiario = b.id);
ALTER TABLE carteira_pesca.beneficiario_titulo ALTER COLUMN nome SET NOT NULL;
ALTER TABLE carteira_pesca.beneficiario_titulo ALTER COLUMN sigla SET NOT NULL;
ALTER TABLE carteira_pesca.beneficiario_titulo ALTER COLUMN cpf_cnpj SET NOT NULL;
ALTER TABLE carteira_pesca.beneficiario_titulo ALTER COLUMN id_endereco SET NOT NULL;

ALTER TABLE carteira_pesca.beneficiario_titulo DROP COLUMN id_beneficiario;
ALTER TABLE carteira_pesca.convenio DROP COLUMN id_beneficiario;

DELETE FROM carteira_pesca.tipo_valor_efetivo;
DELETE FROM carteira_pesca.tipo_segmento;

DROP TABLE carteira_pesca.convenio;
DROP TABLE carteira_pesca.beneficiario;