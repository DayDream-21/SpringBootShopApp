CREATE TABLE items
(
    id    serial NOT NULL,
    title VARCHAR(255),
    CONSTRAINT pk_items PRIMARY KEY (id)
);

INSERT INTO items (title)
VALUES
       ('Box'),
       ('Tree'),
       ('House');