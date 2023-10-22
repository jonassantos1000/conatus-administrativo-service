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
values (3, 'Tipos de modulos', '2023-10-13', 'TIPOS_MODULOS');

insert into tb_dominio (IDENT, DOMINIO, ID_DOM_SITUACAO, ID_TIPO, DT_CADASTRO, COD_DOMINIO) 
values (3, 'ESTOQUE', 1, 3, '2023-10-13', 'MODULO_ESTOQUE');

--

insert into tb_tipo_dominio (IDENT, DS_TIPO, DT_CADASTRO, COD_TIPO_DOMINIO) 
values (4, 'Ramos de atividade empresa', '2023-10-13', 'RAMO_ATIV');

insert into tb_dominio (IDENT, DOMINIO, ID_DOM_SITUACAO, ID_TIPO, DT_CADASTRO, COD_DOMINIO) 
values (4, 'Tecnologia', 1, 4, '2023-10-13', 'RAMO_TECNO');

--

insert into tb_tipo_dominio (IDENT, DS_TIPO, DT_CADASTRO, COD_TIPO_DOMINIO) 
values (5, 'Tipos de movimentações', '2023-10-22', 'TIPO_MOVIM');

insert into tb_dominio (IDENT, DOMINIO, ID_DOM_SITUACAO, ID_TIPO, DT_CADASTRO, COD_DOMINIO) 
values (5, 'Nova contratação de modulo', 1, 5, '2023-10-22', 'MOVIM_CONTRATACAO_MODULO');

insert into tb_dominio (IDENT, DOMINIO, ID_DOM_SITUACAO, ID_TIPO, DT_CADASTRO, COD_DOMINIO) 
values (6, 'Ativação de funcionalidade premium', 1, 5, '2023-10-22', 'MOVIM_ATIVACAO_FUNC_PREMIUM');

--

insert into tb_tipo_dominio (IDENT, DS_TIPO, DT_CADASTRO, COD_TIPO_DOMINIO) 
values (6, 'Tipos de funcionalidades', '2023-10-22', 'TIPO_FUNC');

insert into tb_dominio (IDENT, DOMINIO, ID_DOM_SITUACAO, ID_TIPO, DT_CADASTRO, COD_DOMINIO) 
values (7, 'Funcionalidade padrão', 1, 6, '2023-10-22', 'FUNC_PADRAO');

insert into tb_dominio (IDENT, DOMINIO, ID_DOM_SITUACAO, ID_TIPO, DT_CADASTRO, COD_DOMINIO) 
values (8, 'Funcionalidade premium', 1, 6, '2023-10-22', 'FUNC_PREMIUM');

--

insert into tb_modulo (IDENT, VL_BASE, ID_DOM_SITUACAO, ID_DOM_TIPO, DS_MODULO) 
VALUES (1, 150.0, 1, 3, 'Modulo estoque - padrão');

--

insert into tb_funcionalidade (IDENT, VALOR_FUNCIONALIDADE, ID_DOM_MODULO, ID_DOM_TIPO, DS_DESCRICAO) 
VALUES (1, 15.0, 3, 7, 'Consultar níveis de estoque');

insert into tb_funcionalidade (IDENT, VALOR_FUNCIONALIDADE, ID_DOM_MODULO, ID_DOM_TIPO, DS_DESCRICAO) 
VALUES (2, 30.0, 3, 8, 'Consultar previsão de demanda'); 
