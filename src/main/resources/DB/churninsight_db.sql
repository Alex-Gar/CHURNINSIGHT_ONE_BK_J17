--CREATE DATABASE churninsight_db;
CREATE TABLE
    IF NOT EXISTS roles (
        id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
        rol_nombre VARCHAR(50) NOT NULL UNIQUE,
        descripcion TEXT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        deleted_at TIMESTAMP NULL
    );

-- Datos de fictisios para la tabla roles
INSERT INTO
    roles (rol_nombre, descripcion)
VALUES
    ('ADMIN', 'Administrador con todos los permisos'),
    ('USUARIO', 'Usuario inscrito');

------------------------------------------------------------------------
CREATE TABLE
    IF NOT EXISTS permisos (
        id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
        permiso_nombre VARCHAR(50) NOT NULL UNIQUE,
        descripcion TEXT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        deleted_at TIMESTAMP NULL
    );

-- Datos de fictisios para la tabla roles
INSERT INTO
    permisos (permiso_nombre, descripcion)
VALUES
    ('CREATE', 'Permiso para crear'),
    ('READ', 'Permiso para leer'),
    ('UPDATE', 'Permiso para actualizar'),
    ('DELETE', 'Permiso para eliminar'),
    ('MANAGE_USERS', 'Permiso para gestionar usuarios'),
    ('MANAGE_ROLES', 'Permiso para gestionar roles'),
    (
        'MANAGE_PERMISSIONS',
        'Permiso para gestionar permisos'
    ),
    ('VIEW_REPORTS', 'Permiso para ver reportes'),
    (
        'ACCESS_ADMIN_PANEL',
        'Permiso para acceder al panel de administración'
    );

------------------------------------------------------------------------
CREATE TABLE
    IF NOT EXISTS roles_permisos (
        id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
        rol_id BIGINT NOT NULL,
        permiso_id BIGINT NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        deleted_at TIMESTAMP NULL,
        FOREIGN KEY (rol_id) REFERENCES roles (id),
        FOREIGN KEY (permiso_id) REFERENCES permisos (id)
    );

-- Datos ficticios para la tabla roles_permisos
INSERT INTO
    roles_permisos (rol_id, permiso_id)
VALUES
    (1, 1), -- ADMIN tiene permiso CREATE
    (1, 2), -- ADMIN tiene permiso READ
    (1, 3), -- ADMIN tiene permiso UPDATE
    (1, 4), -- ADMIN tiene permiso DELETE
    (1, 5), -- ADMIN tiene permiso MANAGE_USERS
    (1, 6), -- ADMIN tiene permiso MANAGE_ROLES
    (1, 7), -- ADMIN tiene permiso MANAGE_PERMISSIONS
    (1, 8), -- ADMIN tiene permiso VIEW_REPORTS
    (1, 9), -- ADMIN tiene permiso ACCESS_ADMIN_PANEL
    (2, 2), -- Usuario tiene permiso CREATE
    (2, 3), -- Usuario tiene permiso READ
    (2, 5), -- Usuario tiene permiso UPDATE
    (2, 6), -- Usuario tiene permiso DELETE
    (2, 8);

-- Usuario tiene permiso VIEW_REPORTS
------------------------------------------------------------------------
CREATE TABLE
    IF NOT EXISTS usuarios (
        id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
        nombre VARCHAR(50) NOT NULL,
        p_apellido VARCHAR(25) NOT NULL,
        s_apellido VARCHAR(25),
        email VARCHAR(100) NOT NULL UNIQUE,
        password VARCHAR(255) NOT NULL,
        telefono VARCHAR(10) NULL,
        is_enabled BOOLEAN NOT NULL,
        account_no_expired BOOLEAN NOT NULL,
        account_no_locked BOOLEAN NOT NULL,
        credential_no_expired BOOLEAN NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        deleted_at TIMESTAMP NULL
    );

INSERT INTO
    usuarios (
        nombre,
        p_apellido,
        s_apellido,
        email,
        password,
        telefono,
        is_enabled,
        account_no_expired,
        account_no_locked,
        credential_no_expired,
        created_at,
        updated_at
    )
VALUES
    (
        'Alexis',
        'Garcia',
        'Platas',
        'aleX@example.com',
        '1234567890',
        '2821234565',
        FALSE,
        TRUE,
        TRUE,
        TRUE,
        '2020-04-10 10:00:00',
        '2020-04-10 10:00:00'
    ),
    (
        'jazmin',
        'Hernandez',
        'Mendez',
        'jaz@example.com',
        '1234567890',
        '2821234565',
        TRUE,
        TRUE,
        TRUE,
        TRUE,
        '2020-04-10 10:00:00',
        '2020-04-10 10:00:00'
    ),
    (
        'Pedro',
        'Hernandez',
        'Mendez',
        'pedro@example.com',
        '1234567890',
        '2821234565',
        TRUE,
        TRUE,
        TRUE,
        TRUE,
        '2020-04-10 10:00:00',
        '2020-04-10 10:00:00'
    ),
    (
        'Juan',
        'Hernandez',
        'Mendez',
        'juan@example.com',
        '1234567890',
        '2821234565',
        TRUE,
        TRUE,
        TRUE,
        TRUE,
        '2020-04-10 10:00:00',
        '2020-04-10 10:00:00'
    ),
    (
        'Anyi',
        'Hernandez',
        'Mendez',
        'anyi@example.com',
        '1234567890',
        '2821234565',
        TRUE,
        TRUE,
        TRUE,
        TRUE,
        '2020-04-10 10:00:00',
        '2020-04-10 10:00:00'
    ),
    (
        'Jackelin',
        'Hernandez',
        'Mendez',
        'jackelin@example.com',
        '1234567890',
        '2821234565',
        TRUE,
        TRUE,
        TRUE,
        TRUE,
        '2020-04-10 10:00:00',
        '2020-04-10 10:00:00'
    );

------------------------------------------------------------------------
CREATE TABLE
    IF NOT EXISTS usuarios_roles (
        id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
        usuario_id BIGINT NOT NULL,
        rol_id BIGINT NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        deleted_at TIMESTAMP NULL,
        FOREIGN KEY (usuario_id) REFERENCES usuarios (id),

        FOREIGN KEY (rol_id) REFERENCES roles (id)
    );

INSERT INTO
    usuarios_roles (usuario_id, rol_id, created_at)
VALUES
    (1, 1, '2020-04-10 10:00:00'),
    (2, 2, '2020-04-10 10:00:00'),
    (3, 1, '2020-04-10 10:00:00'),
    (4, 2, '2020-04-10 10:00:00'),
    (5, 1, '2020-04-10 10:00:00'),
    (6, 2, '2020-04-10 10:00:00');

------------------------------------------------------------------------