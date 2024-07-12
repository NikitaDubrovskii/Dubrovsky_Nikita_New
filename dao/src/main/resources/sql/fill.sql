INSERT INTO users (username, password, email)
VALUES ('User 1', 'password123', 'user1@example.com'),
       ('User 2', 'password456', 'user2@example.com'),
       ('User 3', 'password789', 'user3@example.com');

INSERT INTO categories (name, description)
VALUES ('Action', 'Action games'),
       ('Card', 'Card games'),
       ('RPG', 'RPG games');

INSERT INTO products (name, description, price, category_id)
VALUES ('Game 1', 'Game 1 description', 59.99, 1),
       ('Game 2', 'Game 2 description', 49.99, 2),
       ('Game 3', 'Game 3 description', 69.99, 3);

INSERT INTO carts (user_id)
VALUES (1),
       (2),
       (3);

INSERT INTO cart_items (quantity, cart_id, product_id)
VALUES (1, 1, 1),
       (1, 1, 3),
       (2, 3, 1);

INSERT INTO orders (total_price, address, payment_method_id, user_id)
VALUES (131, 'Minsk', 1, 1),
       (121, 'Grodno', 2, 3);

INSERT INTO order_items (quantity, order_id, product_id)
VALUES (1, 1, 1),
       (1, 1, 3),
       (2, 2, 1);

INSERT INTO loyalty_programs (name, description)
VALUES ('Gold', 'Gold program'),
       ('Silver', 'Silver program');

INSERT INTO user_loyalty_programs (user_id, program_id)
VALUES (1, 1),
       (2, 2);

INSERT INTO bonuses (name, description, points, program_id)
VALUES ('Bonus 1', 'Bonus 1 description', 20, 1),
       ('Bonus 2', 'Bonus 2 description', 100, 2);

INSERT INTO user_bonuses (user_id, bonus_id)
VALUES (1, 1),
       (2, 2);

INSERT INTO analytics (activity, user_id)
VALUES ('Subscribe', 1),
       ('Comment', 2),
       ('Purchase', 3);
