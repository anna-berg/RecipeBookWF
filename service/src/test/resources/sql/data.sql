INSERT INTO author(id, name)
VALUES  (1, 'Sveta'),
        (2, 'Anna');
SELECT SETVAL('author_id_seq', (SELECT MAX(id) FROM author));

INSERT INTO category_recipe(id, category)
VALUES  (1, 'breakfast'),
        (2, 'first snack'),
        (3, 'lunch'),
        (4, 'second snack'),
        (5, 'dinner');
SELECT SETVAL('category_recipe_id_seq', (SELECT MAX(id) FROM category_recipe));

INSERT INTO product(id, name, type)
VALUES  (1, 'buckwheat', 'cereals'),
        (2, 'cabbage', 'vegetables'),
        (3, 'carrot', 'vegetables'),
        (4, 'cucumber', 'vegetables'),
        (5, 'kiwi', 'fruits'),
        (6, 'orange', 'fruits'),
        (7, 'apple', 'fruits'),
        (8, 'yogurt', 'milk products'),
        (9, 'baked milk', 'milk products'),
        (10, 'cocoa', 'seasonings'),
        (11, 'gelatin', 'seasonings');
SELECT SETVAL('product_id_seq', (SELECT MAX(id) FROM product));

INSERT INTO recipe(id, author_id, title, description, category_id)
VALUES  (1, (SELECT id FROM author WHERE name = 'Anna'), 'title1', 'description1', (SELECT id FROM category_recipe WHERE category = 'breakfast')),
        (2, (SELECT id FROM author WHERE name = 'Anna'), 'title2', 'description2', (SELECT id FROM category_recipe WHERE category = 'first snack')),
        (3, (SELECT id FROM author WHERE name = 'Anna'), 'title3', 'description3', (SELECT id FROM category_recipe WHERE category = 'lunch')),
        (4, (SELECT id FROM author WHERE name = 'Anna'), 'title4', 'description4', (SELECT id FROM category_recipe WHERE category = 'second snack')),
        (5, (SELECT id FROM author WHERE name = 'Anna'), 'title5', 'description5', (SELECT id FROM category_recipe WHERE category = 'dinner')),
        (6, (SELECT id FROM author WHERE name = 'Anna'), 'title6', 'description6', (SELECT id FROM category_recipe WHERE category = 'breakfast')),
        (7, (SELECT id FROM author WHERE name = 'Sveta'), 'title7', 'description7', (SELECT id FROM category_recipe WHERE category = 'first snack')),
        (8, (SELECT id FROM author WHERE name = 'Sveta'), 'title8', 'description8', (SELECT id FROM category_recipe WHERE category = 'lunch')),
        (9, (SELECT id FROM author WHERE name = 'Sveta'), 'title9', 'description9', (SELECT id FROM category_recipe WHERE category = 'second snack')),
        (10, (SELECT id FROM author WHERE name = 'Sveta'), 'title10', 'description10', (SELECT id FROM category_recipe WHERE category = 'dinner'));
SELECT SETVAL('recipe_id_seq', (SELECT MAX(id) FROM recipe));

INSERT INTO daily_menu(id, title, breakfast, first_snack, lunch, second_snack, dinner)
VALUES  (1, 'TitleDailyMenu1', 1, 2, 3, 4, 5),
        (2, 'TitleDailyMenu2', 6, 7, 8, 9, 10);
SELECT SETVAL('daily_menu_id_seq', (SELECT MAX(id) FROM daily_menu));

INSERT INTO users(id, name, email, password, role, gender)
VALUES  (1, 'Ivan', 'ivan@test.com', '111', 'ADMIN', 'MALE'),
        (2, 'Sveta', 'sveta@test.com', '111', 'USER', 'FEMALE');
SELECT SETVAL('users_id_seq', (SELECT MAX(id) FROM users));

INSERT INTO groups(id, users_id, group_title)
VALUES  (1, 1, 'GroupTitle1'),
        (2, 2, 'GroupTitle2');
SELECT SETVAL('groups_id_seq', (SELECT MAX(id) FROM groups));

INSERT INTO group_day(id, group_id, daily_menu_id, position)
VALUES  (1, 1, 1, 1),
        (2, 1, 2, 2);
SELECT SETVAL('group_day_id_seq', (SELECT MAX(id) FROM group_day));