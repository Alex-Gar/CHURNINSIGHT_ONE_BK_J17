CREATE
OR REPLACE VIEW vw_output_modelo AS
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
   END AS adulto_mayor,
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
   p.servicio_telefono,
   p.lineas_multiples,
   p.servicio_internet,
   p.seguridad_en_linea,
   p.respaldo_en_linea,
   p.proteccion_dispositivo,
   p.soporte_tecnico,
   p.streaming_tv,
   p.streaming_peliculas,
   s.tipo_contrato,
   s.facturacion_electronica,
   s.metodo_pago,
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
   ) AS cargos_totales
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
   usuarios
WHERE
   id = 'USR000010';

SELECT
   *
FROM
   predicciones;

SELECT
   *
FROM
   historial_predicciones;

SELECT
   *
FROM
   vw_output_modelo;

SELECT
   *
FROM
   relacion_predicciones_historial;

SELECT
   *
FROM
   servicios;

SELECT
   *
FROM
   planes;