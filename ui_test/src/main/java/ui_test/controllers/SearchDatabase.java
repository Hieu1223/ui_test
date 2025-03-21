package ui_test.controllers;

import java.util.LinkedList;
import java.util.List;

import ui_test.models.Product;

public class SearchDatabase {
    static class Laptop extends Product{
    
            public Laptop(int id, String name, String description, String productImage, int price, String sourceURL) {
                super(id, name, description, productImage, price, sourceURL);
            }

    }
    public static List<Product> getProduct(SearchController.SearchData searchData){
        List<Product>data = new LinkedList<>();
        for(int i = 0; i< 10; i++)
            data.add(new Laptop(0, "Test name", "", "https://cdnv2.tgdd.vn/mwg-static/tgdd/Products/Images/44/333430/acer-nitro-v-15-anv15-41-r2up-r5-nhqpgsv004-638774737367845195-600x600.jpg", 120000,""));
        return data;
    }
}
