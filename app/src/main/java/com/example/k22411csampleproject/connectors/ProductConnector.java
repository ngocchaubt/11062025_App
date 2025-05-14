package com.example.k22411csampleproject.connectors;

import com.example.k22411csampleproject.models.Product;
import com.example.k22411csampleproject.models.ListProduct;

import java.util.ArrayList;

public class ProductConnector {
    ListProduct listProduct;
    public ProductConnector(){
        listProduct=new ListProduct();
        listProduct.generate_sample_dataset_product();
    };
    public ArrayList<Product> get_all_products() {
        if (listProduct == null) {
            listProduct = new ListProduct();
            listProduct.generate_sample_dataset_product();
        }
        return listProduct.getProducts();
    }

    public ArrayList<Product> get_products_by_provider(String provider)
    {
        if(listProduct==null)
        {
            listProduct=new ListProduct();
            listProduct.generate_sample_dataset_product();
        }
        ArrayList<Product>results=new ArrayList<>();
        for(Product p: listProduct.getProducts())
        {
            if(p.getName().startsWith(provider))
            {
                results.add(p);
            }
        }
        return results;
    }
}
