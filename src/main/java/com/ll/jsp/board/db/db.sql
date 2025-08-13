DROP
DATABASE IF EXISTS board_poj;
CREATE
DATABASE board_proj;

USE
board_proj;

CREATE TABLE article
(
    id      bigint UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    title   varchar(100) NOT NULL,
    content text         NOT NULL
);

SELECT *
FROM article;

CREATE TABLE `member`
(
    id       bigint UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    username varchar(100) NOT NULL,
    password varchar(100) NOT NULL,
    name     varchar(100) NOT NULL
);

SELECT *
FROM `member`;
