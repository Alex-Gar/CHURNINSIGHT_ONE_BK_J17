INSERT INTO
   roles_permisos (rol_id, permiso_id)
VALUES
   (1, 1), -- ADMIN tiene permiso CREATE
   (1, 2), -- ADMIN tiene permiso READ
   (1, 3), -- ADMIN tiene permiso UPDATE
   (1, 4), -- ADMIN tiene permiso DELETE
   (1, 5), -- ADMIN tiene permiso MANAGE_USERS
   (1, 6), -- ADMIN tiene permiso MANAGE_ROLES
   (1, 7), -- ADMIN tiene permiso MANAGE_PERMISSIONS
   (1, 8), -- ADMIN tiene permiso VIEW_REPORTS
   (1, 9), -- ADMIN tiene permiso ACCESS_ADMIN_PANEL
   (2, 2), -- Usuario tiene permiso CREATE
   (2, 3), -- Usuario tiene permiso READ
   (2, 5), -- Usuario tiene permiso UPDATE
   (2, 6), -- Usuario tiene permiso DELETE
   (2, 8);-- Usuario tiene permiso VIEW_REPORTS