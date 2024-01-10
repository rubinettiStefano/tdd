package com.generation.entities;

//Fare come ho fatto io per notebook
//ora vi passo una lista di antivirus e una query per la creazione del db

//1) Creare interfaccia AntivirusRepository (stessi metodi di NotebookRepository)
//2) creare le sue implementazioni Mock e SQL 
//3)Prendere il test che vi mando tra 15 min e farlo funzionare
public class Antivirus 
{
    private int id;
    private String name;
    private int price;
    private int percentageSlow;

    

    public Antivirus(int id, String name, int price, int percentageSlow) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.percentageSlow = percentageSlow;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getPercentageSlow() {
        return percentageSlow;
    }
    public void setPercentageSlow(int percentageSlow) {
        this.percentageSlow = percentageSlow;
    }

    @Override
    public String toString() {
        return "Antivirus [id=" + id + ", name=" + name + ", price=" + price + ", percentageSlow=" + percentageSlow
                + "]";
    }

    
}
