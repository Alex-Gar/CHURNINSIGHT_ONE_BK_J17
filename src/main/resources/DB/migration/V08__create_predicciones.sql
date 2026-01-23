CREATE TABLE
   predicciones (
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