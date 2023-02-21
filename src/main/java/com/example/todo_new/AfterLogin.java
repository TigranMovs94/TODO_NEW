package com.example.todo_new;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AfterLogin implements Initializable {

   @FXML
   public Text greetingText;

   @FXML
   public ImageView afterLoginImage;

 public static String userName = " ";

    public static void setUserName(String userName) {
        AfterLogin.userName = userName;
    }

    public void switchToScene1(ActionEvent event) throws IOException {
        ButtonClickSound.setLogBtnClickingSound();
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchTodo(ActionEvent event) throws IOException {
        ButtonClickSound.setLogBtnClickingSound();
        Parent root = FXMLLoader.load(getClass().getResource("Todolist.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        greetingText.setText("Hello, " + userName.toUpperCase()+"!");

        setAfterLoginImage();

    }


    public void setAfterLoginImage(){
        File file = new File("C:\\Users\\Tigran\\IdeaProjects\\demo1\\TODO_NEW1\\src\\main\\java\\com\\example\\todo_new\\images.png");
        Image image = new Image(file.toURI().toString());
        afterLoginImage.setImage(image);
    }
}
