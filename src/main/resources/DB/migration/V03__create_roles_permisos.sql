CREATE TABLE
    roles_permisos (
        rol_id BIGINT NOT NULL,
        permiso_id BIGINT NOT NULL,
        PRIMARY KEY (rol_id, permiso_id),
        CONSTRAINT fk_rol FOREIGN KEY (rol_id) REFERENCES roles (id),
        CONSTRAINT fk_permiso FOREIGN KEY (permiso_id) REFERENCES permisos (id)
    );