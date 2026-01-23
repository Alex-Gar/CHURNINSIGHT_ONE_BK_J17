INSERT INTO
   planes (
      servicio_telefono,
      servicio_internet,
      seguridad_en_linea,
      respaldo_en_linea,
      proteccion_dispositivo,
      soporte_tecnico,
      lineas_multiples,
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
      'Cable Coaxial',
      false,
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
      'Cable Coaxial',
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
      'Cable Coaxial',
      false,
      false,
      false,
      true,
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
      'Fibra Óptica',
      false,
      false,
      false,
      true,
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
      'Fibra Óptica',
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
      'Fibra Óptica',
      false,
      true,
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
      'Fibra Óptica',
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
      'Fibra Óptica',
      false,
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
      'Fibra Óptica',
      false,
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
      'Fibra Óptica',
      false,
      true,
      false,
      false,
      false,
      false,
      false,
      399.00,
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP,
      NULL
   );