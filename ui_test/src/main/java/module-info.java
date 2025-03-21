module ui_test {
    requires javafx.controls;
    requires javafx.fxml;

    opens ui_test to javafx.fxml;
    opens ui_test.controllers to javafx.fxml;
    exports ui_test;
}
