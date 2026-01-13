SELECT
   pid,
   usename,
   application_name
FROM
   pg_stat_activity
WHERE
   datname = 'churninsight_db';

SELECT
   pg_terminate_backend (pid)
FROM
   pg_stat_activity
WHERE
   datname = 'churninsight_db';

DROP SCHEMA public CASCADE;

CREATE SCHEMA public;

-- DROP DATABASE churninsight_db;
-- CREATE DATABASE churninsight_db;
CREATE TABLE
   roles (
      id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
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

------------------------------------------------------------------------
CREATE TABLE
   permisos (
      id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
      permiso_nombre VARCHAR(50) NOT NULL UNIQUE,
      descripcion TEXT NULL,
      created_at TIMESTAMP NOT NULL,
      updated_at TIMESTAMP NOT NULL,
      deleted_at TIMESTAMP NULL
   );

-- Datos de fictisios para la tabla roles
INSERT INTO
   permisos (
      permiso_nombre,
      descripcion,
      created_at,
      updated_at
   )
VALUES
   (
      'CREATE',
      'Permiso para crear',
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP
   ),
   (
      'READ',
      'Permiso para leer',
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP
   ),
   (
      'UPDATE',
      'Permiso para actualizar',
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP
   ),
   (
      'DELETE',
      'Permiso para eliminar',
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP
   ),
   (
      'MANAGE_USERS',
      'Permiso para gestionar usuarios',
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP
   ),
   (
      'MANAGE_ROLES',
      'Permiso para gestionar roles',
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP
   ),
   (
      'MANAGE_PERMISSIONS',
      'Permiso para gestionar permisos',
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP
   ),
   (
      'VIEW_REPORTS',
      'Permiso para ver reportes',
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP
   ),
   (
      'ACCESS_ADMIN_PANEL',
      'Permiso para acceder al panel de administración',
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP
   );

------------------------------------------------------------------------
CREATE TABLE
   roles_permisos (
      rol_id BIGINT NOT NULL,
      permiso_id BIGINT NOT NULL,
      PRIMARY KEY (rol_id, permiso_id),
      CONSTRAINT fk_rol FOREIGN KEY (rol_id) REFERENCES roles (id),
      CONSTRAINT fk_permiso FOREIGN KEY (permiso_id) REFERENCES permisos (id)
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
-----------------------------------------------
CREATE TABLE
   usuarios (
      id VARCHAR(10) PRIMARY KEY UNIQUE,
      nombre VARCHAR(50) NOT NULL,
      p_apellido VARCHAR(25) NOT NULL,
      s_apellido VARCHAR(25),
      email VARCHAR(100) NOT NULL UNIQUE,
      password VARCHAR(255) NOT NULL,
      telefono VARCHAR(10) NULL,
      fecha_nacimiento DATE NOT NULL,
      genero VARCHAR(15) NOT NULL,
      tiene_conyuge BOOLEAN NOT NULL,
      tiene_dependientes BOOLEAN NOT NULL,
      is_enabled BOOLEAN NOT NULL,
      account_no_expired BOOLEAN NOT NULL,
      account_no_locked BOOLEAN NOT NULL,
      credential_no_expired BOOLEAN NOT NULL,
      created_at TIMESTAMP NOT NULL,
      updated_at TIMESTAMP NOT NULL,
      deleted_at TIMESTAMP NULL
   );

INSERT INTO
   usuarios (
      id,
      nombre,
      p_apellido,
      s_apellido,
      email,
      password,
      telefono,
      fecha_nacimiento,
      genero,
      tiene_conyuge,
      tiene_dependientes,
      is_enabled,
      account_no_expired,
      account_no_locked,
      credential_no_expired,
      created_at,
      updated_at,
      deleted_at
   )
VALUES
   (
      'USR000001',
      'Juan',
      'Pérez',
      'López',
      'juan.perez@mail.com',
      'hash123',
      '5512345678',
      '1990-05-12',
      'Masculino',
      TRUE,
      TRUE,
      TRUE,
      TRUE,
      TRUE,
      TRUE,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      NULL
   ),
   (
      'USR000002',
      'María',
      'García',
      'Hernández',
      'maria.garcia@mail.com',
      'hash123',
      '5512345679',
      '1985-09-20',
      'Femenino',
      TRUE,
      TRUE,
      TRUE,
      TRUE,
      TRUE,
      TRUE,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      NULL
   ),
   (
      'USR000003',
      'Carlos',
      'Ramírez',
      NULL,
      'carlos.ramirez@mail.com',
      'hash123',
      '5512345680',
      '2003-02-10',
      'Masculino',
      FALSE,
      FALSE,
      TRUE,
      TRUE,
      TRUE,
      TRUE,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      '2025-11-10 14:30:00'
   ),
   (
      'USR000004',
      'Ana',
      'Martínez',
      'Cruz',
      'ana.martinez@mail.com',
      'hash123',
      '5512345681',
      '1998-11-03',
      'Femenino',
      FALSE,
      FALSE,
      TRUE,
      TRUE,
      TRUE,
      TRUE,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      NULL
   ),
   (
      'USR000005',
      'Luis',
      'Santos',
      'Morales',
      'luis.santos@mail.com',
      'hash123',
      '5512345682',
      '1975-07-28',
      'Masculino',
      TRUE,
      TRUE,
      TRUE,
      TRUE,
      TRUE,
      TRUE,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      '2025-10-01 09:15:00'
   ),
   (
      'USR000006',
      'Laura',
      'Flores',
      'Jiménez',
      'laura.flores@mail.com',
      'hash123',
      '5512345683',
      '2000-01-15',
      'Femenino',
      FALSE,
      FALSE,
      TRUE,
      TRUE,
      TRUE,
      TRUE,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      NULL
   ),
   (
      'USR000007',
      'Pedro',
      'Castillo',
      'Vega',
      'pedro.castillo@mail.com',
      'hash123',
      '5512345684',
      '1988-04-09',
      'Masculino',
      TRUE,
      FALSE,
      TRUE,
      TRUE,
      TRUE,
      TRUE,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      '2024-12-20 18:45:00'
   ),
   (
      'USR000008',
      'Sofía',
      'Navarro',
      'Ruiz',
      'sofia.navarro@mail.com',
      'hash123',
      '5512345685',
      '1995-12-18',
      'Femenino',
      TRUE,
      FALSE,
      TRUE,
      TRUE,
      TRUE,
      TRUE,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      NULL
   ),
   (
      'USR000009',
      'Diego',
      'Ortega',
      NULL,
      'diego.ortega@mail.com',
      'hash123',
      '5512345686',
      '2006-06-01',
      'Masculino',
      FALSE,
      FALSE,
      TRUE,
      TRUE,
      TRUE,
      TRUE,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      '2025-12-01 12:00:00'
   ),
   (
      'USR000010',
      'Valeria',
      'Mendoza',
      'Salinas',
      'valeria.mendoza@mail.com',
      'hash123',
      '5512345687',
      '1992-03-25',
      'Femenino',
      TRUE,
      TRUE,
      TRUE,
      TRUE,
      TRUE,
      TRUE,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      NULL
   );

------------------------------------------------------------------------
CREATE TABLE
   usuarios_roles (
      usuario_id VARCHAR(10) NOT NULL,
      rol_id BIGINT NOT NULL,
      FOREIGN KEY (usuario_id) REFERENCES usuarios (id),
      FOREIGN KEY (rol_id) REFERENCES roles (id) PRIMARY KEY (usuario_id, rol_id),
      CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios (id),
      CONSTRAINT fk_rol FOREIGN KEY (rol_id) REFERENCES roles (id),
   );

INSERT INTO
   usuarios_roles (usuario_id, rol_id,)
VALUES
   -- Usuarios activos
   ('USR000001', 1,), -- Admin
   ('USR000002', 2,),
   ('USR000003', 2,),
   ('USR000004', 2,),
   -- Usuario con doble rol
   ('USR000005', 1,),
   ('USR000005', 2,),
   -- Roles dados de baja (soft delete)
   ('USR000006', 2,),
   ('USR000007', 2,),
   -- Usuario con rol histórico
   ('USR000008', 1,),
   ('USR000009', 2,);

------------------------------------------------------------------------
CREATE TABLE
   IF NOT EXISTS planes (
      id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
      servicio_telefono BOOLEAN NOT NULL,
      servicio_internet BOOLEAN NOT NULL,
      seguridad_en_linea BOOLEAN NOT NULL,
      respaldo_en_linea BOOLEAN NOT NULL,
      proteccion_dispositivo BOOLEAN NOT NULL,
      soporte_tecnico BOOLEAN NOT NULL,
      streaming_tv BOOLEAN NOT NULL,
      streaming_peliculas BOOLEAN NOT NULL,
      cargo_mensual DECIMAL NOT NULL,
      created_at TIMESTAMP NOT NULL,
      updated_at TIMESTAMP NOT NULL,
      deleted_at TIMESTAMP NULL
   );

INSERT INTO
   planes (
      servicio_telefono,
      servicio_internet,
      seguridad_en_linea,
      respaldo_en_linea,
      proteccion_dispositivo,
      soporte_tecnico,
      streaming_tv,
      streaming_peliculas,
      cargo_mensual,
      created_at,
      updated_at,
      deleted_at
   )
VALUES
   -- Plan Básico Internet
   (
      false,
      true,
      false,
      false,
      false,
      false,
      false,
      false,
      399.00,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      NULL
   ),
   -- Plan Telefonía Básica
   (
      true,
      false,
      false,
      false,
      false,
      false,
      false,
      false,
      299.00,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      NULL
   ),
   -- Plan Dúo Internet + Teléfono
   (
      true,
      true,
      false,
      false,
      false,
      true,
      false,
      false,
      549.00,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      NULL
   ),
   -- Plan Streaming
   (
      false,
      true,
      false,
      false,
      false,
      false,
      true,
      true,
      499.00,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      NULL
   ),
   -- plan Premium
   (
      true,
      true,
      true,
      true,
      true,
      true,
      true,
      true,
      899.00,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      NULL
   ),
   -- Plan Familiar
   (
      true,
      true,
      false,
      true,
      true,
      true,
      true,
      false,
      749.00,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      NULL
   ),
   -- Plan Empresarial
   (
      true,
      true,
      true,
      true,
      true,
      true,
      false,
      false,
      999.00,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      NULL
   ),
   -- Plan Económico (dado de baja)
   (
      false,
      true,
      false,
      false,
      false,
      false,
      false,
      false,
      249.00,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP
   ),
   -- Plan Antiguo (soft delete)
   (
      true,
      true,
      false,
      false,
      false,
      false,
      false,
      false,
      459.00,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP
   ),
   -- Plan Promocional
   (
      true,
      true,
      false,
      true,
      false,
      false,
      false,
      false,
      399.00,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      NULL
   );

------------------------------------------------------------------------
CREATE TABLE
   IF NOT EXISTS servicios (
      id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
      id_usuario VARCHAR(10),
      id_plan BIGINT NOT NULL,
      ultima_fecha_pago TIMESTAMP NOT NULL,
      facturacion_electronica BOOLEAN NOT NULL,
      tipo_contrato VARCHAR(30) NOT NULL,
      subscripcion_activa BOOLEAN NOT NULL,
      created_at TIMESTAMP NOT NULL,
      updated_at TIMESTAMP NOT NULL,
      deleted_at TIMESTAMP NULL,
      FOREIGN KEY (id_usuario) REFERENCES usuarioS (id),
      FOREIGN KEY (id_plan) REFERENCES planes (id)
   );

INSERT INTO
   servicios (
      id_usuario,
      id_plan,
      ultima_fecha_pago,
      facturacion_electronica,
      tipo_contrato,
      subscripcion_activa,
      created_at,
      updated_at,
      deleted_at
   )
VALUES
   --  Usuario activo – plan básico
   (
      'USR000001',
      1,
      CURRENT_TIMESTAMP - INTERVAL '15 days',
      true,
      'Month-to-month',
      true,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      NULL
   ),
   -- Usuario activo – plan dúo
   (
      'USR000002',
      3,
      CURRENT_TIMESTAMP - INTERVAL '1 month',
      false,
      'One year',
      true,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      NULL
   ),
   -- Usuario activo – plan premium
   (
      'USR000003',
      5,
      CURRENT_TIMESTAMP - INTERVAL '5 days',
      true,
      'Two year',
      true,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      NULL
   ),
   -- Usuario en riesgo (pago atrasado)
   (
      'USR000004',
      4,
      CURRENT_TIMESTAMP - INTERVAL '2 months',
      false,
      'Month-to-month',
      true,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      NULL
   ),
   -- Usuario con contrato anual
   (
      'USR000005',
      6,
      CURRENT_TIMESTAMP - INTERVAL '20 days',
      true,
      'One year',
      true,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      NULL
   ),
   -- Usuario con servicio suspendido
   (
      'USR000006',
      2,
      CURRENT_TIMESTAMP - INTERVAL '4 months',
      false,
      'Month-to-month',
      false,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      NULL
   ),
   -- Usuario antiguo (soft delete)
   (
      'USR000007',
      8,
      CURRENT_TIMESTAMP - INTERVAL '6 months',
      true,
      'One year',
      false,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP
   ),
   -- Usuario activo – plan empresarial
   (
      'USR000008',
      7,
      CURRENT_TIMESTAMP - INTERVAL '10 days',
      true,
      'Two year',
      true,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      NULL
   ),
   -- Usuario activo – plan streaming
   (
      'USR000009',
      4,
      CURRENT_TIMESTAMP - INTERVAL '25 days',
      false,
      'Month-to-month',
      true,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      NULL
   ),
   -- Usuario dado de baja recientemente
   (
      'USR000010',
      9,
      CURRENT_TIMESTAMP - INTERVAL '3 months',
      true,
      'Month-to-month',
      false,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP
   );

-------------------------------------------------------------------------
CREATE TABLE
   IF NOT EXISTS predicciones (
      id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
      usuario_id VARCHAR(10) NOT NULL,
      churn BOOLEAN NOT NULL,
      prevision VARCHAR(20),
      probabilidad DOUBLE PRECISION,
      created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
      updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
      deleted_at TIMESTAMP NULL,
      CONSTRAINT fk_prediccion_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios (id)
   );

INSERT INTO
   predicciones (
      usuario_id,
      churn,
      prevision,
      probabilidad,
      created_at,
      updated_at,
      deleted_at
   )
VALUES
   -- Alto riesgo de churn
   (
      'USR000001',
      true,
      'Alta',
      0.91,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      NULL
   ),
   -- Usuario estable
   (
      'USR000002',
      false,
      'Baja',
      0.08,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      NULL
   ),
   -- Riesgo medio
   (
      'USR000003',
      true,
      'Media',
      0.63,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      NULL
   ),
   -- Predicción incierta
   (
      'USR000004',
      false,
      'Incierta',
      0.50,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      NULL
   ),
   -- Usuario dado de baja (soft delete)
   (
      'USR000005',
      true,
      'Alta',
      0.97,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP
   );

CREATE TABLE
   IF NOT EXISTS historial_predicciones (
      id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
      churn BOOLEAN NOT NULL,
      prevision VARCHAR(20),
      probabilidad DOUBLE PRECISION,
      created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
      updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
      deleted_at TIMESTAMP NULL
   );

INSERT INTO
   historial_predicciones (
      churn,
      prevision,
      probabilidad,
      created_at,
      updated_at,
      deleted_at
   )
VALUES
   -- Historial USR000001
   (
      true,
      'Alta',
      0.88,
      CURRENT_TIMESTAMP - INTERVAL '20 days',
      CURRENT_TIMESTAMP - INTERVAL '20 days',
      NULL
   ),
   (
      true,
      'Alta',
      0.91,
      CURRENT_TIMESTAMP - INTERVAL '5 days',
      CURRENT_TIMESTAMP - INTERVAL '5 days',
      NULL
   ),
   -- Historial USR000002
   (
      false,
      'Baja',
      0.12,
      CURRENT_TIMESTAMP - INTERVAL '30 days',
      CURRENT_TIMESTAMP - INTERVAL '30 days',
      NULL
   ),
   (
      false,
      'Baja',
      0.08,
      CURRENT_TIMESTAMP - INTERVAL '10 days',
      CURRENT_TIMESTAMP - INTERVAL '10 days',
      NULL
   ),
   -- Historial USR000003
   (
      false,
      'Media',
      0.45,
      CURRENT_TIMESTAMP - INTERVAL '25 days',
      CURRENT_TIMESTAMP - INTERVAL '25 days',
      NULL
   ),
   (
      true,
      'Media',
      0.63,
      CURRENT_TIMESTAMP - INTERVAL '7 days',
      CURRENT_TIMESTAMP - INTERVAL '7 days',
      NULL
   ),
   -- Historial USR000004
   (
      false,
      'Incierta',
      0.50,
      CURRENT_TIMESTAMP - INTERVAL '15 days',
      CURRENT_TIMESTAMP - INTERVAL '15 days',
      NULL
   ),
   -- Historial USR000005 (usuario soft delete)
   (
      true,
      'Alta',
      0.97,
      CURRENT_TIMESTAMP - INTERVAL '40 days',
      CURRENT_TIMESTAMP - INTERVAL '40 days',
      CURRENT_TIMESTAMP
   );

CREATE TABLE
   IF NOT EXISTS relacion_predicciones_historial (
      id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
      prediccion_id BIGINT NOT NULL,
      historial_id BIGINT NOT NULL,
      created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
      CONSTRAINT fk_ph_prediccion FOREIGN KEY (prediccion_id) REFERENCES predicciones (id),
      CONSTRAINT fk_ph_historial FOREIGN KEY (historial_id) REFERENCES historial_predicciones (id),
      CONSTRAINT uq_prediccion_historial UNIQUE (prediccion_id, historial_id)
   );

INSERT INTO
   relacion_predicciones_historial (prediccion_id, historial_id)
VALUES
   -- USR000001
   (1, 1),
   (1, 2),
   -- USR000002
   (2, 3),
   (2, 4),
   -- USR000003
   (3, 5),
   (3, 6),
   -- USR000004
   (4, 7),
   -- USR000005
   (5, 8);

------------------------------------------------------------------------
CREATE TABLE
   oferta (
      id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
      nombre VARCHAR(100) NOT NULL,
      descripcion TEXT,
      descuento_porcentaje NUMERIC(5, 2),
      descuento_monto NUMERIC(10, 2),
      duracion_meses INTEGER,
      aplica_churn BOOLEAN,
      activa BOOLEAN,
      created_at TIMESTAMP NOT NULL,
      updated_at TIMESTAMP NOT NULL,
      deleted_at TIMESTAMP NULL
   );

INSERT INTO
   oferta (
      nombre,
      descripcion,
      descuento_porcentaje,
      descuento_monto,
      duracion_meses,
      aplica_churn,
      activa,
      created_at,
      updated_at,
      deleted_at
   )
VALUES
   -- OFERTAS DE RETENCIÓN (RIESGO DE CHURN)
   (
      'Retención Plus 20%',
      '20% de descuento para clientes con alto riesgo de cancelación',
      20.00,
      NULL,
      6,
      true,
      true,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      NULL
   ),
   (
      'Retención Cargo Fijo',
      'Descuento fijo mensual para evitar cancelación del servicio',
      NULL,
      150.00,
      3,
      true,
      true,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      NULL
   ),
   (
      'Contrato Anual con Descuento',
      '15% de descuento al renovar contrato por 12 meses',
      15.00,
      NULL,
      12,
      true,
      true,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      NULL
   ),
   (
      'Soporte Premium Gratis',
      'Acceso gratuito a soporte técnico premium por 6 meses',
      NULL,
      NULL,
      6,
      true,
      true,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      NULL
   ),
   -- OFERTAS PARA CLIENTES CONSTANTES
   (
      'Fidelidad 10%',
      '10% de descuento por ser cliente frecuente',
      10.00,
      NULL,
      6,
      false,
      true,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      NULL
   ),
   (
      'Upgrade Streaming',
      'Streaming incluido sin costo adicional por 3 meses',
      NULL,
      NULL,
      3,
      false,
      true,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      NULL
   ),
   (
      'Pago Anual Anticipado',
      '5% de descuento pagando el servicio por un año completo',
      5.00,
      NULL,
      12,
      false,
      true,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      NULL
   ),
   (
      'Cliente Leal $100',
      'Descuento directo mensual por lealtad',
      NULL,
      100.00,
      6,
      false,
      true,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      NULL
   ),
   --  OFERTAS DESCONTINUADAS (SOFT DELETE)
   (
      'Promo Antiguos Clientes',
      'Oferta histórica ya no disponible',
      25.00,
      NULL,
      3,
      true,
      false,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP
   ),
   (
      'Descuento Verano',
      'Campaña estacional finalizada',
      15.00,
      NULL,
      2,
      false,
      false,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP
   );

--------------------------------------------
CREATE
OR REPLACE VIEW vw_servicios_usuarios AS
SELECT
   u.id AS id_cliente,
   u.genero,
   CASE
      WHEN EXTRACT(
         YEAR
         FROM
            AGE (CURRENT_DATE, u.fecha_nacimiento)
      ) >= 60 THEN 1
      ELSE 0
   END AS adulto,
   u.tiene_conyuge AS tiene_pareja,
   u.tiene_dependientes,
   EXTRACT(
      YEAR
      FROM
         AGE (CURRENT_DATE, u.created_at)
   ) * 12 + EXTRACT(
      MONTH
      FROM
         AGE (CURRENT_DATE, u.created_at)
   ) AS antiguedad_meses,
   s.id AS id_servicio,
   s.tipo_contrato,
   s.facturacion_electronica,
   s.subscripcion_activa,
   s.ultima_fecha_pago,
   p.id AS id_plan,
   p.servicio_telefono,
   p.servicio_internet,
   p.seguridad_en_linea,
   p.respaldo_en_linea,
   p.proteccion_dispositivo,
   p.soporte_tecnico,
   p.streaming_tv,
   p.streaming_peliculas,
   p.cargo_mensual,
   (
      p.cargo_mensual * (
         EXTRACT(
            YEAR
            FROM
               AGE (CURRENT_DATE, u.created_at)
         ) * 12 + EXTRACT(
            MONTH
            FROM
               AGE (CURRENT_DATE, u.created_at)
         )
      )
   ) AS cargos_totales,
   u.created_at AS fecha_alta_cliente,
   s.created_at AS fecha_alta_servicio
FROM
   usuarios u
   JOIN servicios s ON u.id = s.id_usuario
   JOIN planes p ON s.id_plan = p.id
WHERE
   u.deleted_at IS NULL
   AND s.deleted_at IS NULL
   AND p.deleted_at IS NULL;

SELECT
   *
FROM
   vw_servicios_usuarios;

select
   *
from
   roles_permisos;

select
   *
from
   historialPrediccion;

select
   *
from
   predicciones;

select
   *
from
   usuarios;