CREATE DATABASE `usertask` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `usertask`;

CREATE TABLE `user`
(
    `id`         int(11)      NOT NULL auto_increment,
    `firstname`  varchar(255) NOT NULL,
    `lastname`   varchar(255) NOT NULL,
    `email`      varchar(255) NOT NULL,
    `created_at` DATETIME     NOT NULL,
    `updated_at` DATETIME,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

CREATE TABLE `task`
(
    `id`          int(11)  NOT NULL auto_increment,
    `user_id`     int(11)  NOT NULL,
    `created_at`  DATETIME NOT NULL,
    `updated_at`  DATETIME,
    `name`        varchar(255) default NULL,
    `description` varchar(255) default NULL,
    PRIMARY KEY  (`id`),
    KEY `task_key` (`user_id`),
    CONSTRAINT `user_task` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8;


