package ui_test.controllers;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import ui_test.models.Product;

public class CellController {

    @FXML
    private ImageView productImage;

    @FXML
    private Text productTitle;

    @FXML
    private Text prpductPrice;

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
        
   } 
}
