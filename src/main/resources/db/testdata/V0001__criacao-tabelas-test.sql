CREATE TABLE `tb_estado` (
  `id` BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `sigla` VARCHAR(2) NOT NULL
);

CREATE TABLE `tb_banco` (
  `id` BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `agencia` VARCHAR(20) NOT NULL,
  `logradouro` VARCHAR(150) NOT NULL,
  `bairro` VARCHAR(60),
  `cidade` VARCHAR(50) NOT NULL,
  `cep` VARCHAR(20),
  `estado_id` BIGINT(20) NOT NULL,
  `data_cadastro` DATETIME NOT NULL,
  `data_atualizacao` DATETIME NOT NULL,
  FOREIGN KEY (`estado_id`) REFERENCES `tb_estado` (`id`)
);

INSERT INTO tb_estado(nome, sigla) VALUES ('São Paulo', 'SP');
INSERT INTO tb_estado(nome, sigla) VALUES ('Rio de Janeiro', 'RJ');

INSERT INTO tb_banco(nome, agencia, logradouro, bairro, cidade, cep, estado_id, data_cadastro, data_atualizacao) VALUES ('Agencia Teste 1', '123-1', 'Rua teste 1', 'Bairro teste 1', 'Cidade 1', '15585980', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO tb_banco(nome, agencia, logradouro, bairro, cidade, cep, estado_id, data_cadastro, data_atualizacao) VALUES ('Agencia Teste 2', '321-2', 'Rua teste 2', 'Bairro teste 2', 'Cidade 2', '25482350', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
