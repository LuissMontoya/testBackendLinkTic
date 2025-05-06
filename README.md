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


