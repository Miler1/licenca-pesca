create table carteira_pesca.motivo
(
  id           serial not null,
  tx_descricao text   not null,
  constraint pk_motivo
    primary key (id),
  constraint motivo_descricao_key
    unique (tx_descricao)
);

grant select, update, delete on table carteira_pesca.motivo to carteira_pesca;
grant select, update on sequence carteira_pesca.motivo_id_seq to carteira_pesca;

comment on table carteira_pesca.motivo is 'Tabela que guarda os motivos relacionados às ocorrências que são informados no arquivo de retorno';

comment on column carteira_pesca.motivo.id is 'identificador da tabela mtivo';

comment on column carteira_pesca.motivo.tx_descricao is 'Texto que descreve o motivo';

create table carteira_pesca.ocorrencia
(
  id     serial   not null,
  nome   text     not null,
  codigo smallint not null,
  constraint pk_ocorrencia
    primary key (id)
);

grant select, update, delete on table carteira_pesca.ocorrencia to carteira_pesca;

grant select, update on sequence carteira_pesca.ocorrencia_id_seq to carteira_pesca;

comment on table carteira_pesca.ocorrencia is 'Tabela que guarda as ocorrências relacionadas aos arquivo de retorno';

comment on column carteira_pesca.ocorrencia.id is 'Identificador da tabela de ocorrêcias';

comment on column carteira_pesca.ocorrencia.nome is 'Texto que descreve o nome da ocorrência';

comment on column carteira_pesca.ocorrencia.codigo is 'Código único que identifica cada ocorrência de acordo com os dados do arquivo de retorno';

create unique index ocorrencia_codigo_uindex
  on carteira_pesca.ocorrencia (codigo);

create table carteira_pesca.motivo_ocorrencia
(
  id                serial   not null,
  id_motivo         integer  not null,
  id_ocorrencia     integer  not null,
  num_codigo_motivo smallint not null,
  constraint pk_motivo_ocorrencia
    primary key (id),
  constraint motivo_ocorrencia_id_motivo_id_ocorrencia_key
    unique (id_motivo, id_ocorrencia),
  constraint fk_mo_motivo
    foreign key (id_motivo) references carteira_pesca.motivo (id),
  constraint fk_mo_ocorrencia
    foreign key (id_ocorrencia) references carteira_pesca.ocorrencia (id)
);

grant select, update, delete on table carteira_pesca.motivo_ocorrencia to carteira_pesca;

grant select, update on sequence carteira_pesca.motivo_ocorrencia_id_seq to carteira_pesca;

comment on table carteira_pesca.motivo_ocorrencia is 'Tabela que relaciona o motivo à ocorrência dos arquivos de retorno de remessa';

comment on column carteira_pesca.motivo_ocorrencia.id is 'Identificador da tabela motivo_ocorrencia';

comment on column carteira_pesca.motivo_ocorrencia.id_motivo is 'Motivo a qual está relacionada';

comment on column carteira_pesca.motivo_ocorrencia.id_ocorrencia is 'Ocorrência a qual está relacionada';

comment on column carteira_pesca.motivo_ocorrencia.num_codigo_motivo is 'Numero do codigo do motivo, ele é único para cada canjunto (motivo,ocorrência)';

create index fk_mo_ocorrencia_idx
  on carteira_pesca.motivo_ocorrencia (id_ocorrencia);

create index fk_mo_motivo_idx
  on carteira_pesca.motivo_ocorrencia (id_motivo);

