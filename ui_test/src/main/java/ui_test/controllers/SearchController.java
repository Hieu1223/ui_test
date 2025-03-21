package ui_test.controllers;

import java.io.Console;
import java.util.LinkedList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

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
                   "\nvendors: " + vendors.toString();
        }
    }
    @FXML
    private Slider priceSlider;

    @FXML
    private Text priceSliderText;

    @FXML
    private TextField searchText;

    @FXML
    private SplitMenuButton sortByBox;

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
        for(MenuItem sortOption : sortByBox.getItems()){
            sortOption.setOnAction((e)->{
                sortByBox.setText(sortOption.getText());
            });
        }

    }

    @FXML
    void HandleSearch(ActionEvent event) {
        List<String> empty = new LinkedList<>();
        SearchData searchData = new SearchData(searchText.getText(), priceSlider.getValue()*5,sortByBox.getText(),vendorList,empty,empty);
        System.out.println(searchData.toString());
    }
    @FXML
    void handleSlider(MouseEvent event) {
        //double val = priceSlider.getValue();
        //priceSlider.setValue((int)(val * sliderLevels)/sliderLevels);
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
}
