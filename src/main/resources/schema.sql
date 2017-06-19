DROP TABLE IF EXISTS todos;
DROP TABLE IF EXISTS todolists;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
  `username` varchar(20) NOT NULL UNIQUE ,
  `password` varchar(20) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_roles` (
  `user_role_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `role` varchar(20) NOT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `user_role_id` (`user_role_id`),
  UNIQUE KEY `username` (`username`,`role`),
  CONSTRAINT `user_roles_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `todolists` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `user_username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq8h8vx3101o51holrlrme4jf` (`user_username`),
  CONSTRAINT `FKq8h8vx3101o51holrlrme4jf` FOREIGN KEY (`user_username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `todos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `completed` bit(1) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `list_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKphx2or332t3eweaavug8xrwjl` (`list_id`),
  CONSTRAINT `FKphx2or332t3eweaavug8xrwjl` FOREIGN KEY (`list_id`) REFERENCES `todolists` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO users(username,password,enabled) VALUES ('user1','user1pass', true);
INSERT INTO users(username,password,enabled) VALUES ('user2','user2pass', true);

INSERT INTO user_roles (username, role) VALUES ('user1', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('user2', 'ROLE_USER');

INSERT INTO todolists (id,name,user_username) VALUES (1,'List 1', 'user1');
INSERT INTO todolists (id,name,user_username) VALUES (2,'List 2', 'user1');
INSERT INTO todolists (id,name,user_username) VALUES (3,'List 3', 'user2');

INSERT INTO todos (completed,description,list_id) VALUES (false, 'Do a thing', 1);
INSERT INTO todos (completed,description,list_id) VALUES (true, 'Do another thing', 1);
INSERT INTO todos (completed,description,list_id) VALUES (false, 'Do all the things', 1);

INSERT INTO todos (completed,description,list_id) VALUES (false, 'Do a thing in the other list', 2);
INSERT INTO todos (completed,description,list_id) VALUES (true, 'Do another thing in the other list', 2);