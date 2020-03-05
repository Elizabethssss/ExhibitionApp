DROP TABLE IF EXISTS `role`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `exhibition`;
DROP TABLE IF EXISTS `exposition`;
DROP TABLE IF EXISTS `ticket`;

CREATE TABLE `role` (
    `id` int(11) NOT NULL,
    `name` varchar(45) NOT NULL,
    `description` varchar(45) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `user` (
    `id` bigint(19) NOT NULL AUTO_INCREMENT,
    `username` longtext NOT NULL,
    `email` varchar(45) NOT NULL,
    `password` varchar(45) NOT NULL,
    `role_id` int(11) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `email_UNIQUE` (`email`),
    CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
);

CREATE TABLE `exhibition` (
    `id` bigint(19) NOT NULL AUTO_INCREMENT,
    `name` varchar(45) NOT NULL,
    `date_from` date NOT NULL,
    `date_to` date NOT NULL,
    `theme` varchar(45) NOT NULL,
    `about` longtext NOT NULL,
    `long_about` longtext,
    `price` double NOT NULL,
    `image` varchar(45) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `exposition` (
    `id` bigint(19) NOT NULL AUTO_INCREMENT,
    `name` varchar(45) NOT NULL,
    `about` longtext,
    `image` varchar(45) NOT NULL,
    `exhib_id` bigint(19) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `exhib_id` FOREIGN KEY (`exhib_id`) REFERENCES `exhibition` (`id`)
);

CREATE TABLE `ticket` (
    `id` bigint(19) NOT NULL AUTO_INCREMENT,
    `user_id` bigint(19) NOT NULL,
    `exhibition_id` bigint(19) NOT NULL,
    `is_bought` tinyint(4) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `exhibition_id` FOREIGN KEY (`exhibition_id`) REFERENCES `exhibition` (`id`),
    CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

