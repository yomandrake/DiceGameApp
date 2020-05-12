create table users(
  username varchar_ignorecase(50) not null primary key,
  password varchar_ignorecase(50) not null,
  enabled boolean not null);

create table authorities (
  username varchar_ignorecase(50) not null,
  authority varchar_ignorecase(50) not null,
  constraint fk_authorities_users foreign key(username) references users(username));

create unique index ix_auth_username on authorities (username,authority);

-- Create table player
CREATE TABLE player (
  player_id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  player_log_name varchar(255) DEFAULT NULL,
  player_log_pass varchar(255) DEFAULT NULL,
  player_name varchar(255) DEFAULT NULL,
  player_reg_date varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
-- 
-- 
-- Create table games
CREATE TABLE `games` (
  `game_id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `dice1` int(11) NOT NULL,
  `dice2` int(11) NOT NULL,
  `dice3` int(11) NOT NULL,
  `game_date_time` varchar(255) DEFAULT NULL,
  `is_win` int(11) NOT NULL,
  `player_id` int(11) NOT NULL,
	FOREIGN KEY (`player_id`) REFERENCES `player` (`player_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4