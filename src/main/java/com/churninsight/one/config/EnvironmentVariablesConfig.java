package com.churninsight.one.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EnvironmentVariablesConfig implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Map<String, Object> systemEnvVars = new HashMap<>();
        
        // Leer variables de entorno del SO y mapearlas
        mapEnvVariable(systemEnvVars, "APP_PORT", "app.port");
        mapEnvVariable(systemEnvVars, "DATABASE_URL", "app.database.url");
        mapEnvVariable(systemEnvVars, "DATABASE_USER", "app.database.username");
        mapEnvVariable(systemEnvVars, "DATABASE_PASSWORD", "app.database.password");
        mapEnvVariable(systemEnvVars, "JWT_SECRET_KEY", "app.jwt.secret");
        mapEnvVariable(systemEnvVars, "JWT_PUBLIC_KEY", "app.jwt.public");
        mapEnvVariable(systemEnvVars, "JWT_EXPIRATION_TIME", "app.jwt.expiration");

        // Agregar al environment con alta prioridad (antes que el archivo YAML)
        if (!systemEnvVars.isEmpty()) {
            environment.getPropertySources().addFirst(
                new MapPropertySource("systemEnvVars", systemEnvVars)
            );
        }
    }

    private void mapEnvVariable(Map<String, Object> map, String envVarName, String propertyName) {
        String value = System.getenv(envVarName);
        if (value != null && !value.isEmpty()) {
            map.put(propertyName, value);
        }
    }
}

