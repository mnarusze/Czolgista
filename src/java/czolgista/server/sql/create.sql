drop table if exists `groups`;
drop table if exists `users`;
drop table if exists `highscores`;

create table `users` (
    id smallint NOT NULL AUTO_INCREMENT,
    username varchar(128) NOT NULL UNIQUE,
    password varchar(32) NOT NULL,
    PRIMARY KEY (id)
);

create table `highscores` (
    id smallint NOT NULL AUTO_INCREMENT,
    user_id smallint NOT NULL,
    score int NOT NULL,
    PRIMARY KEY (id)
);

create table `groups` (
    id smallint NOT NULL AUTO_INCREMENT,
    username varchar(128) NOT NULL UNIQUE,
    groupname varchar(32) NOT NULL,
    PRIMARY KEY (id)
);

insert into users values (DEFAULT, 'user_1', 'haslo_1');
insert into users values (DEFAULT, 'user_2', 'haslo_2');
insert into users values (DEFAULT, 'user_3', 'haslo_3');

insert into highscores values (DEFAULT, 1, 5);
insert into highscores values (DEFAULT, 2, 10);
insert into highscores values (DEFAULT, 3, 15);

insert into groups values (DEFAULT, 'user_1', 'standard_users');
insert into groups values (DEFAULT, 'user_2', 'standard_users');
insert into groups values (DEFAULT, 'user_3', 'admins');