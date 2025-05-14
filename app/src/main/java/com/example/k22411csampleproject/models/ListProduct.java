package com.example.k22411csampleproject.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class ListProduct implements Serializable{
    private ArrayList<Product> products;

    public ListProduct() {
            products = new ArrayList<>();
    }
    public ArrayList<Product> getProducts() {
            return products;
    }
    public void setProducts(ArrayList<Product> products) {
            this.products = products;
    }
    public void addProduct(Product p) {
        products.add(p);
    }
    public void generate_sample_dataset_product()
    {
        Random random = new Random();
        for (int j = 1; j <= 100; j++) {
            int id = j;
            String name = "Product " + j;
            int quantity = j;
            Double price = random.nextDouble();
            int cate_id = j;
            String description = "Description of product " + j;
            Product p = new Product(id,name,quantity,price,cate_id,description);
            addProduct(p);
        }
    }
}
