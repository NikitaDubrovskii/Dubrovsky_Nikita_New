CREATE TABLE users
(
    id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    username   VARCHAR(100) UNIQUE NOT NULL,
    password   VARCHAR(100)        NOT NULL,
    email      VARCHAR(100) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE categories
(
    id          BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name        VARCHAR(100) NOT NULL,
    description TEXT
);

CREATE TABLE products
(
    id          BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name        VARCHAR(100)   NOT NULL,
    description TEXT,
    price       DECIMAL(10, 2) NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    category_id BIGINT         REFERENCES categories (id) ON DELETE SET NULL
);

CREATE TABLE carts
(
    id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id    BIGINT REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE cart_items
(
    id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    quantity   INT NOT NULL,
    cart_id    BIGINT REFERENCES carts (id) ON DELETE CASCADE,
    product_id BIGINT REFERENCES products (id) ON DELETE CASCADE
);

CREATE TABLE payment_method
(
    id     BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    method VARCHAR(50)
);

CREATE TABLE orders
(
    id                BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    total_price       DECIMAL(10, 2) NOT NULL,
    created_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    address           TEXT           NOT NULL,
    payment_method_id BIGINT REFERENCES payment_method (id) ON DELETE SET NULL ,
    user_id           BIGINT REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE order_items
(
    id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    quantity   INT NOT NULL,
    order_id   BIGINT REFERENCES orders (id) ON DELETE CASCADE ,
    product_id BIGINT REFERENCES products (id) ON DELETE CASCADE
);

CREATE TABLE loyalty_programs
(
    id          BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name        VARCHAR(100) NOT NULL,
    description TEXT,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE user_loyalty_programs
(
    user_id     BIGINT REFERENCES users (id) ON DELETE CASCADE,
    program_id  BIGINT REFERENCES loyalty_programs (id) ON DELETE CASCADE,
    received_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, program_id)
);

CREATE TABLE bonuses
(
    id          BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name        VARCHAR(100) NOT NULL,
    description TEXT,
    points      INT          NOT NULL,
    program_id  BIGINT REFERENCES loyalty_programs (id) ON DELETE CASCADE
);

CREATE TABLE user_bonuses
(
    user_id     BIGINT REFERENCES users (id) ON DELETE CASCADE,
    bonus_id    BIGINT REFERENCES bonuses (id) ON DELETE CASCADE,
    received_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, bonus_id)
);

CREATE TABLE analytics
(
    id        BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    activity  TEXT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id   BIGINT REFERENCES users (id) ON DELETE CASCADE
);
