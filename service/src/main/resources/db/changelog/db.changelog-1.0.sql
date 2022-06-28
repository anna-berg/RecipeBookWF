--liquibase formatted sql

--changeset annaberg:1
create table IF NOT EXISTS author
(
    id   bigserial not null
        constraint author_pkey
            primary key,
    name varchar(255)
);

--changeset annaberg:2
create table if not exists category_recipe
(
    id       bigserial not null
        constraint category_recipe_pkey
            primary key,
    category varchar(255)
);

--changeset annaberg:3
create table if not exists product
(
    id            bigserial not null
        constraint product_pkey
            primary key,
    carbohydrates integer,
    fats          integer,
    name          varchar(255),
    proteins      integer,
    type          varchar(255)
);

--changeset annaberg:4
create table if not exists recipe
(
    id          bigserial not null
        constraint recipe_pkey
            primary key,
    description varchar,
    measure     varchar(255),
    title       varchar,
    author_id   bigint
        constraint fkfac6s7ml2nkm4k0f0aasbvp74
            references author,
    category_id bigint
        constraint fk6u9rd73djcfhk1yjne1crvm84
            references category_recipe
);

--changeset annaberg:5
create table if not exists daily_menu
(
    id           bigserial not null
        constraint daily_menu_pkey
            primary key,
    title        varchar(255),
    breakfast    bigint
        constraint fkhudh6glstc46stq7ll2skff39
            references recipe,
    dinner       bigint
        constraint fktpw35xrk5mqh8nv3kv7mbqelr
            references recipe,
    first_snack  bigint
        constraint fk921omymp71harnt24vwaio1n0
            references recipe,
    lunch        bigint
        constraint fkll6sfeblx9jllp9cxdpq7bpo3
            references recipe,
    second_snack bigint
        constraint fkb9s2utl7j6cyur5s6g5tym8ai
            references recipe
);

--changeset annaberg:6
create table if not exists recipe_product
(
    recipe_id  bigint not null
        constraint fkb3poss884qc7j3wsvbq63aad3
            references recipe,
    product_id bigint not null
        constraint fk935n2g21lw35urnpf7vwcyp0w
            references product
);

--changeset annaberg:7
create table if not exists users
(
    id       bigserial not null
        constraint users_pkey
            primary key,
    email    varchar(255),
    gender   varchar(255),
    name     varchar(255),
    password varchar(255),
    role     varchar(255)
);

--changeset annaberg:8
create table if not exists favorite_recipe
(
    id         bigserial not null
        constraint favorite_recipe_pkey
            primary key,
    created_at timestamp,
    rating     integer   not null,
    recipe_id  bigint
        constraint fkkc0xeltectgqgjnuemidtyi4i
            references recipe,
    user_id    bigint
        constraint fkmd21ehkkcaieh3885tt4n4xs5
            references users
);

--changeset annaberg:9
create table if not exists groups
(
    id bigserial not null
        constraint groups_pkey
            primary key,
    group_title varchar(255),
    users_id    bigint
        constraint fkm2v0pc8an83n8g3d2513ejyn0
            references users
);

--changeset annaberg:10
create table if not exists group_day
(
    id  bigserial not null
        constraint group_day_pkey
            primary key,
    position      integer   not null,
    daily_menu_id bigint
        constraint fkdo694wk3js0x5if6io48fp2o5
            references daily_menu,
    group_id      bigint
        constraint fkoleltlburt6mxdg77o66s3al
            references groups
);
