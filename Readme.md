# Proyecto Mascotas - API REST

Este proyecto es una API RESTful desarrollada con Spring Boot para la gestión de mascotas, tipos de mascota, usuarios y adopciones. Implementa validaciones, seguridad con autenticación básica (Basic Auth) y sigue una estructura de carpetas organizada para facilitar el mantenimiento y la escalabilidad.

## Características

*   **API RESTful:** Implementada siguiendo los principios de REST.
*   **Validaciones:** Validaciones robustas en los DTOs para asegurar la integridad de los datos.
*   **Seguridad:** Autenticación básica (Basic Auth) para proteger los endpoints de la API.
*   **Estructura de Carpetas Organizada:** Separación clara de las capas de aplicación y dominio.
*   **Tests Unitarios:** Cobertura de tests para el controlador.

## Estructura del Proyecto

src/main/

├── java/

│   └── com/

│       └── japb/

│           └── mascotas/

│               ├── application/        // Capa de Aplicación

│               │   ├── controller/

│               │   ├── dto/

│               │   ├── mapper/

│               │   └── config/      //Clases de configuracion

│               └── domain/           // Capa de Dominio

│                   ├── model/

│                   └── service/

└── resources/

└── application.yaml       // Archivo de configuración

src/test/
└── java/

└── com/

└── japb/

└── mascotas/

└── application/

└── controller/    // Tests de los controladores



*   `application/controller/`: Contiene los controladores REST (`TipoMascotaController`, `MascotaController`, `UsuarioController`, `AdopcionController`).
*   `application/dto/`: Contiene los Data Transfer Objects (DTOs) con las anotaciones de validación.
*   `application/mapper/`: Contiene los mappers para convertir entre entidades y DTOs.
*   `application/config/`: Contiene la clase de configuración de Spring Security (`SecurityConfig`).
*   `domain/model/`: Contiene las entidades del dominio (`TipoMascota`, `Mascota`, `Usuario`, `Adopcion`).
*   `domain/service/`: Contiene las interfaces e implementaciones de los servicios.
*   `resources/application.properties`: Archivo de configuración de la aplicación.
*   `src/test/java/...`: Contiene las clases de test unitarios para los controladores.

## Dependencias

El proyecto utiliza las siguientes dependencias principales:

*   `spring-boot-starter-web`: Para el desarrollo de APIs REST con Spring MVC.
*   `spring-boot-starter-validation`: Para la validación de datos con Bean Validation.
*   `spring-boot-starter-security`: Para la seguridad con Spring Security.
*   `com.fasterxml.jackson.core:jackson-databind`: Para el manejo de JSON.
    *   `spring-boot-starter-hateoas`: para la implementacion de HATEOAS
*   `spring-boot-starter-test`: Para las pruebas unitarias.
    *   `org.mockito:mockito-core`: Para la creacion de mocks
    *   `org.mockito:mockito-junit-jupiter`: Para la integracion de Mockito con JUnit Jupiter

## Configuración de Seguridad

La seguridad está configurada con autenticación básica (Basic Auth). Se han definido usuarios en memoria para fines de desarrollo:

*   Usuario: `user` / `password` (Rol: `USER`)
*   Usuario: `admin` / `admin` (Rol: `ADMIN`)

Los endpoints `/api/mascotas` (POST, PUT, DELETE) requieren el rol `ADMIN`. El resto de los endpoints bajo `/api/` requieren autenticación.

La clase `SecurityConfig` se encuentra en `com/japb/mascotas/application/config/`.

## Endpoints

*   `/api/tipos-mascota`: Endpoints para la gestión de tipos de mascota.
*   `/api/mascotas`: Endpoints para la gestión de mascotas.
*   `/api/usuarios`: Endpoints para la gestión de usuarios.
*   `/api/adopciones`: Endpoints para la gestión de adopciones.

## Ejecución

Para ejecutar la aplicación, puedes usar Maven:

```bash
./mvnw spring-boot:run
* Ingresar por el navegador a esta dirección 
http://localhost:8080/swagger-ui/index.html

* Si desea ingresar a la BD puede acceder a:
http://localhost:8080/h2-console
