INSERT INTO
   ofertas (
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