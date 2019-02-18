DROP TABLE carteira_pesca.titulo;
DROP TABLE carteira_pesca.beneficiario_titulo;
DROP TABLE carteira_pesca.pagador_titulo;
DROP TABLE carteira_pesca.banco;
DROP TABLE carteira_pesca.endereco;
DROP TABLE carteira_pesca.especie_documento;
DROP TABLE carteira_pesca.arquivo;
DROP TABLE carteira_pesca.tipo_arquivo;

ALTER TABLE carteira_pesca.licenca DROP CONSTRAINT fk_l_titulo;
ALTER TABLE carteira_pesca.licenca DROP COLUMN id_titulo;
