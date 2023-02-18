package com.example.todo_new;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AfterLogin implements Initializable {

   @FXML
   public Text greetingText;

 public static String userName = " ";

    public static void setUserName(String userName) {
        AfterLogin.userName = userName;
    }

    public void switchToScene1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchTodo(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Todolist.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        greetingText.setText("Hello, " + userName.toUpperCase()+"!");

    }
}
