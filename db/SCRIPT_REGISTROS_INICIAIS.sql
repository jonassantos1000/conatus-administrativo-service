-- TRIGGER ATUALIZA VALOR BASE DO MODULO

CREATE OR REPLACE FUNCTION atualizar_valor_base()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE tb_modulo
    SET vl_base = (
        SELECT COALESCE(SUM(VL_FUNCIONALIDADE), 0)
        FROM tb_funcionalidade
        WHERE id_modulo = NEW.id_modulo
    )
    WHERE ident = NEW.id_modulo;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_atualizar_valor_base
AFTER INSERT OR UPDATE ON tb_funcionalidade
FOR EACH ROW
EXECUTE FUNCTION atualizar_valor_base();

-- TRIGGER CHECK SITUACAO

CREATE OR REPLACE FUNCTION check_dominio_situacao()
RETURNS TRIGGER AS $CheckSituacaoDominio$
begin
    IF (select ID_TIPO from tb_dominio d where new.ID_DOM_SITUACAO = d.ident ) <> 1 THEN
        RAISE EXCEPTION 'O dominio informado não possui vinculo valido para o campo situação.';
    END IF;
    RETURN NEW;
END;
$CheckSituacaoDominio$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_check_dom_situacao
BEFORE INSERT ON tb_dominio
FOR EACH ROW
EXECUTE FUNCTION check_dominio_situacao();

--SITUAÇÃO DE CADASTRO

insert into tb_tipo_dominio (IDENT, DS_TIPO, DT_CADASTRO, COD_TIPO_DOMINIO)
values (1, 'Situacoes de cadastros', '2023-10-13', 'SITUACAO_CADASTRO');

insert into tb_dominio (IDENT, DOMINIO, ID_TIPO, DT_CADASTRO, COD_DOMINIO, ID_DOM_SITUACAO) 
values (1, 'Status ativo', 1, '2023-10-13', 'STATUS_ATIVO', 1);

insert into tb_dominio (IDENT, DOMINIO, ID_TIPO, DT_CADASTRO, COD_DOMINIO, ID_DOM_SITUACAO) 
values (2, 'Status cancelado', 1, '2023-10-22', 'STATUS_CANCELADO', 1);

insert into tb_dominio (IDENT, DOMINIO, ID_TIPO, DT_CADASTRO, COD_DOMINIO, ID_DOM_SITUACAO) 
values (3, 'Status inativo', 1, '2023-10-22', 'STATUS_INATIVO', 1);

insert into tb_dominio (IDENT, DOMINIO, ID_TIPO, DT_CADASTRO, COD_DOMINIO, ID_DOM_SITUACAO)
values (4, 'Status pendente', 1, '2023-10-22', 'STATUS_PENDENTE', 1);

insert into tb_dominio (IDENT, DOMINIO, ID_TIPO, DT_CADASTRO, COD_DOMINIO, ID_DOM_SITUACAO)
values (5, 'Status em andamento', 1, '2023-10-22', 'STATUS_EM_ANDAMENTO', 1);

insert into tb_dominio (IDENT, DOMINIO, ID_TIPO, DT_CADASTRO, COD_DOMINIO, ID_DOM_SITUACAO)
values (6, 'Status em análise', 1, '2023-10-22', 'STATUS_EM_ANALISE', 1);

-- CARGOS

insert into tb_tipo_dominio (IDENT, DS_TIPO, DT_CADASTRO, COD_TIPO_DOMINIO)
values (2, 'Tipo de Cargos', '2023-10-13', 'CARGOS');

insert into tb_dominio (IDENT, DOMINIO, ID_DOM_SITUACAO, ID_TIPO, DT_CADASTRO, COD_DOMINIO)
values (200, 'Proprietário', 1, 2, '2023-10-13', 'CARGO_PROPRIETARIO');

insert into tb_dominio (IDENT, DOMINIO, ID_DOM_SITUACAO, ID_TIPO, DT_CADASTRO, COD_DOMINIO)
values (201, 'Diretor', 1, 2, '2023-10-13', 'CARGO_DIRETOR');

