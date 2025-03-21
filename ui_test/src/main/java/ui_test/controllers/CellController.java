package ui_test.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import ui_test.models.Product;

public class CellController {
    @FXML
    private HBox cell;

    @FXML
    private ImageView productImage;

    @FXML
    private Text productTitle;

    @FXML
    private Text prpductPrice;


    Product productData;

    public void setData(Product data){
        productImage.setFitWidth(50);
        productImage.setFitHeight(50);
        productTitle.setText(data.getName());
        prpductPrice.setText("" + data.getPrice());
        System.out.println(data.getImageSource());
        Task<Void> fetchImage = new Task<Void>() {

            @Override
            protected Void call() throws Exception {
                Image image = new Image(data.getImageSource(),50,0,true,false);
                productImage.setImage(image);
                System.out.println(image.getException());     
                return null;
            }
        };
        productData = data;
    }  
    
    @FXML
    public void initialize(){
        cell.hoverProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                
            }
        });
        cell.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                System.out.println("Clicked");
            }
        });
    }
}
