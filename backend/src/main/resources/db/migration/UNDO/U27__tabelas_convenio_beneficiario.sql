ALTER TABLE carteira_pesca.pagador RENAME TO pagador_titulo;

ALTER TABLE carteira_pesca.beneficiario_titulo ADD COLUMN nome CHARACTER VARYING(200) NULL;
COMMENT ON COLUMN carteira_pesca.beneficiario_titulo.nome IS 'Nome do beneficiário.';

ALTER TABLE carteira_pesca.beneficiario_titulo ADD COLUMN sigla CHARACTER VARYING(10) NULL;
COMMENT ON COLUMN carteira_pesca.beneficiario_titulo.sigla IS 'sigla do nome beneficiário.';

ALTER TABLE carteira_pesca.beneficiario_titulo ADD COLUMN cpf_cnpj CHARACTER VARYING(14) NULL;
COMMENT ON COLUMN carteira_pesca.beneficiario_titulo.cpf_cnpj IS 'CPF/CNPJ do beneficiário (sem mascara).';

ALTER TABLE carteira_pesca.beneficiario_titulo ADD COLUMN id_endereco INTEGER NULL;
COMMENT ON COLUMN carteira_pesca.beneficiario_titulo.id_endereco IS 'Identifica a qual o endereço do beneficiário.';

UPDATE carteira_pesca.beneficiario_titulo SET bt.nome =  bt.sigla, bt.cpf_cnpj, bt.id_endereco = (SELECT b.nome, b.sigla, b.cpf_cnpj, b.id_endereco FROM carteira_pesca.beneficiario b, carteira_pesca.beneficiario_titulo bt WHERE b.id = bt.id);

INSERT INTO carteira_pesca.beneficiario_titulo (nome, sigla, cpf_cnpj, id_endereco) (SELECT nome, sigla, cpf_cnpj, id_endereco FROM carteira_pesca.beneficiario b AND b.id = id);

DELETE FROM carteira_pesca.tipo_segmento;

DELETE FROM carteira_pesca.tipo_valor_efetivo;

DROP TABLE carteira_pesca.carteira_pesca.convenio;

ALTER TABLE carteira_pesca.beneficiario_titulo DROP COLUMN id_beneficiario;

DROP TABLE carteira_pesca.beneficiario;
