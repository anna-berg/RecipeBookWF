--liquibase formatted sql

--changeset annaberg:1
CREATE TABLE IF NOT EXISTS product
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(256) NOT NULL UNIQUE,
    proteins      INT,
    fats          INT,
    carbohydrates INT,
    type          VARCHAR(128) NOT NULL
);

--changeset annaberg:2
CREATE TABLE IF NOT EXISTS author
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(256) NOT NULL UNIQUE
);

--changeset annaberg:3
CREATE TABLE IF NOT EXISTS category_recipe
(
    id       SERIAL PRIMARY KEY,
    category VARCHAR(256) NOT NULL UNIQUE
);


--changeset annaberg:4
CREATE TABLE IF NOT EXISTS recipe
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(256)                        NOT NULL,
    author_id   INT REFERENCES author (id),
    description TEXT                                NOT NULL,
    measure     VARCHAR(128),
    category_id INT REFERENCES category_recipe (id) NOT NULL
);

--changeset annaberg:5
CREATE TABLE IF NOT EXISTS recipe_product
(
    recipe_id  INT REFERENCES recipe,
    product_id INT REFERENCES product,
    PRIMARY KEY (recipe_id, product_id)
);

--changeset annaberg:6
CREATE TABLE IF NOT EXISTS users
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(256) NOT NULL,
    email    VARCHAR(256) NOT NULL UNIQUE,
    password VARCHAR(256) NOT NULL,
    role     VARCHAR(64)  NOT NULL
);

--changeset annaberg:7
CREATE TABLE IF NOT EXISTS daily_menu
(
    id           SERIAL PRIMARY KEY,
    breakfast    INT REFERENCES recipe (id) NOT NULL,
    first_snack  INT REFERENCES recipe (id) NOT NULL,
    lunch        INT REFERENCES recipe (id) NOT NULL,
    second_snack INT REFERENCES recipe (id) NOT NULL,
    dinner       INT REFERENCES recipe (id) NOT NULL
);

--changeset annaberg:8
CREATE TABLE IF NOT EXISTS favorites
(
    id         SERIAL PRIMARY KEY,
    user_id    INT REFERENCES users,
    day_id     INT REFERENCES daily_menu (id),
    recipe_id  INT REFERENCES recipe (id),
    rating     INT,
    created_at TIMESTAMP
);

--changeset annaberg:9
CREATE TABLE IF NOT EXISTS grouped
(
    id          SERIAL PRIMARY KEY,
    users_id    INT REFERENCES users (id),
    group_title VARCHAR(256)
);

--changeset annaberg:10
CREATE TABLE IF NOT EXISTS group_day
(
    id            SERIAL PRIMARY KEY,
    group_id      INT REFERENCES grouped (id),
    daily_menu_id INT REFERENCES daily_menu (id),
    position      INT NOT NULL,
    UNIQUE (group_id, position)
);