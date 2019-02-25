DELETE FROM carteira_pesca.tipo_arquivo WHERE id = 4;

ALTER TABLE carteira_pesca.licenca DROP COLUMN id_convenio;
ALTER TABLE carteira_pesca.convenio DROP COLUMN id_arquivo;
ALTER TABLE carteira_pesca.convenio DROP COLUMN id_pagamento;
ALTER TABLE carteira_pesca.convenio DROP COLUMN id_retorno;
ALTER TABLE carteira_pesca.retorno DROP COLUMN tipo_retorno;

ALTER TABLE carteira_pesca.retorno RENAME COLUMN qtd_registros TO qtd_titulo_cobranca;
ALTER TABLE carteira_pesca.retorno RENAME COLUMN valor_registros TO valor_titulo_cobranca;


DROP TABLE carteira_pesca.pagamento_convenio;
DROP TABLE carteira_pesca.tipo_arrecadacao;
DROP TABLE carteira_pesca.tipo_pagamento;
