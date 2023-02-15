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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
public class Signup {

    private static Connection conn;

    @FXML
    private TextField userNameReg;
    @FXML
    private TextField passReg;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;



    public void register() {

        try{
            String userName = userNameReg.getText();
            String pass = passReg.getText();
            String fName = firstName.getText();
            String lName = lastName.getText();




            if(!userName.isBlank() && !pass.isBlank()){
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo", "root1", "Canada2019$#");
                Statement st = conn.createStatement();
                String sql = "INSERT INTO users (username, password, firstName, lastName) VALUES ('"+userName+"','"+pass+"','"+fName+"','"+lName+"')";
                st.executeUpdate(sql);
                userNameReg.clear();
                passReg.clear();
                firstName.clear();
                lastName.clear();
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success!");
                    alert.setHeaderText("You have successfully registered!");
                    alert.showAndWait();

                });
            }

                  else {
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Fail!");
                    alert.setHeaderText("Enter valid data!");
                    alert.showAndWait();

                });
            }

            conn.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public void switchToScene1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
