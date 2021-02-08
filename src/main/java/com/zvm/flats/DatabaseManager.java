package com.zvm.flats;

import java.sql.*;
import java.util.Scanner;

public class DatabaseManager {

    static final String DB_CONNECTION = "jdbc:mysql://localhost:3307/prog1db?serverTimezone=Europe/Kiev";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "Password";
    static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected static void restartDB() throws SQLException {
        try (Statement st = connection.createStatement()) {
            st.execute("DROP TABLE IF EXISTS Flats");
            st.execute("""
                     CREATE TABLE Flats 
                     (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
                     district VARCHAR(20) NOT NULL, 
                     address VARCHAR(50) NOT NULL,
                     space INT NOT NULL,
                     rooms INT NOT NULL,
                     price INT NOT NULL)
                     """);
        }
    }

    protected static void getFlat(
            String district,
            String address,
            Integer space,
            Integer rooms,
            Integer price
    ) throws SQLException {

        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM Flats");
        if (district != null) query.append(" WHERE district=").append(district);
        if (address != null) query.append(" WHERE address=").append(address);
        if (space != null) query.append(" WHERE space=").append(space);
        if (rooms != null) query.append(" WHERE rooms=").append(rooms);
        if (price != null) query.append(" WHERE price=").append(price);

        try (PreparedStatement statement = connection.prepareStatement(query.toString())) {

            try (ResultSet rs = statement.executeQuery()) {

                ResultSetMetaData md = rs.getMetaData();

                for (int i = 1; i <= md.getColumnCount(); i++) {
                    System.out.print(md.getColumnName(i) + "\t\t");
                }
                System.out.println();

                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t\t");
                    }
                    System.out.println();
                }
            }
        }
    }
}
