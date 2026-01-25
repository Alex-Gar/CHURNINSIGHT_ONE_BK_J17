# CHURNINSIGHT_ONE_BK_J17

ChurnInsight es un backend desarrollado en Java con Spring Boot para predecir la probabilidad de **cancelación de clientes (churn)** en servicios con suscripción (Telecom, Fintech, Streaming, e-Commerce, etc.). Utiliza un modelo predictivo de ciencia de datos para identificar clientes con alta probabilidad de desistir y así habilitar acciones de retención.

---

## 📌 Objetivo

Este proyecto proporciona una API REST que:

- Recibe información de clientes (Front-end).
- Consume el servicio la API del modelo, para enviarle infromación acerca de los clientes a analizar 
- Recibe predicciones de churn (sí/no).
- Mantiene un historial de predicciones, siendo persistido en la base de datos para futuras consultas.
- Permite analizar la evolución de riesgo de cada cliente a lo largo del tiempo, asi como lanzar ofertes para retener al cliente.

---

## 🧱 Estructura General

El proyecto está dividido en tres grandes áreas:

## 🗄 Front-end
- Consume los endpoints del backend para:
- Enviar información de clientes.
- Visualizar predicciones e historial.
- Gestionar usuarios y seguridad.

### 🧠 Data Science

Contiene scripts/notebooks para:

- Exploración y limpieza de datos (EDA).
- Ingeniería de características.
- Entrenamiento de modelos.
- Serialización del modelo para uso en producción.

### 🛠 Back-End (Spring Boot)

Provee:

- API REST desarrollada con Spring Boot.
- Persistencia con PostgreSQL y Spring Data JPA.
- Seguridad con Spring Security y JWT.
- Auditoría y borrado lógico.
- Manejo de relaciones entre entidades:
- Usuario
- Predicción
- Historial
- Roles y Permisos
---

## 🗄 Base de Datos

Se usa **PostgreSQL** para almacenar:

- Clientes (usuarios).
- Predicciones actuales.
- Historial de predicciones (evolución en el tiempo).
- Relaciones entre predicciones e historial.
- Ofertas 
- Permisos, Roles e usuaarios
- Planes 
- Servicios

Ejemplo de tablas principales:

- `usuarios`
- `predicciones`
- `historial_predicciones`
- `predicciones_historial`
- `roles` 
- `permisos` 
- `roles_permisos` 
- `roles_usuarios` 
![alt text](<Captura de pantalla_20260113_210055-1.png>)
---

## 🚀 Características

### 📍 Endpoints de API

Swagger 
- `GET /swagger-ui/index.html#/` → Documentación de las Api's del sistema 
- `GET /v3/api-docs` → Documentación de las Api's del sistema
Auth 
- `POST /auth/register` → Registra usuarios.
{
  "id": "string",
  "nombre": "string",
  "pApellido": "string",
  "sApellido": "string",
  "email": "string",
  "password": "stringst",
  "telefono": "5316049943",
  "fechaNacimiento": "2026-01-14T02:38:47.811Z",
  "genero": "MASCULINO",
  "tieneConyuge": true,
  "tieneDependientes": true,
  "isEnabled": true,
  "accountNoExpired": true,
  "accountNoLocked": true,
  "credentialNoExpired": true,
  "createdAt": "2026-01-14T02:38:47.811Z",
  "updatedAt": "2026-01-14T02:38:47.811Z",
  "deletedAt": "2026-01-14T02:38:47.811Z"
}
- `POST /auth/login` → Logeo de usuarios.
{
  "email": "string",
  "password": "string"
}


## Importante loggearse con las credenciales para probar las demas apis

*(Ejemplos basados en estructura estándar de Spring Boot)*

---

## 📦 Tecnologías Back-end

| Capa | Tecnologías |
|------|-------------|
| Backend | Java, Spring Boot, Spring Data JPA, Spring Security, JWT, Swaggerm Spring Auditoring, Swagger / OpenAPI|
| DB | PostgreSQL |
| API | REST |
| Control de versiones | Git, Git Flow, GitHub |
| Prueba de API's | Apidog, Postman, Insomnia

---

## 📁 Estructura de Carpetas

src/
├── main/
│ ├── java/
│ │ ├── controller/
| | ├── config
| | ├── exceptions
│ │ ├── service/
| | |         └──implementations
| | ├── security
│ │ ├── models/
| | |         ├──entities
| | |         ├──payload
| | |         └──repositories
| | └── utils
│ └── resources/
│ ├── application.yml
│ └── other config files
├── test/

