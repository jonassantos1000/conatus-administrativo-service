insert into tb_tipo_dominio (IDENT, DS_TIPO, DT_CADASTRO, COD_TIPO_DOMINIO) 
values (1, 'Situacoes de cadastros', '2023-10-13', 'SITUACAO_CADASTRO');

insert into tb_dominio (IDENT, DOMINIO, ID_TIPO, DT_CADASTRO, COD_DOMINIO) 
values (1, 'Status ativo', 1, '2023-10-13', 'STATUS_ATIVO');

--

insert into tb_tipo_dominio (IDENT, DS_TIPO, DT_CADASTRO, COD_TIPO_DOMINIO) 
values (2, 'Tipo de Cargos', '2023-10-13', 'CARGOS');

insert into tb_dominio (IDENT, DOMINIO, ID_DOM_SITUACAO, ID_TIPO, DT_CADASTRO, COD_DOMINIO) 
values (2, 'Proprietário', 1, 2, '2023-10-13', 'CARGO_PROPRIETARIO');

--

insert into tb_tipo_dominio (IDENT, DS_TIPO, DT_CADASTRO, COD_TIPO_DOMINIO) 
values (3, 'Tipo de licenças', '2023-10-13', 'TIPO_LICENCA');

insert into tb_dominio (IDENT, DOMINIO, ID_DOM_SITUACAO, ID_TIPO, DT_CADASTRO, COD_DOMINIO) 
values (3, 'PREMIUM', 1, 3, '2023-10-13', 'LICENCA_PREMIUM');

--

insert into tb_tipo_dominio (IDENT, DS_TIPO, DT_CADASTRO, COD_TIPO_DOMINIO) 
values (4, 'Ramos de atividade empresa', '2023-10-13', 'RAMO_ATIV');

insert into tb_dominio (IDENT, DOMINIO, ID_DOM_SITUACAO, ID_TIPO, DT_CADASTRO, COD_DOMINIO) 
values (4, 'Tecnologia', 1, 4, '2023-10-13', 'RAMO_TECNO');

--

insert into TB_LICENCA (QT_DURACAO_LICENCA, ID_DOM_SITUACAO, ID_DOM_TIPO_LICENCA) 
values (5, 1, 3);

