package com.berg.util;

import com.berg.entity.Author;
import com.berg.entity.CategoryRecipe;
import com.berg.entity.DailyMenu;
import com.berg.entity.FavoriteRecipe;
import com.berg.entity.GroupDay;
import com.berg.entity.Groups;
import com.berg.entity.Product;
import com.berg.entity.Recipe;
import com.berg.entity.User;
//import com.vladmihalcea.hibernate.naming.CamelCaseToSnakeCaseNamingStrategy;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {

    public static SessionFactory buildSessionFactory (){
        Configuration configuration = buildConfiguration();
        configuration.configure();

        return configuration.buildSessionFactory();
    }

    public static Configuration buildConfiguration() {
        Configuration configuration = new Configuration();
//        configuration.setPhysicalNamingStrategy(new CamelCaseToSnakeCaseNamingStrategy());
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Author.class);
        configuration.addAnnotatedClass(CategoryRecipe.class);
        configuration.addAnnotatedClass(DailyMenu.class);
        configuration.addAnnotatedClass(FavoriteRecipe.class);
        configuration.addAnnotatedClass(GroupDay.class);
        configuration.addAnnotatedClass(Groups.class);
        configuration.addAnnotatedClass(Product.class);
        configuration.addAnnotatedClass(Recipe.class);
        configuration.addAnnotatedClass(User.class);
        return configuration;
    }
}
