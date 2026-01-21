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