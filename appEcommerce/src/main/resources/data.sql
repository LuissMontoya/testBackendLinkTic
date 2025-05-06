CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(50),
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


INSERT INTO users (name, email, password, phone) VALUES ('Luis Fernando Montoya', 'luis@example.com', '$2a$10$gFhHaxmsK5V4ZFyis4siXukr2Jvb2uYo3wWZVrFvSUhH2MO4SR/iq', '123-456-7890');
INSERT INTO users (name, email, password, phone) VALUES ('Carlos García', 'carlos@example.com', '$2a$10$gFhHaxmsK5V4ZFyis4siXukr2Jvb2uYo3wWZVrFvSUhH2MO4SR/iq', '555-555-5555');
INSERT INTO users (name, email, password, phone) VALUES ('Juan Perez', 'juan.perez2@example.com', '$2a$10$gFhHaxmsK5V4ZFyis4siXukr2Jvb2uYo3wWZVrFvSUhH2MO4SR/iq', '555-555-5555');

CREATE TABLE IF NOT EXISTS category (
    id_category INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL
);

INSERT INTO category (id_category, name, status) VALUES (1, 'Computadoras', 'ACTIVO');
INSERT INTO category (id_category, name, status) VALUES (2, 'Accesorios', 'ACTIVO');
INSERT INTO category (id_category, name, status) VALUES (3, 'Monitores', 'ACTIVO');
INSERT INTO category (id_category, name, status) VALUES (4, 'Muebles', 'ACTIVO');

CREATE TABLE IF NOT EXISTS products (
    id_product INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL,
    id_category INT,
    CONSTRAINT fk_category FOREIGN KEY (id_category) REFERENCES category(id_category)
);


INSERT INTO products (id_product, name, description, price, stock, id_category) VALUES (1, 'Laptop Lenovo', 'Laptop 15 pulgadas, 8GB RAM, 256GB SSD', 2500.00, 10, 1);
INSERT INTO products (id_product, name, description, price, stock, id_category) VALUES (2, 'Mouse inalámbrico', 'Mouse con conectividad Bluetooth', 45.50, 50, 2);
INSERT INTO products (id_product, name, description, price, stock, id_category) VALUES (3, 'Teclado mecánico', 'Teclado con retroiluminación RGB', 120.00, 25, 2);
INSERT INTO products (id_product, name, description, price, stock, id_category) VALUES (4, 'Monitor Samsung 24"', 'Monitor Full HD 24 pulgadas', 780.00, 15, 3);
INSERT INTO products (id_product, name, description, price, stock, id_category) VALUES (5, 'Silla ergonómica', 'Silla de oficina con soporte lumbar', 430.00, 8, 4);
