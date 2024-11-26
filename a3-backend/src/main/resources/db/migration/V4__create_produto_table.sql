CREATE TABLE produto (
     Id SERIAL PRIMARY KEY,
     Codigo INTEGER NOT NULL UNIQUE,
     Nome TEXT NOT NULL,
     Quantidade INTEGER NOT NULL DEFAULT 0,
     Is_produto_em_estoque BOOLEAN NOT NULL,
     Is_perecivel BOOLEAN NOT NULL,
     Valor_unitario NUMERIC NOT NULL,
     Data_criacao TIMESTAMP NOT NULL,
     Data_atualizacao TIMESTAMP,
     Empresa_id INTEGER NOT NULL,
     CONSTRAINT fk_produto_empresa FOREIGN KEY (Empresa_id) REFERENCES empresa(Id)
);