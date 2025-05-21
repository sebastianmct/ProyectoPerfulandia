# 🧴 Perfulandia Project - Microservicios con Spring Boot

## 📌 Descripción
Proyecto desarrollado como parte del curso de desarrollo fullstack. Implementa una arquitectura de microservicios usando Spring Boot, Maven y Oracle Cloud Infrastructure (OCI). Cada microservicio gestiona una parte específica del sistema: clientes, productos, ventas, stock y ubicaciones.

---

## ⚙️ Tecnologías Utilizadas
- Java 17  
- Spring Boot  
- Spring Web / Spring Data JPA  
- Oracle Cloud (Base de datos con Wallet)  
- Maven  
- Git & GitHub  
- Postman / Apidog (para pruebas de APIs REST)  
- IntelliJ IDEA  

---

## 🗂️ Estructura del Proyecto

Cada microservicio es un módulo independiente con sus propios controladores, servicios, repositorios y modelos:

- `client-service`  
- `product-service`  
- `sale-service`  
- `stock-service`  
- `ubication-service`

---

## 🛠️ Configuración del Proyecto

### Conexión a Oracle Cloud:
```properties
spring.datasource.url=jdbc:oracle:thin:@springapi_high?TNS_ADMIN=C:/Users/sebas/Downloads/Wallet_SpringAPI
spring.datasource.username=ADMIN
spring.datasource.password=********
spring.jpa.hibernate.ddl-auto=create
```

> ⚠️ Asegúrate de tener el Wallet configurado correctamente en tu máquina para conexión a la base de datos.

---

## 📦 Cómo Ejecutar

```bash
# Clonar el repositorio
git clone https://github.com/sebastianmct/ProyectoPerfulandia.git

# Importar como proyecto Maven en IntelliJ IDEA
# Ejecutar cada microservicio desde su clase principal con @SpringBootApplication
```

---

## 📮 Endpoints Principales

| Microservicio | Método | Endpoint                                     |
|---------------|--------|----------------------------------------------|
| Cliente       | GET    | `/clients`                                   |
| Cliente       | POST   | `/clients`                                   |
| Cliente       | PUT    | `/clients/{id}`                              |
| Cliente       | DELETE | `/clients/{id}`                              |
| Producto      | GET    | `/products`                                  |
| Producto      | POST   | `/products`                                  |
| Producto      | PUT    | `/products/{id}`                             |
| Producto      | DELETE | `/products/{id}`                             |
| Venta         | GET    | `/sales`                                     |
| Venta         | POST   | `/sales`                                     |
| Venta         | PUT    | `/sales/{id}`                                |
| Venta         | DELETE | `/sales/{id}`                                |
| Stock         | GET    | `/stock/{productId}/{ubicationId}`           |
| Stock         | POST   | `/stock`                                     |
| Stock         | PUT    | `/stock/{productId}/{ubicationId}`           |
| Stock         | DELETE | `/stock/{productId}/{ubicationId}`           |
| Ubicación     | GET    | `/ubications`                                |
| Ubicación     | POST   | `/ubications`                                |
| Ubicación     | PUT    | `/ubications/{id}`                           |
| Ubicación     | DELETE | `/ubications/{id}`                           |

---

## ✅ Pruebas

Las operaciones CRUD fueron validadas manualmente utilizando [Postman](https://www.postman.com/) y [Apidog](https://apidog.com/) para los distintos endpoints.

Se realizaron pruebas de:
- Creación de recursos (`POST`)
- Consulta de recursos (`GET`)
- Actualización de recursos (`PUT`)
- Eliminación de recursos (`DELETE`)

---

## 👨‍💻 Autores

**Omar bravo , Sebastian Caamaño , Vicente Ordenes**  
🔗 [Repositorio GitHub](https://github.com/sebastianmct/ProyectoPerfulandia)
