DELETE FROM carteira_pesca.local_pesca;

ALTER TABLE carteira_pesca.local_pesca RENAME descricao TO descricao_pt;
ALTER TABLE carteira_pesca.local_pesca ADD COLUMN descricao_en CHARACTER VARYING(50) NOT NULL;
ALTER TABLE carteira_pesca.local_pesca ADD COLUMN descricao_es CHARACTER VARYING(50) NOT NULL;

COMMENT ON COLUMN carteira_pesca.local_pesca.descricao_pt IS 
'Descrição das opções de local de pesca em português.';

COMMENT ON COLUMN carteira_pesca.local_pesca.descricao_en IS 
'Descrição das opções de local de pesca em inglês.';

COMMENT ON COLUMN carteira_pesca.local_pesca.descricao_es IS 
'Descrição das opções de local de pesca em espanhol.';

INSERT INTO carteira_pesca.local_pesca (id, descricao_pt, descricao_en, descricao_es) VALUES
(0, 'Localmente', 'Locally', 'Localmente'),
(1, 'Em outros estados', 'In other states', 'En otros estados'),
(2, 'Em outros países', 'in other countries', 'En otros paises'); 

DELETE FROM carteira_pesca.renda_mensal;

ALTER TABLE carteira_pesca.renda_mensal RENAME descricao TO descricao_pt;
ALTER TABLE carteira_pesca.renda_mensal ADD COLUMN descricao_en CHARACTER VARYING(50) NOT NULL;
ALTER TABLE carteira_pesca.renda_mensal ADD COLUMN descricao_es CHARACTER VARYING(50) NOT NULL;

COMMENT ON COLUMN carteira_pesca.renda_mensal.descricao_pt IS 
'Descrição das opções de renda mensal em português.';

COMMENT ON COLUMN carteira_pesca.renda_mensal.descricao_en IS 
'Descrição das opções de renda mensal em inglês.';

COMMENT ON COLUMN carteira_pesca.renda_mensal.descricao_es IS 
'Descrição das opções de renda mensal em espanhol.';

INSERT INTO carteira_pesca.renda_mensal (id, descricao_pt, descricao_en, descricao_es) VALUES 
(0, 'Nenhuma renda', 'Nenhuma renda', 'Ninguna renta'), 
(1, 'Até 1 salário mínimo', 'Up to a minimum wage', 'Hasta un salario mínimo'), 
(2, 'De 1 a 3 salários mínimos', 'From 1 to 3 minimum wages', 'De 1 a 3 salarios mínimos'), 
(3, 'De 3 a 6 salários mínimos', 'From 3 to 6 minimum wages', 'De 3 a 6 salarios mínimos'), 
(4, 'De 6 a 9 salários mínimos', 'From 6 to 9 minimum wages', 'De 6 a 9 salarios mínimos'), 
(5, 'De 9 a 12 salários mínimos', 'From 9 to 12 minimum wages', 'De 9 a 12 salarios mínimos'), 
(6, 'De 12 a 15 salários mínimos', 'From 12 to 15 minimum wages', 'De 12 a 15 salarios mínimos'), 
(7, 'Mais de 15 salários mínimos', 'More then 6 to 9 minimum wages', 'Más de 15 salarios mínimos');

DELETE FROM carteira_pesca.preferencia_local_pesca;

ALTER TABLE carteira_pesca.preferencia_local_pesca RENAME descricao TO descricao_pt;
ALTER TABLE carteira_pesca.preferencia_local_pesca ADD COLUMN descricao_en CHARACTER VARYING(50) NOT NULL;
ALTER TABLE carteira_pesca.preferencia_local_pesca ADD COLUMN descricao_es CHARACTER VARYING(50) NOT NULL;

COMMENT ON COLUMN carteira_pesca.preferencia_local_pesca.descricao_pt IS 
'Descrição das opções de local de pesca em português, podendo ser Mar ou Água Doce.';

COMMENT ON COLUMN carteira_pesca.preferencia_local_pesca.descricao_en IS 
'Descrição das opções de local de pesca em inglês, podendo ser Mar ou Água Doce.';

COMMENT ON COLUMN carteira_pesca.preferencia_local_pesca.descricao_es IS 
'Descrição das opções de local de pesca em espanhol, podendo ser Mar ou Água Doce.';

INSERT INTO carteira_pesca.preferencia_local_pesca (id, descricao_pt, descricao_en, descricao_es) VALUES 
(0, 'Mar', 'Sea', 'Mar'), 
(1, 'Água doce', 'Fresh water', 'Agua dulce');

DELETE FROM carteira_pesca.equipamento;

ALTER TABLE carteira_pesca.equipamento RENAME nome TO nome_pt;
ALTER TABLE carteira_pesca.equipamento ADD COLUMN nome_en CHARACTER VARYING(50) NOT NULL;
ALTER TABLE carteira_pesca.equipamento ADD COLUMN nome_es CHARACTER VARYING(50) NOT NULL;

