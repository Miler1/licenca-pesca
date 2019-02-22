DELETE FROM carteira_pesca.tipo_arquivo WHERE id = 4;

ALTER TABLE carteira_pesca.licenca DROP COLUMN id_convenio;
ALTER TABLE carteira_pesca.convenio DROP COLUMN id_arquivo;
ALTER TABLE carteira_pesca.convenio DROP COLUMN id_pagamento;

DROP TABLE carteira_pesca.tipo_arrecadacao;
DROP TABLE carteira_pesca.tipo_pagamento;
DROP TABLE carteira_pesca.pagamento_convenio;