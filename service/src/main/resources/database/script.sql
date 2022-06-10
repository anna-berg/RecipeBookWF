CREATE DATABASE recipe_book;

CREATE TABLE product
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(256) NOT NULL UNIQUE,
    proteins      INT,
    fats          INT,
    carbohydrates INT,
    type          VARCHAR(128) NOT NULL
);

CREATE TABLE author
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(256) NOT NULL UNIQUE
);

CREATE TABLE category_recipe
(
    id       SERIAL PRIMARY KEY,
    category VARCHAR(256) NOT NULL UNIQUE
);


CREATE TABLE recipe
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(256)                        NOT NULL,
    author_id   INT REFERENCES author (id),
    description TEXT                                NOT NULL,
    measure     VARCHAR(128),
    category_id INT REFERENCES category_recipe (id) NOT NULL
);

CREATE TABLE recipe_product
(
    recipe_id  INT REFERENCES recipe,
    product_id INT REFERENCES product,
    PRIMARY KEY (recipe_id, product_id)
);

CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(256) NOT NULL,
    email    VARCHAR(256) NOT NULL UNIQUE,
    password VARCHAR(256) NOT NULL,
    role     VARCHAR(64)  NOT NULL
);

CREATE TABLE daily_menu
(
    id           SERIAL PRIMARY KEY,
    breakfast    INT REFERENCES recipe (id) NOT NULL,
    first_snack  INT REFERENCES recipe (id) NOT NULL,
    lunch        INT REFERENCES recipe (id) NOT NULL,
    second_snack INT REFERENCES recipe (id) NOT NULL,
    dinner       INT REFERENCES recipe (id) NOT NULL
);

CREATE TABLE favorites
(
    id         SERIAL PRIMARY KEY,
    user_id    INT REFERENCES users,
    day_id     INT REFERENCES daily_menu (id),
    recipe_id  INT REFERENCES recipe (id),
    rating     INT,
    created_at TIMESTAMP
);

CREATE TABLE grouped
(
    id          SERIAL PRIMARY KEY,
    users_id    INT REFERENCES users (id),
    group_title VARCHAR(256)
);

CREATE TABLE group_day
(
    id            SERIAL PRIMARY KEY,
    group_id      INT REFERENCES grouped (id),
    daily_menu_id INT REFERENCES daily_menu (id),
    position      INT NOT NULL,
    UNIQUE (group_id, position)
);
INSERT INTO author(name)
VALUES ('Bam-Bam');

INSERT INTO category_recipe(category)
VALUES ('breakfast'),
       ('1 snack'),
       ('lunch'),
       ('2 snack'),
       ('dinner')
       ;

