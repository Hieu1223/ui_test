package ui_test.controllers;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ui_test.models.Product;

public class SearchController {

    

    public static final class SearchData{
        public SearchData(String name, double priceRangeEnd, String sortBy, List<String> vendors, List<String> types,
                List<String> screenSize) {
            this.name = name;
            this.priceRangeEnd = priceRangeEnd;
            this.sortBy = sortBy;
            this.vendors = vendors;
            this.types = types;
            this.screenSize = screenSize;
        }



        public String name;
        public double priceRangeEnd;
        public String sortBy;
        public List<String> vendors;
        public List<String> types;
        public List<String> screenSize;

        

        @Override
        public String toString() {
            return "name: " + name +
                   "\nvendors: " + vendors.toString()+
                   "\nsort by: " + sortBy + 
                   "\ntypes: " + types.toString()+
                   "\nscreen size: " + screenSize.toString();
        }
    }
    @FXML
    private Slider priceSlider;

    @FXML
    private Text priceSliderText;

    @FXML
    private TextField searchText;

    @FXML
    private ComboBox<?> sortByBox;
    
    @FXML
    private VBox productList;


    Stage chatRoom;

    final int sliderLevels = 10;


    String[] priceTextData = {
        "under 5mil",
        "under 10mil",
        "under 15mil",
        "under 20mil",
        "any price",
        "N/a"
    };

    List<String> vendorList = new LinkedList<>();
    List<String> typeList = new LinkedList<>();
    List<String> screenSizeList = new LinkedList<>();
    @FXML
    public void initialize() {
        priceSlider.valueProperty().addListener(new ChangeListener<Number>(){
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val){
                int sliderVal = new_val.intValue();
                priceSlider.setValue(sliderVal);
                if(sliderVal < 0 || sliderVal >5) {
                    sliderVal = 5;
                }
                priceSliderText.setText(priceTextData[sliderVal]);
                //System.out.println(priceSlider.getValue());
            }
        });
        ObservableList list =  FXCollections.observableArrayList();
        list.addAll("Relevance", "Lowest price", "Highest price");
        sortByBox.setItems(list);
        sortByBox.getSelectionModel().selectFirst();
        //sortByBox.setValue(list.get(0));
    }


    @FXML
void HandleSearch(ActionEvent event) {
    productList.getChildren().clear();
    try {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                List<Parent> list = new LinkedList<>();
                SearchData searchData = new SearchData(searchText.getText(), priceSlider.getValue() * 5, sortByBox.getValue().toString(), vendorList, typeList, screenSizeList);
                List<Product> products = SearchDatabase.getProduct(searchData);
                for (int i = 0; i< products.size(); i++) {
                    FXMLLoader loader = new FXMLLoader(SearchController.class.getResource("laptop-cell.fxml"));
                    Parent cell = loader.load();
                    CellController cellController = loader.getController();
                    cellController.setData(products.get(i));
                    list.add(cell);
                }
                Platform.runLater(() -> productList.getChildren().setAll(list));
                return null;
            }
        };
        new Thread(task).start();
    } catch (Exception e) {
        System.out.println(e);
    }
}

    @FXML
    void handleSlider(MouseEvent event) {
        //double val = priceSlider.getValue();
        //priceSlider.setValue((int)(val * sliderLevels)/sliderLevels);
    }
    
    @FXML
    void AddScreenSize(ActionEvent event) {
        CheckBox source = (CheckBox)event.getSource();
        String screenSizeName = source.getText();
        if(source.isSelected()){
            screenSizeList.add(screenSizeName);
        }
        else{
            if(screenSizeList.contains(screenSizeName))
                screenSizeList.remove(screenSizeName);
        }
    }

    @FXML
    void AddType(ActionEvent event) {
        CheckBox source = (CheckBox)event.getSource();
        String typeName = source.getText();
        if(source.isSelected()){
            typeList.add(typeName);
        }
        else{
            if(typeList.contains(typeName))
                typeList.remove(typeName);
        }
    }
    
    @FXML
    void AddVendor(ActionEvent event) {
        CheckBox source = (CheckBox)event.getSource();
        String vendorname = source.getText();
        if(source.isSelected()){
            vendorList.add(vendorname);
        }
        else{
            if(vendorList.contains(vendorname))
                vendorList.remove(vendorname);
        }
    }


    @FXML
    void openChat(MouseEvent event) throws IOException {
        if(chatRoom == null){
            chatRoom = new Stage();
            chatRoom.setTitle("Chat");
            try{
                Parent root = FXMLLoader.load(getClass().getResource("chat-room.fxml"));
                Scene scene = new Scene(root);
                chatRoom.setScene(scene);
            } catch(Exception e){
                System.err.println(e);
            }
            chatRoom.show();        }
        else{
            chatRoom.show();
            chatRoom.toFront();
        }
    }
}
