package com.berg.service.util;

import com.berg.service.entity.Author;
import com.berg.service.entity.CategoryRecipe;
import com.berg.service.entity.DailyMenu;
import com.berg.service.entity.Favorites;
import com.berg.service.entity.GroupDay;
import com.berg.service.entity.Grouped;
import com.berg.service.entity.Product;
import com.berg.service.entity.Recipe;
import com.berg.service.entity.User;
//import com.vladmihalcea.hibernate.naming.CamelCaseToSnakeCaseNamingStrategy;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {

    public static SessionFactory buildSessionFactory (){
        var configuration = new Configuration();
//        configuration.setPhysicalNamingStrategy(new CamelCaseToSnakeCaseNamingStrategy());
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Author.class);
        configuration.addAnnotatedClass(CategoryRecipe.class);
        configuration.addAnnotatedClass(DailyMenu.class);
        configuration.addAnnotatedClass(Favorites.class);
        configuration.addAnnotatedClass(GroupDay.class);
        configuration.addAnnotatedClass(Grouped.class);
        configuration.addAnnotatedClass(Product.class);
        configuration.addAnnotatedClass(Recipe.class);
        configuration.addAnnotatedClass(User.class);
        configuration.configure();

        return configuration.buildSessionFactory();
    }
}
