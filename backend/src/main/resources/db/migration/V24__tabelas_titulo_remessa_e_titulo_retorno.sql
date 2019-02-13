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

create index fk_trem_titulo_idx
  on carteira_pesca.titulo_remessa (id_titulo);

create index fk_trem_remessa_idx
  on carteira_pesca.titulo_remessa (id_remessa);

create table carteira_pesca.titulo_retorno
(
  id                   serial not null,
  id_titulo            int    not null,
  id_retorno           int    not null,
  id_motivo_ocorrencia int    not null,
  constraint pk_titulo_retorno
    primary key (id),
  constraint fk_tret_titulo
    foreign key (id_titulo) references carteira_pesca.titulo (id),
  constraint fk_tret_retorno
    foreign key (id_retorno) references carteira_pesca.retorno (id),
  constraint fk_tret_motivo_ocorrencia
    foreign key (id_motivo_ocorrencia) references carteira_pesca.motivo_ocorrencia (id)
);

grant select, update, delete on table carteira_pesca.titulo_retorno to carteira_pesca;
grant select, update on sequence carteira_pesca.titulo_retorno_id_seq to carteira_pesca;

comment on table carteira_pesca.titulo_retorno is 'Tabela que relaciona títulos e retornos';
comment on column carteira_pesca.titulo_retorno.id is 'Identificador da tabela';
comment on column carteira_pesca.titulo_retorno.id_titulo is 'Referência para o título pertencente';
comment on column carteira_pesca.titulo_retorno.id_retorno is 'Referência para o retorno pertencente';
comment on column carteira_pesca.titulo_retorno.id_motivo_ocorrencia is 'Referência para o motivo da ocorrência pertencente';

create index fk_tret_titulo_idx
  on carteira_pesca.titulo_retorno (id_titulo);

create index fk_tret_retorno_idx
  on carteira_pesca.titulo_retorno (id_retorno);

create index fk_tret_motivo_ocorrencia_idx
  on carteira_pesca.titulo_retorno (id_motivo_ocorrencia);