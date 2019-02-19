drop table carteira_pesca.titulo_remessa;

create table carteira_pesca.titulo_remessa
(
  id         serial not null,
  id_titulo  int    not null,
  id_remessa int    not null,
  constraint pk_titulo_remessa
    primary key (id),
  constraint fk_trem_titulo
    foreign key (id_titulo) references carteira_pesca.titulo (id),
  constraint fk_trem_remessa
    foreign key (id_remessa) references carteira_pesca.remessa (id)
);

grant select, update, delete on table carteira_pesca.titulo_remessa to carteira_pesca;
grant select, update on sequence carteira_pesca.titulo_remessa_id_seq to carteira_pesca;

comment on table carteira_pesca.titulo_remessa is 'Tabela que relaciona títulos e remessas';
comment on column carteira_pesca.titulo_remessa.id is 'Identificador da tabela';
comment on column carteira_pesca.titulo_remessa.id_titulo is 'Referência para o título pertencente';
comment on column carteira_pesca.titulo_remessa.id_remessa is 'Referência para a remessa pertencente';

ALTER TABLE carteira_pesca.titulo ADD COLUMN id_remessa INTEGER;
ALTER TABLE carteira_pesca.titulo ADD CONSTRAINT fk_remessa_titulo FOREIGN KEY (id_remessa)
  REFERENCES carteira_pesca.remessa (id);

COMMENT ON COLUMN carteira_pesca.titulo.id_remessa IS 'Remessa gerada para o título.';

ALTER TABLE carteira_pesca.titulo ADD COLUMN id_retorno INTEGER NULL;
ALTER TABLE carteira_pesca.titulo ADD CONSTRAINT fk_retorno_titulo FOREIGN KEY (id_retorno)
  REFERENCES carteira_pesca.retorno (id);

COMMENT ON COLUMN carteira_pesca.titulo.id_retorno IS 'Retorno que o titulo foi processado.';

ALTER TABLE carteira_pesca.titulo DROP COLUMN fl_gerar_remessa;
