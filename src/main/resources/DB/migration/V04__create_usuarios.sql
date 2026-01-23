CREATE TABLE
   usuarios (
      id VARCHAR(10) PRIMARY KEY UNIQUE,
      nombre VARCHAR(50) NOT NULL,
      p_apellido VARCHAR(25) NOT NULL,
      s_apellido VARCHAR(25),
      email VARCHAR(100) NOT NULL UNIQUE,
      password VARCHAR(255) NOT NULL,
      telefono VARCHAR(10) NULL,
      fecha_nacimiento DATE NOT NULL,
      genero VARCHAR(15) NOT NULL,
      tiene_conyuge BOOLEAN NOT NULL,
      tiene_dependientes BOOLEAN NOT NULL,
      is_enabled BOOLEAN NOT NULL,
      account_no_expired BOOLEAN NOT NULL,
      account_no_locked BOOLEAN NOT NULL,
      credential_no_expired BOOLEAN NOT NULL,
      created_at TIMESTAMP NOT NULL,
      updated_at TIMESTAMP NOT NULL,
      deleted_at TIMESTAMP NULL
   );