insert into tb_dominio (IDENT, DOMINIO, ID_DOM_SITUACAO, ID_TIPO, DT_CADASTRO, COD_DOMINIO)
values (202, 'Gerente', 1, 2, '2023-10-13', 'CARGO_GERENTE');

insert into tb_dominio (IDENT, DOMINIO, ID_DOM_SITUACAO, ID_TIPO, DT_CADASTRO, COD_DOMINIO)
values (203, 'Funcionario', 1, 2, '2023-10-13', 'CARGO_FUNCIONARIO');

-- Tipos de modulos

insert into tb_tipo_dominio (IDENT, DS_TIPO, DT_CADASTRO, COD_TIPO_DOMINIO)
values (3, 'Tipos de modulos', '2023-10-13', 'TIPOS_MODULOS');

insert into tb_dominio (IDENT, DOMINIO, ID_DOM_SITUACAO, ID_TIPO, DT_CADASTRO, COD_DOMINIO)
values (300, 'Estoque', 1, 3, '2023-10-22', 'MODULO_ESTOQUE');

insert into tb_dominio (IDENT, DOMINIO, ID_DOM_SITUACAO, ID_TIPO, DT_CADASTRO, COD_DOMINIO)
values (301, 'Vendas', 1, 3, '2023-10-22', 'MODULO_VENDAS');

insert into tb_dominio (IDENT, DOMINIO, ID_DOM_SITUACAO, ID_TIPO, DT_CADASTRO, COD_DOMINIO)
values (302, 'Clientes', 1, 3, '2023-10-22', 'MODULO_CLIENTES');

insert into tb_dominio (IDENT, DOMINIO, ID_DOM_SITUACAO, ID_TIPO, DT_CADASTRO, COD_DOMINIO)
values (303, 'Produtos', 1, 3, '2023-10-22', 'MODULO_PRODUTOS');

--RAMOS DE ATIVIDADE

insert into tb_tipo_dominio (IDENT, DS_TIPO, DT_CADASTRO, COD_TIPO_DOMINIO) 
values (4, 'Ramos de atividade empresa', '2023-10-13', 'RAMO_ATIV');

insert into tb_dominio (IDENT, DOMINIO, ID_DOM_SITUACAO, ID_TIPO, DT_CADASTRO, COD_DOMINIO)
values (400, 'Tecnologia', 1, 4, '2023-10-13', 'RAMO_TECNO');

insert into tb_dominio (IDENT, DOMINIO, ID_DOM_SITUACAO, ID_TIPO, DT_CADASTRO, COD_DOMINIO)
values (401, 'Logística', 1, 4, '2023-10-22', 'RAMO_LOGISTICA');

insert into tb_dominio (IDENT, DOMINIO, ID_DOM_SITUACAO, ID_TIPO, DT_CADASTRO, COD_DOMINIO)
values (402, 'Varejo', 1, 4, '2023-10-22', 'RAMO_VAREJO');

insert into tb_dominio (IDENT, DOMINIO, ID_DOM_SITUACAO, ID_TIPO, DT_CADASTRO, COD_DOMINIO)
values (403, 'Industria', 1, 4, '2023-10-22', 'RAMO_INDUSTRIA');

insert into tb_dominio (IDENT, DOMINIO, ID_DOM_SITUACAO, ID_TIPO, DT_CADASTRO, COD_DOMINIO)
values (404, 'Construção', 1, 4, '2023-10-22', 'RAMO_CONSTRUCAO');

insert into tb_dominio (IDENT, DOMINIO, ID_DOM_SITUACAO, ID_TIPO, DT_CADASTRO, COD_DOMINIO)
values (405, 'Eletronicos', 1, 4, '2023-10-22', 'RAMO_ELETRONICOS');

insert into tb_dominio (IDENT, DOMINIO, ID_DOM_SITUACAO, ID_TIPO, DT_CADASTRO, COD_DOMINIO)
values (406, 'Outros', 1, 4, '2023-10-22', 'RAMO_OUTROS');
 
-- TIPOS DE MOVIMENTAÇÃO

insert into tb_tipo_dominio (IDENT, DS_TIPO, DT_CADASTRO, COD_TIPO_DOMINIO) 
values (5, 'Tipos de movimentações', '2023-10-22', 'TIPO_MOVIM');

