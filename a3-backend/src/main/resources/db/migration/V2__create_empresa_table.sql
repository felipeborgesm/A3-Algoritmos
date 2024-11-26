CREATE TABLE empresa (
     Id SERIAL PRIMARY KEY,
     Nome TEXT NOT NULL,
     CNPJ TEXT NOT NULL UNIQUE,
     Data_criacao TIMESTAMP NOT NULL,
     Data_atualizacao TIMESTAMP,
     Administrador_id INTEGER,
     CONSTRAINT fk_administrador FOREIGN KEY (Administrador_id) REFERENCES usuario(Id)
);
