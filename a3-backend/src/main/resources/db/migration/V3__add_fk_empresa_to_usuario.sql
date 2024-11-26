ALTER TABLE usuario
    ADD CONSTRAINT fk_empresa
        FOREIGN KEY (Empresa_id) REFERENCES empresa(id);