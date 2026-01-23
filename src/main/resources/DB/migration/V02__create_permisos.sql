CREATE TABLE
   permisos (
      id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
      permiso_nombre VARCHAR(50) NOT NULL UNIQUE,
      descripcion TEXT NULL,
      created_at TIMESTAMP NOT NULL,
      updated_at TIMESTAMP NOT NULL,
      deleted_at TIMESTAMP NULL
   );