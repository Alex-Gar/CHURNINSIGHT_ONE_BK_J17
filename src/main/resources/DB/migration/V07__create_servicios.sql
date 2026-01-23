CREATE TABLE
   servicios (
      id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
      id_usuario VARCHAR(10),
      id_plan BIGINT NOT NULL,
      ultima_fecha_pago TIMESTAMP NOT NULL,
      facturacion_electronica BOOLEAN NOT NULL,
      tipo_contrato VARCHAR(30) NOT NULL,
      subscripcion_activa BOOLEAN NOT NULL,
      metodo_pago VARCHAR(30) NOT NULL,
      created_at TIMESTAMP NOT NULL,
      updated_at TIMESTAMP NOT NULL,
      deleted_at TIMESTAMP NULL,
      FOREIGN KEY (id_usuario) REFERENCES usuarios (id),
      FOREIGN KEY (id_plan) REFERENCES planes (id)
   );