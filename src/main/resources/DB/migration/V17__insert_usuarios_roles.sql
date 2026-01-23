INSERT INTO
    usuarios_roles (usuario_id, rol_id)
VALUES
    -- Usuarios activos
    ('USR000001', 1), -- Admin
    ('USR000002', 2),
    ('USR000003', 2),
    ('USR000004', 2),
    -- Usuario con doble rol
    ('USR000005', 1),
    ('USR000005', 2),
    -- Roles dados de baja (soft delete)
    ('USR000006', 2),
    ('USR000007', 2),
    -- Usuario con rol histórico
    ('USR000008', 1),
    ('USR000009', 2);