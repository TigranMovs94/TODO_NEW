package com.example.todo_new;
import java.sql.*;

public class DBcon {
    private static Connection conn;
    String task = "";
    int isDone = 0;
    public Connection connect() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo", "root1", "Canada2019$#");
            //System.out.println("connected successfully");
            String sql ="insert into todo (title,isCompleted)"+" VALUES (?,?)";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(isDone, task);

            conn.close();


        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }
}
