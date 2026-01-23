CREATE OR REPLACE VIEW vw_modelo_insumos AS
SELECT
    u.id AS id_cliente,
    CASE 
        WHEN u.genero = 'MASCULINO' THEN 'Male'
        WHEN u.genero = 'FEMENINO' THEN 'Female'
        ELSE u.genero 
    END AS gender,
    CASE 
        WHEN EXTRACT(YEAR FROM AGE(CURRENT_DATE, u.fecha_nacimiento)) >= 60 THEN 'Yes'
        ELSE 'No'
    END AS seniorCitizen,
    CASE WHEN u.tiene_conyuge THEN 'Yes' ELSE 'No' END AS partner,
    CASE WHEN u.tiene_dependientes THEN 'Yes' ELSE 'No' END AS dependents,
    CAST((EXTRACT(YEAR FROM AGE(CURRENT_DATE, u.created_at)) * 12 + EXTRACT(MONTH FROM AGE(CURRENT_DATE, u.created_at))) AS INTEGER) AS tenure,
    CASE WHEN p.servicio_telefono THEN 'Yes' ELSE 'No' END AS phoneService,
    CASE WHEN p.lineas_multiples THEN 'Yes' ELSE 'No' END AS multipleLines,
    CASE 
        WHEN p.servicio_internet = 'Cable Coaxial' THEN 'DSL'
        WHEN p.servicio_internet = 'Fibra Óptica' THEN 'Fiber optic'
        ELSE 'No'
    END AS internetService,
    CASE WHEN p.seguridad_en_linea THEN 'Yes' ELSE 'No' END AS onlineSecurity,
    CASE WHEN p.respaldo_en_linea THEN 'Yes' ELSE 'No' END AS onlineBackup,
    CASE WHEN p.proteccion_dispositivo THEN 'Yes' ELSE 'No' END AS deviceProtection,
    CASE WHEN p.soporte_tecnico THEN 'Yes' ELSE 'No' END AS techSupport,
    CASE WHEN p.streaming_tv THEN 'Yes' ELSE 'No' END AS streamingTV,
    CASE WHEN p.streaming_peliculas THEN 'Yes' ELSE 'No' END AS streamingMovies,
    s.tipo_contrato AS contract,
    CASE WHEN s.facturacion_electronica THEN 'Yes' ELSE 'No' END AS paperlessBilling,
    CASE 
        WHEN s.metodo_pago = 'E-wallet' THEN 'Electronic check'
        WHEN s.metodo_pago = 'Pagos QR' THEN 'Mailed check'
        WHEN s.metodo_pago = 'Débito' THEN 'Bank transfer (automatic)'
        WHEN s.metodo_pago = 'Crédito' THEN 'Credit card (automatic)'
        WHEN s.metodo_pago = 'Tarjeta Prepago' THEN 'Electronic check'
        ELSE 'Mailed check'
    END AS paymentMethod,
    p.cargo_mensual AS monthlyCharges,
    (p.cargo_mensual * (EXTRACT(YEAR FROM AGE(CURRENT_DATE, u.created_at)) * 12 + EXTRACT(MONTH FROM AGE(CURRENT_DATE, u.created_at)))) AS totalCharges
FROM
    usuarios u
    JOIN servicios s ON u.id = s.id_usuario
    JOIN planes p ON s.id_plan = p.id
WHERE
    u.deleted_at IS NULL
    AND s.deleted_at IS NULL
    AND p.deleted_at IS NULL;
