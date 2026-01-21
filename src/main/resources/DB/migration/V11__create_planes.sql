CREATE TABLE
   planes (
      id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
      servicio_telefono BOOLEAN NOT NULL,
      servicio_internet VARCHAR(20) NOT NULL,
      seguridad_en_linea BOOLEAN NOT NULL,
      respaldo_en_linea BOOLEAN NOT NULL,
      proteccion_dispositivo BOOLEAN NOT NULL,
      soporte_tecnico BOOLEAN NOT NULL,
      lineas_multiples BOOLEAN NOT NULL,
      streaming_tv BOOLEAN NOT NULL,
      streaming_peliculas BOOLEAN NOT NULL,
      cargo_mensual DECIMAL NOT NULL,
      created_at TIMESTAMP NOT NULL,
      updated_at TIMESTAMP NOT NULL,
      deleted_at TIMESTAMP NULL
   );