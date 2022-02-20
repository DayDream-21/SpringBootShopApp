CREATE TABLE products (
    id serial,
    title varchar(100),
    price int,
    views int DEFAULT 0
    );

INSERT INTO products (title, price)
VALUES
       ('Bread',        40),
       ('Milk',         80),
       ('Onion',        35),
       ('Pepper',       270),
       ('Coca-cola',    60),
       ('Water',        30),
       ('Flour',        220),
       ('Cheese',       220),
       ('Bananas',      85),
       ('Potato',       40),
       ('Carrot',       70),
       ('Lemon',        130),
       ('Avocado',      300),
       ('Orange',       100),
       ('Sugar',        55),
       ('Salt',         70),
       ('Apple',        110),
       ('Juice',        90),
       ('Egg',          85),
       ('Chicken',      360),
       ('Butter',       370),
       ('Cookie',       55);