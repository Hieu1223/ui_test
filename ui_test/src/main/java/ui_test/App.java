package ui_test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui_test.controllers.Navigation;

import java.io.IOException;
import java.util.Stack;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    @Override
    public void start(Stage stage) {
        System.setProperty("http.agent", "Chrome");
        try{
        Parent mainPage = loadFXML("primary");
        scene = new Scene(mainPage);
        Navigation.mainPage = mainPage;
        Navigation.productPage = loadFXML("product-page");
        Navigation.scene = scene;
        Navigation.stage = stage;
        } catch(Exception e){
            System.out.println(e);
        }
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}