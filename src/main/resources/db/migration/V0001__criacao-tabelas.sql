CREATE SEQUENCE sq_estado START WITH 1 NOCACHE;

CREATE SEQUENCE sq_banco START WITH 1 NOCACHE;

CREATE TABLE tb_estado (
    id             		NUMBER(10) DEFAULT sq_estado.NEXTVAL NOT NULL,
    nome   	       		VARCHAR2(100) NOT NULL,
    sigla          		VARCHAR2(2) NOT NULL
);

ALTER TABLE tb_estado ADD CONSTRAINT pk_estado PRIMARY KEY ( id );

CREATE TABLE tb_banco (
    id        	   		NUMBER(10) DEFAULT sq_banco.NEXTVAL NOT NULL,
    nome           		VARCHAR2(100) NOT NULL,
    agencia   	   		VARCHAR2(20) NOT NULL,
    logradouro     		VARCHAR2(150) NOT NULL,
    bairro         		VARCHAR2(60),
    cidade   	   		VARCHAR2(50) NOT NULL,
    cep        	   		VARCHAR2(20),
    estado_id      		NUMBER(10) NOT NULL,
    data_cadastro   	DATE NOT NULL,
    data_atualizacao    DATE NOT NULL
);

ALTER TABLE tb_banco ADD CONSTRAINT pk_banco PRIMARY KEY ( id );

ALTER TABLE tb_banco
    ADD CONSTRAINT fk_banco_estado FOREIGN KEY ( estado_id )
        REFERENCES tb_estado ( id );