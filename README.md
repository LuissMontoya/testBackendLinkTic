# testBackendLinkTic

📌 Descripción del Proyecto
Este proyecto consiste en dos microservicios desarrollados con Spring Boot:

* Microservicio de Productos: Permite CRUD completo de productos.
* Microservicio de Inventario: Gestiona cantidades disponibles y se comunica con el microservicio de productos.
* Microservicio de Categorias: Permite CRUD completo de Categorias.
* Microservicio de Órdenes: Permite CRUD completo de Órdenes.
* Microservicio de Usuarios: Permite CRUD completo de Usuarios.
* Microservicio de Login: Permite realizar un loggueo con correo y contraseña. 

Los servicios se comunican usando el estándar JSON:API.

📦 Estructura del Proyecto:

src/main/java/co.com.test.linktic.appEcommerce  <br />
├── Config/ # Configuraciones globales (seguridad, CORS, beans, etc.)  <br />
├── controllers/ # Controladores REST que manejan las solicitudes HTTP  <br />
├── dto/ # Clases que representan los datos transferidos entre capas  <br />
├── entity/ # Entidades JPA que representan las tablas de la base de datos  <br />
├── mapper/ # Mappers entre entidades y DTOs <br />
├── repositories/ # Repositorios JPA (acceso a datos) <br />
├── service/ # Interfaces de lógica de negocio <br />
├── service/impl/ # Implementaciones de servicios <br />
├── utils/ # Clases utilitarias (mapeo, constantes, validaciones) <br />
└── AppEcommerceApplication.java # Clase principal  <br />

⚙️ Tecnologías Usadas
Java 17
Spring Boot 2.5.5 
Docker + Docker Compose
JUnit 5
Springdoc OpenAPI (Swagger)
BD H2
Lombok
MapStruct 
RestTemplate para comunicación entre microservicios

🚀 Instrucciones de instalación y ejecución
Requisitos previos:
Docker y Docker Compose instalados
docker-compose build
docker-compose up

Swagger UI:
http://localhost:8080/swagger-ui/index.html#/

🔐 Autenticación
La mayoría de los endpoints requieren un token JWT, que se puede obtener con el siguiente servicio:
POST - http://localhost:8080/api/auth/login?email=juan.perez2@example.com&password=admin

🪵 Logging
Se utiliza Logback como framework de logging, el cual está configurado desde el archivo logback.xml
Los logs de la aplicación quedan en la siguiente ruta con base en su configuración:
C:\logs\pruebaLinkTic.log



✍️ Autor
Luis Fernando Montoya
Desarrollador Backend Java + Angular

