
-- DROP DATABASE churninsight_db;
-- CREATE DATABASE churninsight_db;
CREATE TABLE roles (
        id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
        rol_nombre VARCHAR(50) NOT NULL UNIQUE,
        descripcion TEXT NULL,
        created_at TIMESTAMP NOT NULL,
        updated_at TIMESTAMP NOT NULL,
        deleted_at TIMESTAMP NULL
    );

-- Datos de fictisios para la tabla roles
INSERT INTO
    roles (rol_nombre, descripcion, created_at, updated_at)
VALUES
    ('ADMIN', 'Administrador con todos los permisos', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('USUARIO', 'Usuario inscrito', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

------------------------------------------------------------------------
CREATE TABLE permisos (
        id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
        permiso_nombre VARCHAR(50) NOT NULL UNIQUE,
        descripcion TEXT NULL,
        created_at TIMESTAMP NOT NULL,
        updated_at TIMESTAMP NOT NULL,
        deleted_at TIMESTAMP NULL
    );

-- Datos de fictisios para la tabla roles
INSERT INTO
    permisos (permiso_nombre, descripcion, created_at, updated_at)
VALUES
    ('CREATE', 'Permiso para crear', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('READ', 'Permiso para leer', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('UPDATE', 'Permiso para actualizar', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('DELETE', 'Permiso para eliminar', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('MANAGE_USERS', 'Permiso para gestionar usuarios', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('MANAGE_ROLES', 'Permiso para gestionar roles', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('MANAGE_PERMISSIONS', 'Permiso para gestionar permisos', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('VIEW_REPORTS', 'Permiso para ver reportes', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('ACCESS_ADMIN_PANEL', 'Permiso para acceder al panel de administración', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

------------------------------------------------------------------------
CREATE TABLE roles_permisos (
        id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
        rol_id BIGINT NOT NULL ,
        permiso_id BIGINT NOT NULL ,
        created_at TIMESTAMP NOT NULL,
        updated_at TIMESTAMP NOT NULL,
        deleted_at TIMESTAMP NULL,
        FOREIGN KEY (rol_id) REFERENCES roles (id),
        FOREIGN KEY (permiso_id) REFERENCES permisos (id)
    );

-- Datos ficticios para la tabla roles_permisos
INSERT INTO
    roles_permisos (rol_id, permiso_id, created_at, updated_at)
VALUES
    (1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- ADMIN tiene permiso CREATE
    (1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- ADMIN tiene permiso READ
    (1, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- ADMIN tiene permiso UPDATE
    (1, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- ADMIN tiene permiso DELETE
    (1, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- ADMIN tiene permiso MANAGE_USERS
    (1, 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- ADMIN tiene permiso MANAGE_ROLES
    (1, 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- ADMIN tiene permiso MANAGE_PERMISSIONS
    (1, 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- ADMIN tiene permiso VIEW_REPORTS
    (1, 9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- ADMIN tiene permiso ACCESS_ADMIN_PANEL
    (2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- Usuario tiene permiso CREATE
    (2, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- Usuario tiene permiso READ
    (2, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- Usuario tiene permiso UPDATE
    (2, 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- Usuario tiene permiso DELETE
    (2, 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP); -- Usuario tiene permiso VIEW_REPORTS

------------------------------------------------------------------------

CREATE TABLE usuarios (
	id VARCHAR(10) PRIMARY KEY UNIQUE,
	nombre VARCHAR(50) NOT NULL,
	p_apellido VARCHAR(25) NOT NULL,
	s_apellido VARCHAR(25),
	email VARCHAR(100) NOT NULL UNIQUE,
	password VARCHAR(255) NOT NULL,
	telefono VARCHAR(10) NULL,
	fecha_nacimiento DATE NOT NULL,
	genero VARCHAR(15) NOT NULL,
	is_enabled BOOLEAN NOT NULL,
	account_no_expired BOOLEAN NOT NULL,
	account_no_locked BOOLEAN NOT NULL,
	credential_no_expired BOOLEAN NOT NULL,
	created_at TIMESTAMP NOT NULL,
	updated_at TIMESTAMP NOT NULL,
	deleted_at TIMESTAMP NULL );

INSERT INTO usuarios (
    id,
    nombre,
    p_apellido,
    s_apellido,
    email,
    password,
    telefono,
    fecha_nacimiento,
    genero,
    is_enabled,
    account_no_expired,
    account_no_locked,
    credential_no_expired,
    created_at,
    updated_at
) VALUES
(
    'HNDD-OPKSD',
    'Alexis',
    'Garcia',
    'Platas',
    'alex@example.com',
    '1234567890',
    '2821234565',
    '1990-05-15',
    'MASCULINO',
    FALSE,
    TRUE,
    TRUE,
    TRUE,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
),
(
    'HNDD-OPKPL',
    'Jazmin',
    'Hernandez',
    'Mendez',
    'jaz@example.com',
    '1234567890',
    '2821234565',
    '1998-05-15',
    'FEMENINO',
    TRUE,
    TRUE,
    TRUE,
    TRUE,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
),
(
    'VNDD-SPKSD',
    'Pedro',
    'Hernandez',
    'Mendez',
    'pedro@example.com',
    '1234567890',
    '2821234565',
    '2010-07-26',
    'MASCULINO',
    TRUE,
    TRUE,
    TRUE,
    TRUE,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
),
(
    'SNDH-BPKAD',
    'Juan',
    'Hernandez',
    'Mendez',
    'juan@example.com',
    '1234567890',
    '2821234565',
    '2002-01-05',
    'MASCULINO',
    TRUE,
    TRUE,
    TRUE,
    TRUE,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
),
(
    'SVRD-TPKSD',
    'Anyi',
    'Hernandez',
    'Mendez',
    'anyi@example.com',
    '1234567890',
    '2821234565',
    '2005-03-20',
    'FEMENINO',
    TRUE,
    TRUE,
    TRUE,
    TRUE,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
),
(
    'CNVD-RPJSD',
    'Jackelin',
    'Hernandez',
    'Mendez',
    'jackelin@example.com',
    '1234567890',
    '2821234565',
    '1995-11-11',
    'FEMENINO',
    TRUE,
    TRUE,
    TRUE,
    TRUE,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

------------------------------------------------------------------------
CREATE TABLE usuarios_roles (
        id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
        usuario_id VARCHAR(10) NOT NULL,
        rol_id BIGINT NOT NULL ,
        created_at TIMESTAMP NOT NULL,
        updated_at TIMESTAMP NOT NULL,
        deleted_at TIMESTAMP NULL,
        FOREIGN KEY (usuario_id) REFERENCES usuarios (id),
        FOREIGN KEY (rol_id) REFERENCES roles (id)
    );

INSERT INTO
    usuarios_roles (usuario_id, rol_id, created_at, updated_at)
VALUES
    ('HNDD-OPKSD', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('HNDD-OPKPL', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('VNDD-SPKSD', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('SNDH-BPKAD', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('SVRD-TPKSD', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('CNVD-RPJSD', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS servicio (
    id VARCHAR(10) PRIMARY KEY UNIQUE,
    id_usuario VARCHAR(10) NOT NULL,
    servicio_telefono BOOLEAN NOT NULL,
    lineas_multiples VARCHAR(30),
    servicio_internet BOOLEAN NOT NULL,
    seguridad_en_linea VARCHAR(30),
    respaldo_en_linea VARCHAR(30),
    proteccion_dispositivo VARCHAR(30),
    soporte_tecnico VARCHAR(30),
    streaming_tv VARCHAR(30),
    streaming_peliculas VARCHAR(30),
    tipo_contrato VARCHAR(20),
    facturacion_electronica BOOLEAN NOT NULL,
    metodo_pago VARCHAR(40),
    cargo_mensual NUMERIC(10,2) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP NULL,
	FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);

INSERT INTO servicio (
    id,
    id_usuario,
    servicio_telefono,
    lineas_multiples,
    servicio_internet,
    seguridad_en_linea,
    respaldo_en_linea,
    proteccion_dispositivo,
    soporte_tecnico,
    streaming_tv,
    streaming_peliculas,
    tipo_contrato,
    facturacion_electronica,
    metodo_pago,
    cargo_mensual,
    created_at,
    updated_at,
    deleted_at
) VALUES
-- Alexis
(
    'SRV-001', 'HNDD-OPKSD', true, 'Yes',
    true, 'Yes', 'Yes',
    'Yes', 'Yes', 'Yes',
    'Yes', 'Two year', true,
    'Credit card (automatic)', 95.50,
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL
),

-- Jazmin
(
    'SRV-002', 'HNDD-OPKPL', true, 'No',
    true, 'No', 'No',
    'No', 'No', 'No',
    'No', 'Month-to-month', false,
    'Electronic check', 55.00,
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL
),

-- Pedro
(
    'SRV-003', 'VNDD-SPKSD', true, 'No phone service',
    false, 'No internet service', 'No internet service',
    'No internet service', 'No internet service', 'No internet service',
    'No internet service', 'One year', true,
    'Mailed check', 25.99,
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL
),

-- Juan
(
    'SRV-004', 'SNDH-BPKAD', false, 'No phone service',
    true, 'Yes', 'Yes',
    'Yes', 'No', 'Yes',
    'Yes', 'Month-to-month', true,
    'Bank transfer (automatic)', 75.25,
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL
),

-- Anyi (soft delete)
(
    'SRV-005', 'SVRD-TPKSD', true, 'Yes',
    true, 'Yes', 'Yes',
    'Yes', 'Yes', 'Yes',
    'Yes', 'Two year', false,
    'Credit card (automatic)', 110.00,
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
);

------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS prediccion (
    id BIGSERIAL PRIMARY KEY,
    id_usuario VARCHAR(10) NOT NULL UNIQUE,
    churn BOOLEAN NOT NULL,
    prevision VARCHAR(20),
    probabilidad DOUBLE PRECISION,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL,
    CONSTRAINT fk_prediccion_usuario
        FOREIGN KEY (id_usuario) REFERENCES usuarios (id)
);

INSERT INTO prediccion (
    id_usuario, churn, prevision, probabilidad
) VALUES
-- Usuario con alta probabilidad de churn
('HNDD-OPKSD', true, 'Yes', 0.92),
-- Usuario estable
('HNDD-OPKPL', false, 'No', 0.08),
-- Usuario en riesgo medio
('VNDD-SPKSD', true, 'Yes', 0.65),
-- Predicción incierta
('SNDH-BPKAD', false, 'Unknown', 0.50),
-- Usuario casi seguro que permanece
('SVRD-TPKSD', false, 'No', 0.03);

------------------------------------------------------------------------

CREATE TABLE oferta (
        id BIGSERIAL PRIMARY KEY UNIQUE,
        nombre VARCHAR(100) NOT NULL,
        descripcion TEXT,
        descuento_porcentaje NUMERIC(5,2),
        descuento_monto NUMERIC(10,2),
        duracion_meses INTEGER,
        aplica_churn BOOLEAN,
        activa BOOLEAN,
        created_at TIMESTAMP NOT NULL,
        updated_at TIMESTAMP NOT NULL ,
        deleted_at TIMESTAMP NULL
);

INSERT INTO oferta (
    nombre, 
    descripcion,
    descuento_porcentaje,
    descuento_monto,
    duracion_meses,
    aplica_churn,
    activa,
    created_at,
    updated_at
) VALUES
-- Oferta de bienvenida
(
    'Bienvenida Fibra',
    '10% de descuento por contratar el servicio',
    10.00,
    NULL,
    6,
    false,
    true,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
),

-- Oferta de retención (churn)
(
    'Retención Premium',
    '20% de descuento para clientes con riesgo de cancelación',
    20.00,
    NULL,
    12,
    true,
    true,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
),

-- Oferta por pago anual
(
    'Pago Anual',
    'Ahorra pagando el servicio por un año completo',
    15.00,
    NULL,
    12,
    false,
    true,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
),

-- Descuento fijo
(
    'Descuento $100',
    'Descuento directo en el cargo mensual',
    NULL,
    100.00,
    3,
    false,
    true,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

