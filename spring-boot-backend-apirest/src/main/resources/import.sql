 /* Populate tabla clientes */
 
 INSERT INTO regiones (id, nombre) VALUES (1, 'Sudamerica');
 INSERT INTO regiones (id, nombre) VALUES (2, 'Centroamerica');
 INSERT INTO regiones (id, nombre) VALUES (3, 'Norteamerica');
 INSERT INTO regiones (id, nombre) VALUES (4, 'Europa');
 INSERT INTO regiones (id, nombre) VALUES (5, 'Asia');
 INSERT INTO regiones (id, nombre) VALUES (6, 'Africa');
 INSERT INTO regiones (id, nombre) VALUES (7, 'Oceania');
 INSERT INTO regiones (id, nombre) VALUES (8, 'Antartida'); 
 INSERT INTO clientes (region_id, nombre, apellido, email, create_at) values(1, 'Cristian', 'Pena', 'capem76@hotmail.com', '2021-04-28');
 INSERT INTO clientes (region_id, nombre, apellido, email, create_at) values(2, 'Pedro', 'Gonzales', 'pgonzales@hotmail.com', '2021-03-28');
 INSERT INTO clientes (region_id, nombre, apellido, email, create_at) values(4, 'Alberto', 'Monaco', 'amonaco@hgmail.com', '2021-01-08');
 INSERT INTO clientes (region_id, nombre, apellido, email, create_at) values(3,'Pedro Pablo', 'Espina', 'ppespina@gmail.com', '2021-02-18');
 INSERT INTO clientes (region_id, nombre, apellido, email, create_at) values(5,'Pablo Alberto', 'Espinoza', 'paespinoza76@gmail.com', '2021-01-29');
 INSERT INTO clientes (region_id, nombre, apellido, email, create_at) values(7,'Juan Carlos', 'Gutti', 'gcgutti@hotmail.com', '2020-06-29');
 INSERT INTO clientes (region_id, nombre, apellido, email, create_at) values(8,'Jose Maria', 'Mezza', 'jomezza@hotmail.com', '2020-05-22');
 INSERT INTO clientes (region_id, nombre, apellido, email, create_at) values(7,'Juan Maria', 'Messina', 'juanma@hotmail.com', '2020-11-21');
 INSERT INTO clientes (region_id, nombre, apellido, email, create_at) values(6,'Jesus', 'Nutria', 'jnutria@hotmail.com', '2020-10-18');
 INSERT INTO clientes (region_id, nombre, apellido, email, create_at) values(5,'Nacho', 'Cano', 'nachocano@hotmail.com', '2019-09-09');
 INSERT INTO clientes (region_id, nombre, apellido, email, create_at) values(4, 'Isabel', 'Astorga', 'isastorga@hotmail.com', '2019-08-19');
 INSERT INTO clientes (region_id, nombre, apellido, email, create_at) values(4, 'Francisca', 'Patacabra', 'fpatacabra@hotmail.com', '2020-04-06');
 INSERT INTO clientes (region_id, nombre, apellido, email, create_at) values(4, 'Veronica', 'Pesadilla', 'veropesadilla@hotmail.com', '2020-05-31');
 INSERT INTO clientes (region_id, nombre, apellido, email, create_at) values(3, 'Magali', 'Campo Verde', 'mcampov@hotmail.com', '2021-04-28');
 INSERT INTO clientes (region_id, nombre, apellido, email, create_at) values(3, 'Cristian', 'Pena', 'capem76@hotmail.com', '2021-04-28');
 INSERT INTO clientes (region_id, nombre, apellido, email, create_at) values(3, 'Pedro', 'Gonzales', 'pgonzales@hotmail.com', '2021-03-28');
 INSERT INTO clientes (region_id, nombre, apellido, email, create_at) values(2, 'Alberto', 'Monaco', 'amonaco@hgmail.com', '2021-01-08');
 INSERT INTO clientes (region_id, nombre, apellido, email, create_at) values(2, 'Pedro Pablo', 'Espina', 'ppespina@gmail.com', '2021-02-18');
 INSERT INTO clientes (region_id, nombre, apellido, email, create_at) values(2, 'Pablo Alberto', 'Espinoza', 'paespinoza76@gmail.com', '2021-01-29');
 INSERT INTO clientes (region_id, nombre, apellido, email, create_at) values(1, 'Juan Carlos', 'Gutti', 'gcgutti@hotmail.com', '2020-06-29');
 INSERT INTO clientes (region_id, nombre, apellido, email, create_at) values(1, 'Jose Maria', 'Mezza', 'jomezza@hotmail.com', '2020-05-22');
 INSERT INTO clientes (region_id, nombre, apellido, email, create_at) values(1, 'Juan Maria', 'Messina', 'juanma@hotmail.com', '2020-11-21');
 INSERT INTO clientes (region_id, nombre, apellido, email, create_at) values(5, 'Jesus', 'Nutria', 'jnutria@hotmail.com', '2020-10-18');
 INSERT INTO clientes (region_id, nombre, apellido, email, create_at) values(6, 'Nacho', 'Cano', 'nachocano@hotmail.com', '2019-09-09');
 INSERT INTO clientes (region_id, nombre, apellido, email, create_at) values(7, 'Isabel', 'Astorga', 'isastorga@hotmail.com', '2019-08-19');
 INSERT INTO clientes (region_id, nombre, apellido, email, create_at) values(8, 'Francisca', 'Patacabra', 'fpatacabra@hotmail.com', '2020-04-06');
 
 /* Creamos algunos usuarios con sus roles */
 INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('andres', '$2a$10$bw5VQyPlBiHy/M/vxlNsIONlF.D6jilOpVZhwrtGgPwCfTCzTprZy', 1, 'Andres', 'Guzman', 'aguzman@chile.cl');
 INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('admin', '$2a$10$xRduA2lwKawgnQpS.emX3Oe/XH.Zbam2pBC2AXxmvhdVZgCgKcjku', 1, 'Cristian', 'Pena', 'apena@gmail.com');
 
 INSERT INTO `roles` (nombre) VALUES ('ROLE_USER');
 INSERT INTO `roles` (nombre) VALUES ('ROLE_ADMIN');
 
 INSERT INTO `usuario_roles` (usuario_id, role_id) VALUES (1, 1);
 INSERT INTO `usuario_roles` (usuario_id, role_id) VALUES (2, 2);
 INSERT INTO `usuario_roles` (usuario_id, role_id) VALUES (2, 1);
 
 /* Populate tabla productos */
 INSERT INTO productos (nombre, precio, create_at) VALUES ('Panasonic Pantalla LCD', 259990, now() );
 INSERT INTO productos (nombre, precio, create_at) VALUES ('Sony Camara digital DSC-W320B', 123490, now() );
 INSERT INTO productos (nombre, precio, create_at) VALUES ('Apple ipod shuffle', 1499990, now() );
 INSERT INTO productos (nombre, precio, create_at) VALUES ('Sony Notebook Z119', 37990, now() );
 INSERT INTO productos (nombre, precio, create_at) VALUES ('Hewlett Packard Multifuncional F2280', 69990, now() );
 INSERT INTO productos (nombre, precio, create_at) VALUES ('Bianchi Bicicleta Aro 26', 69990, now() );
 INSERT INTO productos (nombre, precio, create_at) VALUES ('Mica Comoda 5 cajones', 299990, now() );
 INSERT INTO productos (nombre, precio, create_at) VALUES ('Samsung smartphone A23', 588521, now() );
 
 /* Populate algunas facturas */
 INSERT INTO  facturas ( descripcion, observacion, cliente_id, create_at ) VALUES ( 'Factura equipos de oficina', null, 1, now() ); 
 INSERT INTO  facturas_items ( cantidad, factura_id, producto_id) VALUES (1, 1, 1);
 INSERT INTO  facturas_items ( cantidad, factura_id, producto_id) VALUES (2, 1, 4);
 INSERT INTO  facturas_items ( cantidad, factura_id, producto_id) VALUES (1, 1, 5);
 INSERT INTO  facturas_items ( cantidad, factura_id, producto_id) VALUES (1, 1, 7);
 
 INSERT INTO  facturas ( descripcion, observacion, cliente_id, create_at ) VALUES ( 'Factura de Bicicleta', 'Alguna nota importante!', 1, now() ); 
 INSERT INTO facturas_items ( cantidad, factura_id, producto_id) VALUES (3, 2, 6);
 
 
 
 