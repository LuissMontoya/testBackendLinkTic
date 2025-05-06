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
Java 17 <br />
Spring Boot 2.5.5 <br /> 
Docker + Docker Compose <br />
JUnit 5 <br />
Springdoc OpenAPI (Swagger) <br />
BD H2 <br />
Lombok <br />
MapStruct  <br />
RestTemplate para comunicación entre microservicios <br />

🚀 Instrucciones de instalación y ejecución <br />
Requisitos previos: <br />
Docker y Docker Compose instalados
docker-compose build <br />
docker-compose up <br />

Swagger UI: <br />
http://localhost:8080/swagger-ui/index.html#/

🔐 Autenticación <br />
La mayoría de los endpoints requieren un token JWT, que se puede obtener con el siguiente servicio:
POST - http://localhost:8080/api/auth/login?email=juan.perez2@example.com&password=admin

🪵 Logging <br />
Se utiliza Logback como framework de logging, el cual está configurado desde el archivo logback.xml
Los logs de la aplicación quedan en la siguiente ruta con base en su configuración:
C:\logs\pruebaLinkTic.log

✅ Tests Automatizados <br />
El proyecto incluye una suite de pruebas integradas (@SpringBootTest) utilizando MockMvc  <br />
para validar el comportamiento de los endpoints del controlador ProductController. <br />
A continuación se describen los principales escenarios probados: <br />

🔐 Autenticación: <br /> 
Obtención de token mediante login con credenciales válidas. <br /> 

📦 Consulta de productos: <br /> 
✔️ Consulta exitosa de producto por ID (status 200).<br />  
❌ Consulta de producto inexistente retorna código 404 con mensaje NOT_FOUND. <br /> 

➕ Creación de producto: <br /> 
✔️ Creación exitosa de un producto y validación de los atributos retornados (status 201). <br /> 

🔄 Actualización de producto: <br /> 
✔️ Actualización correcta de producto existente y validación de respuesta (status 200). <br />  
❌ Actualización de producto no existente devuelve 404 con mensaje de error.   <br />  
⚠️ Error por datos incompletos o inválidos retorna error 400 con mensaje correspondiente.  <br />  


✍️ Autor <br />
Luis Fernando Montoya <br />
Desarrollador Backend Java + Angular

