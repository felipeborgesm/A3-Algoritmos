CREATE TABLE pedido (
    Id SERIAL PRIMARY KEY,
    Produto_id INTEGER NOT NULL,
    Quantidade INTEGER NOT NULL,
    Valor_Total NUMERIC NOT NULL,
    Is_pedido_Finalizado BOOLEAN NOT NULL,
    Data_criacao TIMESTAMP NOT NULL,
    Data_atualizacao TIMESTAMP,
    CONSTRAINT fk_pedido_produto FOREIGN KEY (Produto_id) REFERENCES produto(Id)
);