INSERT INTO users (username, password, enabled)
	values ('user',
		'pass',
		true
		);

INSERT INTO users (username, password, enabled)
	values ('admin',
		'pass',
		true
		);

INSERT INTO authorities (username, authority)
	values ('user','ROLE_USER');

INSERT INTO authorities (username, authority)
	values ('admin','ROLE_ADMIN');

INSERT INTO PLAYER (player_log_name, player_log_pass, player_name, player_reg_date) VALUES ('pepe','pass','Jose', '2020-05-06T15:59:16.338');
INSERT INTO PLAYER (player_log_name, player_log_pass, player_name, player_reg_date) VALUES ('rob','pass','Roberta', '2020-05-06T15:59:16.339');
