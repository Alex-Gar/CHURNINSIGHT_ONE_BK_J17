INSERT INTO
    predicciones (
        usuario_id,
        churn,
        prevision,
        probabilidad,
        created_at,
        updated_at,
        deleted_at
    )
VALUES
    -- Alto riesgo de churn
    (
        'USR000001',
        true,
        'Alta',
        0.91,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        NULL
    ),
    -- Usuario estable
    (
        'USR000002',
        false,
        'Baja',
        0.08,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        NULL
    ),
    -- Riesgo medio
    (
        'USR000003',
        true,
        'Media',
        0.63,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        NULL
    ),
    -- Predicción incierta
    (
        'USR000004',
        false,
        'Incierta',
        0.50,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        NULL
    ),
    -- Usuario dado de baja (soft delete)
    (
        'USR000005',
        true,
        'Alta',
        0.97,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP
    );