insert into tb_dominio (IDENT, DOMINIO, ID_DOM_SITUACAO, ID_TIPO, DT_CADASTRO, COD_DOMINIO)
values (500, 'Nova contratação de modulo', 1, 5, '2023-10-22', 'MOVIM_CONTRATACAO_MODULO');

insert into tb_dominio (IDENT, DOMINIO, ID_DOM_SITUACAO, ID_TIPO, DT_CADASTRO, COD_DOMINIO)
values (501, 'Ativação de funcionalidade premium', 1, 5, '2023-10-22', 'MOVIM_ATIVACAO_FUNC_PREMIUM');

insert into tb_dominio (IDENT, DOMINIO, ID_DOM_SITUACAO, ID_TIPO, DT_CADASTRO, COD_DOMINIO)
values (503, 'Desativação de funcionalidade premium', 1, 5, '2023-10-22', 'MOVIM_DESATIVACAO_FUNC_PREMIUM');

insert into tb_dominio (IDENT, DOMINIO, ID_DOM_SITUACAO, ID_TIPO, DT_CADASTRO, COD_DOMINIO)
values (504, 'Cancelamento de contração de modulo', 1, 5, '2023-10-22', 'MOVIM_CANCEL_CONT_MODULO');

insert into tb_dominio (IDENT, DOMINIO, ID_DOM_SITUACAO, ID_TIPO, DT_CADASTRO, COD_DOMINIO)
values (505, 'Registro de novo funcionario', 1, 5, '2023-10-22', 'MOVIM_REGISTRO_NOVO_FUNCIONARIO');

insert into tb_dominio (IDENT, DOMINIO, ID_DOM_SITUACAO, ID_TIPO, DT_CADASTRO, COD_DOMINIO)
values (506, 'Remoção de funcionario', 1, 5, '2023-10-22', 'MOVIM_REMOCAO_FUNCIONARIO');

-- TIPOS DE FUNCIONALIDADES

insert into tb_tipo_dominio (IDENT, DS_TIPO, DT_CADASTRO, COD_TIPO_DOMINIO) 
values (6, 'Tipos de funcionalidades', '2023-10-22', 'TIPO_FUNC');

insert into tb_dominio (IDENT, DOMINIO, ID_DOM_SITUACAO, ID_TIPO, DT_CADASTRO, COD_DOMINIO)
values (600, 'Padrão', 1, 6, '2023-10-22', 'FUNC_PADRAO');

insert into tb_dominio (IDENT, DOMINIO, ID_DOM_SITUACAO, ID_TIPO, DT_CADASTRO, COD_DOMINIO)
values (601, 'Premium', 1, 6, '2023-10-22', 'FUNC_PREMIUM');

-- TIPOS DE GENEROS

insert into tb_tipo_dominio (IDENT, DS_TIPO, DT_CADASTRO, COD_TIPO_DOMINIO) 
values (7, 'Tipos de gêneros', '2023-10-22', 'TIPO_GENERO');

insert into tb_dominio (IDENT, DOMINIO, ID_DOM_SITUACAO, ID_TIPO, DT_CADASTRO, COD_DOMINIO)
values (700, 'Masculino', 1, 7, '2023-10-22', 'GENERO_MASCULINO');

insert into tb_dominio (IDENT, DOMINIO, ID_DOM_SITUACAO, ID_TIPO, DT_CADASTRO, COD_DOMINIO)
values (701, 'Feminino', 1, 7, '2023-10-22', 'GENERO_FEMININO');

insert into tb_dominio (IDENT, DOMINIO, ID_DOM_SITUACAO, ID_TIPO, DT_CADASTRO, COD_DOMINIO)
values (702, 'Não binário', 1, 7, '2023-10-22', 'GENERO_NAO_BINARIO');

insert into tb_dominio (IDENT, DOMINIO, ID_DOM_SITUACAO, ID_TIPO, DT_CADASTRO, COD_DOMINIO)
values (703, 'Prefere não informar', 1, 7, '2023-10-22', 'GENERO_NAO_INFORMAR');

-- TABELA MODULOS

insert into tb_modulo (IDENT, VL_BASE, ID_DOM_SITUACAO, ID_DOM_TIPO, DS_MODULO) 
VALUES (1, 150.0, 1, 300, 'Estoque');

