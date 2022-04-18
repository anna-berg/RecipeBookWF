
import com.berg.service.entity.Author;
import com.berg.service.entity.CategoryRecipe;
import com.berg.service.entity.DailyMenu;
import com.berg.service.entity.Recipe;
import com.berg.service.util.HibernateUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EntityMappingTest {

    @Test
    public void SaveRecipeTest() {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();



            session.getTransaction().commit();
        }
    }

    @Test
    public void CreateRecipeTest() {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();
            var author = session.get(Author.class, 1L);
            var category = session.get(CategoryRecipe.class, 5L);
            var recipe1 = Recipe.builder()
                    .author(author)
                    .categoryRecipe(category)
                    .title(" Хек, запеченный с специями и лимоном в рукаве + 2 огурца")
                    .description(
                            "Можно использовать филе хека, или целую тушку. Запекать 15-20 минут при температуре 180 градусов. Огурцы подать в виде салата с зеленью и полезным маслом.\n" +
                            "Смешать все ингредиенты. Подавать на листьях салата.")
                    .build();

            session.save(recipe1);
            Assertions.assertSame(recipe1, session.get(Recipe.class, recipe1.getId()));
            session.getTransaction().commit();
        }
    }

    @Test
    void saveDailyMenu(){

        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

            var breakfast = session.get(Recipe.class, 103L);
            var firstSnack = session.get(Recipe.class, 105L);
            var lunch = session.get(Recipe.class, 106L);
            var secondSnack = session.get(Recipe.class, 107L);
            var dinner = session.get(Recipe.class, 108L);

            var dailyMenu = DailyMenu.builder()
                    .breakfast(breakfast)
                    .firstSnack(firstSnack)
                    .lunch(lunch)
                    .secondSnack(secondSnack)
                    .dinner(dinner)
                    .title("day 6")
                    .build();
            session.save(dailyMenu);
            assertThat(session.get(DailyMenu.class, dailyMenu.getId())).isEqualTo(dailyMenu);
            session.getTransaction().commit();
        }
    }
}