COMMENT ON COLUMN carteira_pesca.equipamento.nome_pt IS 
'Nome dos materiais/equipamentos em português.';

COMMENT ON COLUMN carteira_pesca.equipamento.nome_en IS 
'Nome dos materiais/equipamentos em inglês.';

COMMENT ON COLUMN carteira_pesca.equipamento.nome_es IS 
'Nome dos materiais/equipamentos em espanhol.';

INSERT INTO carteira_pesca.equipamento (id, nome_pt, nome_en, nome_es) VALUES 
(0, 'Linha Caniço', 'Caniço Line', 'Línea Caniço'), 
(1, 'Carretilha Molinete', 'Windlass Reel', 'Carretilla Molinete'), 
(2, 'Fly', 'Fly', 'Fly'), 
(3, 'Outros', 'Others', 'Otros');

DELETE FROM carteira_pesca.tipo_isca;

ALTER TABLE carteira_pesca.tipo_isca RENAME descricao TO descricao_pt;
ALTER TABLE carteira_pesca.tipo_isca ADD COLUMN descricao_en CHARACTER VARYING(50) NOT NULL;
ALTER TABLE carteira_pesca.tipo_isca ADD COLUMN descricao_es CHARACTER VARYING(50) NOT NULL;

COMMENT ON COLUMN carteira_pesca.tipo_isca.descricao_pt IS 
'Descrição dos tipos de isca em português.';

COMMENT ON COLUMN carteira_pesca.tipo_isca.descricao_en IS 
'Descrição dos tipos de isca em inglês.';

COMMENT ON COLUMN carteira_pesca.tipo_isca.descricao_es IS 
'Descrição dos tipos de isca em espanhol.';

INSERT INTO carteira_pesca.tipo_isca (id, descricao_pt, descricao_en, descricao_es) VALUES 
(0, 'Natural', 'Natural' ,'Natural'), 
(1, 'Artificial', 'Artificial', 'Artificial');

DELETE FROM carteira_pesca.faixa_etaria;

ALTER TABLE carteira_pesca.faixa_etaria RENAME descricao TO descricao_pt;
ALTER TABLE carteira_pesca.faixa_etaria ADD COLUMN descricao_en CHARACTER VARYING(50) NOT NULL;
ALTER TABLE carteira_pesca.faixa_etaria ADD COLUMN descricao_es CHARACTER VARYING(50) NOT NULL;

COMMENT ON COLUMN carteira_pesca.faixa_etaria.descricao_pt IS 
'Descrição da faixa etária em português.';

COMMENT ON COLUMN carteira_pesca.faixa_etaria.descricao_en IS 
'Descrição da faixa etária em inglês.';

COMMENT ON COLUMN carteira_pesca.faixa_etaria.descricao_es IS 
'Descrição da faixa etária em espanhol.';

INSERT INTO carteira_pesca.faixa_etaria (id, descricao_pt, descricao_en, descricao_es) VALUES
(0, 'De 0 a 20 anos', 'To 0 to 20 years old', 'De 0 a 20 años'),
(1, 'De 21 a 30 anos', 'To 21 to 30 years old', 'De 21 a 30 año'),
(2, 'De 31 a 40 anos', 'To 31 to 40 years old', 'De 31 a 40 años'),
(3, 'De 41 a 50 anos', 'To 41 to 50 years old', 'De 41 a 50 años'),
(4, 'De 51 a 60 anos', 'To 51 to 60 years old', 'De 51 a 60 años'),
(5, 'De 61 a 70 anos', 'To 61 to 70 years old', 'De 61 a 70 años'),
(6, 'Mais que 71 anos', 'More then 71 years old', 'Más de 71 años');

DELETE FROM carteira_pesca.modalidade;

ALTER TABLE carteira_pesca.modalidade RENAME nome TO nome_pt;
ALTER TABLE carteira_pesca.modalidade ADD COLUMN nome_en CHARACTER VARYING(50) NOT NULL;
ALTER TABLE carteira_pesca.modalidade ADD COLUMN nome_es CHARACTER VARYING(50) NOT NULL;

COMMENT ON COLUMN carteira_pesca.modalidade.nome_pt IS 
'Nome da modalidade em português.';

COMMENT ON COLUMN carteira_pesca.modalidade.nome_en IS 
'Nome da modalidade em inglês.';

COMMENT ON COLUMN carteira_pesca.modalidade.nome_es IS 
'Nome da modalidade em espanhol.';

INSERT INTO carteira_pesca.modalidade (id, nome_pt, nome_en, nome_es) VALUES
(0, 'Pesca Esportiva', 'Sport Fishing', 'Pesca Deportiva'),
(1, 'Pesca Recreativa', 'Recreational Fishing', 'Pesca Recreativa');
