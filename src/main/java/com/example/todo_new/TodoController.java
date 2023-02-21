package com.example.todo_new;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;




public class TodoController implements Initializable {

    public static String username = "";

    private  Scene scene;
    private Parent root;
    @FXML
    public Button logBtn;

    @FXML
    public TextField userNameField;
    @FXML
    public TextField passwordField;

    @FXML
    public ImageView introImage;



    String sessionToken = " ";


    public int login(){

        try {


            String text = userNameField.getText();
            String pass = passwordField.getText();

            DBcon connect = new DBcon();
            Connection conn = connect.connect("todo");
            Statement st = conn.createStatement();

            String sql = "SELECT * FROM users WHERE username='" + text + "' AND password='" + pass + "'";
            ResultSet rs = st.executeQuery(sql);


            if (rs.next()) {
                AfterLogin.setUserName(userNameField.getText());

                conn.close();
                return 1;



            } else {
                System.out.println("Login failed. Invalid username or password.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



        return 0;
    }

    public void switchToScene2(ActionEvent event) throws IOException {

        if(login()==1){

            ButtonClickSound.setLogBtnClickingSound();
            Parent root = FXMLLoader.load(getClass().getResource("afterLogin.fxml"));
             username = userNameField.getText();
             sessionToken = SessionManager.generateSessionToken();
            // Save the session token to the database
             SessionManager.saveSessionToDatabase(userNameField.getText(), sessionToken);


            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();



        }
        else {

            Platform.runLater(() -> {
                ButtonClickSound.setLogBtnClickingSound();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Fail!");
                alert.setHeaderText("Enter a valid password or username!");
                alert.showAndWait();

            });
        }



    }
    public void signUp(ActionEvent event) throws IOException {

            ButtonClickSound.setLogBtnClickingSound();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("signUp.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }







    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

       setImage();
    }


    public void setImage(){

        File file = new File("C:\\Users\\Tigran\\IdeaProjects\\demo1\\TODO_NEW1\\src\\main\\java\\com\\example\\todo_new\\images.png");
        Image image = new Image(file.toURI().toString());
        introImage.setImage(image);
    }




    public static String getUsername(){
        return username;
    }

    public String getGeneratedToken(){
        return sessionToken;
    }

}