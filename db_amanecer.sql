-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 12, 2025 at 11:39 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_amanecer`
--

-- --------------------------------------------------------

--
-- Table structure for table `bloque_disponible`
--

CREATE TABLE `bloque_disponible` (
  `id` int(11) NOT NULL,
  `hora_inicio` time(6) DEFAULT NULL,
  `hora_fin` time(6) DEFAULT NULL,
  `id_rango_duracion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bloque_disponible`
--

INSERT INTO `bloque_disponible` (`id`, `hora_inicio`, `hora_fin`, `id_rango_duracion`) VALUES
(1, '09:00:00.000000', '11:00:00.000000', 3),
(2, '11:00:00.000000', '13:00:00.000000', 3);

-- --------------------------------------------------------

--
-- Table structure for table `categoria_terapia`
--

CREATE TABLE `categoria_terapia` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `categoria_terapia`
--

INSERT INTO `categoria_terapia` (`id`, `nombre`, `descripcion`) VALUES
(1, 'Terapia Física', 'Mejora la movilidad y alivia dolores musculares.'),
(2, 'Terapia Ocupacional', 'Ayuda a recuperar habilidades para las actividades diarias.');

-- --------------------------------------------------------

--
-- Table structure for table `cita`
--

CREATE TABLE `cita` (
  `id` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `fecha_reserva` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `estado` int(11) NOT NULL,
  `duracion_total` varchar(255) DEFAULT NULL,
  `total` decimal(38,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cita`
--

INSERT INTO `cita` (`id`, `id_cliente`, `fecha_reserva`, `estado`, `duracion_total`, `total`) VALUES
(1, 4, '2025-05-13 15:30:00', 1, '00:30', 50.00),
(2, 5, '2025-05-14 20:00:00', 0, '01:00', 90.00),
(3, 6, '2025-05-15 14:00:00', 1, '00:45', 70.50);

-- --------------------------------------------------------

--
-- Table structure for table `cliente`
--

CREATE TABLE `cliente` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `dni` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `genero` varchar(255) DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `estado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cliente`
--

INSERT INTO `cliente` (`id`, `nombre`, `apellido`, `dni`, `telefono`, `genero`, `id_usuario`, `estado`) VALUES
(4, 'María Luisa', 'González Pérez', '12345678', '999888777', 'femenino', 2, 1),
(5, 'Antony aq', 'Aranda', '12345678', '999888777', 'femenino', 1, 1),
(6, 'Andrea', 'Pérez Torr', '75839264', '987654321', 'F', 4, 1);

-- --------------------------------------------------------

--
-- Table structure for table `detalle_cita`
--

