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
    content text         NOT NULL,
    regDate datetime     NOT NULL
);

SELECT *
FROM article;

CREATE TABLE `member`
(
    id       bigint UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    username varchar(100) NOT NULL,
    password varchar(100) NOT NULL,
    name     varchar(100) NOT NULL,
    regDate  datetime     NOT NULL
);

SELECT *
FROM `member`;

INSERT INTO article
SET title = "제목1",
content = "내용1",
regDate = now();

INSERT INTO article
SET title = "제목2",
content = "내용2",
regDate = now();