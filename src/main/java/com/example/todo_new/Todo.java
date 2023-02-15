package com.example.todo_new;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Todo extends Application {

    private ObservableList<String> tasks = FXCollections.observableArrayList();
    private int taskCounter = 1;

    @Override
    public void start(Stage primaryStage) throws Exception {
        ListView<String> taskListView = new ListView<>(tasks);


        Button addButton = new Button("Add");
        addButton.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add Task");
            dialog.setHeaderText("Enter task:");
            dialog.showAndWait().ifPresent(task -> {
                tasks.add(taskCounter + ". " + task);

                taskCounter++;
            });
        });

        Button removeButton = new Button("Remove");
        removeButton.setOnAction(event -> {
            String selectedTask = taskListView.getSelectionModel().getSelectedItem();
            if (selectedTask != null) {
                tasks.remove(selectedTask);
            }
        });

        Button editButton = new Button("Edit");
        editButton.setOnAction(event -> {
            String selectedTask = taskListView.getSelectionModel().getSelectedItem();
            if (selectedTask != null) {
                Dialog<String> editDialog = new Dialog<>();
                editDialog.setTitle("Edit Task");
                editDialog.setHeaderText("Enter updated task:");

                TextField taskTextField = new TextField();
                taskTextField.setText(selectedTask);

                ButtonType confirmButtonType = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
                ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                editDialog.getDialogPane().getButtonTypes().addAll(confirmButtonType, cancelButtonType);

                editDialog.getDialogPane().setContent(taskTextField);

                editDialog.setResultConverter(buttonType -> {
                    if (buttonType == confirmButtonType) {
                        return taskTextField.getText();
                    }
                    return null;
                });

                editDialog.showAndWait().ifPresent(updatedTask -> {
                    if (updatedTask != null) {
                        int index = tasks.indexOf(selectedTask);
                        tasks.set(index, updatedTask);
                    }
                });
            }
        });

        HBox buttonBox = new HBox(10, addButton, removeButton, editButton);

        VBox root = new VBox(10, taskListView, buttonBox);
        root.setPadding(new Insets(10));

        primaryStage.setScene(new Scene(root,300,500));
        primaryStage.setTitle("To-Do Program");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}
