INSERT INTO
   permisos (
      permiso_nombre,
      descripcion,
      created_at,
      updated_at
   )
VALUES
   (
      'CREATE',
      'Permiso para crear',
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP
   ),
   (
      'READ',
      'Permiso para leer',
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP
   ),
   (
      'UPDATE',
      'Permiso para actualizar',
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP
   ),
   (
      'DELETE',
      'Permiso para eliminar',
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP
   ),
   (
      'MANAGE_USERS',
      'Permiso para gestionar usuarios',
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP
   ),
   (
      'MANAGE_ROLES',
      'Permiso para gestionar roles',
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP
   ),
   (
      'MANAGE_PERMISSIONS',
      'Permiso para gestionar permisos',
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP
   ),
   (
      'VIEW_REPORTS',
      'Permiso para ver reportes',
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP
   ),
   (
      'ACCESS_ADMIN_PANEL',
      'Permiso para acceder al panel de administración',
      CURRENT_TIMESTAMP,
      CURRENT_TIMESTAMP
   );