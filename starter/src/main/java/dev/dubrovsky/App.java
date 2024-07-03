package dev.dubrovsky;

import java.sql.Connection;

public class App {

    public static void main(String[] args) {
        Connection connection = ConnectionDataBase.getConnection();
        if (connection != null) {
            System.out.println("Hello World!");
        }
    }

}
