INSERT INTO servicios (
    id_usuario,
    id_plan,
    ultima_fecha_pago,
    facturacion_electronica,
    tipo_contrato,
    subscripcion_activa,
    metodo_pago,
    created_at,
    updated_at,
    deleted_at
)
VALUES
-- Usuario activo – plan básico
(
    'USR000001',
    1,
    CURRENT_TIMESTAMP - INTERVAL '15 days',
    true,
    'Month-to-month',
    true,
    'E-wallet',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    NULL
),
-- Usuario activo – plan dúo
(
    'USR000002',
    3,
    CURRENT_TIMESTAMP - INTERVAL '1 month',
    false,
    'One year',
    true,
    'Débito',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    NULL
),
-- Usuario activo – plan premium
(
    'USR000003',
    5,
    CURRENT_TIMESTAMP - INTERVAL '5 days',
    true,
    'Two year',
    true,
    'Crédito',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    NULL
),
-- Usuario en riesgo (pago atrasado)
(
    'USR000004',
    4,
    CURRENT_TIMESTAMP - INTERVAL '2 months',
    false,
    'Month-to-month',
    true,
    'Pagos QR',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    NULL
),
-- Usuario con contrato anual
(
    'USR000005',
    6,
    CURRENT_TIMESTAMP - INTERVAL '20 days',
    true,
    'One year',
    true,
    'Pagos QR',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    NULL
),
-- Usuario con servicio suspendido
(
    'USR000006',
    2,
    CURRENT_TIMESTAMP - INTERVAL '4 months',
    false,
    'Month-to-month',
    false,
    'Débito',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    NULL
),
-- Usuario antiguo (soft delete)
(
    'USR000007',
    8,
    CURRENT_TIMESTAMP - INTERVAL '6 months',
    true,
    'One year',
    false,
    'Débito',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
),
-- Usuario activo – plan empresarial
(
    'USR000008',
    7,
    CURRENT_TIMESTAMP - INTERVAL '10 days',
    true,
    'Two year',
    true,
    'E-wallet',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    NULL
),
-- Usuario activo – plan streaming
(
    'USR000009',
    4,
    CURRENT_TIMESTAMP - INTERVAL '25 days',
    false,
    'Month-to-month',
    true,
    'Tarjeta Prepago',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    NULL
),
-- Usuario dado de baja recientemente
(
    'USR000010',
    9,
    CURRENT_TIMESTAMP - INTERVAL '3 months',
    true,
    'Month-to-month',
    false,
    'Tarjeta Prepago',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);
