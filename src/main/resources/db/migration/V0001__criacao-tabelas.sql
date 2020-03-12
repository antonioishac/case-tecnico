CREATE TABLE `tb_estado` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `sigla` VARCHAR(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `tb_banco` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `agencia` VARCHAR(20) NOT NULL,
  `logradouro` VARCHAR(150) NOT NULL,
  `bairro` VARCHAR(60),
  `cidade` VARCHAR(50) NOT NULL,
  `cep` VARCHAR(20),
  `estado_id` BIGINT(20) NOT NULL,
  `data_cadastro` DATETIME NOT NULL,
  `data_atualizacao` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_banco_estado` (`estado_id`),
  CONSTRAINT `fk_banco_estado` FOREIGN KEY (`estado_id`) REFERENCES `tb_estado` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;