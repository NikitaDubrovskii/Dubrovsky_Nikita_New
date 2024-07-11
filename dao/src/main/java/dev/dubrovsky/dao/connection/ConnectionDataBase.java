package dev.dubrovsky.dao.connection;

import dev.dubrovsky.exception.DbException;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionDataBase {

    private static final Properties properties = new Properties();
    private static Connection connection;

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
    }

}