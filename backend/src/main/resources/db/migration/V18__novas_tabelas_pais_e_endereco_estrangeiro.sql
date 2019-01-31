CREATE TABLE carteira_pesca.pais
(
	id SERIAL NOT NULL,
	nome CHARACTER VARYING (255) NOT NULL,
	sigla CHARACTER VARYING (2) NOT NULL,
	CONSTRAINT pk_pais PRIMARY KEY (id)
) WITH (
  OIDS=FALSE
);

ALTER TABLE carteira_pesca.pais
  OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.pais TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.pais TO carteira_pesca;
COMMENT ON TABLE carteira_pesca.pais
  IS 'Entidade responsável por armazenar os países.';
COMMENT ON COLUMN carteira_pesca.pais.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.pais.nome IS 'Nome do País. ';
COMMENT ON COLUMN carteira_pesca.pais.sigla IS 'Sigla do País. ';

INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(1, 'Afeganistão','AF');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(2,'África do Sul','ZA');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(3,'Albânia','AL');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(4,'Alemanha','DE');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(5,'Andorra','AD');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(6,'Angola','AO');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(7,'Anguilla','AI');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(8,'Antártida','AQ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(9,'Antígua e Barbuda','AG');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(10,'Antilhas Holandesas','AN');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(11,'Arábia Saudita','SA');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(12,'Argélia','DZ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(13,'Argentina','AR');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(14,'Armênia','AM');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(15,'Aruba','AW');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(16,'Austrália','AU');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(17,'Áustria','AT');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(18,'Azerbaijão','AZ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(19,'Bahamas','BS');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(20,'Bahrein','BH');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(21,'Bangladesh','BD');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(22,'Barbados','BB');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(23,'Belarus','BY');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(24,'Bélgica','BE');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(25,'Belize','BZ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(26,'Benin','BJ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(27,'Bermudas','BM');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(28,'Bolívia','BO');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(29,'Bósnia-Herzegóvina','BA');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(30,'Botsuana','BW');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(31,'Brasil','BR');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(32,'Brunei','BN');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(33,'Bulgária','BG');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(34,'Burkina Fasso','BF');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(35,'Burundi','BI');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(36,'Butão','BT');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(37,'Cabo Verde','CV');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(38,'Camarões','CM');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(39,'Camboja','KH');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(40,'Canadá','CA');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(41,'Cazaquistão','KZ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(42,'Chade','TD');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(43,'Chile','CL');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(44,'China','CN');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(45,'Chipre','CY');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(46,'Cingapura','SG');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(47,'Colômbia','CO');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(48,'Congo','CG');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(49,'Coréia do Norte','KP');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(50,'Coréia do Sul','KR');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(51,'Costa do Marfim','CI');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(52,'Costa Rica','CR');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(53,'Croácia (Hrvatska)','HR');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(54,'Cuba','CU');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(55,'Dinamarca','DK');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(56,'Djibuti','DJ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(57,'Dominica','DM');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(58,'Egito','EG');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(59,'El Salvador','SV');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(60,'Emirados Árabes Unidos','AE');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(61,'Equador','EC');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(62,'Eritréia','ER');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(63,'Eslováquia','SK');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(64,'Eslovênia','SI');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(65,'Espanha','ES');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(66,'Estados Unidos','US');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(67,'Estônia','EE');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(68,'Etiópia','ET');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(69,'Federação Russa','RU');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(70,'Fiji','FJ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(71,'Filipinas','PH');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(72,'Finlândia','FI');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(73,'França','FR');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(74,'Gabão','GA');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(75,'Gâmbia','GM');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(76,'Gana','GH');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(77,'Geórgia','GE');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(78,'Gibraltar','GI');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(79,'Grã-Bretanha (Reino Unido - Uk)','GB');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(80,'Granada','GD');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(81,'Grécia','GR');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(82,'Groelândia','GL');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(83,'Guadalupe','GP');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(84,'Guam (Território Dos Estados Unidos)','GU');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(85,'Guatemala','GT');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(86,'Guernsey','GG');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(87,'Guiana','GY');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(88,'Guiana Francesa','GF');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(89,'Guiné','GN');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(90,'Guiné Equatorial','GQ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(91,'Guiné-Bissau','GW');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(92,'Haiti','HT');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(93,'Holanda','NL');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(94,'Honduras','HN');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(95,'Hong Kong','HK');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(96,'Hungria','HU');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(97,'Iêmen','YE');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(98,'Ilha Bouvet (Território da Noruega)','BV');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(99,'Ilha do Homem','IM');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(100,'Ilha Natal','CX');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(101,'Ilha Pitcairn','PN');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(102,'Ilha Reunião','RE');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(103,'Ilhas Aland','AX');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(104,'Ilhas Cayman','KY');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(105,'Ilhas Cocos','CC');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(106,'Ilhas Comores','KM');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(107,'Ilhas Cook','CK');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(108,'Ilhas Faroes','FO');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(109,'Ilhas Falkland (Malvinas)','FK');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(110,'Ilhas Geórgia do Sul e Sandwich do Sul','GS');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(111,'Ilhas Heard e Mcdonald (Território da Austrália)','HM');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(112,'Ilhas Marianas do Norte','MP');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(113,'Ilhas Marshall','MH');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(114,'Ilhas Menores Dos Estados Unidos','UM');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(115,'Ilhas Norfolk','NF');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(116,'Ilhas Seychelles','SC');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(117,'Ilhas Solomão','SB');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(118,'Ilhas Svalbard e Jan Mayen','SJ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(119,'Ilhas Tokelau','TK');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(120,'Ilhas Turks e Caicos','TC');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(121,'Ilhas Virgens (Estados Unidos)','VI');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(122,'Ilhas Virgens (Inglaterra)','VG');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(123,'Ilhas Wallis e Futuna','WF');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(124,'Índia','IN');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(125,'Indonésia','ID');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(126,'Irã','IR');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(127,'Iraque','IQ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(128,'Irlanda','IE');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(129,'Islândia','IS');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(130,'Israel','IL');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(131,'Itália','IT');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(132,'Jamaica','JM');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(133,'Japão','JP');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(134,'Jersey','JE');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(135,'Jordânia','JO');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(136,'Kênia','KE');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(137,'Kiribati','KI');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(138,'Kuait','KW');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(139,'Laos','LA');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(140,'Látvia','LV');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(141,'Lesoto','LS');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(142,'Líbano','LB');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(143,'Libéria','LR');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(144,'Líbia','LY');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(145,'Liechtenstein','LI');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(146,'Lituânia','LT');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(147,'Luxemburgo','LU');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(148,'Macau','MO');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(149,'Macedônia (República Yugoslava)','MK');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(150,'Madagascar','MG');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(151,'Malásia','MY');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(152,'Malaui','MW');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(153,'Maldivas','MV');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(154,'Mali','ML');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(155,'Malta','MT');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(156,'Marrocos','MA');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(157,'Martinica','MQ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(158,'Maurício','MU');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(159,'Mauritânia','MR');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(160,'Mayotte','YT');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(161,'México','MX');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(162,'Micronésia','FM');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(163,'Moçambique','MZ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(164,'Moldova','MD');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(165,'Mônaco','MC');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(166,'Mongólia','MN');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(167,'Montenegro','ME');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(168,'Montserrat','MS');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(169,'Myanma','MM');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(170,'Namíbia','NA');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(171,'Nauru','NR');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(172,'Nepal','NP');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(173,'Nicarágua','NI');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(174,'Níger','NE');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(175,'Nigéria','NG');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(176,'Niue','NU');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(177,'Noruega','NO');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(178,'Nova Caledônia','NC');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(179,'Nova Zelândia','NZ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(180,'Omã','OM');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(181,'Palau','PW');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(182,'Panamá','PA');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(183,'Papua-Nova Guiné','PG');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(184,'Paquistão','PK');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(185,'Paraguai','PY');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(186,'Peru','PE');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(187,'Polinésia Francesa','PF');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(188,'Polônia','PL');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(189,'Porto Rico','PR');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(190,'Portugal','PT');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(191,'Qatar','QA');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(192,'Quirguistão','KG');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(193,'República Centro-Africana','CF');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(194,'República Democrática do Congo','CD');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(195,'República Dominicana','DO');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(196,'República Tcheca','CZ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(197,'Romênia','RO');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(198,'Ruanda','RW');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(199,'Saara Ocidental','EH');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(200,'Saint Vincente e Granadinas','VC');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(201,'Samoa Ocidental','AS');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(202,'Samoa Ocidental','WS');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(203,'San Marino','SM');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(204,'Santa Helena','SH');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(205,'Santa Lúcia','LC');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(206,'São Bartolomeu','BL');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(207,'São Cristóvão e Névis','KN');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(208,'São Martim','MF');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(209,'São Tomé e Príncipe','ST');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(210,'Senegal','SN');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(211,'Serra Leoa','SL');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(212,'Sérvia','RS');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(213,'Síria','SY');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(214,'Somália','SO');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(215,'Sri Lanka','LK');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(216,'St. Pierre And Miquelon','PM');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(217,'Suazilândia','SZ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(218,'Sudão','SD');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(219,'Suécia','SE');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(220,'Suíça','CH');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(221,'Suriname','SR');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(222,'Tadjiquistão','TJ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(223,'Tailândia','TH');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(224,'Taiwan','TW');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(225,'Tanzânia','TZ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(226,'Território Britânico do Oceano Índico','IO');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(227,'Territórios do Sul da França','TF');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(228,'Territórios Palestinos Ocupados','PS');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(229,'Timor Leste','TP');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(230,'Togo','TG');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(231,'Tonga','TO');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(232,'Trinidad And Tobago','TT');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(233,'Tunísia','TN');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(234,'Turcomenistão','TM');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(235,'Turquia','TR');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(236,'Tuvalu','TV');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(237,'Ucrânia','UA');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(238,'Uganda','UG');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(239,'Uruguai','UY');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(240,'Uzbequistão','UZ');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(241,'Vanuatu','VU');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(242,'Vaticano','VA');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(243,'Venezuela','VE');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(244,'Vietnã','VN');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(245,'Zâmbia','ZM');
INSERT INTO carteira_pesca.pais (id, nome, sigla) VALUES(246,'Zimbábue','ZW');

CREATE TABLE carteira_pesca.endereco_estrangeiro
(
    id SERIAL NOT NULL,
    descricao_endereco CHARACTER VARYING(400) NOT NULL,
    cidade CHARACTER VARYING(70) NOT NULL,
    estado CHARACTER VARYING(70) NOT NULL,
    id_nacionalidade INTEGER NOT NULL,
    id_pais INTEGER NOT NULL,  
    CONSTRAINT pk_estrangeiro PRIMARY KEY (id),
    CONSTRAINT fk_ee_nacionalidade FOREIGN KEY (id_nacionalidade)
      REFERENCES carteira_pesca.pais (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
    CONSTRAINT fk_ee_pais FOREIGN KEY (id_pais)
      REFERENCES carteira_pesca.pais (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
) WITH (
  OIDS=FALSE
);

ALTER TABLE carteira_pesca.endereco_estrangeiro
  OWNER TO postgres;
GRANT ALL ON TABLE carteira_pesca.endereco_estrangeiro TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE carteira_pesca.endereco_estrangeiro TO carteira_pesca;
COMMENT ON TABLE carteira_pesca.endereco_estrangeiro
  IS 'Entidade responsável por armazenar o endereço estrangeiro.';
COMMENT ON COLUMN carteira_pesca.endereco_estrangeiro.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN carteira_pesca.endereco_estrangeiro.descricao_endereco IS 'Descrição do endereço.';
COMMENT ON COLUMN carteira_pesca.endereco_estrangeiro.cidade IS 'Nome da cidade.';
COMMENT ON COLUMN carteira_pesca.endereco_estrangeiro.estado IS 'Nome do estado.';
COMMENT ON COLUMN carteira_pesca.endereco_estrangeiro.id_nacionalidade IS 'Identificador único da entidade país que faz relacionamento entre pais e endereco_estrangeiro.';
COMMENT ON COLUMN carteira_pesca.endereco_estrangeiro.id_pais IS 'Identificador único da entidade país que faz relacionamento entre pais e endereco_estrangeiro.';


ALTER TABLE carteira_pesca.solicitante ADD COLUMN id_endereco_estrangeiro INTEGER;
COMMENT ON COLUMN carteira_pesca.solicitante.id_endereco_estrangeiro IS 'Identificador único da entidade endereco_estrangeiro que realizará o relacionamento entre endereco_estrangeiro e solicitante.';

ALTER TABLE carteira_pesca.solicitante ADD CONSTRAINT fk_s_endereco_estrangeiro FOREIGN KEY (id_endereco_estrangeiro)
      REFERENCES carteira_pesca.endereco_estrangeiro (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT;