/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Evellyn Gomes
 * Created: 20 de mar. de 2023
 */

DROP DATABASE IF EXISTS homecenter;

CREATE DATABASE homecenter;
USE homecenter;

CREATE TABLE cliente (
    cod_cliente INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100),
    email VARCHAR(50),
    cpf VARCHAR(15),
    telefone VARCHAR(20),
    endereco VARCHAR(100),
    cidade VARCHAR(45),
    cep VARCHAR(10),
    uf CHAR(2),
    PRIMARY KEY(cod_cliente)
);

CREATE TABLE funcionario (
    cod_funcionario INT NOT NULL AUTO_INCREMENT,
    nivel_acesso VARCHAR(20),
    nome VARCHAR(100),
    cpf VARCHAR(15),
    senha VARCHAR(15),
    salario DOUBLE,
    data_admissao DATE,
    data_demissao DATE,
    PRIMARY KEY(cod_funcionario)
);

CREATE TABLE fornecedor (
    cod_fornecedor INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100),
    cnpj VARCHAR(20),
    email VARCHAR(50),
    telefone VARCHAR(20),
    endereco VARCHAR(100),
    cidade VARCHAR(45),
    cep VARCHAR(10),
    uf CHAR(2),
    PRIMARY KEY(cod_fornecedor)
);

CREATE TABLE departamento (
    cod_departamento INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(50),
    descricao VARCHAR(100),
    PRIMARY KEY(cod_departamento)
);

CREATE TABLE produto (
    cod_produto INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(45),
    descricao VARCHAR(100),
    qtdeEstoque INT,
    precoVenda DOUBLE,
    qtdeMinimo INT,
    departamento INT,
    FOREIGN KEY (departamento) REFERENCES departamento(cod_departamento),
    PRIMARY KEY(cod_produto)
);

CREATE TABLE caixa (
    cod_caixa INT NOT NULL AUTO_INCREMENT,
    data_caixa DATE,
    horarioAbertura TIME,
    horarioFechamento TIME,
    valorAbertura DOUBLE,
    valorEntrada DOUBLE,
    valorSaida DOUBLE,
    saldo DOUBLE,
    status_caixa VARCHAR(15),
    PRIMARY KEY(cod_caixa)
);

CREATE TABLE venda (
    cod_venda INT NOT NULL AUTO_INCREMENT,
    formaPagamento VARCHAR(45),
    valorTotal DOUBLE,
    dataVenda DATE,
    funcionario INT,
    cliente INT,
    dataPagamento DATE,
    caixa INT,
    FOREIGN KEY (funcionario) REFERENCES funcionario(cod_funcionario),
    FOREIGN KEY (cliente) REFERENCES cliente(cod_cliente),
    FOREIGN KEY (caixa) REFERENCES caixa(cod_caixa),
    PRIMARY KEY(cod_venda)
);

CREATE TABLE itensVenda (
    cod_item INT,
    cod_venda INT,
    cod_produto INT,
    quantidade INT,
    preco DOUBLE,
    FOREIGN KEY (cod_venda) REFERENCES venda(cod_venda),
    FOREIGN KEY (cod_produto) REFERENCES produto(cod_produto),
    PRIMARY KEY(cod_venda, cod_produto)
);

CREATE TABLE sangria (
    cod_caixa INT,
    cod_sangria INT,
    motivo VARCHAR(50),
    valor DOUBLE,
    data_sangria DATE,
    tipo VARCHAR(45),
    FOREIGN KEY (cod_caixa) REFERENCES caixa(cod_caixa),
    PRIMARY KEY(cod_caixa, cod_sangria)
);

CREATE TABLE pagamento_venda (
    cod_venda INT,
    idparcela INT,
    vencimento DATE,
    valor DOUBLE,
    status_pag VARCHAR(45),
    cod_caixa INT,
    FOREIGN KEY(cod_caixa) REFERENCES caixa(cod_caixa),
    PRIMARY KEY(cod_venda, idparcela)
);

CREATE TABLE compra (
    cod_compra INT NOT NULL AUTO_INCREMENT,
    formaPagamento VARCHAR(45),
    valorTotal DOUBLE,
    dataCompra DATE,
    fornecedor INT,
    dataPagamento DATE,
    caixa INT,
    FOREIGN KEY (fornecedor) REFERENCES fornecedor(cod_fornecedor),
    FOREIGN KEY (caixa) REFERENCES caixa(cod_caixa),
    PRIMARY KEY(cod_compra)
);

CREATE TABLE itensCompra (
    cod_item INT,
    cod_compra INT,
    cod_produto INT,
    qtde INT,
    preco DOUBLE,
    FOREIGN KEY (cod_compra) REFERENCES compra(cod_compra),
    FOREIGN KEY (cod_produto) REFERENCES produto(cod_produto),
    PRIMARY KEY(cod_compra, cod_produto)
);

CREATE TABLE pagamento_compra (
    cod_compra INT,
    idparcela INT,
    vencimento DATE,
    valor DOUBLE,
    status_pag VARCHAR(45),
    caixa INT,
    FOREIGN KEY (cod_compra) REFERENCES compra(cod_compra),
    FOREIGN KEY (caixa) REFERENCES caixa(cod_caixa),
    PRIMARY KEY(cod_compra, idparcela)
);

INSERT INTO departamento (nome, descricao)
VALUES ("Segurança do trabalho", "Equipamentos para proteção"),
       ("Equipamentos Manuais", "Ferramentas que precisam do esforço físico"),
       ("Massas", "Ligamento para artefatos e construções"),
	   ('Materiais de Construção', 'Materiais básicos para construção civil'),
  ('Ferramentas', 'Ferramentas manuais e elétricas para construção e manutenção'),
  ('Tintas e Pintura', 'Tintas, pincéis e acessórios para pintura'),
  ('Elétrica', 'Produtos elétricos, fios e cabos'),
  ('Hidráulica', 'Materiais hidráulicos e encanamento'),
  ('Madeiras', 'Madeiras para construção e acabamento'),
  ('Pisos e Revestimentos', 'Pisos, azulejos e revestimentos diversos'),
  ('Iluminação', 'Luminárias e produtos de iluminação'),
  ('Ferragens', 'Ferragens e itens de fixação'),
  ('Decoração', 'Produtos de decoração para interiores e exteriores');
       
INSERT INTO produto (nome, descricao, qtdeEstoque, precoVenda, qtdeMinimo, departamento)
VALUES ("Luvas de borracha", "Luvas para proteção das mãos", 15, 10.00, 5, 1),
       ("Martelo", "...", 6, 22.00, 2, 2),
       ("Argamassa", "Massa com propriedades de endurecimento e aderência.", 20, 22.0, 5, 3),
       ('Capacete de Segurança', 'Capacete de segurança para proteção', 100, 25.00, 10, 1),
  ('Chave de Fenda', 'Chave de fenda de aço carbono', 200, 5.00, 20, 2),
  ('Massa Corrida', 'Massa corrida para acabamento de paredes', 50, 15.00, 5, 3),
  ('Tijolo Cerâmico', 'Tijolo de cerâmica de alta qualidade', 1000, 1.50, 500, 4),
  ('Furadeira Elétrica', 'Furadeira elétrica de alta potência', 30, 120.00, 10, 5),
  ('Tinta Látex', 'Tinta látex de diversas cores', 80, 30.00, 20, 6),
  ('Fio Elétrico 2.5mm', 'Rolo de fio elétrico 2.5mm', 150, 2.50, 50, 7),
  ('Tubo PVC 50mm', 'Tubo de PVC para encanamento', 60, 8.00, 10, 8),
  ('Madeira de Pinus', 'Prancha de madeira de pinus', 40, 12.00, 5, 9),
  ('Azulejo Cerâmico', 'Azulejo cerâmico para revestimento', 300, 3.00, 100, 10);
       
INSERT INTO fornecedor (nome, cnpj, email, telefone, endereco, cidade, cep, uf)
VALUES ("TijoloBom", "11.111.111/1111-11", "tijolo.bom@gmail.com", "(18)99777-7777", "xxx", "Presidente Epitácio", "19470-000", "SP"),
	   ("Madeira Nobre", "22.222.222/2222-22", "nobre.madeiraA@gmail.com", "(18)99877-6666", "xxx", "Presidente Epitácio", "19470-000", "SP"),
	   ("Argamassa Rápida", "33.333.333/3333-33", "argamassa.rapida@gmail.com", "(18)99677-2213", "xxx", "Presidente Prudente", "19000-000", "SP"),
       ("Construarte", "44.444.444/4444-44", "arteConstrucao@gmail.com", "(18)99876-4356", "xxx", "Presidente Prudente", "19000-000", "SP"),
       ('Fornecedor de Tijolos', '12.345.678/0001-00', 'tijolos@email.com', '(11)11234-5678', 'Rua dos Tijolos, 123', 'Cidade A', '12345-678', 'SP'),
  ('Fornecedor ABC Ltda', '12.345.678/0001-00', 'fornecedorabc@email.com', '(11)22233-4455', 'Rua dos Fornecedores, 123', 'Cidade A', '12345-678', 'SP'),
  ('Fornecedora XYZ S/A', '23.456.789/0001-11', 'fornecedora_xyz@email.com', '(22)33344-5566', 'Av. do Fornecimento, 456', 'Cidade B', '23456-789', 'RJ'),
  ('Indústria de Suprimentos Ltda', '34.567.890/0001-22', 'industriasuprimentos@email.com', '(33)44455-6677', 'Rua das Indústrias, 789', 'Cidade C', '34567-890', 'MG'),
  ('Comércio de Materiais de Construção Ltda', '45.678.901/0001-33', 'comerciomateriais@email.com', '(44)55566-7788', 'Rua dos Comerciantes, 101', 'Cidade D', '45678-901', 'RS'),
  ('Materiais de Construção e Cia', '56.789.012/0001-44', 'materiaisecia@email.com', '(55)66677-8899', 'Av. das Construções, 202', 'Cidade E', '56789-012', 'PR'),
  ('Fornecedor de Telhas e Revestimentos', '67.890.123/0001-55', 'fornecedor_telhas@email.com', '(66)77788-9900', 'Rua das Telhas, 303', 'Cidade F', '67890-123', 'BA'),
  ('Loja de Ferragens e Acessórios', '78.901.234/0001-66', 'lojaferragens@email.com', '(77)88899-0011', 'Av. das Ferragens, 404', 'Cidade G', '78901-234', 'CE'),
  ('Indústria de Pisos e Revestimentos', '89.012.345/0001-77', 'industriapisos@email.com', '(88)99900-1122', 'Rua dos Pisos, 505', 'Cidade H', '89012-345', 'PE'),
  ('Comércio de Luminárias e Iluminação', '90.123.456/0001-88', 'comercioluminarias@email.com', '(99)00011-2233', 'Rua das Luminárias, 606', 'Cidade I', '90123-456', 'PA'),
  ('Fornecedora de Tintas Especiais', '01.234.567/0001-99', 'fornecedora_tintasespeciais@email.com', '(00)11122-3344', 'Av. das Tintas Especiais, 707', 'Cidade J', '01234-567', 'GO');

INSERT INTO cliente (nome, email, cpf, telefone, endereco, cidade, cep, uf)
VALUES ( "Evellyn", "evellyn.silva@aluno.ifsp.edu.br", "111.111.111-11", "(18)99999-9999", "xxxx", "Presidente Epitácio", "19470-000", "SP"),
       ( "Samanta Mendes de Ortega", "saah.ortega@gmail.com", "784.493.650-90", "(12) 42484-8351", "xxxx", "Presidente Epitácio", "19470-000", "SP"),
       ( "Pâmela Guiomar Franco Padilha", "padilha.guiomar@gmail.com", "569.246.980-42", "(18) 12072-7127", "xxxx", "Presidente Epitácio", "19470-000", "SP"),
       ( 'Ana Lima', 'ana@email.com', '111.222.333-44', '(11) 1111-2222', 'Rua D, 456', 'Porto Alegre', '54321-987', 'RS'),
	   ('Lucas Fernandes', 'lucas@email.com', '444.555.666-77', '(21) 2222-3333', 'Av. E, 789', 'Salvador', '65432-109', 'BA'),
		('Fernanda Santos', 'fernanda@email.com', '777.888.999-00', '(31) 3333-4444', 'Rua F, 101', 'Belo Horizonte', '12345-678', 'MG'),
		('Ricardo Silva', 'ricardo@email.com', '000.111.222-33', '(11) 5555-6666', 'Rua G, 210', 'São Paulo', '98765-432', 'SP'),
		('Camila Alves', 'camila@email.com', '222.333.444-55', '(21) 7777-8888', 'Av. H, 303', 'Rio de Janeiro', '54321-098', 'RJ'),
		('Felipe Rodrigues', 'felipe@email.com', '555.666.777-88', '(31) 9999-0000', 'Rua I, 456', 'Belo Horizonte', '65432-109', 'MG'),
		('Laura Oliveira', 'laura@email.com', '888.999.000-11', '(11) 4444-5555', 'Av. J, 789', 'São Paulo', '54321-987', 'SP'),
		('Mariano Pereira', 'mariano@email.com', '111.222.333-44', '(21) 8888-9999', 'Rua K, 101', 'Rio de Janeiro', '12345-678', 'RJ'),
		('Carolina Santos', 'carolina@email.com', '444.555.666-77', '(31) 6666-7777', 'Av. L, 303', 'Belo Horizonte', '65432-109', 'MG'),
		('Gustavo Lima', 'gustavo@email.com', '777.888.999-00', '(11) 7777-8888', 'Rua M, 210', 'São Paulo', '98765-432', 'SP'),
		('Isabella Alves', 'isabella@email.com', '000.111.222-33', '(21) 9999-0000', 'Av. N, 456', 'Rio de Janeiro', '54321-098', 'RJ'),
		('Rafael Rodrigues', 'rafael@email.com', '222.333.444-55', '(31) 4444-5555', 'Rua O, 789', 'Belo Horizonte', '65432-109', 'MG'),
		('Sofia Oliveira', 'sofia@email.com', '555.666.777-88', '(11) 5555-6666', 'Av. P, 101', 'São Paulo', '54321-987', 'SP'),
		('Eduardo Pereira', 'eduardo@email.com', '888.999.000-11', '(21) 7777-8888', 'Rua Q, 303', 'Rio de Janeiro', '12345-678', 'RJ'),
		('Larissa Santos', 'larissa@email.com', '111.222.333-44', '(31) 6666-7777', 'Av. R, 210', 'Belo Horizonte', '65432-109', 'MG'),
		('Diego Lima', 'diego@email.com', '444.555.666-77', '(11) 4444-5555', 'Rua S, 456', 'São Paulo', '98765-432', 'SP');

INSERT INTO funcionario (nivel_acesso, nome, cpf, senha, salario, data_admissao, data_demissao)
VALUES ("Administrador", "EvellynADM", "111.111.111-11", "Dazai00@", 5500, "2020-04-20", null),
       ("Vendedor", "Evellyn", "222.222.222-22", "Dazai00@", 1000, "2020-04-20", null),
       ('Administrador', 'João Silva', '123.456.789-00', 'senha123', 5000.00, '2023-09-11', NULL),
  ('Vendedor', 'Maria Oliveira', '987.654.321-00', 'senha456', 4000.00, '2023-09-12', NULL),
  ('Administrador', 'Pedro Santos', '111.222.333-44', 'senha789', 4500.00, '2023-09-13', NULL),
  ('Vendedor', 'Ana Pereira', '555.666.777-88', 'senha101', 3500.00, '2023-09-14', NULL),
  ('Vendedor', 'Luiz Souza', '999.888.777-66', 'senha202', 3800.00, '2023-09-15', NULL),
  ('Administrador', 'Carla Rodrigues', '777.888.999-00', 'senha303', 5500.00, '2023-09-16', NULL),
  ('Vendedor', 'Mário Almeida', '444.555.666-77', 'senha404', 3200.00, '2023-09-17', NULL),
  ('Administrador', 'Sara Costa', '888.777.666-55', 'senha505', 6000.00, '2023-09-18', NULL),
  ('Vendedor', 'Ricardo Ferreira', '222.333.444-55', 'senha606', 3800.00, '2023-09-19', NULL),
  ('Administrador', 'Alice Lima', '777.888.999-11', 'senha707', 5700.00, '2023-09-20', NULL);

INSERT INTO caixa (cod_caixa, data_caixa, horarioAbertura, horarioFechamento,
 valorAbertura, valorEntrada, valorSaida, saldo, status_caixa)
VALUES (1, "2023-09-04", "16:36:00", null, 0, 0, 0, 0, "Aberto");