INSERT INTO carteira_pesca.ocorrencia (id, nome, codigo) VALUES (1, 'Entrada confirmada', 2);
INSERT INTO carteira_pesca.ocorrencia (id, nome, codigo) VALUES (2, 'Entrada Rejeitada', 3);
INSERT INTO carteira_pesca.ocorrencia (id, nome, codigo) VALUES (3, 'Liquidação', 6);
INSERT INTO carteira_pesca.ocorrencia (id, nome, codigo) VALUES (4, 'Baixado Automaticamente via Arquivo', 9);
INSERT INTO carteira_pesca.ocorrencia (id, nome, codigo) VALUES (5, 'Baixado pelo Banco', 10);
INSERT INTO carteira_pesca.ocorrencia (id, nome, codigo) VALUES (6, 'Liquidação em cartório', 15);
INSERT INTO carteira_pesca.ocorrencia (id, nome, codigo) VALUES (7, 'Liquidação após baixa ou Título não registrado', 17);
INSERT INTO carteira_pesca.ocorrencia (id, nome, codigo) VALUES (8, 'Entrada Rejeitada por CEP irregular', 24);
INSERT INTO carteira_pesca.ocorrencia (id, nome, codigo) VALUES (9, 'Baixa Rejeitada', 27);
INSERT INTO carteira_pesca.ocorrencia (id, nome, codigo) VALUES (10, 'Débito de Tarifas/Custas', 28);
INSERT INTO carteira_pesca.ocorrencia (id, nome, codigo) VALUES (11, 'Ocorrência do Pagador', 29);
INSERT INTO carteira_pesca.ocorrencia (id, nome, codigo) VALUES (12, 'Alteração de Outros Dados Rejeitados', 30);
INSERT INTO carteira_pesca.ocorrencia (id, nome, codigo) VALUES (13, 'Instrução Rejeitada', 32);
INSERT INTO carteira_pesca.ocorrencia (id, nome, codigo) VALUES (14, 'Desagendamento do Débito Automático', 35);

ALTER SEQUENCE carteira_pesca.ocorrencia_id_seq RESTART WITH 15;

INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (1, 'Acond. De papelatas (RPB)s PERSONAL');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (2, 'Acondicionador de papeletas (RPB)S');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (3, 'Agência Beneficiário não prevista');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (4, 'Agência/Conta/dígito inválidos');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (5, 'Alteração de produto negociado');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (6, 'Arq retorno Hora/Hora');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (7, 'Arq. Títulos a vencer mensal');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (8, 'Arquivo Retorno Antecipado');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (9, 'Baixa Comandada pelo cliente');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (10, 'Baixa por acerto cliente');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (11, 'Baixa registro em duplicidade');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (12, 'Baixado Conforme Instruções da Agência');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (13, 'Cadastro Concessionária serv. Publ.');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (14, 'Cadastro de Mensagem Fixa');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (15, 'Cadastro de reembolso de diferença');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (16, 'Canalização de Crédito');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (17, 'Cancelado pelo pagador e baixado, conforme negociação');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (18, 'Cancelado pelo Pagador e Mantido Pendente, conforme negociação');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (19, 'Característica da cobrança incompatível');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (20, 'Características da cobrança incompatíveis');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (21, 'Carteira inválida');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (22, 'Carteira/Agência/Conta/nosso número inválidos');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (23, 'CEP Inválido');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (24, 'CEP irregular - Banco Correspondente');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (25, 'CEP referente a um Banco correspondente');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (26, 'Classif. Extrato Conta Corrente');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (27, 'Cobrado baixa manual');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (28, 'Código da multa inválido');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (29, 'Código da ocorrência inválida');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (30, 'Código da ocorrência não numérico');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (31, 'Código de desconto inválido');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (32, 'Código de desconto via Telebradesco inválido');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (33, 'Código de juros de mora inválido');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (34, 'Código de ocorrência não permitido para a carteira');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (35, 'Código do Banco inválido');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (36, 'Código do desconto inválido');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (37, 'Código do movimento não permitido para a carteira');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (38, 'Código do registro detalhe inválido');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (39, 'Código para baixa/devolução inválido');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (40, 'Código para baixa/devolução via Tele Bradesco inválido');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (41, 'Concessão abatimento - Já existe abatimento anterior');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (42, 'Concessão de desconto - Já existe desconto anterior');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (43, 'Contabilidade especial');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (44, 'Custas de protesto');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (45, 'Data da emissão inválida');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (46, 'Data da multa inválida');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (47, 'Data de Juros de mora Inválida');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (48, 'Data de vencimento anterior a data de emissão');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (49, 'Data de vencimento inválida');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (50, 'Data do desconto inválida');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (51, 'Débito automático agendado');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (52, 'Débito não agendado - Beneficiário não autorizado pelo Pagador');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (53, 'Débito não agendado - Beneficiário não participa do débito automático');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (54, 'Débito não agendado - Código de moeda diferente de R$');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (55, 'Débito não agendado - Conforme seu pedido, Título não registrado');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (56, 'Débito não agendado - Data de vencimento inválida');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (57, 'Débito não agendado - Data de vencimento inválida/vencida');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (58, 'Débito não agendado - erro nos dados de remessa');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (59, 'Débito não agendado - Pagador não consta no cadastro de autorizante');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (60, 'Débito não agendado - Tipo do número de inscrição do pagador debitado inválido');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (61, 'Débito não agendado – Tipo de número de inscrição do debitado inválido');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (62, 'Desconto a conceder não confere');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (63, 'E-mail Pagador não lido no prazo 5 dias');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (64, 'E-mail pagador não recebido');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (65, 'Email Pagador não enviado – título com débito automático');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (66, 'Email pagador não enviado – título de cobrança sem registro');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (67, 'Emissão Extrato mov. Carteira');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (68, 'Emissão papeleta sem valor');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (69, 'Endereço do pagador não informado');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (70, 'Entrada para Título já cadastrado');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (71, 'Espécie do Título inválido');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (72, 'Espécie não permitida para a carteira');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (73, 'Extrato de protesto');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (74, 'Formulário A4 serrilhado');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (75, 'Fornecimento de Impressoras');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (76, 'Fornecimento de máquina FAX');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (77, 'Fornecimento de máquinas óticas');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (78, 'Fornecimento de softwares consulta');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (79, 'Fornecimento de softwares transmiss');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (80, 'Fornecimento Micro Completo');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (81, 'Fornecimento MODEN');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (82, 'Identificação da emissão do bloqueto inválida');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (83, 'Impressão de títulos baixados');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (84, 'Impressão de títulos pagos');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (85, 'Inclusão Bloqueada face a determinação Judicial');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (86, 'Instrução não permitida título negativado');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (87, 'Limite excedido');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (88, 'Listagem auxiliar de crédito');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (89, 'Mensagem campo local de pagto');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (90, 'Movimento para Título não cadastrado');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (91, 'Nº de inscrição do Pagador/avalista inválidos (CPF/CNPJ)');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (92, 'Nome do Pagador inválido');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (93, 'Nome do pagador não informado');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (94, 'Nosso número duplicado');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (95, 'Nosso número inválido');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (96, 'Número autorização inexistente');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (97, 'Ocorrência aceita');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (98, 'Pagador aceita/reconhece o faturamento');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (99, 'Pagador alega que faturamento e indevido');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (100, 'Pagador Eletrônico DDA - Esse motivo somente será disponibilizado no arquivo retorno para as empresas cadastradas nessa condição.');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (101, 'Pagador/avalista não informado');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (102, 'Pagamento Parcial');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (103, 'Papeleta formulário branco');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (104, 'Pedido de sustação/excl p/ Título sem instrução de protesto/Negativação');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (105, 'Pedido para protesto/ Negativação não permitido para o título');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (106, 'Prazo para baixa e devolução inválido');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (107, 'Prazo para protesto/ Negativação inválido');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (108, 'Rateio não efetuado, cód. Calculo 2 (VLR. Registro) e v');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (109, 'Realimentação pagto');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (110, 'Reativação de título');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (111, 'Relatório fluxo de pagto');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (112, 'Repasse de Créditos');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (113, 'Sem uso');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (114, 'Seu número do documento inválido');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (115, 'Seu número inválido');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (116, 'Tarifa alteração de vencimento');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (117, 'Tarifa baixa por contabilidade');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (118, 'Tarifa cadastro cartela instrução permanente');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (119, 'Tarifa cancelamento de abatimento');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (120, 'Tarifa cancelamento desconto');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (121, 'Tarifa concessão abatimento');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (122, 'Tarifa concessão desconto');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (123, 'Tarifa de outras instruções');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (124, 'Tarifa de outras ocorrências');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (125, 'Tarifa de permanência título cadastrado');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (126, 'Tarifa de protesto/Incl Negativação');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (127, 'Tarifa de registro');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (128, 'Tarifa de sustação/Excl Negativação');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (129, 'Tarifa emissão 2ª via papeleta');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (130, 'Tarifa emissão de contra recibo');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (131, 'Tarifa emissão Papeleta');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (132, 'Tarifa fornec papeleta semi preenchida');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (133, 'Tarifa impressão de títulos pendentes');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (134, 'Tarifa Rateio de Crédito');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (135, 'Tarifa reapresentação automática título');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (136, 'Tarifa reg. Pagto outras mídias');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (137, 'Tarifa Reg/Pagto – Net Empresa');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (138, 'Tarifa reg/pagto Bradesco Expresso');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (139, 'Tarifa reg/pgto – guichê caixa');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (140, 'Tarifa registro título déb. Automático');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (141, 'Tarifa regravação arquivo retorno');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (142, 'Tarifa título Baix. Pg. Cartório');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (143, 'Tarifa título baixado acerto BCO');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (144, 'Tarifa título baixado conf. Pedido');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (145, 'Tarifa título baixado decurso prazo');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (146, 'Tarifa título baixado franco pagto');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (147, 'Tarifa título baixado Judicialmente');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (148, 'Tarifa título baixado não pago');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (149, 'Tarifa título baixado p/ devolução');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (150, 'Tarifa título baixado protestado');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (151, 'Tarifa título baixado rastreamento');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (152, 'Tarifa título baixado SUS/SEM/REM/CARTÓRIO');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (153, 'Tarifa título baixado SUST/RET/CARTÓRIO');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (154, 'Tarifa título baixado via remessa');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (155, 'Tarifa título Déb. Postagem');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (156, 'Tarifa título pago BDN');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (157, 'Tarifa título pago Cartão de Crédito');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (158, 'Tarifa título pago cics');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (159, 'Tarifa título pago Comp Eletrônica');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (160, 'Tarifa título pago compensação');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (161, 'Tarifa título pago Fone Fácil');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (162, 'Tarifa título pago Internet');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (163, 'Tarifa título pago no Bradesco');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (164, 'Tarifa título pago Pág-Contas');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (165, 'Tarifa título pago Pagfor');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (166, 'Tarifa título pago retaguarda');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (167, 'Tarifa título pago Subcentro');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (168, 'Tarifa título pago term. gerencial serviços');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (169, 'Tarifa título pago Term. Multi Função');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (170, 'Tarifa título pago vencido');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (171, 'Tarifa título transferido desconto');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (172, 'Telefone beneficiário não informado / inconsistente');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (173, 'Tentativas esgotadas, baixado');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (174, 'Tentativas esgotadas, pendente');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (175, 'Tipo de inscrição do pagador avalista inválidos');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (176, 'Tipo/número de inscrição do pagador inválidos');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (177, 'Tipo/Número de inscrição do pagador/avalista inválidos');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (178, 'Titulo Baixado e Transferido para Desconto');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (179, 'Título Baixado pelo Banco por decurso Prazo');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (180, 'Titulo Baixado Transferido Carteira');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (181, 'Título com ordem de protesto emitido');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (182, 'Título com ordem/pedido de protesto/Negativação emitido');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (183, 'Título com pagamento vinculado');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (184, 'Título excluído');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (185, 'Título pago com cheque');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (186, 'Título pago com dinheiro');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (187, 'Título Penhorado – Instrução Não Liberada pela Agência');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (188, 'Título Protestado');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (189, 'TR Emissão aviso rateio');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (190, 'TR Tít. Baixado por decurso prazo');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (191, 'TR. Agendamento Déb Aut');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (192, 'TR. Agendamento rat. Crédito');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (193, 'Tr. credito online');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (194, 'Tr. tentativa cons deb aut');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (195, 'Transferência para desconto não permitido para a carteira');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (196, 'Valor do abatimento inválido');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (197, 'Valor do abatimento maior/igual ao valor do Título');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (198, 'Valor do desconto maior/igual ao valor do Título');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (199, 'Valor do IOF inválido');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (200, 'Valor do título inválido');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (201, 'Valor/taxa de juros de mora inválido');
INSERT INTO carteira_pesca.motivo (id, tx_descricao) VALUES (202, 'Vencimento fora do prazo de operação');

