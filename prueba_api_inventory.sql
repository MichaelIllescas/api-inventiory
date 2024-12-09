-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-12-2024 a las 10:17:40
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `prueba_api_inventory`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `id` bigint(20) NOT NULL,
  `descripcion` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id`, `descripcion`) VALUES
(4, 'ALIMENTOS'),
(1, 'ELECTRONICA'),
(2, 'HOGAR'),
(5, 'OTROS'),
(3, 'ROPA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id` bigint(20) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `marca` varchar(255) DEFAULT NULL,
  `nombre` varchar(100) NOT NULL,
  `precio` double NOT NULL,
  `stock` int(11) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `categoria_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id`, `descripcion`, `marca`, `nombre`, `precio`, `stock`, `user_id`, `categoria_id`) VALUES
(12, 'de oro', 'cara', 'cachuca', 1225.99, 1, 1, 1),
(13, 'celeste 1Litro', 'Stangley', 'termo', 5000, 40, 1, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('ADMIN','USER') DEFAULT NULL,
  `fecha_registro` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `email`, `enabled`, `first_name`, `last_name`, `password`, `role`, `fecha_registro`) VALUES
(1, 'joni.illes@hotmail.com', b'0', 'Jonathan', 'Illescas', '$2a$10$thRPBMGpwYijh11BEINJIOKYUdKw3MoEC.SPxB910Yp8sfQ5lm7zu', 'USER', '2024-12-08 18:08:58.000000'),
(3, 'joni.illescas@hotmail.com', b'0', 'Jonathan', 'Illescas', '$2a$10$0KEsfpIkKxFo9SS1caFO..MbMaKEewyNUzG8hBBD23YInYMkdaY2a', 'USER', '2024-12-08 18:09:10.000000'),
(5, 'pepito@hotmail.com', b'0', 'Jonathan', 'Illescas', '$2a$10$ez4dVlkUhH1n.DoWKAf.6.wNniV6880OX6lTIiMMC1SY77VMuj6za', 'USER', '2024-12-08 18:09:15.000000'),
(6, 'juanse@hotmail.com', b'0', 'Jonathan', 'Illescas', '$2a$10$ZiufpJ68NA8Yetu/Z0WlduwofH9lkaYKEKGctrBFc4Z2gR8znVVFW', 'USER', '2024-12-08 21:12:10.000000'),
(7, 'jose@gmail.com', b'0', 'jose', 'carlo', '$2a$10$z3ov9SndoWJvS0bWc7Zvk.Ud1ReYPTlMVH4NHrfUBLPxU8oEdhJ4K', 'ADMIN', '2024-12-08 21:20:59.000000'),
(8, 'juan@gmail.com', b'0', 'juan', 'perez', '$2a$10$4z5nCGsTqR7kY4TEyNh2eOKVAQyc7xptSF8owd1ZJCzb05R5eICnW', 'ADMIN', '2024-12-08 21:24:22.000000'),
(9, 'pepe@gmail.com', b'0', 'pepe', 'argento', '$2a$10$x.Ham/8T0atOajzMpNjS.upf7J3F5PDl.HYzF1NgQORVP27kBRUgq', 'ADMIN', '2024-12-08 21:29:45.000000');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_meq7xehc2jvf9km87vg6ayorq` (`descripcion`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKpml2u7ks6cjw2yw2es4pyysw4` (`nombre`,`descripcion`,`marca`),
  ADD KEY `FKmi33q9040kfk6ad4ap74x6pdw` (`user_id`),
  ADD KEY `FKodqr7965ok9rwquj1utiamt0m` (`categoria_id`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `FKmi33q9040kfk6ad4ap74x6pdw` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKodqr7965ok9rwquj1utiamt0m` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
