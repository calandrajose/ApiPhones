DROP DATABASE phones;
CREATE DATABASE phones;
USE phones;

SET @@global.time_zone = '+00:00';
SET @@session.time_zone = '+00:00';


/* TABLES */
CREATE TABLE `provinces` (
	`id` INT AUTO_INCREMENT NOT NULL,
    `name` VARCHAR(255) NOT NULL UNIQUE,
    CONSTRAINT `Pk_provinces` PRIMARY KEY (`id`)
);


CREATE TABLE `cities` (
	`id` INT AUTO_INCREMENT NOT NULL,
    `name` VARCHAR(255) NOT NULL UNIQUE,
    `prefix` VARCHAR(255) NOT NULL UNIQUE,
    `id_province` INT NOT NULL,
    CONSTRAINT `Pk_cities` PRIMARY KEY (`id`),
	CONSTRAINT `Fk_cities_province` FOREIGN KEY(`id_province`) REFERENCES `provinces`(`id`)
);


CREATE TABLE `users` (
    `id` INT AUTO_INCREMENT NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `surname` VARCHAR(255) NOT NULL,
    `dni` VARCHAR(255) NOT NULL UNIQUE,
	`username` VARCHAR(255) NOT NULL UNIQUE,
    `password` VARCHAR(255) NOT NULL,

    /* Si hay error, usar DATETIME en vez de TIMESTAMP */
    `creation_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    `is_active` BOOLEAN DEFAULT TRUE,
    `id_city` INT NOT NULL,    
    CONSTRAINT `Pk_users` PRIMARY KEY (`id`),
    CONSTRAINT `Fk_users_city` FOREIGN KEY(`id_city`) REFERENCES `cities`(`id`)    
);


CREATE TABLE `user_roles` (
	`id` INT AUTO_INCREMENT NOT NULL,
    `role` VARCHAR(255) NOT NULL,    
    CONSTRAINT `Pk_user_roles` PRIMARY KEY (`id`)
);


CREATE TABLE `users_x_user_roles` (
	`id_user` INT NOT NULL,
    `id_user_role` INT NOT NULL,
    CONSTRAINT `Fk_users_x_user_roles__user` FOREIGN KEY(`id_user`) REFERENCES `users`(`id`),
    CONSTRAINT `Fk_users_x_user_roles__user_rol` FOREIGN KEY(`id_user_role`) REFERENCES `user_roles`(`id`)
);


CREATE TABLE `line_types` (
	`id` INT AUTO_INCREMENT NOT NULL,
    `type` VARCHAR(255) NOT NULL UNIQUE,
    CONSTRAINT `Pk_line_types` PRIMARY KEY (`id`)    
);


CREATE TABLE `lines` (
	`id` INT AUTO_INCREMENT NOT NULL,
    `number` VARCHAR(255) NOT NULL UNIQUE,

    `creation_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    `status` ENUM('ENABLED', 'DISABLED', 'SUSPENDED') NOT NULL,
    `id_user` INT NOT NULL,
    `id_line_type` INT NOT NULL,
	CONSTRAINT `Pk_lines` PRIMARY KEY (`id`),
    CONSTRAINT `Fk_lines_user` FOREIGN KEY(`id_user`) REFERENCES `users`(`id`),
    CONSTRAINT `Fk_lines_line_type` FOREIGN KEY(`id_line_type`) REFERENCES `line_types`(`id`)
);


CREATE TABLE `rates` (
	`id` INT AUTO_INCREMENT NOT NULL,
    `price_minute` FLOAT NOT NULL,
    `cost` FLOAT NOT NULL,
    `id_city_origin` INT NOT NULL,
    `id_city_destination` INT NOT NULL,
    CONSTRAINT `Pk_rates` PRIMARY KEY (`id`),
    CONSTRAINT `Fk_rates_city_origin` FOREIGN KEY(`id_city_origin`) REFERENCES `cities`(`id`),
    CONSTRAINT `Fk_rates_city_destination` FOREIGN KEY(`id_city_destination`) REFERENCES `cities`(`id`),
    CONSTRAINT `Unique_rates` UNIQUE (`id_city_origin`, `id_city_destination`)
);


