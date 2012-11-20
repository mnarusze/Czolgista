drop table if exists `groups`;
drop table if exists `users`;
drop table if exists `highscores`;

create table `users` (
    id smallint NOT NULL AUTO_INCREMENT,
    login varchar(128) NOT NULL UNIQUE,
    pass varchar(32) NOT NULL,
    PRIMARY KEY (id)
);

create table `highscores` (
    id smallint NOT NULL AUTO_INCREMENT,
    user_id smallint NOT NULL,
    score int NOT NULL,
    PRIMARY KEY (id)
);

insert into users values (DEFAULT, 'user_1', 'haslo_1');
insert into users values (DEFAULT, 'user_2', 'haslo_2');
insert into users values (DEFAULT, 'user_3', 'haslo_3');

insert into highscores values (DEFAULT, 1, 5);
insert into highscores values (DEFAULT, 2, 10);
insert into highscores values (DEFAULT, 3, 15);