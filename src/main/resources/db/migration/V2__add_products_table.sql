CREATE TABLE products (
    id serial,
    title varchar(100),
    price int
    );

INSERT INTO products (title, price)
VALUES
       ('Bread', 40),
       ('Milk', 80);