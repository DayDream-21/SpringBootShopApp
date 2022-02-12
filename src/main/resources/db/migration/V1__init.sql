SHOW search_path;

CREATE TABLE users (
    username varchar(50) NOT NULL,
    password varchar(100) NOT NULL,
    enabled boolean NOT NULL,
    PRIMARY KEY (username)
);

INSERT INTO users (username, password, enabled)
VALUES
       ('user1', '{noop}123', true),
       ('user2', '{noop}123', true),
       ('user3', '{noop}123', true);

CREATE TABLE authorities (
    username varchar(50) NOT NULL,
    authority varchar(50) NOT NULL,
    CONSTRAINT authorities_idx UNIQUE (username, authority),
    CONSTRAINT authorities_idfk_1 FOREIGN KEY (username) REFERENCES users (username)
);

INSERT INTO authorities (username, authority)
VALUES
       ('user1', 'ROLE_ADMIN'),
       ('user1', 'ROLE_USER'),
       ('user2', 'ROLE_USER'),
       ('user3', 'ROLE_USER');
