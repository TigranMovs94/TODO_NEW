package com.example.todo_new;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.sql.DriverManager;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Todolist implements Initializable {


    private static Connection conn;
    @FXML
    public ListView<String> listView;


    @FXML
    public TextField todo;

    //Constructor
    public Todolist() {
        listView = new ListView<>();
    }


    public void onAddButton() {

        String insertedText = todo.getText();


        if (!insertedText.isBlank()) {
            try {
                DBcon connect = new DBcon();
                conn = connect.connect("todo");

                Statement st = conn.createStatement();
                String query = "INSERT INTO todo (title) values('" + insertedText + "')";
                st.executeUpdate(query);


                st.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("try something new ");
        }



        Platform.runLater(() -> {
            listView.getItems().add(refreshListView());
        });
    }


    public void deleteButton() {
        String todoForDeletion = listView.getSelectionModel().selectedItemProperty().getValue();
        System.out.println(todoForDeletion);
        if (todoForDeletion != null) {
            try {
                DBcon connect = new DBcon();
                conn = connect.connect("todo");

                Statement st = conn.createStatement();
                String query = "DELETE FROM todo WHERE title = '" + todoForDeletion + "'";
                st.executeUpdate(query);

                st.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }

        } else {
            System.out.println("please select something");
        }

        Platform.runLater(() -> {
            listView.getItems().add(refreshListView());
        });
        todo.clear();
    }






    public void switchAfterLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("afterLogin.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void display(){
        try{
            DBcon connect = new DBcon();
            conn = connect.connect("todo");

            Statement st = conn.createStatement();
            String query = "SELECT title FROM todo";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()){
                String title = rs.getString("title");
                listView.getItems().add(title);
            }
            rs.close();
            st.close();
            conn.close();
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        display();
    }


    private String refreshListView() {
        // Clear the previous list before adding new items
        listView.getItems().clear();


        // Query the database for new items and add them to the list
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo",
                "root1", "Canada2019$#");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT title FROM todo")) {

            while (rs.next()) {
                String name = rs.getString("title");
                // Add the new item to the list view
                Platform.runLater(() -> {
                    listView.getItems().add(name);
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String str = listView.getItems()
                .stream()
                .filter(s -> !s.isEmpty()) // filter out empty strings
                .collect(Collectors.joining());


        return str.trim();
    }
}
