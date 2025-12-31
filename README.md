# ChurnInsight – Propuesta POC MVP

**Propuesta de modelo funcional para el proyecto ChurnInsight**

Propuesta enfocada en la colaboración para el sistema ChurnInsight, basada en un desarrollo incremental mediante ajustes, cambios y variantes, promoviendo una forma de trabajo iterativa orientada al bien común del proyecto.
 
- Los avances se documentan de forma progresiva, permitiendo consultar cada ajuste realizado, junto con su respectiva justificación técnica.


## 📋 Índice

**Implementaciones**
-  [📦 README Original "Compañero Alex" ](#churninsight--guía-de-configuración-inicial)
-  [📦 Ajustes de estructura del proyecto ](#-ajustes-de-estructura-del-proyecto)
-  [📈 Implementacion "LOMBOK" y ajuste "POM""](#-implementación-de-lombok-y-ajustes-en-pom)
-  [📁 Ajustes de configuración (YAML y perfiles)](#-ajustes-de-configuración-yaml-y-perfiles)

---

# 📁 Ajustes de configuración (YAML y perfiles)


**Se realizaron ajustes en la configuración del proyecto para separar los entornos de ejecución y facilitar el desarrollo del MVP, sin modificar la lógica de negocio ni el comportamiento funcional del sistema.**

### Cambios realizados

- Separación de la configuración en múltiples archivos YAML según el entorno.
- Definición de un archivo application.yml base con configuración común.
- Activación del perfil h2 como entorno por defecto para el MVP.
- Creación de un archivo application-h2.yml para configuración de base de datos en memoria.
- Habilitación de la consola H2 para facilitar pruebas y validación de datos.
- Separación de la configuración de PostgreSQL en un archivo independiente.
- Mantenimiento de la configuración original de PostgreSQL para futuras etapas del proyecto.
- Centralización del nivel de logging en la configuración base.

### 🎯 Objetivo del ajuste

Permitir un cambio controlado entre entornos de desarrollo y persistencia sin modificar código, reducir fricción durante el desarrollo inicial y preparar el proyecto para una configuración más robusta en etapas posteriores.

### 🧱 Impacto técnico

Estos ajustes no agregan nuevas funcionalidades ni afectan al usuario final.
Su finalidad es mejorar la organización de la configuración, facilitar pruebas locales y evitar dependencias innecesarias durante el desarrollo del MVP.

## [📋 Volver al índice ☝️](#-índice)


---

# 📈 Implementación de Lombok y ajustes en POM

**Se realizaron ajustes en el archivo pom.xml con el objetivo de ordenar las dependencias, simplificar el entorno de desarrollo del MVP y mejorar la calidad y legibilidad del código, sin alterar el comportamiento funcional del sistema.**

### Cambios realizados

- Reorganización del archivo pom.xml por bloques funcionales para mejorar su lectura y mantenimiento.
- Incorporación de Lombok para reducir código repetitivo en entidades, DTOs y servicios.
- Configuración del procesador de anotaciones para Lombok y exclusión de la librería del artefacto final.
- Integración de H2 como base de datos en memoria para la etapa de MVP.
- Habilitación de consola H2 para facilitar pruebas y validación de datos.
- Suspensión temporal de PostgreSQL, manteniéndolo comentado para futuras etapas.
- Eliminación de metadata vacía y secciones sin uso del POM original.
- Mantenimiento de herramientas de desarrollo y testing necesarias para el flujo actual.

### 🎯 Objetivo del ajuste

Simplificar el entorno técnico del proyecto en la etapa inicial, reducir fricción durante el desarrollo y establecer una configuración clara y mantenible para el crecimiento posterior del sistema.

### 🧱 Impacto técnico

Estos ajustes no agregan nuevas funcionalidades ni modifican la lógica de negocio.
Su propósito es mejorar la legibilidad del código, reducir ruido estructural y evitar complejidad innecesaria durante el desarrollo del MVP.

## [📋 Volver al índice ☝️](#-índice)

---
# 📦 Ajustes de estructura del proyecto

**Se realizaron ajustes menores en la estructura de carpetas con el objetivo de ordenar el proyecto y alinearlo con una separación básica de responsabilidades, sin alterar la lógica funcional del sistema.**

Estos cambios buscan facilitar el mantenimiento del código y preparar la base para los siguientes pasos del desarrollo.

**Cambios realizados:**

- Se movió la carpeta repository al nivel raíz del proyecto, junto a controller y la clase principal.
- Se eliminó la carpeta entities dentro de models.
- Se reorganizó el dominio por entidad funcional, creando un paquete por entidad.
- Cada entidad contiene su clase JPA y un subpaquete dto para objetos de transferencia de datos.
- Se simplificó la estructura de service, eliminando la carpeta implementations.
- Se mantuvieron los servicios con nombres autodescriptivos por entidad.
- Se centralizó la lógica auxiliar en una carpeta utils/helper.
- Se separó el manejo de excepciones como una preocupación transversal del sistema.

**Objetivo del ajuste:**

Establecer una estructura clara y coherente que evite mezclar responsabilidades, reduzca ruido innecesario y permita que el proyecto mantenga un nivel técnico consistente conforme avance el desarrollo.

**🍱 Beneficio:**

- Mejor legibilidad
- Menor acoplamiento
- Base más clara para futuros cambios

**🧱 Impacto técnico**

Estos ajustes no agregan nuevas funcionalidades ni representan mejoras visibles para el usuario final. Su propósito es evitar desorden estructural, facilitar la lectura del código y prevenir deuda técnica temprana.

## [📋 Volver al índice ☝️](#-índice)

---

# Contribucion 
## 👨‍💻 Desarrollador

**Jesús Medina Casas**
- 💻 Apasionado por desarrollo backend con Java y Spring Boot
- 🎓 Estudiante de Oracle Next Education (ONE)


- 🌐 [LinkedIn](https://www.linkedin.com/in/jesus-medina-casas/)   🧑‍💻 [GitHub](https://github.com/chuycode15)

---

# ChurnInsight – Guía de Configuración Inicial

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

## [📋 Volver al índice ☝️](#-índice)