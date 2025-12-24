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

![alt Diagrama E-R](image.png)



<em> Retos del Equipo y Lineamientos de Desarrollo </em>

Esta sección describe los principales retos técnicos y las reglas de trabajo colaborativo que deberán seguir todos los integrantes del equipo durante el desarrollo del proyecto ChurnInsight.

🗄️ Modelado de Datos y Relaciones (JPA)

Diseñar y crear correctamente las relaciones entre tablas utilizando anotaciones de JPA:

@OneToMany

@ManyToOne

@ManyToMany

Garantizar una correcta representación del modelo de dominio y la integridad de los datos en la base de datos.

🗑️ Borrado Lógico (Soft Delete)

Implementar un borrado lógico, evitando la eliminación física de registros en la base de datos.

Se deberá utilizar el siguiente campo para marcar un registro como eliminado:

deleted_at TIMESTAMP NULL


Cuando un registro sea eliminado lógicamente:

El campo deleted_at deberá almacenar la fecha y hora de la eliminación.

Todas las consultas SELECT deberán:

Retornar únicamente los registros donde deleted_at sea NULL.

Excluir automáticamente los registros marcados como eliminados.

El borrado lógico deberá integrarse con Spring Data Auditing para mantener trazabilidad e historial de cambios.

🔐 Seguridad de la Aplicación

Implementar Spring Security con JWT (JSON Web Tokens) para:

Autenticación de usuarios

Autorización de accesos

Protección de endpoints según roles y permisos

🌱 Flujo de Trabajo con Git Flow

El proyecto utiliza Git Flow como estrategia oficial de control de versiones.

Reglas obligatorias:

❌ No se permite realizar commits directamente en la rama master.

✅ Todo el desarrollo debe realizarse a partir de la rama develop.

Flujo recomendado por funcionalidad:

Crear una nueva rama feature para cada tarea:

git flow feature start CRUD_Usuario


Realizar todos los commits relacionados con la tarea únicamente en la rama feature.

Una vez que la funcionalidad:

Esté completamente implementada

Funcione correctamente

Haya sido validada

Se deberá cerrar la feature:

git flow feature finish CRUD_Usuario


Este proceso:

Cerrará la rama feature

Fusionará automáticamente los cambios en la rama develop

Subir los cambios al repositorio remoto:

git push origin develop


Esto permitirá que todo el equipo tenga acceso a los avances más recientes del proyecto.

✅ Buenas Prácticas Esperadas

Commits claros, pequeños y descriptivos

Código funcional antes de cerrar una feature

Uso correcto de Git Flow

Respeto a las convenciones del proyecto

Comunicación constante con el equipo