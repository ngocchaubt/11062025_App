package com.example.k22411csampleproject.models;

import java.util.ArrayList;

public class ListCategory {
    private ArrayList<Category> categories;

    public ListCategory() {
        categories = new ArrayList<>();
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public void addCategories(Category cate){
        categories.add(cate);
    }

    public void generate_sample_dataset_category()

    {
        for (int i = 1; i <= 100; i++) {
            int id = i;
            String name = "Category" + i;
            Category cate = new Category(id, name);
            addCategories(cate);
        }
    }
}
