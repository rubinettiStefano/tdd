package com.generation.entities;

public class Notebook 
{
    private int id;
    private String model;
    private int price;

    public Notebook(){}

    

    public Notebook(int id, String model, int price) {
        this.id = id;
        this.model = model;
        this.price = price;
    }



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }



    @Override
    public String toString() {
        return "Notebook [id=" + id + ", model=" + model + ", price=" + price + "]";
    }

    
}
