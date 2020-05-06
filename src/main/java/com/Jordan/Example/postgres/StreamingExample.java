package com.Jordan.Example.postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class StreamingExample {

    public static void main(String[] argv) {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://192.168.66.79:5432/fu_glass", "postgres", "synergies");
            Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
//            statement.setFetchSize(Integer.MIN_VALUE);
            ResultSet resultSet= statement.executeQuery("select * from  process_info");
            System.out.println(resultSet);


            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
