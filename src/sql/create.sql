CREATE DATABASE base;

CREATE TABLE base.Cliente(
    clienteId int NOT NULL AUTO_INCREMENT,
    nombre varchar(50) NOT NULL,
    apellido varchar(50) NOT NULL,
    dni int,
    PRIMARY KEY(clienteId)
);

CREATE TABLE base.Producto(
    productoId int NOT NULL AUTO_INCREMENT,
    descripcion varchar(255),
    codigo varchar(255),
    stock int,
    precio FLOAT(5, 2),
    PRIMARY KEY(productoId)
);

CREATE TABLE base.Venta(
    ventaId int NOT NULL AUTO_INCREMENT,
    total FLOAT(5, 2),
    clienteid int,
    fecha datetime,
    PRIMARY KEY(ventaId),
    CONSTRAINT FK_cliente FOREIGN KEY (clienteId)
);

CREATE TABLE base.DetalleVenta(
    detalleVentaId int NOT NULL AUTO_INCREMENT,
    ventaId int,
    cantidad int,
    productoId int,
    precio FLOAT(10, 2),
    PRIMARY KEY (detalleVentaId),
    CONSTRAINT FkCcomprobante FOREIGN KEY (ventaId),
    CONSTRAINT FkProducto FOREIGN KEY (productoId),
);




