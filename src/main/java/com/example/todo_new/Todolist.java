package com.example.todo_new;

import javafx.beans.Observable;
import javafx.beans.value.ObservableListValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.sql.DriverManager;
import java.util.ResourceBundle;

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


    public void onAddButton(){

        String insertedText = todo.getText();


        if(!insertedText.isBlank()){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo", "root1", "Canada2019$#");

                Statement st = conn.createStatement();
                String query = "INSERT INTO todo (title) values('"+insertedText+"')";
                st.executeUpdate(query);
                listView.refresh();

                st.close();
                conn.close();
            }
            catch (Exception e){
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
        else {
            System.out.println("try something new ");
        }
    }

    public void deleteButton(){
        String todoForDeletion = listView.getSelectionModel().selectedItemProperty().getValue();
        System.out.println(todoForDeletion);
        if(todoForDeletion!=null){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo", "root1", "Canada2019$#");

                Statement st = conn.createStatement();
                String query = "DELETE FROM todo WHERE title = '"+todoForDeletion+"'";
                st.executeUpdate(query);
                listView.refresh();

                st.close();
                conn.close();
            }
            catch (Exception e){
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }

        }
        else {
            System.out.println("please select something");
        }



    }




    public void switchAfterLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("afterLogin.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo", "root1", "Canada2019$#");

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
}
