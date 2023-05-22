-- REMOVE OLD DATA --
SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE film_sessions;
TRUNCATE TABLE films;
TRUNCATE TABLE uploaders;

SET FOREIGN_KEY_CHECKS = 1;

-- INSERT uploaders --
INSERT INTO `uploaders` (`id`, `username`, `password`)
VALUES
(NULL, 'user1', 'user1'),
(NULL, 'User2', 'user2'),
(NULL, 'user3', 'user3'),
(NULL, 'user4', 'user4'),
(NULL, 'user5', 'user5');


-- INSERT films --
--ALTER TABLE `films` MODIFY COLUMN `duration` DECIMAL(21,2);

VALUES
(NULL, 'test', 'test', '3.1', NULL, 'test', NULL, NULL, NULL, NULL, '1');

INSERT INTO `films` (`id`, `title`, `duration`, `language`, `subtitles`, `director`, `actors`, `minimum_age`, `start_date`, `end_date`, `uploader_id`)
VALUES
(NULL, 'Film 1', 1.5, 'English', 'Spanish', 'Director 1', 'Actor 1, Actor 2', 12, '2023-01-01', '2023-01-31', 1),
(NULL, 'Film 2', 2.25, 'French', 'English', 'Director 2', 'Actor 3, Actor 4', 18, '2023-02-01', '2023-02-28', 2),
(NULL, 'Film 3', 1.75, 'German', 'French', 'Director 3', 'Actor 5, Actor 6', 15, '2023-03-01', '2023-03-31', 3),
(NULL, 'Film 4', 1.5, 'Spanish', 'German', 'Director 4', 'Actor 7, Actor 8', 10, '2023-04-01', '2023-04-30', 4),
(NULL, 'Film 5', 2, 'English', 'Spanish', 'Director 5', 'Actor 9, Actor 10', 16, '2023-05-01', '2023-05-31', 5),
(NULL, 'Film 6', 1.7, 'French', 'English', 'Director 6', 'Actor 11, Actor 12', 14, '2023-06-01', '2023-06-30', 1),
(NULL, 'Film 7', 2.4, 'German', 'French', 'Director 7', 'Actor 13, Actor 14', 12, '2023-07-01', '2023-07-31', 2),
(NULL, 'Film 8', 1.9, 'Spanish', 'German', 'Director 8', 'Actor 15, Actor 16', 17, '2023-08-01', '2023-08-31', 3),
(NULL, 'Film 9', 1.5, 'English', 'Spanish', 'Director 9', 'Actor 17, Actor 18', 13, '2023-09-01', '2023-09-30', 4),
(NULL, 'Film 10', 1.2, 'French', 'English', 'Director 10', 'Actor 19, Actor 20', 11, '2023-10-01', '2023-10-31', 5);

-- INSERT film_sessions --
INSERT INTO `film_sessions` (`id`, `calendar_date`, `week_day`, `start_hour`, `end_hour`, `cinema`, `city`, `film_id`)
VALUES (NULL, '2023-05-01', 'Monday', '13:00:00', '15:00:00', 'Cinema 1', 'City 1', '5');