CREATE SCHEMA `crawler` DEFAULT CHARACTER SET utf8 ;

USE `crawler`;

CREATE TABLE `crawler`.`user` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `salt` CHAR(8) NULL,
  `password` CHAR(60) NULL,
  `active` TINYINT(1) UNSIGNED NULL,
  `activation_code` CHAR(60) NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC));

CREATE TABLE `crawler`.`role` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  `active` TINYINT(1) UNSIGNED NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `crawler`.`user_role` (
  `user_id` INT UNSIGNED NOT NULL,
  `role_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  INDEX `fk_user_role_2_idx` (`role_id` ASC),
  CONSTRAINT `fk_user_role_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `crawler`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_role_2`
    FOREIGN KEY (`role_id`)
    REFERENCES `crawler`.`role` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `crawler`.`user_criteria` (
  `id` INT UNSIGNED NOT NULL,
  `price_min` DECIMAL(10,2) NULL,
  `price_max` DECIMAL(10,2) NULL,
  `size_min` DECIMAL(5,2) NULL,
  `size_max` DECIMAL(5,2) NULL,
  `keywords` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_user_criteria_1`
    FOREIGN KEY (`id`)
    REFERENCES `crawler`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `crawler`.`source` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  `url` VARCHAR(45) NULL,
  `active` TINYINT(1) UNSIGNED NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `crawler`.`source_generator` (
  `id` INT UNSIGNED NOT NULL,
  `type` VARCHAR(15) NULL,
  `regex` VARCHAR(255) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_source_generator_1`
    FOREIGN KEY (`id`)
    REFERENCES `crawler`.`source` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `crawler`.`crawled` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `source_id` INT UNSIGNED NULL,
  `url` VARCHAR(255) NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_crawled_1_idx` (`source_id` ASC),
  CONSTRAINT `fk_crawled_1`
    FOREIGN KEY (`source_id`)
    REFERENCES `crawler`.`source` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `crawler`.`crawled_info` (
  `id` INT UNSIGNED NOT NULL,
  `price` DECIMAL(10,2) NULL,
  `price_per_square` DECIMAL(10,2) NULL,
  `size` DECIMAL(5,2) NULL,
  `region` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_crawled_info_1`
    FOREIGN KEY (`id`)
    REFERENCES `crawler`.`crawled` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `crawler`.`rating` (
  `id` INT UNSIGNED NOT NULL,
  `average` DECIMAL(3,1) NULL,
  `price` DECIMAL(3,1) NULL,
  `price_per_square` DECIMAL(3,1) NULL,
  `size` DECIMAL(3,1) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_rating_1`
    FOREIGN KEY (`id`)
    REFERENCES `crawler`.`crawled` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);



