
ALTER TABLE carteira_pesca.local_pesca RENAME descricao_pt TO descricao;
ALTER TABLE carteira_pesca.local_pesca DROP COLUMN descricao_en;
ALTER TABLE carteira_pesca.local_pesca DROP COLUMN descricao_es;

COMMENT ON COLUMN carteira_pesca.local_pesca.descricao IS 'Descrição das opções de local de pesca.';

ALTER TABLE carteira_pesca.renda_mensal RENAME descricao_pt TO descricao;
ALTER TABLE carteira_pesca.renda_mensal DROP COLUMN descricao_en;
ALTER TABLE carteira_pesca.renda_mensal DROP COLUMN descricao_es;

COMMENT ON COLUMN carteira_pesca.renda_mensal.descricao IS 'Descrição das opções de renda mensal.';

ALTER TABLE carteira_pesca.preferencia_local_pesca RENAME descricao_pt TO descricao;
ALTER TABLE carteira_pesca.preferencia_local_pesca DROP COLUMN descricao_en;
ALTER TABLE carteira_pesca.preferencia_local_pesca DROP COLUMN descricao_es;

COMMENT ON COLUMN carteira_pesca.preferencia_local_pesca.descricao IS 'Descrição das opções de local de pesca, podendo ser Mar ou Água Doce.';

ALTER TABLE carteira_pesca.equipamento RENAME nome_pt TO nome;
ALTER TABLE carteira_pesca.equipamento DROP COLUMN nome_en;
ALTER TABLE carteira_pesca.equipamento DROP COLUMN nome_es;

COMMENT ON COLUMN carteira_pesca.equipamento.nome IS 'Nome dos materiais/equipamentos em português.';

ALTER TABLE carteira_pesca.tipo_isca RENAME descricao_pt TO descricao;
ALTER TABLE carteira_pesca.tipo_isca DROP COLUMN descricao_en;
ALTER TABLE carteira_pesca.tipo_isca DROP COLUMN descricao_es;

COMMENT ON COLUMN carteira_pesca.tipo_isca.descricao IS 'Descrição dos tipos de isca.';

ALTER TABLE carteira_pesca.faixa_etaria RENAME descricao_pt TO descricao;
ALTER TABLE carteira_pesca.faixa_etaria DROP COLUMN descricao_en;
ALTER TABLE carteira_pesca.faixa_etaria DROP COLUMN descricao_es;

COMMENT ON COLUMN carteira_pesca.faixa_etaria.descricao IS 'Descrição da faixa etária.';

ALTER TABLE carteira_pesca.modalidade RENAME nome_pt TO nome;
ALTER TABLE carteira_pesca.modalidade DROP COLUMN nome_en;
ALTER TABLE carteira_pesca.modalidade DROP COLUMN nome_es;

COMMENT ON COLUMN carteira_pesca.modalidade.nome IS 'Nome da modalidade.';