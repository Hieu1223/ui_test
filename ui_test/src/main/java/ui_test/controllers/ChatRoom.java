package ui_test.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ChatRoom {

    @FXML
    private TextField chatBox;

    @FXML
    private VBox messageBox;

    @FXML
    void clickedSend(MouseEvent event) {
        messageBox.getChildren().add(new Text(chatBox.getText()));
        chatBox.clear();
    }

}
