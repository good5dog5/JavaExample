package com.shark.example.postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamingExample {

    public static void main(String[] argv) {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://pg.dev.sis.ai:5432/sygps", "shark", "123");
            Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            statement.setFetchSize(Integer.MIN_VALUE);
            ResultSet resultSet= statement.executeQuery("select * from  tb_595f8b18567848369a457d277b71ff01");


            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
