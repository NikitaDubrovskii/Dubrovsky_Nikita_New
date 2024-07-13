package dev.dubrovsky.dao.connection;

import dev.dubrovsky.model.analytics.Analytics;
import dev.dubrovsky.model.bonus.Bonus;
import dev.dubrovsky.model.bonus.UserBonus;
import dev.dubrovsky.model.cart.Cart;
import dev.dubrovsky.model.cart.CartItem;
import dev.dubrovsky.model.category.Category;
import dev.dubrovsky.model.loyalty_program.LoyaltyProgram;
import dev.dubrovsky.model.loyalty_program.UserLoyaltyProgram;
import dev.dubrovsky.model.order.Order;
import dev.dubrovsky.model.order.OrderItem;
import dev.dubrovsky.model.payment_method.PaymentMethod;
import dev.dubrovsky.model.product.Product;
import dev.dubrovsky.model.user.User;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class ConnectionDataBase {

    private static final Properties properties = new Properties();

    /*private static Connection connection;

    private ConnectionDataBase() {

    }

    public static Connection getConnection() {
        try (FileInputStream inputStream = new FileInputStream("dao/src/main/resources/dao.properties")) {
            if (connection == null) {
                properties.load(inputStream);
                connection = DriverManager.getConnection(
                        properties.getProperty("db.url"),
                        properties.getProperty("db.user"),
                        properties.getProperty("db.password")
                );
            }

            return connection;
        } catch (Exception e) {
            throw new DbException("Ошибка подключения к базе данных: " + e.getMessage());
        }
    }*/

    private static EntityManagerFactory entityManagerFactory;

    private ConnectionDataBase() {}

    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            try {
                entityManagerFactory = new Configuration()
                        .addAnnotatedClass(Analytics.class)
                        .addAnnotatedClass(User.class)
                        .addAnnotatedClass(Bonus.class)
                        .addAnnotatedClass(UserBonus.class)
                        .addAnnotatedClass(Cart.class)
                        .addAnnotatedClass(CartItem.class)
                        .addAnnotatedClass(Category.class)
                        .addAnnotatedClass(LoyaltyProgram.class)
                        .addAnnotatedClass(UserLoyaltyProgram.class)
                        .addAnnotatedClass(Order.class)
                        .addAnnotatedClass(OrderItem.class)
                        .addAnnotatedClass(PaymentMethod.class)
                        .addAnnotatedClass(Product.class)
                        .addAnnotatedClass(User.class)
                        .buildSessionFactory();
            } catch (Exception e) {
                System.out.println("Error in getEntityManager: " + e);
            }
        }

        return entityManagerFactory;
    }

}