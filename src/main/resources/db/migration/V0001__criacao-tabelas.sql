CREATE TABLE tb_estado(
  id serial PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  sigla VARCHAR(2) NOT NULL
);

CREATE TABLE tb_banco(
  id serial PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  agencia VARCHAR(20) NOT NULL,
  logradouro VARCHAR(150) NOT NULL,
  bairro VARCHAR(60),
  cidade VARCHAR(50) NOT NULL,
  cep VARCHAR(20),
  estado_id integer NOT NULL,
  data_cadastro timestamp NOT NULL,
  data_atualizacao timestamp NOT NULL,
  CONSTRAINT fk_banco_estado FOREIGN KEY (estado_id) REFERENCES tb_estado (id)
);