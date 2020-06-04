DROP DATABASE IF EXISTS DSAGame;
CREATE DATABASE DSAGame;

USE DSAGame;


CREATE TABLE User (
					id VARCHAR(255),
					name VARCHAR(255) NOT NULL UNIQUE,
					password VARCHAR(255) NOT NULL,
					email VARCHAR(255) NOT NULL,
					exp INT,
					level INT,
					adminRights VARCHAR(255),
					PRIMARY KEY (id)
					);

CREATE TABLE Token (
                            id VARCHAR(255),
                            idUser VARCHAR(255),
                            admin VARCHAR(6),
                            PRIMARY KEY (id),
                            FOREIGN KEY (idUser) REFERENCES User(id)
);

CREATE TABLE ShopItem (
							id VARCHAR(255),
							name VARCHAR(255),
							description VARCHAR(255),
							cost INT,
							PRIMARY KEY (id)
);

CREATE TABLE Game (
						id VARCHAR(255),
						idUser VARCHAR(255),
						cash INT,
						neurons int,
						mask VARCHAR(255),
						lifes INT,
						completedLevels INT,
						PRIMARY KEY (id),
						FOREIGN KEY (idUser) REFERENCES User(id)
);

CREATE TABLE Level (
						id VARCHAR(255),
						map VARCHAR(255),
						levelNumber INT,
						PRIMARY KEY (levelNumber)
);

CREATE TABLE BestLevel (
						id VARCHAR(255),
						levelNumber INT,
						bestScore INT,
						bestTime INT,
						startDate DATE,
						idUser VARCHAR(255),
						PRIMARY KEY (id),
						FOREIGN KEY (levelNumber) REFERENCES Level(levelNumber),
						FOREIGN KEY (idUser) REFERENCES User(id)
);

CREATE TABLE Message (
						id VARCHAR(255),
						username VARCHAR(255),
						receivedDate DATE,
						content VARCHAR(255)
);

INSERT INTO User VALUES ('admin', 'admin', MD5('admin'), 'admin@admin.com', 0, 100, 'TRUE');