SET SQL_MODE='ALLOW_INVALID_DATES';
CREATE TABLE `invoices` (
	`id` INT AUTO_INCREMENT NOT NULL,
    `number_calls` INT DEFAULT 0,
    `cost_price` FLOAT NOT NULL,
    `total_price` FLOAT NOT NULL,
    `id_line` INT NOT NULL,

    `creation_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    `due_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `is_paid` BOOLEAN DEFAULT FALSE,
    CONSTRAINT `Pk_invoices` PRIMARY KEY (`id`),
    CONSTRAINT `Fk_invoices_line` FOREIGN KEY(`id_line`) REFERENCES `lines`(`id`)
);


CREATE TABLE `calls` (
	`id` INT AUTO_INCREMENT NOT NULL,
    `duration` INT NOT NULL,
    `total_price` FLOAT NOT NULL,

    `creation_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    `id_origin_line` INT NOT NULL,       
    `id_destination_line` INT NOT NULL,
    `id_rate` INT NOT NULL,
    `id_invoice` INT,
    CONSTRAINT `Pk_calls` PRIMARY KEY (`id`),
    CONSTRAINT `Fk_calls_origin_line` FOREIGN KEY(`id_origin_line`) REFERENCES `lines`(`id`),
    CONSTRAINT `Fk_calls_destination_line` FOREIGN KEY(`id_destination_line`) REFERENCES `lines`(`id`),
    CONSTRAINT `Fk_calls_rate` FOREIGN KEY(`id_rate`) REFERENCES `rates`(`id`),
    CONSTRAINT `Fk_calls_invoice` FOREIGN KEY(`id_invoice`) REFERENCES `invoices`(`id`)
);


/* BASIC DATA */

	/* Provinces */
    insert into `provinces`(name) values ('Buenos Aires');


    /* Cities */
    insert into 
		`cities`
			(name, prefix, id_province) 
		values 
			('Mar del Plata', '223', 1),
            ('Miramar', '2291', 1),
            ('La Plata', '221', 1),
            ('Buenos Aires', '11', 1),
            ('Bahia Blanca', '291', 1),
            ('Azul', '2281', 1),
            ('Maipu', '2268', 1);


	/* Rates */
    insert into
		`rates`
			(price_minute, cost, id_city_origin, id_city_destination)
		values
			(10, 5, 1, 1),		/* Mdp to Mdp */
			(12, 5, 1, 2),		/* Mdp to Miramar */
            (20, 5, 1, 3),		/* Mdp to La Plata */
            (22, 5, 1, 4),		/* Mdp to Buenos Aires */
            (18, 5, 1, 5),		/* Mdp to Bahia Blanca */
            (15, 5, 1, 6),		/* Mdp to Azul */
            (14, 5, 1, 7);		/* Mdp to Maipu */


	/* User Role */
    insert into
		`user_roles`
			(role)
		values
			('Employee'),
            ('Client');


	/* User */
	insert into
		`users`
			(name, surname, dni, username, password, creation_date, is_active, id_city)
		values
			('Rodrigo', 'Leon', '404040', 'Rl97', '1234', now(), 1, 1);


	/* Users x User_Roles */
    insert into
		`users_x_user_roles`
			(id_user, id_user_role)
		values
			(1, 1),
            (1, 2);


	/* Line Type */
    insert into
		`line_types`
			(type)
			values
				('Mobile'),
                ('Residential');


	/* Line */
        insert into
			`lines`
				(number, creation_date, status, id_user, id_line_type)
			values				
				('2235245050', now(), 'ENABLED',1, 1);


/* STORE PROCEDURE */