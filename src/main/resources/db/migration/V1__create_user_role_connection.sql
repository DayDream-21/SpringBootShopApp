SHOW search_path;

CREATE TABLE users (
    id serial NOT NULL,
    username varchar(50) NOT NULL,
    password varchar(100) NOT NULL,
    name varchar(100) NOT NULL,
    email varchar(50) NOT NULL,
    enabled boolean NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE roles (
    id serial NOT NULL,
    role varchar(50) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT authorities_idx UNIQUE (id, role)
);

CREATE TABLE users_roles (
    user_id int8 NOT NULL,
    role_id int8 NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

INSERT INTO roles (role)
VALUES
       ('ROLE_USER'),
       ('ROLE_ADMIN');

INSERT INTO users (username, password, name, email, enabled)
VALUES
       ('admin', '$2a$12$GFqzvE3.XsTfygEPk.ZuUOMvbfJLMQtKGqNvTywzHVGT9na.qpceq', 'Bob Johnson', 'bob@gmail.com', true),
       ('user1', '$2a$12$lSDViqfFQGZ0LMJS7ideseJv5KwSY20pZD6HmnTjDWszUCaqJPzTO', 'Mike Brown', 'mike@gmail.com', true);

INSERT INTO users_roles (user_id, role_id)
VALUES
       (1, 1),
       (1, 2),
       (2, 1);