ALTER SEQUENCE carteira_pesca.motivo_id_seq RESTART WITH 203;

INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (1, 97, 1, 0);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (2, 35, 1, 1);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (3, 37, 1, 4);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (4, 20, 1, 15);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (5, 48, 1, 17);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (6, 71, 1, 21);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (7, 45, 1, 24);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (8, 201, 1, 27);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (9, 107, 1, 38);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (10, 105, 1, 39);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (11, 106, 1, 43);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (12, 92, 1, 45);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (13, 176, 1, 46);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (14, 69, 1, 47);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (15, 23, 1, 48);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (16, 25, 1, 50);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (17, 91, 1, 53);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (18, 101, 1, 54);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (19, 51, 1, 67);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (20, 58, 1, 68);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (21, 59, 1, 69);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (22, 52, 1, 70);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (23, 53, 1, 71);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (24, 54, 1, 72);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (25, 57, 1, 73);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (26, 60, 1, 75);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (27, 100, 1, 76);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (28, 114, 1, 86);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (29, 65, 1, 89);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (30, 66, 1, 90);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (31, 38, 2, 2);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (32, 29, 2, 3);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (33, 34, 2, 4);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (34, 30, 2, 5);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (35, 4, 2, 7);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (36, 95, 2, 8);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (37, 94, 2, 9);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (38, 21, 2, 10);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (39, 82, 2, 13);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (40, 49, 2, 16);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (41, 202, 2, 18);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (42, 200, 2, 20);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (43, 71, 2, 21);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (44, 72, 2, 22);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (45, 45, 2, 24);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (46, 36, 2, 28);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (47, 107, 2, 38);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (48, 3, 2, 44);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (49, 93, 2, 45);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (50, 176, 2, 46);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (51, 69, 2, 47);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (52, 23, 2, 48);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (53, 24, 2, 50);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (54, 70, 2, 63);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (55, 87, 2, 65);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (56, 96, 2, 66);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (57, 58, 2, 68);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (58, 59, 2, 69);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (59, 52, 2, 70);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (60, 53, 2, 71);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (61, 54, 2, 72);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (62, 56, 2, 73);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (63, 55, 2, 74);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (64, 61, 2, 75);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (65, 186, 3, 0);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (66, 185, 3, 15);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (67, 102, 3, 18);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (68, 108, 3, 42);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (69, 97, 4, 0);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (70, 9, 4, 10);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (71, 12, 5, 0);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (72, 188, 5, 14);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (73, 184, 5, 15);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (74, 179, 5, 16);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (75, 180, 5, 17);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (76, 178, 5, 20);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (77, 186, 6, 0);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (78, 185, 6, 15);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (79, 186, 7, 0);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (80, 185, 7, 15);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (81, 23, 8, 48);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (82, 34, 9, 4);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (83, 4, 9, 7);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (84, 95, 9, 8);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (85, 21, 9, 10);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (86, 22, 9, 15);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (87, 181, 9, 40);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (88, 40, 9, 42);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (89, 90, 9, 60);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (90, 195, 9, 77);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (91, 183, 9, 85);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (92, 125, 10, 2);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (93, 128, 10, 3);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (94, 126, 10, 4);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (95, 123, 10, 5);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (96, 124, 10, 6);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (97, 44, 10, 8);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (98, 127, 10, 12);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (99, 163, 10, 13);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (100, 160, 10, 14);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (101, 148, 10, 15);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (102, 116, 10, 16);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (103, 121, 10, 17);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (104, 119, 10, 18);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (105, 122, 10, 19);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (106, 120, 10, 20);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (107, 158, 10, 21);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (108, 162, 10, 22);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (109, 168, 10, 23);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (110, 164, 10, 24);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (111, 161, 10, 25);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (112, 155, 10, 26);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (113, 133, 10, 27);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (114, 156, 10, 28);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (115, 169, 10, 29);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (116, 83, 10, 30);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (117, 84, 10, 31);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (118, 165, 10, 32);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (119, 139, 10, 33);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (120, 166, 10, 34);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (121, 167, 10, 35);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (122, 157, 10, 36);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (123, 159, 10, 37);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (124, 142, 10, 38);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (125, 143, 10, 39);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (126, 11, 10, 40);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (127, 145, 10, 41);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (128, 147, 10, 42);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (129, 154, 10, 43);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (130, 151, 10, 44);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (131, 144, 10, 45);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (132, 150, 10, 46);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (133, 149, 10, 47);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (134, 146, 10, 48);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (135, 153, 10, 49);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (136, 152, 10, 50);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (137, 171, 10, 51);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (138, 27, 10, 52);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (139, 10, 10, 53);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (140, 117, 10, 54);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (141, 194, 10, 55);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (142, 193, 10, 56);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (143, 138, 10, 57);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (144, 131, 10, 58);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (145, 132, 10, 59);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (146, 2, 10, 60);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (147, 1, 10, 61);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (148, 103, 10, 62);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (149, 74, 10, 63);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (150, 79, 10, 64);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (151, 78, 10, 65);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (152, 80, 10, 66);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (153, 81, 10, 67);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (154, 76, 10, 68);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (155, 77, 10, 69);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (156, 75, 10, 70);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (157, 110, 10, 71);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (158, 5, 10, 72);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (159, 130, 10, 73);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (160, 129, 10, 74);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (161, 141, 10, 75);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (162, 7, 10, 76);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (163, 88, 10, 77);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (164, 118, 10, 78);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (165, 16, 10, 79);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (166, 14, 10, 80);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (167, 135, 10, 81);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (168, 140, 10, 82);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (169, 134, 10, 83);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (170, 68, 10, 84);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (171, 113, 10, 85);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (172, 15, 10, 86);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (173, 111, 10, 87);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (174, 67, 10, 88);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (175, 89, 10, 89);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (176, 13, 10, 90);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (177, 26, 10, 91);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (178, 43, 10, 92);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (179, 109, 10, 93);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (180, 112, 10, 94);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (181, 136, 10, 96);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (182, 137, 10, 97);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (183, 170, 10, 98);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (184, 190, 10, 99);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (185, 8, 10, 100);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (186, 6, 10, 101);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (187, 191, 10, 102);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (188, 192, 10, 105);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (189, 189, 10, 106);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (190, 73, 10, 107);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (191, 99, 11, 78);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (192, 98, 11, 95);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (193, 35, 12, 1);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (194, 34, 12, 4);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (195, 30, 12, 5);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (196, 95, 12, 8);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (197, 19, 12, 15);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (198, 49, 12, 16);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (199, 48, 12, 17);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (200, 202, 12, 18);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (201, 45, 12, 24);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (202, 33, 12, 26);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (203, 201, 12, 27);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (204, 31, 12, 28);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (205, 198, 12, 29);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (206, 62, 12, 30);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (207, 42, 12, 31);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (208, 199, 12, 32);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (209, 196, 12, 33);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (210, 197, 12, 34);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (211, 107, 12, 38);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (212, 105, 12, 39);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (213, 182, 12, 40);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (214, 39, 12, 42);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (215, 176, 12, 46);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (216, 23, 12, 48);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (217, 177, 12, 53);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (218, 101, 12, 54);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (219, 28, 12, 57);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (220, 46, 12, 58);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (221, 90, 12, 60);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (222, 47, 12, 79);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (223, 50, 12, 80);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (224, 183, 12, 85);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (225, 63, 12, 88);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (226, 64, 12, 91);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (227, 35, 13, 1);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (228, 38, 13, 2);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (229, 34, 13, 4);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (230, 30, 13, 5);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (231, 4, 13, 7);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (232, 95, 13, 8);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (233, 21, 13, 10);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (234, 20, 13, 15);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (235, 49, 13, 16);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (236, 48, 13, 17);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (237, 202, 13, 18);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (238, 200, 13, 20);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (239, 71, 13, 21);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (240, 72, 13, 22);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (241, 45, 13, 24);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (242, 32, 13, 28);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (243, 198, 13, 29);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (244, 62, 13, 30);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (245, 42, 13, 31);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (246, 196, 13, 33);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (247, 197, 13, 34);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (248, 41, 13, 36);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (249, 107, 13, 38);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (250, 105, 13, 39);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (251, 182, 13, 40);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (252, 104, 13, 41);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (253, 39, 13, 42);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (254, 93, 13, 45);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (255, 176, 13, 46);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (256, 69, 13, 47);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (257, 23, 13, 48);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (258, 25, 13, 50);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (259, 175, 13, 53);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (260, 90, 13, 60);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (261, 183, 13, 85);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (262, 115, 13, 86);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (263, 187, 13, 94);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (264, 86, 13, 97);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (265, 85, 13, 98);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (266, 172, 13, 99);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (267, 173, 14, 81);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (268, 174, 14, 82);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (269, 18, 14, 83);
INSERT INTO carteira_pesca.motivo_ocorrencia (id, id_motivo, id_ocorrencia, num_codigo_motivo) VALUES (270, 17, 14, 84);

ALTER SEQUENCE carteira_pesca.motivo_ocorrencia_id_seq RESTART WITH 271;