package com.example.myapp1;

public class Vendor {
    Vegetable bittergourd, onion,potato;

    public Vendor() {
        this.bittergourd = new Vegetable();
        this.onion = new Vegetable();
        this.potato = new Vegetable();
    }
    public Vendor(Vegetable bittergourd, Vegetable onion, Vegetable potato) {
        this.bittergourd = bittergourd;
        this.onion = onion;
        this.potato = potato;
    }

    public Vegetable getBittergourd() {
        return bittergourd;
    }

    public void setBittergourd(Vegetable bittergourd) {
        this.bittergourd = bittergourd;
    }

    public Vegetable getOnion() {
        return onion;
    }

    public void setOnion(Vegetable onion) {
        this.onion = onion;
    }

    public Vegetable getPotato() {
        return potato;
    }

    public void setPotato(Vegetable potato) {
        this.potato = potato;
    }
}


class Vegetable{
    String weight,price;

    public Vegetable() {
        this.weight = "0";
        this.price = "0";
    }

    public Vegetable(String weight, String price) {
        this.weight = weight;
        this.price = price;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}