INSERT INTO recipe (title, author_id, description, measure, category_id)
VALUES ('Булгур + авокадовый капрезе', 1, '🔻1/2 желтого помидора, 1/2 красного помидора, 1/2 авокадо, 1 ч. л. бальзамического соуса, 1 ст. л. оливкового масла, зелень по вкусу(листья салата/руккола), соль, перец.
🔻Нарежьте полукольцами помидоры и авокадо. Выложите слоями на тарелку. Сверху выложите зелень. Посыпьте солью и перцем. Сбрызните салат маслом и полейте бальзамическим соусом.',
        'MASS', 1),
       ('Смузи из дыни и арбуза ', 1, '(Замена: 2 яблока или другие всевозможные фрукты/ягоды, кроме бананов и винограда)
🔻Ломтик арбуза без косточек, ломтик дыни и 1ч.л. мёда(или кленового сиропа) взбить в блендере до желаемой', 'mass',
        2),
       ('Салат с индейкой и гранатом', 1, 'Горсть салатных листьев, 200 гр. филе индейки(или курицы), 3-4 помидоров черри, 1 ст.л. зерен граната, 1 ст.л. рекомендованного масла, 1 ч.л. дижонской горчицы, соль, перец.
🔻Крупно нарежьте филе, посолите, поперчите, на сухой сковороде, с добавлением воды по надобности. Взбейте вилкой масло, горчицу, соль и перец. Выложите в тарелку салатные листья, нарезанные помидоры, индейку. Полейте заправкой, посыпьте зернами граната.',
        'mass', 3),
       ('йогурт', 1, 'Стакан йогурта+ 1ст.л. корицы', 'mass', 4),
       (' Кабачки фаршированные творогом', 1, '🔻Кабачок 1шт., творог 150 гр., огурец 1шт., чеснок 1 зубчик, густой йогурт 2 ст.л., сыр сулугуни 50гр., итальянские травы, соль.
🔻Разрезать кабачки вдоль, освободить от семян. Нарезать кубиками огурец. Растолочь творог. Добавить йогурт, тертый чеснок, специи, соль по вкусу. Перемешать. Начинить фаршем кабачки. Разогреть духовку, выпекать кабачки около 30 минут. Натереть сыр на мелкой терке. Посыпать сыром блюдо за 5 минут до готовности.',
        'mass', 5),
       (' Творожные кексы с фруктами', 1, '(Замена: Ячневая каша с персиками и кедровыми орехами)
🔻Творог 200 гр. яйцо 1 шт.+ 1 белок, вишни и нектарин по надобности (можно использовать малину, ежевику, чернику), мука рисовая 2ст.л., подсластитель(стевия) по вкусу, разрыхлитель 1ч.л., ванилин.
🔻Яйца с подсластителем взбить в пышную пену, добавить творог и хорошо перемешать. Высыпать муку, разрыхлитель и ванилин и хорошо взбить. Масса получается достаточно густой. Раскладываем по формочкам, оставляя место, так как кексы поднимутся. Раскладываем нарезанные фрукты, слегка вдавливая в тесто. Отправляем в духовку на 25-30 минут при 180 градусах.',
        'mass', 1),
       ('яблоки', 1, '2 зелёных яблока', 'mass', 2),
       ('Кабачковые рулетики с курицей', 1, '🔻Кабачок 1 шт., куриное филе 300 гр., чеснок 1-2 зубчика, томатный соус(или паста) 1 ст. л., листья базилика маленький пучок, сыр сулугуни 50 гр., соль, перец по вкусу
🔻Кабачок очистить, нарезать вдоль полосками. Противень застелить бумагой для выпечки, разложить кабачки, сбрызнуть их маслом, посолить. Запекать 7-8 минут при 180 °C до мягкости. Куриное филе нарезать тонкими полосками, немного отбить, посолить и поперчить, посыпать мелко нарубленным чесноком. Выложить на кабачки по кусочку куриного филе, листик базилика, посыпать сыром, натертым на мелкой терке, полить томатным соусом.
Свернуть кабачки с куриным филе в рулетики, сколоть зубочисткой. Запекать 20-30 минут при 180гр.', 'mass', 3),
       (' 2 хлебца', 1, ' 2 хлебца', 'mass', 4),
       ('Тушеная спаржевая фасоль', 1,
        'Тушеная спаржевая фасоль (2ст.л.) с луком(1/2), белой рыбой(150гр.) и грибами(2-3шт.) в специях(куркума, орегано, красный перец). В конце посыпать зеленью и сушеным чесноком.',
        'mass', 5),
       ('Рулет из лаваша с тунцом', 1, '🔻Тунец в с/с 1/2 банки, лаваш 1 пласт, салат ½ пучка, 1 огурец, 1 яйцо, 1ст.л. йогурта, 2 ст. л. творожного сыра, чеснок 1 зубчик (по желанию), перец по вкусу
🔻Яйцо отварить и натереть. Чеснок выдавить. Смешать в чашке яйцо, йогурт, сыр, чеснок, перец черный. Смазать лаваш массой. Огурец очистить и тонко нарезать (можно овощечисткой). Тунец размять вилкой. Выкладываем на лаваш листья салата, тунец, огурцы. Заворачиваем лаваш в рулет со стороны огурца. Обернуть пленкой, убрать в холодильник на 20 мин.',
        'mass', 1),
       ('персик', 1, '2 персика (Замена: 2 груши или другие всевозможные фрукты/ягоды, кроме бананов и винограда)',
        'mass', 2),
       ('апечённое куриное филе с апельсином и грибами+2 огурца', 1, '🔻​​Куриное филе 1 шт., апельсин 1 шт., свежие шампиньоны 150 гр., тертый сыр адыгейский 40 гр., соль/карри по вкусу
🔻Филе отварить. Нарезанные пластинками шампиньоны протушить на воде. Апельсин очистить и разделить на дольки. Сыр нарезать тонкими ломтиками. В форму для запекания выложить филе, апельсин и грибы. Сверху посыпать сыром. Запекать при температуре 170-180 градусов 15-20 минут.',
        'mass', 3),
       (' 1/2 авокадо + 1 огурец', 1, ' 1/2 авокадо + 1 огурец', 'mass', 4),
       (' Запеченная капуста на кефире', 1, '🔻Цветная капуста 100 гр., брокколи 100гр., кефир 60 мл., желток 1 шт.,
сыр сулугуни 40 гр., соль/перец по вкусу
🔻Разогрейте духовку до 220-230 гр. Цветную капусту пробланшируйте в кипящей подсоленной воде 1 минуту. Добавьте брокколи, бланшируйте еще две минуты. После этого слейте воду. Взбейте венчиком кефир с яичным желтком, солью и перцем. Капусту смешайте с полученной смесью, после чего выложите на смазанный маслом противень или в форму для запекания. Посыпьте овощи тертым сыром. Отправляйте блюдо запекаться в духовку на 10-15 минут',
        'mass', 5),
       ('Злаковый батончик + запечённое яблоко', 1, '🔻Поровну берем: орехи (можно смесь из нескольких видов – грецкие, миндаль, кешью, фундук, арахис), овсяные хлопья, очищенные семечки (подсолнечника, тыквенные, кунжут), сухофрукты, мед (в качестве связующего 1 ч. л).
🔻Смешайте измельченные ингредиенты между собой в глубокой миске. Выложите массу на поднос, застеленный пергаментной бумагой, и плотно утрамбуйте. Поставьте на полчаса в холодильник, а затем нарежьте полосками.
⠀', 'mass', 1),
       ('3-4 абрикоса', 1,
        ' 3-4 абрикоса (Замена: 2 яблока или другие всевозможные фрукты/ягоды, кроме бананов и винограда)', 'mass', 2),
       ('Чечевичный суп-пюре с гренками из ц/з хлеба', 1, '🔻1 стакан красной чечевицы, лук, морковь, помидоры, томатная паста, чеснок, специи. Гренки из цельнозернового хлеба.
🔻Отварить красную чечевицу до готовности, добавить лук, морковь, вяленые или свежие помидоры, 1 ст ложку томатной пасты. Варить с овощами 10-12 минут. Перед выключением добавить чеснок, специи. Перебить блендером. Добавить зелень. Порезать цельнозерновой хлеб кубиками, посыпать специями(итальянские травы, базилик, орегано), подсушить в духовке. При подаче добавить гренки в суп.',
        'mass', 3),
       ('Микс орехов 10-12шт.', 1, 'Микс орехов 10-12шт.', 'mass', 4),
       (' Белая рыба под грибным соусом', 1, '🔻Филе белой рыбы - 1-2шт., 1 лук, грибы - 100-150гр., йогурт натуральный 50мл.
🔻Филе нарезаем кусочками. Лук и грибы тушим в небольшом количестве воды. Перекладываем рыбу в огнеупорную форму в один слой. Разогреваем духовку до 180 градусов. В сковороду с грибами, сделав маленький огонь, в конце добавляем йогурт, не доводя до кипения и заливаем соусом рыбу. Отправляем в духовку на 15-20 минут до готовности рыбы.',
        'mass', 5),
       ('Гречневая каша', 1,
        ' Гречневая каша+ соевый соус+ салат из капусты, моркови, клюквы(или кураги) и сыра сулугуни 40 г.', 'mass',
        1),
       ('Десерт из черники ', 1, 'Десерт из черники (Замена: 2 киви или 1 апельсин)
🔻1/2стакана черники заправляем 2ст.л. густого йогурта и 1 ч.л. мёда
🔻Перемешать, можно взбить блендером, или заморозить как мороженое.', 'mass', 2),
       ('Белковый салат', 1, '🔻100 гр. отварной красной фасоли, 100 гр. отварного филе курицы, 1 большой огурец, 4 перепелиных яйца, лук красный 1 небольшая головка, 2ст.л. густого йогурта, 1ст.л. соевого соуса, 1ч.л. горчицы, кунжут.
🔻Яйца, филе отварить, остудить. С фасоли слить жидкость. Огурец и все остальное нарезать соломкой, заправить, добавить соль перец по вкусу, посыпать кунжутом.',
        'mass', 3),
       ('Суфле из ряженки с какао', 1, '🔻150 мл. ряженки, 2 гр. какао., 10 гр желатина, 50 мл. воды, подсластитель (стевия) по желанию
🔻Желатин смешиваем с водой и ставим в микроволновку на 15 секунд ( добавляем время если он полностью не растворился ). В ряженку добавляем какао и подсластитель, вливаем желатин и на высокой мощности блендером взбиваем. Разливаем в форму и ставим в холодильник на 20-30 минут
⠀', 'mass', 4),
       ('Салат из творога', 1,
        'Салат из творога 150гр., зелени., чеснока и лука по желанию, заправленный полезным маслом, йогуртом(1ст.л.) и соевым соусом(1ч.л.)',
        'mass', 5)
--        ('', 1, '', 'mass', 1),
--        ('', 1, '', 'mass', 2),
--        ('', 1, '', 'mass', 3),
--        ('', 1, '', 'mass', 4),
--        ('', 1, '', 'mass', 5)
;

INSERT INTO product(name, type)
VALUES ('гречка', 'крупа'),
       ('соевый соус', 'приправы'),
       ('капуста', 'овощи'),
       ('морковка', 'овощи'),
       ('клюква', 'фрукты'),
       ('курага', 'орехи'),
       ('черника', 'фрукты'),
       ('киви', 'фрукты'),
       ('апельсин', 'фрукты'),
       ('йогурт', 'молочные продукты'),
       ('мед', 'мед'),
       ('красная фасоль', 'крупа'),
       ('филе курицы', 'мясо'),
       ('огурец', 'овощи'),
       ('перепелиные яйца', 'яйца'),
       ('лук красный', 'овощи'),
       ('густой йогурт', 'молочные продукты'),
       ('горчица', 'приправы'),
       ('кунжут', 'приправы'),
       ('ряженка', 'молочные продукты'),
       ('какао', 'приправы'),
       ('желатин', 'приправы'),
       ('творог', 'молочные продукты'),
       ('зелень', 'овощи'),
       ('орехи', 'орехи'),
       ('овсяные хлопья', 'крупа'),
       ('семечки очищенные', 'орехи'),
       ('сухофрукты', 'орехи'),
       ('абрикос', 'фрукты'),
       ('яблоки', 'фрукты'),
       ('красная чечевица', 'крупа'),
       ('помидоры', 'овощи'),
       ('томатная паста', 'приправы'),
       ('гренки из цельнозернового хлеба', 'хлеб'),
       ('Филе белой рыбы', 'рыба'),
       ('грибы', 'овощи'),
       ('лук', 'овощи'),
       ('Тунец в с/с ', 'рыба'),
       ('лаваш', 'хлеб'),
       ('салат пучек', 'овощи'),
       ('яйца', 'яйца'),
       ('творожный сыр', 'молочные продукты'),
       ('груши', 'фрукты'),
       ('сыр адыгейский', 'молочные продукты'),
       ('авокадо', 'овощи'),
       ('Цветная капуста', 'овощи'),
       ('кефир', 'молочные продукты'),
       ('сыр сулугуни', 'молочные продукты'),
       ('Ячневая крупа', 'крупа'),
       ('мука рисовая ', 'крупа'),
       ('разрыхлитель', 'приправы'),
       ('ванилин', 'приправы'),
       ('Кабачок', 'овощи'),
       ('базилик', 'овощи'),
       ('спаржевая фасоль', 'овощи'),
       ('Булгур', 'крупа'),
       ('бальзамический соус', 'приправы'),
       ('оливковое масло', 'приправы'),
       ('дыня', 'фрукты'),
       ('арбуз', 'фрукты'),
       ('филе индейки', 'мясо'),
       ('гранат', 'фрукты'),
       ('корица', 'приправы'),
       ('итальянские травы', 'приправы')
       ;
