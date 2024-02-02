package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class Util {
    // реализуйте настройку соединения с БД
    private static final Properties PROPERTIES = new Properties();

    // * блок `static` сработает один раз при первом запросе к классу `Util`
    // * и проинициализирует `PROPERTIES` данными из файла `application.properties`
    static {
        try (InputStream inputStream = Util.class
                .getClassLoader()
                .getResourceAsStream("application.properties"))
        {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }

        return null;
    }

    public static SessionFactory getSessionFactory() {
        return new Configuration()
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }
}
