package com.example.todo_new;
import java.sql.*;

public class DBcon {
    private static Connection conn;
    String task = "";
    int isDone = 0;
    public Connection connect (String dbName) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName+"", "root1", "Canada2019$#");
            //System.out.println("connected successfully");


        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }
}
