package com.example.todo_new;
import java.io.IOException;
import java.sql.*;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.DriverManager;

public class TodoController {
    private static Connection conn;

    private Stage stage;
    private  Scene scene;
    private Parent root;
    @FXML
    private Label welcomeText;

    @FXML
    public TextField userNameField;
    @FXML
    public TextField passwordField;





    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    public int login(){
        try {

            String text = userNameField.getText();
            String pass = passwordField.getText();

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo", "root1", "Canada2019$#");
            Statement st = conn.createStatement();

            String sql = "SELECT * FROM users WHERE username='" + text + "' AND password='" + pass + "'";
            ResultSet rs = st.executeQuery(sql);




            if (rs.next()) {

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

            Parent root = FXMLLoader.load(getClass().getResource("afterLogin.fxml"));

            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else {

            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Fail!");
                alert.setHeaderText("Enter a valid password or username!");
                alert.showAndWait();

            });
        }




    }
    public void signUp(ActionEvent event) throws IOException {


            Parent root = FXMLLoader.load(getClass().getResource("signUp.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
}