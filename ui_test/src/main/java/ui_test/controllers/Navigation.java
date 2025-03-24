package ui_test.controllers;

import java.util.Stack;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Navigation {
    public static Parent mainPage;
    public static Parent productPage;
    public static Stack<Parent> stack = new Stack<>();
    public Parent pageMountPoint;
    public static Stage stage;
    public static Scene scene;
    public static PopStackButton productPageController;
}