insert into tb_modulo (IDENT, VL_BASE, ID_DOM_SITUACAO, ID_DOM_TIPO, DS_MODULO) 
VALUES (2, 250.0, 1, 301, 'Vendas');

insert into tb_modulo (IDENT, VL_BASE, ID_DOM_SITUACAO, ID_DOM_TIPO, DS_MODULO) 
VALUES (4, 60.0, 1, 302, 'Clientes');

insert into tb_modulo (IDENT, VL_BASE, ID_DOM_SITUACAO, ID_DOM_TIPO, DS_MODULO) 
VALUES (3, 50.0, 1, 303, 'Produtos');

-- TABELA DE FUNCIONALIDADES

insert into tb_funcionalidade (IDENT, VL_FUNCIONALIDADE, ID_MODULO, ID_DOM_TIPO, DS_DESCRICAO) 
VALUES (1, 15.0, 1, 600, 'Consultar níveis de estoque');

insert into tb_funcionalidade (IDENT, VL_FUNCIONALIDADE, ID_MODULO, ID_DOM_TIPO, DS_DESCRICAO) 
VALUES (2, 30.0, 1, 601, 'Consultar previsão de demanda'); 

insert into tb_funcionalidade (IDENT, VL_FUNCIONALIDADE, ID_MODULO, ID_DOM_TIPO, DS_DESCRICAO) 
VALUES (3, 15.0, 1, 600, 'Cadastrar produto');

insert into tb_funcionalidade (IDENT, VL_FUNCIONALIDADE, ID_MODULO, ID_DOM_TIPO, DS_DESCRICAO) 
VALUES (4, 30.0, 1, 601, 'Visualizar curva ABC'); 

insert into tb_funcionalidade (IDENT, VL_FUNCIONALIDADE, ID_MODULO, ID_DOM_TIPO, DS_DESCRICAO) 
VALUES (5, 15.0, 2, 600, 'Consultar vendas');

insert into tb_funcionalidade (IDENT, VL_FUNCIONALIDADE, ID_MODULO, ID_DOM_TIPO, DS_DESCRICAO) 
VALUES (6, 30.0, 2, 601, 'Consultar vendas por funcionario'); 

insert into tb_funcionalidade (IDENT, VL_FUNCIONALIDADE, ID_MODULO, ID_DOM_TIPO, DS_DESCRICAO) 
VALUES (7, 15.0, 2, 600, 'Consultar vendas por mês');

insert into tb_funcionalidade (IDENT, VL_FUNCIONALIDADE, ID_MODULO, ID_DOM_TIPO, DS_DESCRICAO) 
VALUES (8, 30.0, 2, 601, 'Consultar vendas por ano'); 

insert into tb_funcionalidade (IDENT, VL_FUNCIONALIDADE, ID_MODULO, ID_DOM_TIPO, DS_DESCRICAO) 
VALUES (9, 15.0, 4, 600, 'Consultar clientes');

insert into tb_funcionalidade (IDENT, VL_FUNCIONALIDADE, ID_MODULO, ID_DOM_TIPO, DS_DESCRICAO) 
VALUES (10, 30.0, 4, 600, 'Cadastrar clientes'); 

insert into tb_funcionalidade (IDENT, VL_FUNCIONALIDADE, ID_MODULO, ID_DOM_TIPO, DS_DESCRICAO) 
VALUES (11, 15.0, 4, 600, 'Alterar clientes');

insert into tb_funcionalidade (IDENT, VL_FUNCIONALIDADE, ID_MODULO, ID_DOM_TIPO, DS_DESCRICAO) 
VALUES (12, 30.0, 4, 601, 'Enviar notificação por e-mail para clientes');

insert into tb_funcionalidade (IDENT, VL_FUNCIONALIDADE, ID_MODULO, ID_DOM_TIPO, DS_DESCRICAO) 
VALUES (13, 15.0, 1, 600, 'Consultar movimentações');

-- TABELA VINCULO MODULO X FUNCIONALIDADE
insert into tb_modulo_func (IDENT, id_dom_situacao, id_funcionalidade, id_modulo) 
values (1, 1, 1, 1);
