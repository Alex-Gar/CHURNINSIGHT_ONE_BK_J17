CREATE TABLE
   relacion_predicciones_historial (
      prediccion_id BIGINT NOT NULL,
      historial_id BIGINT NOT NULL,
      PRIMARY KEY (prediccion_id, historial_id),
      CONSTRAINT fk_ph_prediccion FOREIGN KEY (prediccion_id) REFERENCES predicciones (id),
      CONSTRAINT fk_ph_historial FOREIGN KEY (historial_id) REFERENCES historial_predicciones (id)
   );