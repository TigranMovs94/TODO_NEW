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

    @FXML
    public TextField userNameField;

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





    public String getCurrentLoggedInUser(String sessionToken) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String username = null;

        try {
            DBcon connect = new DBcon();
            conn = connect.connect("todo");
            String sql = "SELECT username FROM sessions WHERE token = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, sessionToken);
            rs = stmt.executeQuery();

            if (rs.next()) {
                username = rs.getString("username");
            }
        } catch (SQLException e) {
            // handle exception
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return username;
    }






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TodoController controller = new TodoController();
        try {
            greetingText.setText("Hello "+getCurrentLoggedInUser(controller.sessionToken));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
