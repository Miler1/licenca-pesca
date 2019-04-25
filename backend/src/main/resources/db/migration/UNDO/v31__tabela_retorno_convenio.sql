DROP TABLE carteira_pesca.retorno_convenio;

DELETE FROM carteira_pesca.tipo_arquivo WHERE id = 5;

ALTER TABLE carteira_pesca.pagamento_convenio ALTER COLUMN id_tipo_pagamento SET NOT NULL;

ALTER TABLE carteira_pesca.pagamento_convenio ADD COLUMN id_retorno INTEGER;

ALTER TABLE carteira_pesca.pagamento_convenio ADD CONSTRAINT fk_pc_retorno FOREIGN KEY (id_retorno)
  REFERENCES carteira_pesca.retorno (id) MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT;
