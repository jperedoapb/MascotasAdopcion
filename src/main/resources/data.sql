INSERT INTO TB_TIPO_MASCOTA (nombre, version) VALUES ('Perro', 0);
INSERT INTO TB_TIPO_MASCOTA (nombre, version) VALUES ('Gato', 0);
INSERT INTO TB_TIPO_MASCOTA (nombre, version) VALUES ('Hámster', 0);
INSERT INTO TB_TIPO_MASCOTA (nombre, version) VALUES ('Conejo', 0);
INSERT INTO TB_TIPO_MASCOTA (nombre, version) VALUES ('Loro', 0);
INSERT INTO TB_TIPO_MASCOTA (nombre, version) VALUES ('Canario', 0);
INSERT INTO TB_TIPO_MASCOTA (nombre, version) VALUES ('Serpiente', 0);
INSERT INTO TB_TIPO_MASCOTA (nombre, version) VALUES ('Tortuga', 0);
INSERT INTO TB_TIPO_MASCOTA (nombre, version) VALUES ('Pez de agua dulce', 0);
-- Insertar Usuarios
INSERT INTO TB_USUARIO (nombre, correo_electronico, telefono) VALUES ('Juan Pérez', 'juan@example.com', '123456789');
INSERT INTO TB_USUARIO (nombre, correo_electronico, telefono) VALUES ('María Gómez', 'maria@example.com', '987654321');
INSERT INTO TB_USUARIO (nombre, correo_electronico, telefono) VALUES ('Carlos Rodriguez', 'carlos@example.com', '555123456');

-- Insertar Mascotas (después de insertar Tipos de Mascota)
INSERT INTO TB_MASCOTA (nombre, edad, disponible, tipo_mascota_id) VALUES ('Firulais', 3, TRUE, 1); -- Perro
INSERT INTO TB_MASCOTA (nombre, edad, disponible, tipo_mascota_id) VALUES ('Michi', 2, TRUE, 2); -- Gato
INSERT INTO TB_MASCOTA (nombre, edad, disponible, tipo_mascota_id) VALUES ('Piolín', 1, FALSE, 3); -- Ave

-- Insertar Adopciones (después de insertar Mascotas y Usuarios)
INSERT INTO TB_ADOPCION (mascota_id, usuario_id, fecha_adopcion) VALUES (1, 1, '2024-07-20'); -- Firulais adoptado por Juan
INSERT INTO TB_ADOPCION (mascota_id, usuario_id, fecha_adopcion) VALUES (2, 2, '2024-07-15'); -- Michi adoptado por María