CREATE TABLE `detalle_cita` (
  `id` bigint(20) NOT NULL,
  `id_cita` int(11) NOT NULL,
  `id_terapeuta_sede_servicio` int(11) NOT NULL,
  `sub_monto` decimal(38,2) DEFAULT NULL,
  `id_bloque_disponible` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `detalle_cita`
--

INSERT INTO `detalle_cita` (`id`, `id_cita`, `id_terapeuta_sede_servicio`, `sub_monto`, `id_bloque_disponible`) VALUES
(1, 1, 1, 89.19, 2),
(2, 1, 3, 100.19, 1);

-- --------------------------------------------------------

--
-- Table structure for table `metodo_pago`
--

CREATE TABLE `metodo_pago` (
  `id` int(11) NOT NULL,
  `codigo` varchar(50) NOT NULL,
  `nombre` varchar(160) NOT NULL,
  `descripcion` text NOT NULL,
  `proveedor` varchar(100) NOT NULL,
  `config` text NOT NULL,
  `activo` tinyint(1) NOT NULL,
  `id_tipo_pago` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pago`
--

CREATE TABLE `pago` (
  `id` int(11) NOT NULL,
  `cita_id` int(11) NOT NULL,
  `fecha_emision` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `monto_total` double(10,2) NOT NULL,
  `estado_pago` bigint(20) NOT NULL,
  `id_metodo_pago` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `rango_duracion`
--

CREATE TABLE `rango_duracion` (
  `id` int(11) NOT NULL,
  `duracion` time(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rango_duracion`
--

INSERT INTO `rango_duracion` (`id`, `duracion`) VALUES
(1, '00:30:00.000000'),
(2, '01:30:00.000000'),
(3, '02:00:00.000000');

-- --------------------------------------------------------

--
-- Table structure for table `sede`
--

CREATE TABLE `sede` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `distrito` varchar(255) DEFAULT NULL,
  `departamento` varchar(255) DEFAULT NULL,
  `hora_abierto` time(6) DEFAULT NULL,
  `hora_cerrado` time(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sede`
--

INSERT INTO `sede` (`id`, `nombre`, `direccion`, `distrito`, `departamento`, `hora_abierto`, `hora_cerrado`) VALUES
(1, 'Sede Central', 'Av. Los Olivos 123', 'San Isidro', 'Lima', '08:00:00.000000', '18:00:00.000000'),
(2, 'Sede Sur xd', 'Calle 45 Sur N° 456', 'Santiago de Surco', 'Lima', '09:00:00.000000', '17:00:00.000000');

-- --------------------------------------------------------

--
-- Table structure for table `sede_servicio`
--

CREATE TABLE `sede_servicio` (
  `id` int(11) NOT NULL,
  `id_sede` int(11) NOT NULL,
  `id_servicio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sede_servicio`
--

INSERT INTO `sede_servicio` (`id`, `id_sede`, `id_servicio`) VALUES
(1, 1, 2),
(2, 2, 3),
(3, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `servicio`
--

CREATE TABLE `servicio` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `precio` decimal(38,2) DEFAULT NULL,
  `id_rango_duracion` int(11) NOT NULL,
  `id_categoria_terapia` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `servicio`
--

INSERT INTO `servicio` (`id`, `nombre`, `descripcion`, `precio`, `id_rango_duracion`, `id_categoria_terapia`) VALUES
(1, 'Masaje Descontracturante', 'Alivia la tensión muscular y mejora la circulación.', 90.00, 2, 1),
(2, 'Rehabilitación Postoperatoria', 'Ejercicios personalizados para recuperación física.', 120.50, 3, 2),
(3, 'Terapia Cognitiva', 'Mejora habilidades mentales y de concentración.', 80.00, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `terapeuta`
--

CREATE TABLE `terapeuta` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `dni` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `terapeuta`
--

INSERT INTO `terapeuta` (`id`, `nombre`, `apellido`, `dni`) VALUES
(1, 'Carlos', 'Ramírez', '74583920'),
(2, 'Lucía', 'Fernández', '48291647'),
(3, 'Javier', 'Torres', '63927815');

-- --------------------------------------------------------

--
-- Table structure for table `terapeuta_sede_servicio`
--

CREATE TABLE `terapeuta_sede_servicio` (
  `id` int(11) NOT NULL,
  `id_terapeuta` int(11) NOT NULL,
  `id_sede_servicio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `terapeuta_sede_servicio`
--

INSERT INTO `terapeuta_sede_servicio` (`id`, `id_terapeuta`, `id_sede_servicio`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 2, 2),
(4, 3, 2);

-- --------------------------------------------------------

--
-- Table structure for table `tipo_pago`
--

CREATE TABLE `tipo_pago` (
  `id` int(11) NOT NULL,
  `codigo` varchar(50) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text NOT NULL,
  `activo` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `estado` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `rol` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`id`, `correo`, `password`, `estado`, `nombre`, `rol`) VALUES
(1, '3456antony@gmail.com', 'eshn28', 1, 'Antony', 'admin'),
(2, 'Luna@gmail.com', 'eshn28', 1, 'Luna', 'admin'),
(4, 'Libro@gmail.com', '1234', 1, 'book - ipcna', 'cliente');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bloque_disponible`
--
ALTER TABLE `bloque_disponible`
  ADD PRIMARY KEY (`id`),
  ADD KEY `bloque_disponible_rango_duracion` (`id_rango_duracion`);

--
-- Indexes for table `categoria_terapia`
--
ALTER TABLE `categoria_terapia`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cita`
--
ALTER TABLE `cita`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cita_id_cliente` (`id_cliente`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ibfk_cliente_id_usuario` (`id_usuario`);

--
-- Indexes for table `detalle_cita`
--
ALTER TABLE `detalle_cita`
  ADD PRIMARY KEY (`id`),
  ADD KEY `detalle_cita_id_terapeuta_sede_servicio` (`id_terapeuta_sede_servicio`),
  ADD KEY `detalle_cita_id_cita` (`id_cita`),
  ADD KEY `detalle_cita_id_bloque_disponible` (`id_bloque_disponible`);

--
-- Indexes for table `metodo_pago`
--
ALTER TABLE `metodo_pago`
  ADD PRIMARY KEY (`id`),
  ADD KEY `metodo_pago_tipo_Pago` (`id_tipo_pago`);

--
-- Indexes for table `pago`
--
ALTER TABLE `pago`
  ADD PRIMARY KEY (`id`),
  ADD KEY `pago_id_metodo_pago` (`id_metodo_pago`);

--
-- Indexes for table `rango_duracion`
--
ALTER TABLE `rango_duracion`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sede`
--
ALTER TABLE `sede`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sede_servicio`
--
ALTER TABLE `sede_servicio`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sede_servicio_id_sede` (`id_sede`),
  ADD KEY `sede_servicio_id_servicio` (`id_servicio`);

--
-- Indexes for table `servicio`
--
ALTER TABLE `servicio`
  ADD PRIMARY KEY (`id`),
  ADD KEY `servicio_id_rango_duracion` (`id_rango_duracion`),
  ADD KEY `servicio_id_categoria_terapia` (`id_categoria_terapia`);

--
-- Indexes for table `terapeuta`
--
ALTER TABLE `terapeuta`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `terapeuta_sede_servicio`
--
ALTER TABLE `terapeuta_sede_servicio`
  ADD PRIMARY KEY (`id`),
  ADD KEY `tss_id_terapeuta` (`id_terapeuta`),
  ADD KEY `tss_id_sede_servicio` (`id_sede_servicio`);

--
-- Indexes for table `tipo_pago`
--
ALTER TABLE `tipo_pago`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `correo` (`correo`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bloque_disponible`
--
ALTER TABLE `bloque_disponible`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `categoria_terapia`
--
ALTER TABLE `categoria_terapia`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `cita`
--
ALTER TABLE `cita`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `detalle_cita`
--
ALTER TABLE `detalle_cita`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `metodo_pago`
--
ALTER TABLE `metodo_pago`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pago`
--
ALTER TABLE `pago`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `rango_duracion`
--
ALTER TABLE `rango_duracion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `sede`
--
ALTER TABLE `sede`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `sede_servicio`
--
ALTER TABLE `sede_servicio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `servicio`
--
ALTER TABLE `servicio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `terapeuta`
--
ALTER TABLE `terapeuta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `terapeuta_sede_servicio`
--
ALTER TABLE `terapeuta_sede_servicio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tipo_pago`
--
ALTER TABLE `tipo_pago`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bloque_disponible`
--
ALTER TABLE `bloque_disponible`
  ADD CONSTRAINT `bloque_disponible_rango_duracion` FOREIGN KEY (`id_rango_duracion`) REFERENCES `rango_duracion` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `cita`
--
ALTER TABLE `cita`
  ADD CONSTRAINT `cita_id_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `ibfk_cliente_id_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `detalle_cita`
--
ALTER TABLE `detalle_cita`
  ADD CONSTRAINT `detalle_cita_id_bloque_disponible` FOREIGN KEY (`id_bloque_disponible`) REFERENCES `bloque_disponible` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `detalle_cita_id_cita` FOREIGN KEY (`id_cita`) REFERENCES `cita` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detalle_cita_id_terapeuta_sede_servicio` FOREIGN KEY (`id_terapeuta_sede_servicio`) REFERENCES `terapeuta_sede_servicio` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `metodo_pago`
--
ALTER TABLE `metodo_pago`
  ADD CONSTRAINT `metodo_pago_tipo_Pago` FOREIGN KEY (`id_tipo_pago`) REFERENCES `tipo_pago` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `pago`
--
ALTER TABLE `pago`
  ADD CONSTRAINT `pago_id_metodo_pago` FOREIGN KEY (`id_metodo_pago`) REFERENCES `metodo_pago` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sede_servicio`
--
ALTER TABLE `sede_servicio`
  ADD CONSTRAINT `sede_servicio_id_sede` FOREIGN KEY (`id_sede`) REFERENCES `sede` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `sede_servicio_id_servicio` FOREIGN KEY (`id_servicio`) REFERENCES `servicio` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `servicio`
--
ALTER TABLE `servicio`
  ADD CONSTRAINT `servicio_id_categoria_terapia` FOREIGN KEY (`id_categoria_terapia`) REFERENCES `categoria_terapia` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `servicio_id_rango_duracion` FOREIGN KEY (`id_rango_duracion`) REFERENCES `rango_duracion` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `terapeuta_sede_servicio`
--
ALTER TABLE `terapeuta_sede_servicio`
  ADD CONSTRAINT `tss_id_sede_servicio` FOREIGN KEY (`id_sede_servicio`) REFERENCES `sede_servicio` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tss_id_terapeuta` FOREIGN KEY (`id_terapeuta`) REFERENCES `terapeuta` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
