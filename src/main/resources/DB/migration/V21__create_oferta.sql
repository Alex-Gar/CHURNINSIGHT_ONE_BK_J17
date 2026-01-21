CREATE TABLE
   ofertas (
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
