package dev.rk.listme.models;

public class Product {
    public int id;
    public int imgId;
    public int price;
    public String description;

    public Product(int id, int imgId, int price, String description) {
        this.id = id;
        this.imgId = imgId;
        this.price = price;
        this.description = description;
    }



}
