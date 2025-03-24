package ui_test.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class PopStackButton {

    @FXML
    void goBack(MouseEvent event) {
        Navigation.scene.setRoot(Navigation.stack.pop());
    }

}
