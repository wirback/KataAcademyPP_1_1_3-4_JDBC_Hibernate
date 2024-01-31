package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class Util {
    // реализуйте настройку соединения с БД
    private static final Properties PROPERTIES = new Properties();
    private static SessionFactory sessionFactory;

    // * блок `static` сработает один раз при первом запросе к классу `Util`
    // * и проинициализирует `PROPERTIES` данными из файла `application.properties`
    static {
        try (InputStream inputStream = Util.class
                .getClassLoader()
                .getResourceAsStream("application.properties"))
        {
            PROPERTIES.load(inputStream);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Util(){
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    PROPERTIES.getProperty("db.url"),
                    PROPERTIES.getProperty("db.username"),
                    PROPERTIES.getProperty("db.password")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                configuration.setProperties(PROPERTIES);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (HibernateException e) {
                throw new RuntimeException(e);
            }
        }
        
        return sessionFactory;
    }
}
