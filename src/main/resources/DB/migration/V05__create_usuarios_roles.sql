CREATE TABLE
    usuarios_roles (
        usuario_id VARCHAR(10) NOT NULL,
        rol_id BIGINT NOT NULL,
        PRIMARY KEY (usuario_id, rol_id),
        CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios (id),
        CONSTRAINT fk_rol FOREIGN KEY (rol_id) REFERENCES roles (id)
    );