package model.entities;

public class Product {
    private String name;
    private int quantity;
    private double price;



    public Product(String name, double price, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    private double total(){

        return quantity*price;
    }

    @Override
    public String toString(){
        return name + ";" + total();
    }

}
