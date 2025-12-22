ChurnInsight – Guía de Configuración Inicial

Este documento describe los pasos necesarios para configurar correctamente el proyecto ChurnInsight en un entorno de desarrollo local y las reglas básicas de trabajo en equipo.

🧱 1. Configuración inicial del proyecto

El proyecto ya fue configurado con:

Librerías necesarias para el desarrollo Back-end

Estructura de carpetas base

Configuración inicial para conexión a base de datos (omitir los archivos Prueba)

Antes de ejecutar el proyecto, es obligatorio completar la configuración descrita a continuación.

🐘 2. Configuración de la base de datos PostgreSQL
2.1 Archivo de configuración

En el archivo de configuración:

application.yml

Configurar los siguientes valores:

spring:
  datasource:
    username: postgres
    password: "tu password"
    url: jdbc:postgresql://localhost:5432/churninsight_db

2.2 Creación de la base de datos

Es necesario crear previamente la base de datos en PostgreSQL:

CREATE DATABASE churninsight_db;


📌 Nota:
Si la base de datos no existe o las credenciales son incorrectas, el proyecto fallará al iniciar.

🌱 3. Flujo de trabajo con Git (Git Flow)

El proyecto utiliza Git Flow como estrategia de control de versiones.

Reglas importantes:

❌ Nunca realizar commits directamente en la rama master

✅ Todo el desarrollo debe realizarse en la rama develop

🌿 Nuevas funcionalidades deben partir desde develop

Ejemplo de flujo correcto:

master
 └── develop
      └── feature/nombre-funcionalidad


Esto garantiza:

Estabilidad en master

Integración ordenada de nuevas funcionalidades

Mejor control de cambios en equipo

🔐 4. Archivos de configuración y buenas prácticas

⚠️ Importante

Los archivos de configuración NO deben subirse al repositorio, ya que contienen información sensible (credenciales, URLs, etc.).

Asegúrate de que los siguientes archivos estén ignorados por Git:

application.yml
application.properties


Verifica que estén incluidos en el archivo .gitignore.

🛠️ 5. Configuración de Git y VS Code

Antes de subir cambios al repositorio:

Revisa que tus configuraciones locales no se incluyan en el commit

Usa correctamente el .gitignore

Verifica los archivos a subir con:

git status


Esto evita subir:

Credenciales
Configuraciones locales


🚀 Listo para comenzar
