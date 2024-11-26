CREATE TABLE usuario (
     Id SERIAL PRIMARY KEY,
     Nome TEXT NOT NULL,
     Login TEXT NOT NULL UNIQUE,
     Senha TEXT NOT NULL,
     Data_criacao TIMESTAMP NOT NULL,
     Data_atualizacao TIMESTAMP,
     Empresa_id INTEGER
);