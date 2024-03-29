
CREATE TABLE IF NOT EXISTS `User` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `isAdmin` tinyint(1) DEFAULT NULL,
  `createdDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=24 ;


INSERT INTO `User` (`id`, `name`, `age`, `isAdmin`, `createdDate`) VALUES
(3, 'Петров', 34, 0, '2015-08-07 10:05:12'),
(4, 'Дон Педро', 26, 0, '2015-08-07 10:05:12'),
(5, 'Хуанита', 30, 0, '2015-08-06 21:00:00'),
(7, 'Кончита', 30, 0, '2015-08-07 15:12:47'),
(8, 'Мартемьян', 30, 0, '2015-08-07 15:15:49'),
(9, 'Хулио', 22, 0, '2015-08-07 15:21:06'),
(10, 'Вован', 23, 0, '2015-08-07 15:21:16'),
(11, 'Пабло', 24, 0, '2015-08-07 15:21:26'),
(12, 'Дуркин', 24, 0, '2015-08-07 15:21:41'),
(13, 'Стрункин', 25, 0, '2015-08-07 15:22:06'),
(14, 'Богатырев', 26, 1, '2015-08-07 15:25:15'),
(15, 'Курлов', 26, 1, '2015-08-07 15:25:32'),
(16, 'Батанкин', 27, 1, '2015-08-07 15:25:46'),
(17, 'Валенков', 28, 1, '2015-08-07 15:25:58'),
(18, 'Трошкин', 29, 1, '2015-08-07 15:26:17'),
(19, 'Матрешкин', 28, 1, '2015-08-07 15:26:33'),
(20, 'Тимофеев', 30, 1, '2015-08-07 15:26:49'),
(21, 'Конфеткин', 28, 1, '2015-08-07 15:27:01'),
(22, 'Задрочкин', 30, 1, '2015-08-07 15:27:14'),
(23, 'Пекин', 28, 1, '2015-08-07 15:27:26');

