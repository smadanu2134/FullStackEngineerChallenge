create database feedback;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `password` varchar(500) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIusersQUE` (`user_name`)
);

delimiter //
CREATE DEFINER=`root`@`localhost` TRIGGER `users_BEFORE_INSERT` BEFORE INSERT ON `users` FOR EACH ROW BEGIN
 set new.full_name = concat(new.first_name, ' ', new.last_name);
END;//
delimiter;


CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(45) NOT NULL,
  `role_desc` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_name_UNIQUE` (`role_name`)
);

CREATE TABLE `user_roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `role_id_idx` (`role_id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);

CREATE TABLE `participants` (
  `id` int NOT NULL AUTO_INCREMENT,
  `participant_id` int DEFAULT NULL,
  `comments` varchar(300) DEFAULT NULL,
  `rating` varchar(45) DEFAULT NULL,
  `assign_date` date DEFAULT NULL,
  `complete_date` date DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `particpant_user_fk_idx` (`participant_id`),
  CONSTRAINT `particpant_user_fk` FOREIGN KEY (`participant_id`) REFERENCES `users` (`id`)
);


CREATE TABLE `feedback_participants` (
  `id` int NOT NULL AUTO_INCREMENT,
  `feedback_id` int DEFAULT NULL,
  `participant_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fp_participant_fk_idx` (`participant_id`),
  KEY `fp_feedbaack_fk_idx` (`feedback_id`),
  CONSTRAINT `fp_feedbaack_fk` FOREIGN KEY (`feedback_id`) REFERENCES `feedback` (`id`),
  CONSTRAINT `fp_participant_fk` FOREIGN KEY (`participant_id`) REFERENCES `participants` (`id`)
);

CREATE TABLE `feedback` (
  `id` int NOT NULL AUTO_INCREMENT,
  `reviewee_id` int DEFAULT NULL,
  `feedback_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_reviewee_feedback` (`reviewee_id`,`feedback_name`),
  KEY `reviewee_user_fk_idx` (`reviewee_id`),
  CONSTRAINT `reviewee_user_fk` FOREIGN KEY (`reviewee_id`) REFERENCES `users` (`id`)
);




INSERT INTO `feedback`.`roles`
(`id`,
`role_name`,
`role_desc`)
VALUES
(1,
'ADMIN',
'Administartor');

INSERT INTO `feedback`.`roles`
(`id`,
`role_name`,
`role_desc`)
VALUES
(2,
'EMPL',
'Employee');

INSERT INTO `feedback`.`users` (`id`, `email`, `password`, `last_name`, `user_name`, `first_name`) 
VALUES (1, 'admin@feedback.com', '$2a$10$14NpTBvTMr64Z8iwBibztuiJvyCC6rp98S6EYnglOANK//OIvldde', 'Admin', 'admin', 'Admin');
-- password

INSERT INTO `feedback`.`user_roles`
(`id`,
`role_id`,
`user_id`)
VALUES
(1,
1,
1);
