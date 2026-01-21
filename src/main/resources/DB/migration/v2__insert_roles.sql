INSERT INTO
    roles (rol_nombre, descripcion, created_at, updated_at)
VALUES
    (
        'ADMIN',
        'Administrador con todos los permisos',
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP
    ),
    (
        'USUARIO',
        'Usuario inscrito',
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP
    );